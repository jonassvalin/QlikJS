import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AutoCompare {

private Pattern p;	
	
	public AutoCompare(String covPath, String comPath) {
		File coverage = new File(covPath);
		File commits = new File(comPath);
		this.p = Pattern.compile("^\100\100\\s.([0-9]+).([0-9]+)\\s.([0-9]+).([0-9]+)\\s\100\100.*");
		getGitLines(comPath);
		
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

	private void getGitLines(String filePath) {
		try {
		FileReader file = new FileReader(filePath);	
		BufferedReader br = new BufferedReader(file);
		String line;
		int lineEnd = 0;
		int lineCounter = 0;
		line = br.readLine();
		int counter = 0;
		String currentKey = "";
		JSONObject output = new JSONObject();

		while (line != null) {
			counter ++;
			System.out.println(counter + " : " + line);
			if(Pattern.matches("diff --.*", line)){
				String[] Lines = line.split("/");
				currentKey = Lines[Lines.length-1];
				//output.put(currentKey, currentKeyArray);
			}else if (lineRegex(line) ){
				line = line.substring(line.indexOf("@@") +2 ,line.indexOf("@@",line.indexOf(" ") ) );
				//System.out.println(line.substring(line.indexOf("+"),line.indexOf(" ",line.indexOf("+"))).replace("+",""));
				String lineNumber[] = line.substring(line.indexOf("+"),line.indexOf(" ",line.indexOf("+"))).replace("+","").split(",");
				lineCounter  = Integer.parseInt(lineNumber[0]);
				lineEnd = Integer.parseInt(lineNumber[1]) + Integer.parseInt(lineNumber[0]);
				JSONArray currentKeyArray = new JSONArray();
				while(lineCounter <= lineEnd){
					currentKeyArray.add(Integer.toString(lineCounter));
					lineCounter++;
				}
				output.put(currentKey, currentKeyArray);
			}
			line = br.readLine();
		}
		
		br.close();
		PrintWriter writer = new PrintWriter("C:/wamp/www/coverageanalysis.json");
		writer.print(output.toJSONString());
		writer.flush();
		writer.close();
		}catch (IOException e) {

			e.printStackTrace();
		}
	}

	private boolean lineRegex(String line) {
		// System.out.println(line.trim());
		Matcher m = p.matcher(line);
		return m.matches();

	}

}
