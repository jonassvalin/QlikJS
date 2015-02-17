import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class Commander {

	private String cmd;
	private ProcessBuilder processBuilder;
	private Process process;

	public Commander(){

	}
	
	public ArrayList<String> run(String path, String command){
		BufferedReader r = reader(path, command);
		ArrayList<String> result = new ArrayList<String>(); 
		String a = "";
		try{
			while(true) {
				a = r.readLine();
				if(a != null) {
					System.out.println(a);
					result.add(a);
				} else { break; }
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public BufferedReader reader(String path, String command){
		cmd = "cd " + "\"" + path + "\" && " + command; 
		processBuilder = new ProcessBuilder("cmd.exe", "/c", cmd);
		processBuilder.redirectErrorStream(true);
		process = null;
		try {
			process = processBuilder.start();
		} catch (IOException e1) {
			System.out.println("fel i processbuilder.start");
			e1.printStackTrace();
		}
		return new BufferedReader(new InputStreamReader(process.getInputStream()));
	}
	
	public void stop() {
		process.destroy();
	}
}
