package com.mt.employeesystem.files.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mt.employeesystem.model.Employee;

public class GenerateExcelFile {
	static String[] HEADER = { "Id", "FirstName", "LastName", "Age", "Department" };
	static String SHEET = "Employees";

	public static ByteArrayInputStream employeesToExcel(List<Employee> employees) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADER.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADER[col]);
			}

			int rowId = 1;
			for (Employee employee : employees) {
				Row row = sheet.createRow(rowId++);

				row.createCell(0).setCellValue(employee.getId());
				row.createCell(1).setCellValue(employee.getFirstName());
				row.createCell(2).setCellValue(employee.getLastName());
				row.createCell(3).setCellValue(employee.getAge());
				row.createCell(4).setCellValue(employee.getDepartment());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
}
