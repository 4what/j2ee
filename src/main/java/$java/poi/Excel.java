package $java.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Excel {
	/**
	 * @param titles
	 * @return
	 */
	public static Workbook getWorkbook(String[] titles) {
		Workbook wb =
			new HSSFWorkbook() // 2003-
			//new XSSFWorkbook() // 2007+
		;

		// Note that sheet name is Excel must not exceed 31 characters
		// and must not contain any of the any of the following characters:
		// 0x0000
		// 0x0003
		// colon (:)
		// backslash (\)
		// asterisk (*)
		// question mark (?)
		// forward slash (/)
		// opening square bracket ([)
		// closing square bracket (])

		// You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
		// for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
		Sheet sheet = wb.createSheet(
			//WorkbookUtil.createSafeSheetName("")
		);
		sheet.createFreezePane(0, 1);
		sheet.setDefaultColumnWidth(20);

		// thead
		Row row = sheet.createRow(0);
		row.setHeightInPoints(20);

		Font font = wb.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());

		for (int i = 0, l = titles.length; i < l; i++) {
			Cell cell = row.createCell(i);

			cell.setCellValue(titles[i]);

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setBorderBottom( BorderStyle.MEDIUM);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
			cellStyle.setFillPattern( FillPatternType.SOLID_FOREGROUND);
			cellStyle.setFont(font);
			cellStyle.setVerticalAlignment( VerticalAlignment.CENTER);

			cell.setCellStyle(cellStyle);
		}

		return wb;
	}

	/**
	 * @param input
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static List<List> read(InputStream input) throws InvalidFormatException, IOException {
		try {
			List<List> result = new ArrayList<>();

/*
			InputStream input = new FileInputStream(
				"workbook.xls"
				//"workbook.xlsx"
			);
*/

			Workbook wb = WorkbookFactory.create(input);

			Sheet sheet = wb.getSheetAt(0);

			for (Row row : sheet) {
				List items = new ArrayList();

				for (Cell cell : row) {
					Object value = null;

					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							value = cell.getBooleanCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								value = cell.getDateCellValue();
							} else {
								value = cell.getNumericCellValue();
							}
							break;
						case Cell.CELL_TYPE_STRING:
							value = cell.getRichStringCellValue().getString();
							break;
						default:
							break;
					}

					items.add(value);
				}

				result.add(items);
			}

			return result;
		} finally {
			input.close();
		}
	}

	/**
	 * @param output
	 * @param wb
	 * @throws IOException
	 */
	public static void write(OutputStream output, Workbook wb) throws IOException {
		try {
			CreationHelper creationHelper = wb.getCreationHelper();

			CellStyle style_date = wb.createCellStyle();
			style_date.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

			Sheet sheet = wb.getSheetAt(0);

			// tbody
			for (int i = 0; i < 100; i++) {
				Row row = sheet.createRow(i + 1);
				row.setHeightInPoints(15);

				Cell cell = row.createCell(0);
				cell.setCellValue("value-" + i); // not null

				cell = row.createCell(1);
				cell.setCellValue(new Date());
				cell.setCellStyle(style_date);
			}

/*
			OutputStream output = new FileOutputStream(
				"workbook.xls"
				//"workbook.xlsx"
			);
*/

			wb.write(output);
		} finally {
			output.close();
		}
	}


	public static void main(String[] args) throws InvalidFormatException, IOException {
		String filename = "workbook.xls";

		// read
		//System.out.println(read(new FileInputStream(filename)));

		// write
		write(new FileOutputStream(filename), Excel.getWorkbook(new String[]{"ID", "DATE"}));
	}
}
