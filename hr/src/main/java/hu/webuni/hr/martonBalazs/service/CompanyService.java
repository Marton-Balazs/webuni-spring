package hu.webuni.hr.martonBalazs.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.martonBalazs.Repository.CompanyRepository;
import hu.webuni.hr.martonBalazs.model.Company;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	public Company save(Company company) {
		return companyRepository.save(company);
	}
	
	public Company update(Company company) {
		if (companyRepository.existsById(company.getId())) {
			return companyRepository.save(company);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}
	
	public void delete(long id) {
		companyRepository.deleteById(id);
	}
}
