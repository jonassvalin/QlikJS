import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class ReadJSON {
	public ReadJSON(String path) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(path+"jscoverage.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject temp = (JSONObject) jsonObject.get("//test.js");
			JSONArray lineData = (JSONArray) temp.get("lineData");
			Iterator<Integer> it = lineData.iterator();
			
			PrintWriter writer = new PrintWriter(path + "coverageanalysis.txt", "UTF-8");
			int counter = 0;
			while (it.hasNext()) {
				writer.println(counter + " " + it.next());
				counter++;
			}
			//writer.println("The following lines were covered: ");
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
