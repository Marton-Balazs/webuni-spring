package hu.webuni.hr.martonBalazs.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.Repository.EmployeeRepository;
import hu.webuni.hr.martonBalazs.model.Employee;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {
	
	@Autowired
	EmployeeRepository employeeRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
		return new User(username, employee.getPassword(), employee.getRoles().stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));
		
	}



}
