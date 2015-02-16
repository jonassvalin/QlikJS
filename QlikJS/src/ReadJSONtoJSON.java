import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.google.gson.*;

public class ReadJSONtoJSON {
	
	public ReadJSONtoJSON(String path) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(path+"jscoverage.json"));
			JSONObject jsonObject = (JSONObject) obj;
			Iterator keys = jsonObject.keySet().iterator();
			
			JSONObject output = new JSONObject();

			while (keys.hasNext()) {
				String currentKey = (String) keys.next();
				JSONObject temp = (JSONObject) jsonObject.get(currentKey);
				JSONArray lineData = (JSONArray) temp.get("lineData");
				Iterator<Integer> it = lineData.iterator();
				
				JSONArray currentKeyArray = new JSONArray();

				int counter = 0;
				while (it.hasNext()) {
					Object object = it.next();
					if (object != null) {
						long covered = (long) object;
						if (covered > 0) {
							currentKeyArray.add(counter);
						}
					}
					counter++;
				}
			output.put(currentKey, currentKeyArray);
			}
			PrintWriter writer = new PrintWriter(path + "coverageanalysis.json");
			writer.print(output.toJSONString());
			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
