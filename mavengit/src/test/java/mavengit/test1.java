package mavengit;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class test1 {

	WebDriver driver;
	String Url="https://www.walmart.com/";	
	int myRows, myCols;	
	String inputData;
	String Email;
	String passwd;
		@BeforeTest	
		public void setup() throws Exception{		
			driver=new FirefoxDriver();		
		}
		@Test	
		public void runall() throws Exception{		
			String SheetPath="C:\\Users\\KingKongPawn\\Documents\\Walmart.xls";	
			String SheetName="Sheet1-Wal";	
			String[][] myXLSheet = readXLSheet(SheetPath, "Sheet1-Wal");		
			//this is the for loop for navigating excel file	
			for(int k=0; k<myRows; k++){		
				inputData=myXLSheet[k][0];
				Email=myXLSheet[k][1];
				passwd=myXLSheet[k][2];
			driver.get(Url);	
			driver.manage().window().maximize();	
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
			driver.findElement(By.id("global-search-input")).sendKeys(inputData);	
	driver.findElement(By.xpath("//button[@class='header-GlobalSearch-submit btn']")).click();	
				
			driver.navigate().back();		
				
			driver.findElement(By.linkText("Hello. Sign In")).click();		
				
			WebDriverWait Wait= new WebDriverWait (driver, 20);
			Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
			driver.findElement(By.name("email")).sendKeys(Email);
			
			driver.findElement(By.name("password")).sendKeys(passwd);
			
           driver.findElement(By.xpath("//button[@class='btn btn-block']")).click();	
			
		    String ActualTitle=driver.getTitle();	
			System.out.println("the test passed"+ ActualTitle);	
System.out.println("i completed the round i am going to the next round"+ driver.getCurrentUrl());	
		   }
		   }
		   //method/function for reading data from excel sheet	
		   public String[][] readXLSheet(String SheetPath, String SheetName) throws Exception{	
			String[][] xData;
			File myXLSheet= new File(SheetPath);	
			FileInputStream myStream=new FileInputStream(myXLSheet);	
			HSSFWorkbook myWB=new HSSFWorkbook(myStream);	
			HSSFSheet mySheet=myWB.getSheet(SheetName);	
			myRows= mySheet.getLastRowNum()+1;	
			myCols = mySheet.getRow(0).getLastCellNum();	
			xData= new String[myRows][myCols];	
			for(int i=0; i<myRows; i++){			
				HSSFRow row=mySheet.getRow(i);		
				for(int j=0; j<myCols; j++){			
					HSSFCell cell=row.getCell(j);			
					String value=cellToString(cell);		
					xData[i][j]=value;
				}
			}
			myWB.close();
			return xData;	
		}
		//method/function to change cell type
		public static String cellToString(HSSFCell cell){	
			int type=cell.getCellType();
			Object result;
			switch(type){
			case HSSFCell.CELL_TYPE_NUMERIC://0
				result=cell.getNumericCellValue();
				break;
	         case HSSFCell.CELL_TYPE_STRING://1	
				result=cell.getStringCellValue();
				break;
	         case HSSFCell.CELL_TYPE_FORMULA://2
				throw new RuntimeException("we can't evaluate formula in java");
	         case HSSFCell.CELL_TYPE_BLANK://3
				result="";
				break;
	       case HSSFCell.CELL_TYPE_BOOLEAN://4
				result=cell.getBooleanCellValue();
				break;
	        case HSSFCell.CELL_TYPE_ERROR://5
				throw new RuntimeException("this cell has an error");
				default:	
			throw new RuntimeException("we don't support this cell:"+ type);
			}
			return result.toString();
		}
		@AfterTest
		public void CloseTime() throws Exception{
			driver.close();
		}
	}