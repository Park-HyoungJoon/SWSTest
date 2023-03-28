package kr.inhatc.workshop.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.workshop.company.entity.Company;
import lombok.Value;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	@Query(value="select * from company Where business_num=?1",nativeQuery=true)
	Company findById(int id);
	
}
