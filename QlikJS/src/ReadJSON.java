import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import org.json.simple.*;

public class ReadJSON {
	public ReadJSON(String path) {
		try {
			Object obj = new FileReader(path);
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);
			PrintWriter writer = new PrintWriter(path + "coverageanalysis.txt", "UTF-8");
			//writer.println("The following lines were covered: ");
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
