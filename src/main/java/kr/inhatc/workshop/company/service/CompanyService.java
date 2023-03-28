package kr.inhatc.workshop.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.workshop.company.dto.CompanyDto;
import kr.inhatc.workshop.company.entity.Company;
import kr.inhatc.workshop.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CompanyService {
	private final CompanyRepository companyRepository;
	
	public Company addCompany(CompanyDto companyDto) {
		Company company = companyDto.dtoToCompany();
		companyRepository.save(company);
		return company;
	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company getEmployeeById(int id) {
		return companyRepository.findById(id);
	}

	public void deleteCompany(int id) {
		Company company = getEmployeeById(id);
		companyRepository.delete(company);
		
	}

}
