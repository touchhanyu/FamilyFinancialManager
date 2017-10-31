package com.ffm.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ExcelUtil {
	private static final String XLS = "xls";
	private static final String XLSX = "xlsx";

	/**
	 * 根据给定路径解析Excel文件
	 * 
	 * @param filePath 文件路径
	 */
	public void readExcel(String filePath) {
		int index = filePath.lastIndexOf(".");
		String fileType = filePath.substring(index + 1, filePath.length());
		if (XLS.equalsIgnoreCase(fileType)) {// 解析Excel03
			HSSFWorkbook wb = readExcelXls(filePath);
			readHSSFWorkbook(wb);
		} else if (XLSX.equalsIgnoreCase(fileType)) {// 结息Excel07以上版本
			XSSFWorkbook wb = readExcelXlsx(filePath);
			readXSSFWorkbook(wb);
		}
	}

	/**
	 * 读取xlsx格式的Excel文件
	 * 
	 * @param filePath 文件路径
	 * @return
	 */
	private XSSFWorkbook readExcelXlsx(String filePath) {
		FileInputStream fis = null;
		XSSFWorkbook wb = null;
		try {
			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 释放资源
			try {
				if (wb != null)
					wb.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wb;
	}

	/**
	 * 
	 * @param wb
	 */
	private void readXSSFWorkbook(XSSFWorkbook wb) {
		if (wb == null)
			return;
		XSSFSheet sheet = wb.getSheetAt(0);
		int totalRow = sheet.getLastRowNum();
		for (int i = 0; i < totalRow; i++) {
			XSSFRow row = sheet.getRow(i);
			short totalCell = row.getLastCellNum();
			for (int j = 0; j < totalCell; j++) {
				XSSFCell cell = row.getCell(j);
				int cellType = cell.getCellType();
				switch (cellType) {
				case Cell.CELL_TYPE_NUMERIC:
					
					break;
				case Cell.CELL_TYPE_STRING:
					break;
				case Cell.CELL_TYPE_FORMULA:
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				}
			}
		}
	}

	/**
	 * 读取xls格式的Excel文件
	 * 
	 * @param filePath 文件路径
	 * @return
	 */
	private HSSFWorkbook readExcelXls(String filePath) {
		FileInputStream fis = null;
		HSSFWorkbook wb = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 释放资源
			try {
				if (wb != null)
					wb.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wb;
	}

	/**
	 * 
	 * @param wb
	 */
	private void readHSSFWorkbook(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		for (int i = 0; i < rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			short cellNum = row.getLastCellNum();
			for (int j = 0; j < cellNum; j++) {
				HSSFCell cell = row.getCell(j);
			}
		}
	}

	protected abstract void checkXSSFCell(int row, int col, XSSFCell cell);

	protected abstract void checkHSSFCell(int row, int col, HSSFCell cell);
}