import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AutoCompare {

	private Pattern p;
	private JSONObject output;
	private JSONArray currentKeyArray;
	private JSONParser parser;
	private String currentKey;
	private String line;
	private String comPath;
	private String covPath;
	private static final String REGEX = "^\100\100\\s.([0-9]+).([0-9]+)\\s.([0-9]+).([0-9]+)\\s\100\100.*";

	public AutoCompare(String covPath, String comPath) {
		this.covPath = covPath;
		this.comPath = comPath;
		File coverage = new File(covPath);
		File commits = new File(comPath + "//git.diff");
		p = Pattern.compile(REGEX);
		getGitLines(commits);
		finalResult();
		
	}

	private void finalResult() {
		
		try {
			parser = new JSONParser();
			FileReader coverage = new FileReader(covPath);
			FileReader commits = new FileReader(comPath +  "//commitanalysis.json");
			JSONObject jsonCov = (JSONObject) parser.parse(coverage);
			JSONObject jsonCom = (JSONObject) parser.parse(commits);
			JSONObject result = jsonCompare(jsonCov, jsonCom);
			writeToFile(comPath + "//finalresult.json", result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONObject jsonCompare(JSONObject jsonCov, JSONObject jsonCom) {
		JSONObject result = new JSONObject();
		Iterator keys = jsonCov.keySet().iterator();
		while (keys.hasNext()) {
			JSONArray resultArray = new JSONArray();
			String currentKey = (String) keys.next();
			if (jsonCom.containsKey(currentKey)) {
				JSONArray covArray = (JSONArray) jsonCov.get(currentKey);
				JSONArray comArray = (JSONArray) jsonCom.get(currentKey);
				resultArray = arrayCompare(covArray, comArray);
				result.put(currentKey, resultArray);
			}
		}
		return result;
	}

	private JSONArray arrayCompare(JSONArray covArray, JSONArray comArray) {
		JSONArray resultArray = new JSONArray();
		for (int j = 0; covArray.size() > j; j++) {
			long line = (long) covArray.get(j);
			for(int k = 0; comArray.size()  > k ; k++){
				if (line == (long) comArray.get(k)) {
					resultArray.add(line);
				}
			}
		}
		return resultArray;
	}

	private void getGitLines(File filePath) {
		try {
			FileReader file = new FileReader(filePath);
			BufferedReader br = new BufferedReader(file);
			output = new JSONObject();
			line = br.readLine();

			while (line != null) {
				if (Pattern.matches("diff --.*", line)) {
					newFileIdentified(line);
				} else if (lineRegex(line)) {
					newChangeIdentified(line);
				}
				line = br.readLine();
			}
			br.close();
			writeToFile(comPath +  "//commitanalysis.json", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile(String path, JSONObject printer) throws IOException {
		PrintWriter writer = new PrintWriter(path);
		writer.print(printer.toJSONString());
		writer.flush();
		writer.close();
	}

	private void newChangeIdentified(String line) {
		int lineEnd = 0;
		int lineCounter = 0;
		line = line.substring(line.indexOf("@@") + 2,
				line.indexOf("@@", line.indexOf(" ")));
		String lineNumber[] = line
				.substring(line.indexOf("+"),
						line.indexOf(" ", line.indexOf("+"))).replace("+", "")
				.split(",");
		lineCounter = Integer.parseInt(lineNumber[0]);
		lineEnd = Integer.parseInt(lineNumber[1])
				+ Integer.parseInt(lineNumber[0]);
		while (lineCounter < lineEnd) {
			currentKeyArray.add(lineCounter);
			lineCounter++;
		}
		output.put(currentKey, currentKeyArray);
	}

	private void newFileIdentified(String line) {
		currentKeyArray = new JSONArray();
		String[] Lines = line.split("/");
		currentKey = "/" + Lines[Lines.length - 1];
		output.put(currentKey, currentKeyArray);
	}

	private boolean lineRegex(String line) {
		Matcher m = p.matcher(line);
		return m.matches();
	}
}
