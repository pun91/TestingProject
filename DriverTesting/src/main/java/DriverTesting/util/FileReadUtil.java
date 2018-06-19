package DriverTesting.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.log4testng.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import au.com.bytecode.opencsv.CSVReader;

//import com.google.common.collect.Table.Cell;

/**
 * This class contains the methods to read the data present in the Excel and the
 * CSV file
 * 
 */
public class FileReadUtil {
	private static final Logger LOGGER = Logger.getLogger(FileReadUtil.class);

	/**
	 * This method is to read the data in the excel file row by row and returns
	 * the data<blockquote> NOTE : While filling the data in excel please
	 * <b>avoid blank row </b> between the 2 successive data rows </blockquote>
	 * 
	 * @param filePath
	 *            This parameter provides the path where the file is stored
	 * @param sheetName
	 *            This parameter provides the name of the sheet for which data
	 *            to be read
	 * @return the data
	 * 
	 */
	public static Object[][] readingExcelFile(String filePath, String sheetName) {
		FileInputStream fileInput = null;
		Object[][] dataInExcel = null;
		try {
			fileInput = new FileInputStream(filePath);
			Workbook excelWorkbook = null;
			excelWorkbook = WorkbookFactory.create(fileInput);
			Sheet sheet = excelWorkbook.getSheet(sheetName);
			dataInExcel = new Object[sheet.getLastRowNum() + 1][];
			for (Row row : sheet) {
				Object[] rowData = null;
				if (row.getLastCellNum() > 0) {
					rowData = new Object[row.getLastCellNum()];
					for (Cell cell : row) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							rowData[cell.getColumnIndex()] = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							rowData[cell.getColumnIndex()] = cell.getNumericCellValue();
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							rowData[cell.getColumnIndex()] = cell.getBooleanCellValue();
							break;
						default:
							LOGGER.error("Wrong format Cell" + cell.getStringCellValue());
							rowData = null;
							break;
						}
						if (rowData == null) {
							break;
						}
					}
					if (rowData != null) {
						dataInExcel[row.getRowNum()] = rowData;
					}
				}
				if (rowData == null) {
					dataInExcel = Arrays.copyOf(dataInExcel, row.getRowNum());
					break;
				}
			}
		} catch (IOException | InvalidFormatException e) {
			LOGGER.error("Exception in Reading excel file", e);
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					LOGGER.error("Exception in Close File Stream", e);
				}
			}
		}
		return dataInExcel;
	}

	/**
	 * This method is to read a specific CSV file and returns the data available
	 * in the csv file
	 * 
	 * @param csvPath
	 *            This parameter provides the path of the CSV file
	 * @return the data
	 * 
	 */
	public static Object[][] CSVReader(String csvPath) {
		CSVReader csvReader = null;
		final String COMMENT_PREFIX = "--";
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(csvPath);
			csvReader = new CSVReader(fileReader);
			List<String[]> complete = csvReader.readAll();
			complete.remove(0);

			// Ignore commented out rows from csv
			Iterator<String[]> iter = complete.iterator();
			while (iter.hasNext()) {
				String[] row = iter.next();
				if (row[0].startsWith(COMMENT_PREFIX)) {
					iter.remove();
				}
			}

			Object[][] arrayResp = new Object[complete.size()][];
			for (int i = 0; i < complete.size(); i++) {
				arrayResp[i] = complete.get(i);
			}
			return arrayResp;
		} catch (FileNotFoundException e) {
			LOGGER.error("CSV file Not found Exception", e);
		} catch (IOException e) {
			LOGGER.error("Exception in reading file", e);
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					LOGGER.error("Exception in Close File Stream", e);
				}
			}
			if (csvReader != null) {
				try {
					csvReader.close();
				} catch (IOException e) {
					LOGGER.error("Exception in Close CSV Reader", e);
				}

			}
		}
		return new Object[][] {};
	}
}
