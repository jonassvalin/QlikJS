import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AutoCompare {

	public AutoCompare(String covPath, String comPath) {
		File coverage = new File(covPath);
		File commits = new File(comPath);
		getGitLines(comPath);

	}

	private void getGitLines(String filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			int lineEnd = 0;
			int lineCounter = 0;
			String currentKey = "";
			JSONObject output = new JSONObject();
			line = br.readLine();
			
			while (line != null) {
				System.out.println(line);
				if (Pattern.matches("diff --.*", line)) {
					String[] Lines = line.split("/");
					currentKey = Lines[Lines.length-1];
				} else if (lineRegex(line)) {
					line = line.substring(line.indexOf("@@") + 2,
							line.indexOf("@@", line.indexOf(" ")));
					String lineNumber[] = line.substring(line.indexOf("+"),line.indexOf(" ", line.indexOf("+"))).replace("+", "").split(",");
					lineCounter = Integer.parseInt(lineNumber[0]);
					lineEnd = Integer.parseInt(lineNumber[1]) + Integer.parseInt(lineNumber[0]);
					JSONArray currentKeyArray = new JSONArray();
					while(lineCounter <= lineEnd){
						currentKeyArray.add(lineCounter);
						lineCounter++;
					}
					System.out.println(currentKey);
					output.put(currentKey, currentKeyArray);
				}
				line = br.readLine();
			}
			br.close();
			PrintWriter writer = new PrintWriter("C:\\wamp\\www\\coverageanalysis.json");
			writer.print(output.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean lineRegex(String line) {
		// System.out.println(line.trim());
		return Pattern
				.matches(
						"^\100\100\\s.([0-9]+).([0-9]+)\\s.([0-9]+).([0-9]+)\\s\100\100.*",
						line);
	}

}
