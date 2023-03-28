package kr.inhatc.workshop.company.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;

import kr.inhatc.workshop.company.entity.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class CompanyDto {
	
	//사업자등록번호
	private Integer businessNum;
	
	//법인등록번호
	private String corporationNum;
	
	//대표자명
	private String businessName;
	
	//대표자주민번호
	private String rpn;
	private String rpn1;
	private String rpn2;
	
	//대표자외국인여부
	private String businessNationYN;
	
	private String postcode1;
	private String postcode2;
	
	
	//사업장주소
	private String businessAddress;
	private String address1;
	private String detailAddress1;
	
	//본점주소
	private String operationAddress;
	private String address2;
	private String detailAddress2;
	
	//업태
	private String businessSubject;
	
	//종목
	private String businessItem;
	
	//사업장전화번호
	private String businessPhoneNumber;
	private String businessPhoneNum1;
	private String businessPhoneNum2;
	private String businessPhoneNum3;
	
	//팩스번호
	private String paxNumber;
	private String paxNumber1;
	private String paxNumber2;
	private String paxNumber3;
	
	//법인구분
	private String corporationYN;
	
	//법인종류구분
	private String corporationItem;

	//중소기업여부
	private String smallBusinessYN;

	//설립년월일
	private LocalDate establishedYear;
	private String establishedYear1;

    //개입년월일
	private LocalDate interventionYear;
	private String interventionYear1;
	
	public CompanyDto DtoToDto(CompanyDto companyDto) {
		if(companyDto.getPaxNumber()!="") {
			try {
			String[] paxNum = companyDto.getPaxNumber().split("-");
			companyDto.setPaxNumber1(paxNum[0]);
			companyDto.setPaxNumber2(paxNum[1]);
			companyDto.setPaxNumber3(paxNum[2]);}
			catch (Exception e) {
				// TODO: handle exception
			}{
				
			}
		}
		if(companyDto.getBusinessPhoneNumber()!="") {
			try {
			String[] BusinessNum = companyDto.getBusinessPhoneNumber().split("-");
			companyDto.setBusinessPhoneNum1(BusinessNum[0]);
			companyDto.setBusinessPhoneNum2(BusinessNum[1]);
			companyDto.setBusinessPhoneNum3(BusinessNum[2]);}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(companyDto.getBusinessAddress()!="") {
			try {
			String[] addr = companyDto.getBusinessAddress().split("^");
			companyDto.setAddress1(addr[0]);
			companyDto.setDetailAddress1(addr[1]);}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(companyDto.getOperationAddress()!="") {
			try {
			String[] addr = companyDto.getOperationAddress().split("^");
			companyDto.setAddress2(addr[0]);
			companyDto.setDetailAddress2(addr[1]);}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(companyDto.getEstablishedYear1()!=null) {
			companyDto.setEstablishedYear(LocalDate.parse(establishedYear1,DateTimeFormatter.ISO_LOCAL_DATE));
		}
		if(companyDto.getInterventionYear1()!=null) {
			companyDto.setEstablishedYear(LocalDate.parse(interventionYear1,DateTimeFormatter.ISO_LOCAL_DATE));	
		}
		if(companyDto.getRpn()!="") {
			try {
			String[] rpn = companyDto.getRpn().split("-");
			companyDto.setRpn1(rpn[0]);
			companyDto.setRpn2(rpn[1]);}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		return companyDto;
	}
	
	
	public CompanyDto combineDto(CompanyDto companyDto) {
		if(companyDto.getPaxNumber1()!="" && companyDto.getPaxNumber2() !="" && companyDto.getPaxNumber3()!="") {
			String paxNum = companyDto.getPaxNumber1() +"-"+ companyDto.getPaxNumber2() +"-"+ companyDto.getPaxNumber3();
			companyDto.setPaxNumber(paxNum);
		}
		if(companyDto.getBusinessPhoneNum1()!="" && companyDto.getBusinessPhoneNum2() !="" && companyDto.getBusinessPhoneNum3()!="") {
			String BusinessNum = companyDto.getBusinessPhoneNum1() +"-"+ companyDto.getBusinessPhoneNum2() +"-"+ companyDto.getBusinessPhoneNum3();
			companyDto.setBusinessPhoneNumber(BusinessNum);
		}
		if(companyDto.getAddress1()!="" && companyDto.getDetailAddress1()!="") {
			String addr = companyDto.getAddress1() +"^" +  companyDto.getDetailAddress1();
			companyDto.setBusinessAddress(addr);
		}
		if(companyDto.getAddress2()!="" && companyDto.getDetailAddress2()!="") {
			String addr = companyDto.getAddress2() +"^" +  companyDto.getDetailAddress2();
			companyDto.setOperationAddress(addr);
		}
		if(companyDto.getEstablishedYear1()!=null) {
			companyDto.setEstablishedYear(LocalDate.parse(establishedYear1,DateTimeFormatter.ISO_LOCAL_DATE));
		}
		if(companyDto.getInterventionYear1()!=null) {
			companyDto.setEstablishedYear(LocalDate.parse(interventionYear1,DateTimeFormatter.ISO_LOCAL_DATE));	
		}
		if(companyDto.getRpn1()!="" && companyDto.getRpn2()!="") {
			String rpn = companyDto.getRpn1() + "-" + companyDto.getRpn2();
			companyDto.setRpn(rpn);
		}
		return companyDto;
	}
	
	private ModelMapper modelmapper = new ModelMapper();
	
	public Company dtoToCompany() {return modelmapper.map(this,Company.class);}
	
	public CompanyDto CompanyToDto(Company company) {return modelmapper.map(company, CompanyDto.class);}
		
}
