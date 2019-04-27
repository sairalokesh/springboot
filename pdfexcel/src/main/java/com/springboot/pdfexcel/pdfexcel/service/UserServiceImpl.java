package com.springboot.pdfexcel.pdfexcel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.pdfexcel.pdfexcel.model.User;
import com.springboot.pdfexcel.pdfexcel.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public boolean createPdf(List<User> users, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			
			String filePath=context.getRealPath("/resources/reports");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if(!exists){
				new File(filePath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"users"+".pdf"));
			document.open();

			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Paragraph p = new Paragraph("All Users", mainFont);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setIndentationLeft(50);
			p.setIndentationRight(50);
			p.setSpacingAfter(10);
			document.add(p);
			
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);
			
			Font tablehearder = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tablebody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			
			float[] columnWidths = {2f, 2f, 2f, 2f};
			table.setWidths(columnWidths);
			
			PdfPCell cell1 = new PdfPCell(new Paragraph("First Name", tablehearder));
			cell1.setBorderColor(BaseColor.BLACK);
			cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell1.setExtraParagraphSpace(5f);
			table.addCell(cell1);
			
			PdfPCell cell2 = new PdfPCell(new Paragraph("Last Name", tablehearder));
			cell2.setBorderColor(BaseColor.BLACK);
			cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_CENTER);
			cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell2.setExtraParagraphSpace(5f);
			table.addCell(cell2);
			
			PdfPCell cell3 = new PdfPCell(new Paragraph("Email", tablehearder));
			cell3.setBorderColor(BaseColor.BLACK);
			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_CENTER);
			cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell3.setExtraParagraphSpace(5f);
			table.addCell(cell3);
			
			PdfPCell cell4 = new PdfPCell(new Paragraph("Phone Number", tablehearder));
			cell4.setBorderColor(BaseColor.BLACK);
			cell4.setPaddingLeft(10);
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setVerticalAlignment(Element.ALIGN_CENTER);
			cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell4.setExtraParagraphSpace(5f);
			table.addCell(cell4);
			
			for(User user: users) {
				PdfPCell cell5 = new PdfPCell(new Paragraph(user.getFirstName(), tablebody));
				cell5.setBorderColor(BaseColor.BLACK);
				cell5.setPaddingLeft(10);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setVerticalAlignment(Element.ALIGN_CENTER);
				cell5.setBackgroundColor(BaseColor.WHITE);
				cell5.setExtraParagraphSpace(5f);
				table.addCell(cell5);
				
				PdfPCell cell6 = new PdfPCell(new Paragraph(user.getLastName(), tablebody));
				cell6.setBorderColor(BaseColor.BLACK);
				cell6.setPaddingLeft(10);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setVerticalAlignment(Element.ALIGN_CENTER);
				cell6.setBackgroundColor(BaseColor.WHITE);
				cell6.setExtraParagraphSpace(5f);
				table.addCell(cell6);
				
				PdfPCell cell7 = new PdfPCell(new Paragraph(user.getEmail(), tablebody));
				cell7.setBorderColor(BaseColor.BLACK);
				cell7.setPaddingLeft(10);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell7.setVerticalAlignment(Element.ALIGN_CENTER);
				cell7.setBackgroundColor(BaseColor.WHITE);
				cell7.setExtraParagraphSpace(5f);
				table.addCell(cell7);
				
				PdfPCell cell8 = new PdfPCell(new Paragraph(user.getPhoneNumber(), tablebody));
				cell8.setBorderColor(BaseColor.BLACK);
				cell8.setPaddingLeft(10);
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell8.setVerticalAlignment(Element.ALIGN_CENTER);
				cell8.setBackgroundColor(BaseColor.WHITE);
				cell8.setExtraParagraphSpace(5f);
				table.addCell(cell8);
			}
			document.add(table);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean createExcel(List<User> users, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		String filePath=context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists){
			new File(filePath).mkdirs();
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file+"/"+"users"+".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Users");
			worksheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFRow headerrow = worksheet.createRow(0);
			
			HSSFCell cell1 = headerrow.createCell(0);
			cell1.setCellValue("First Name");
			cell1.setCellStyle(headerCellStyle);
			
			HSSFCell cell2 = headerrow.createCell(1);
			cell2.setCellValue("Last Name");
			cell2.setCellStyle(headerCellStyle);
			
			HSSFCell cell3 = headerrow.createCell(2);
			cell3.setCellValue("Email");
			cell3.setCellStyle(headerCellStyle);
			
			HSSFCell cell4 = headerrow.createCell(3);
			cell4.setCellValue("Phone Number");
			cell4.setCellStyle(headerCellStyle);
			
			int i = 1;
			for(User user: users) {
				HSSFRow bodyrow = worksheet.createRow(i);
				
				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
				
				HSSFCell firstName = bodyrow.createCell(0);
				firstName.setCellValue(user.getFirstName());
				firstName.setCellStyle(bodyCellStyle);
				
				HSSFCell lastName = bodyrow.createCell(1);
				lastName.setCellValue(user.getLastName());
				lastName.setCellStyle(bodyCellStyle);
				
				HSSFCell email = bodyrow.createCell(2);
				email.setCellValue(user.getEmail());
				email.setCellStyle(bodyCellStyle);
				
				HSSFCell phoneNumber = bodyrow.createCell(3);
				phoneNumber.setCellValue(user.getPhoneNumber());
				phoneNumber.setCellStyle(bodyCellStyle);
				i++;
			}
			workbook.write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
