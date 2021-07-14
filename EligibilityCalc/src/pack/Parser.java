package pack;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Parser {
	
	private File[] fileList;
	private ArrayList<XSSFWorkbook> workbookList = new ArrayList<XSSFWorkbook>();
	private XSSFSheet dateConversion;
	private XSSFSheet plancodeSheet;
	private XSSFSheet dataSheet;
	
	
	public Parser(File[] theList) {
		fileList = theList;

		//load as workable XLSXs
		try {
			for (File file:fileList) {
				FileInputStream fis = new FileInputStream(file); 
				workbookList.add(new XSSFWorkbook(fis));
			}
		}
		catch(Exception e) {
			System.out.println("[-] XLSX Initialization Unsuccessfully.");
			e.printStackTrace();
			System.exit(0);
		}
		
		dateConversion = workbookList.get(0).getSheetAt(0); 
		plancodeSheet = workbookList.get(1).getSheetAt(0);
		dataSheet = workbookList.get(2).getSheetAt(0);
		
		System.out.println("[+] XLSX Initialization Successful.");
		
		//end constructor
		}
	
	public ArrayList<RowPack> initalize() {
		ArrayList<RowPack> myList = new ArrayList<RowPack>();
		for (Row theRow: dataSheet) {
			
			Agent agent1 = new Agent((int)theRow.getCell(31).getNumericCellValue(),
									theRow.getCell(32).getNumericCellValue()/100,
									this.getAgentDate((int)theRow.getCell(31).getNumericCellValue()));
			Agent agent2 = new Agent((int)theRow.getCell(33).getNumericCellValue(),
					theRow.getCell(34).getNumericCellValue()/100,
					this.getAgentDate((int)theRow.getCell(33).getNumericCellValue()));
			Agent agent3 = new Agent((int)theRow.getCell(35).getNumericCellValue(),
					theRow.getCell(36).getNumericCellValue()/100,
					this.getAgentDate((int)theRow.getCell(35).getNumericCellValue()));
			
			int policyNum = (int)theRow.getCell(1).getNumericCellValue();
			String planCode = this.convertPlanCode(theRow.getCell(31).getStringCellValue());
			String premiumDate = theRow.getCell(45).getStringCellValue();
			double premiumAmt = theRow.getCell(24).getNumericCellValue();
		
			
			
			RowPack thePack = new RowPack(policyNum,planCode,premiumDate,agent1,agent2,agent3,premiumAmt);
			System.out.println(thePack);
			
			myList.add(thePack);
				
		}
		return myList;
	}
	
	public String convertPlanCode(String old) {
		String returnThis = null;
		for (Row theRow: plancodeSheet) {
				String here = theRow.getCell(0).getStringCellValue();
				if (here.equals(old)) {
					String temp = theRow.getCell(1).getStringCellValue();
					returnThis = temp;
				}
			}
		return returnThis;
	}
	
	public Date getAgentDate(int agentID) {
		Date returnThis = null;
		for (Row theRow: dateConversion) {
				int num = (int)theRow.getCell(1).getNumericCellValue();
				if (num == agentID){
					Date temp = new Date(theRow.getCell(3).getStringCellValue());
					returnThis = temp;
				}
			}
		return returnThis;
	}
		
}
