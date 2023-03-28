package com.example.demo.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import kr.inhatc.workshop.SmartWorkShopApplication;
import kr.inhatc.workshop.company.dto.CompanyDto;
import kr.inhatc.workshop.company.entity.Company;
import kr.inhatc.workshop.company.repository.CompanyRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = SmartWorkShopApplication.class)
public class CompanyRepositoryTest{
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Test
	@DisplayName("값 추가 테스트")
	public void insert() {
		CompanyDto companyDto = new CompanyDto();
		Company company = new Company();
		for(int i=1; i<100; i++) {
			company.setBusinessNum(i);
			company.setBusinessName("박형준");
			company.setBusinessAddress("인천광역시 남동구 청능대로 111길 1 1동 1호");
			company.setOperationAddress("인하공업 전문대학 7호관");
			company.setBusinessItem("스프링부트");
			company.setBusinessNationYN("Y");
			company.setBusinessPhoneNumber("010-9181-5838");
			company.setBusinessSubject("제작");
			company.setCorporationItem("기업아이템");
			company.setCorporationNum("032-870-1234");
			company.setSmallBusinessYN("Y");
			company.setRpn("980605-1155112");
			company.setCorporationYN("N");
			company.setEstablishedYear(LocalDate.parse("2022-09-23",DateTimeFormatter.ISO_LOCAL_DATE));
			company.setInterventionYear(LocalDate.parse("2022-12-23",DateTimeFormatter.ISO_LOCAL_DATE));
			companyRepository.save(company);
		}
	}
}
