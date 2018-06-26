package com.xmhx.cnlife.buzz.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.csvreader.CsvReader;
import com.xmhx.cnlife.buzz.exception.ResponseException;

/**
 * Excel辅助类
 * @author ex_kjkfb_wujin
 *
 */
public class ExcelUtils {
	
	/**
	 * CSV读
	 * @param absolutePath
	 * @param destineHeaders 各列名用逗号隔开
	 * 			例："code,date,open,high,low,close"
	 * @return
	 * @throws IOException 
	 */
	public static List<String[]> analysisCsv(String absolutePath, String destineHeaders) throws IOException {
		List<String[]> csvlist = new ArrayList<String[]>(); 							// 用来保存数据
		CsvReader reader = new CsvReader(absolutePath, ',', Charset.forName("GBK")); 	// 一般用这编码读就可以了
		// 跳过表头 如果需要表头的话，不要写这句
		if (reader.readHeaders()) {
			String[] headerseq = TextUtils.notEmpty(destineHeaders) ? destineHeaders.split("\\,") : null;
			// 读取列头
			String[] headers = reader.getHeaders();
			// 判断列头定义是否按标准顺序
			for (int i = 0, n = headers.length; headerseq != null && i < n; i++) {
				if (!headers[i].equals(headerseq[i])) {
					throw new ResponseException("导入中的CSV文件字段顺序异常【" + destineHeaders.toString() + "】");
				}
			}
		}
		// 逐行读入除表头外的数据
		while (reader.readRecord()) {
			csvlist.add(reader.getValues());
		}
		reader.close();
		return csvlist;
	}
	
	/**
	 * 获取Excel单元格值
	 * @param cell
	 * @return
	 */
	public static String getText(HSSFCell cell) {
		if (cell == null || HSSFCell.CELL_TYPE_ERROR == cell.getCellType() ||
				HSSFCell.CELL_TYPE_BLANK == cell.getCellType()) {
			return "";
		} else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
			return cell.getRichStringCellValue().getString();
		} else if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
			DecimalFormat fmt = new DecimalFormat("#0.##");
			return fmt.format(cell.getNumericCellValue());
		} else if (HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (HSSFDateUtil.isCellDateFormatted(cell)) {
			Date date = cell.getDateCellValue();
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		} else if (HSSFCell.CELL_TYPE_FORMULA == cell.getCellType()) {
			// 导入时如果为公式生成的数据则无值
			if (!cell.getStringCellValue().equals("")) {
				return cell.getStringCellValue();
			} else {
				return cell.getNumericCellValue() + "";
			}
		} 		
		return "";
	}
	
	/**
	 * .xlsx格式获取页签
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static Workbook getXlsxWorkbook(String filePath) throws IOException{ 
		Workbook book = null;
		FileInputStream fis = null; 
		try { 
			File file = new File(filePath); 
			if(!file.exists()) { 
				throw new RuntimeException("文件不存在"); 
			} else { 
				fis = new FileInputStream(file); 
				book = WorkbookFactory.create(fis); 
			} 
		} catch (Exception e) { 
			throw new RuntimeException(e.getMessage()); 
		} finally { 
			if (fis != null) {
				fis.close(); 
			} 
		} 
		return book; 
	}
	
	public static Sheet getSheetByNum(Workbook book, int number){ 
		Sheet sheet = null; 
		try { 
			sheet = book.getSheetAt(number); 
		} catch (Exception e) { 
			throw new RuntimeException(e.getMessage()); 
		} 
		return sheet; 
	} 
	
	/**
	 * 设置单元格样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle getStyleContent(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(getFontContent(workbook));
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); 			// 左右居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	// 上下居中
		// 设置单元格的文本方式为可多行编写方式
		cellStyle.setWrapText(true);
		// 单元格边框,样式,颜色
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			// 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				// 左边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);			// 右边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				// 上边框
		return cellStyle;
	}
	
	/**
	 * 设置字体
	 * @return
	 */
	public static HSSFFont getFontContent(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 9); 		// 字体高度
		font.setColor(HSSFFont.BOLDWEIGHT_BOLD); 	// 字体颜色
		font.setFontName("宋体"); 					// 字体
		return font;
	}
	
}
