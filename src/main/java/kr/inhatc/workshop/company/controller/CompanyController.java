package kr.inhatc.workshop.company.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.inhatc.workshop.company.dto.CompanyDto;
import kr.inhatc.workshop.company.dto.ExcelDto;
import kr.inhatc.workshop.company.entity.Company;
import kr.inhatc.workshop.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CompanyController {
	@Autowired
	CompanyService companyService;
	
	
    @GetMapping("/")
    public String login(Model model) {
    	List<Company> companies = companyService.getAllCompanies();
    	model.addAttribute("tableList",companies);
    	CompanyDto companyDto = new CompanyDto();
    	try {
    		CompanyDto cd = new CompanyDto();
    	model.addAttribute("compan",cd);}
    	catch (Exception e) {
    		CompanyDto cd = new CompanyDto();
        	model.addAttribute("compan",cd);
		}
        return "main";  
    }
    
    
    @PostMapping("/company/uploader")
    public String CompanyUploader(@Valid CompanyDto companyDto,Model model) throws NullPointerException,IOException{
    	companyDto = companyDto.combineDto(companyDto);
    	Company company = companyService.addCompany(companyDto);
    	List<Company> companies = companyService.getAllCompanies(); 
    	companyDto = companyDto.DtoToDto(companyDto);
    	model.addAttribute("compan",companyDto);
    	model.addAttribute("tableList",companies);
    	return "main";
    }

    @GetMapping("/company/{id}")
    public String CompanyUploaderById(@PathVariable("id") String divideId, @Valid CompanyDto companyDto,Model model) throws NullPointerException,IOException{
    	companyDto = companyDto.combineDto(companyDto);
    	List<Company> companies = companyService.getAllCompanies();
    	
    	Company company2 = companyService.getEmployeeById(Integer.parseInt(divideId));
    	CompanyDto companyDto2 = companyDto.CompanyToDto(company2);
    	companyDto2 = companyDto2.DtoToDto(companyDto2);
    	model.addAttribute("compan",companyDto2);
    	model.addAttribute("tableList",companies);
    	return "main";
    }
    @GetMapping("/company/delete/{id}")
    public String CompanyDeleteById(@PathVariable("id") String divideId, @Valid CompanyDto companyDto,Model model) throws NullPointerException,IOException{
    	companyDto = companyDto.combineDto(companyDto);
    	companyService.deleteCompany(Integer.parseInt(divideId));
    	
    	List<Company> companies = companyService.getAllCompanies();
    	model.addAttribute("tableList",companies);
		CompanyDto cd = new CompanyDto();  
    	model.addAttribute("compan",cd);
    	return "main";
    }
    @GetMapping("/excel/download")
    public void excelDown(HttpServletResponse response,ExcelDto excelDto) throws IOException{
    	Company company = companyService.getEmployeeById(excelDto.getId());
        Workbook wb = new XSSFWorkbook();
    	//Workbook wb = new XSSFWorkbook();
    	Sheet sheet = wb.createSheet("새로운시트1");
        //숫자 포맷은 아래 numberCellStyle을 적용시킬 것이다다
        CellStyle numberCellStyle = wb.createCellStyle();
        numberCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
    	Row row = null;
    	Cell cell = null;
    	int rowNum = 0;
    	
    	//Header
    	row = sheet.createRow(rowNum++);
    	cell = row.createCell(0);
    	cell.setCellValue("사업자등록번호");
    	cell = row.createCell(1);
    	cell.setCellValue("법인등록번호");
    	cell = row.createCell(2);
    	cell.setCellValue("대표자명");
    	cell = row.createCell(3);
    	cell.setCellValue("대표자주민번호");
    	cell = row.createCell(4);
    	cell.setCellValue("대표자외국인여부");
    	cell = row.createCell(5);
    	cell.setCellValue("사업장주소");
    	cell = row.createCell(6);
    	cell.setCellValue("본점주소");
    	cell = row.createCell(7);
    	cell.setCellValue("업태");
    	cell = row.createCell(8);
    	cell.setCellValue("종목");
    	cell = row.createCell(9);
    	cell.setCellValue("사업장전화번호");
    	cell = row.createCell(10);
    	cell.setCellValue("팩스번호");
    	cell = row.createCell(11);
    	cell.setCellValue("법인구분");
    	cell = row.createCell(12);
    	cell.setCellValue("법인종류구분");
    	cell = row.createCell(13);
    	cell.setCellValue("중소기업여부");
    	cell = row.createCell(14);
    	cell.setCellValue("설립년월일");
    	cell = row.createCell(15);
    	cell.setCellValue("개입년월일");
    	
    		row = sheet.createRow(rowNum++);
    		cell = row.createCell(0);
    		cell.setCellValue(company.getBusinessNum());
    		cell = row.createCell(1);
    		cell.setCellValue(company.getCorporationNum());
    		cell = row.createCell(2);
    		cell.setCellValue(company.getBusinessName());
    		cell = row.createCell(3);
    		cell.setCellValue(company.getRpn());
    		cell = row.createCell(4);
    		cell.setCellValue(company.getBusinessNationYN());
    		cell = row.createCell(5);
    		cell.setCellValue(company.getBusinessAddress());
    		cell = row.createCell(6);
    		cell.setCellValue(company.getOperationAddress());
    		cell = row.createCell(7);
    		cell.setCellValue(company.getBusinessSubject());
    		cell = row.createCell(8);
    		cell.setCellValue(company.getBusinessItem());
    		cell = row.createCell(9);
    		cell.setCellValue(company.getBusinessPhoneNumber());
    		cell = row.createCell(10);
    		cell.setCellValue(company.getPaxNumber());
    		cell = row.createCell(11);
    		cell.setCellValue(company.getCorporationYN());
    		cell = row.createCell(12);
    		cell.setCellValue(company.getCorporationItem());
    		cell = row.createCell(13);
    		cell.setCellValue(company.getSmallBusinessYN());
    		cell = row.createCell(14);
    		cell.setCellValue(company.getEstablishedYear());
    		cell = row.createCell(15);
    		cell.setCellValue(company.getInterventionYear());
    		
    		  // 컨텐츠 타입과 파일명 지정
            String fileName = "spring_excel_download";

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
           // response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

            ServletOutputStream servletOutputStream = response.getOutputStream();
            
            // Excel File Output
            wb.write(servletOutputStream);
            wb.close();
            servletOutputStream.flush();
            servletOutputStream.close();
    }
    
}
