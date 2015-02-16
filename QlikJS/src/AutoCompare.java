import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoCompare {

	public AutoCompare(String covPath, String comPath) {
		File coverage = new File(covPath);
		File commits = new File(comPath);
		ArrayList<String> a = getGitLines(comPath);
		/*
		 * try { Scanner scanCoverage = new Scanner(coverage); while
		 * (scanCoverage.hasNextLine()) { String coverageLine =
		 * scanCoverage.nextLine(); Scanner scanCommits = new Scanner(commits);
		 * while (scanCommits.hasNextLine()) { String commitsLine =
		 * scanCommits.nextLine(); if(commitsLine.contains(coverageLine) &&
		 * coverageLine.length() > 3) { System.out.println("Method: " +
		 * coverageLine); } }
		 * 
		 * } } catch (Exception e) { System.out.println("SCANNER ISSUE " + e);
		 * 
		 * }
		 */
	}

	private ArrayList<String> getGitLines(String filePath){
		try {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		int lineEnd = 0;
		int lineCounter = 0;
		line = br.readLine();
		int counter = 0;
		boolean inCode = false;
		
		
		
		while (line != null) {
			counter ++;
			//System.out.println(counter + " : " + line);
			if(Pattern.matches("diff --.*", line)){
				//System.out.println(line);
				inCode = false;
			}else if (linenum(line) && !inCode ){
				line = line.substring(line.indexOf("@@") +2 ,line.indexOf("@@",line.indexOf(" ") ) );
				System.out.println(line);
				//System.out.println(line.substring(line.indexOf("+"),line.indexOf(" ",line.indexOf("+"))).replace("+",""));
				String lineNumber[] = line.substring(line.indexOf("+"),line.indexOf(" ",line.indexOf("+"))).replace("+","").split(",");
				lineCounter  = Integer.parseInt(lineNumber[0]);
				lineEnd = Integer.parseInt(lineNumber[1]) + Integer.parseInt(lineNumber[0]);
				inCode = true;
			
			}else if(lineCounter < lineEnd && inCode){
				int num = lineCounter;
				//System.out.println("line number :" + String.valueOf(num));
				lineCounter ++;
			}else if(lineCounter >= lineEnd && inCode){
				//System.out.println("end");
				inCode = false;
			}
			line = br.readLine();
		} 
		}catch (IOException e) {
			e.printStackTrace();
		}	
		return new ArrayList<String>();
	}
	private boolean linenum(String line){
		//System.out.println(line.trim());
		return Pattern.matches("(\\0100\\0100)", line.trim());
	}
	
}
