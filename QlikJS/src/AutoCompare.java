import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AutoCompare {

	private Pattern p;
	private JSONObject output;
	private JSONArray currentKeyArray;
	private String currentKey;
	private String line;
	private String comPath;
	private static final String REGEX = "^\100\100\\s.([0-9]+).([0-9]+)\\s.([0-9]+).([0-9]+)\\s\100\100.*";

	public AutoCompare(String covPath, String comPath) {
		this.comPath = comPath;
		File coverage = new File(covPath);
		File commits = new File(comPath + "//git.diff");
		p = Pattern.compile(REGEX);
		getGitLines(commits);
		finalResult();
	}

	private void finalResult() {
		
		
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
			writeToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile() throws IOException {
		PrintWriter writer = new PrintWriter(
				comPath +  "//coverageanalysis.json");
		writer.print(output.toJSONString());
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
			currentKeyArray.add(Integer.toString(lineCounter));
			lineCounter++;
		}
		output.put(currentKey, currentKeyArray);
	}

	private void newFileIdentified(String line) {
		currentKeyArray = new JSONArray();
		String[] Lines = line.split("/");
		currentKey = Lines[Lines.length - 1];
		output.put(currentKey, currentKeyArray);
	}

	private boolean lineRegex(String line) {
		Matcher m = p.matcher(line);
		return m.matches();
	}
}
