import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class git_commander {

	private String cmd;

	public git_commander(){

		//this.cmd = "cd " + "\"" + path + "\" && ";                               
		// "cmd.exe", "/c", "cd \"C:\\git\\qliktest\" && git diff a7afc9227df4aba409e652054f0f92c992230784"
	}
	public ArrayList<String> run(String path, String command){
		BufferedReader r = reader(path, command);
		ArrayList result = new ArrayList<String>(); 
		String a = "";
		try{
			while(true) {
				a = r.readLine();
				if(a != null) {
					result.add(a);
				} else { break; }
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private BufferedReader reader(String path, String command){
		cmd = "cd " + "\"" + path + "\" && " + command; 
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", cmd);
		processBuilder.redirectErrorStream(true);
		Process process = null;
		try {
			process = processBuilder.start();
		} catch (IOException e1) {
			System.out.println("fel i processbuilder.start");
			e1.printStackTrace();
		}
		return new BufferedReader(new InputStreamReader(process.getInputStream()));
	}
}
