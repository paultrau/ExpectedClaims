package pack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		//get folderpath from user
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("[?] Enter filepath: ");
	    String folderPath = myObj.nextLine();  // Read user input
	    System.out.println("[+] Filepath is: " + folderPath);  // Output user input
	    File[] allFiles = new File(folderPath).listFiles(); 
	  
	    Parser theParser = new Parser(allFiles);
	    
	   
	    
	    
	}	
}
