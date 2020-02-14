package GuruBank99Automation;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils { 
	public static final String key = "webdriver.gecko.driver";
	public static final String value = "./driver/geckodriver.exe";
	public static final long timeout = 30;
	public static final String baseUrl = "http://www.demo.guru99.com/V4/";

	public static final String FILE_PATH = "C:/adesktop/PADMA/Guru99BankApplication Automation/Test.xlsx"; // File Path

	// Expected output
	public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	public static final String EXPECT_ERROR = "User or Password is not valid";

	public static String[][] getDataFromExcel(String xlFilePath) throws IOException {
		
			FileInputStream inputStream = new FileInputStream(xlFilePath);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet mySheet = workbook.getSheetAt(0);

			int numRows = mySheet.getLastRowNum()+1;
			int numCols = mySheet.getRow(0).getLastCellNum();
			String[][] excelData = new String[numRows][numCols];

			for (int i=0; i<numRows; i++) {
				XSSFRow row = mySheet.getRow(i);
				for (int j=0; j<numCols; j++) {
					XSSFCell cell = row.getCell(j);
					String value = String.valueOf(cell);
					excelData[i][j] = value;
				}   
			    }		
		return (excelData);
	}
}
