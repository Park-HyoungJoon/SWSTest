package kr.inhatc.workshop.company.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Company {
	
	//사업자등록번호
	@Id
	private Integer businessNum;
	
	//법인등록번호
	private String corporationNum;
	
	//대표자명
	private String businessName;
	
	//대표자주민번호
	private String rpn;
	
	//대표자외국인여부
	@Column(length=1)
	private String businessNationYN;
	

	private String postcode1;
	//사업장주소
	private String businessAddress;
	
	
	private String postcode2;
	
	
	//본점주소
	private String operationAddress;

	//업태
	private String businessSubject;
	
	//종목
	private String businessItem;
	
	
	//사업장전화번호
	private String businessPhoneNumber;
	
	//팩스번호
	private String paxNumber;
	
	//법인구분
	@Column(length=1)
	private String corporationYN;
	
	//법인종류구분
	private String corporationItem;

	//중소기업여부
	@Column(length=1)
	private String smallBusinessYN;

	//설립년월일
    @Column(columnDefinition = "timestamp")
	private LocalDate establishedYear;

    //개입년월일
    @Column(columnDefinition = "timestamp")
	private LocalDate interventionYear;
	
}
