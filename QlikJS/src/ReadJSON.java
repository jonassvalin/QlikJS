import java.io.File;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ReadJSON {
	public ReadJSON(String path) {
		try {
			File JSON = new File(path + "jscoverage.json");
			//System.out.println(toString(JSON));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonOutput = gson.toJson(JSON);
			System.out.println(jsonOutput);
			PrintWriter writer = new PrintWriter(path + "coverageanalysis.txt", "UTF-8");
			//writer.println("The following lines were covered: ");
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
