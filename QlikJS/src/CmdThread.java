import java.util.ArrayList;


public class CmdThread extends Thread {
	private Commander g;
	private String path;
	private String command;
	
	public CmdThread(Commander g, String path, String command) {
		this.g = g;
		this.path = path;
		this.command = command;
		start();
	}
	
	public void run() {
		g.run(path, command);
	}

	public void stopProcess() {
		g.stop();
		this.interrupt();
	}
}
