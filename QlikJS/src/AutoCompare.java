import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
		line = br.readLine();
		while (line != null) {
			if (line.indexOf("@@") != -1){
				System.out.println(line);
			}
			line = br.readLine();
		} 
		}catch (IOException e) {
			e.printStackTrace();
		}	
		return new ArrayList<String>();
	}
}
