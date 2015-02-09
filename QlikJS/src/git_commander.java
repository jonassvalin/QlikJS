

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class git_commander {
	
	private String command;
	
	public git_commander(String path , String command){
	
		this.command = "cd " + "\"" + path + "\" && " + command;		
		// "cmd.exe", "/c", "cd \"C:\\git\\qliktest\" && git diff a7afc9227df4aba409e652054f0f92c992230784"
	}
	public ArrayList<String> run(){
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
		 processBuilder.redirectErrorStream(true);
		 Process process = null;
		try {
			process = processBuilder.start();
		} catch (IOException e1) {
			System.out.println("fel i processbuilder.start");
			e1.printStackTrace();
		}
		 BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		 ArrayList result = new ArrayList<String>(); 
		 String a = "";
		 try{
		 while(true){
			 a = r.readLine();
			 if(a != null){
				 result.add(a);
			 }else{break;}
		 }
		 }catch(IOException e){
			 e.printStackTrace();
		 }
		 
		 return result;
	}
}
