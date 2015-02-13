import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class ReadJSON {
	public ReadJSON(String path) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(path+"jscoverage.json"));
			JSONObject jsonObject = (JSONObject) obj;
			Iterator keys = jsonObject.keySet().iterator();
			PrintWriter writer = new PrintWriter(path + "coverageanalysis.txt", "UTF-8");

			while (keys.hasNext()) {
				String currentKey = (String) keys.next();
				writer.print(currentKey+";");
				JSONObject temp = (JSONObject) jsonObject.get(currentKey);
				JSONArray lineData = (JSONArray) temp.get("lineData");
				Iterator<Integer> it = lineData.iterator();

				int counter = 0;
				while (it.hasNext()) {
					Object object = it.next();
					if (object != null) {
						long covered = (long) object;
						if (covered > 0) {
							writer.print(counter);
								writer.print(",");
						}
					}
					counter++;
				}
				writer.println();
			}
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
