import java.util.ArrayList;

import junit.framework.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.testscripts.Test3;

public class main {
	
	private static final String JONAS = "C:\\Users\\ext_jvs\\JScover";
	private static final String AXEL = "C:\\jscover\\jscover- build";
	private static final String WAMP = "C:\\wamp\\www";

	public static void main(String[] args) {

		String path = JONAS;
		String gitCommand = "git diff --pretty=oneline 11f91de8996b758302c39381e0425f32126eae1d";
		
		new MoveRemove(path + "\\target\\local-storage-proxy\\no-frames\\jscoverage.json");
		CmdThread webServer = new CmdThread(new Commander(), path + "\\examples\\localStorage-proxy", "sh web-server.sh");
		CmdThread jscoverProxy = new CmdThread(new Commander(), path + "\\examples\\localStorage-proxy", "sh jscover-proxy.sh");
		Result result = JUnitCore.runClasses(Test3.class);
		webServer.stopProcess();
		jscoverProxy.stopProcess();
		
		new ReadJSONtoJSON(path + "\\target\\local-storage-proxy\\no-frames\\");
		Commander cm = new Commander();
		String gitResult = cm.run(WAMP, gitCommand +" > git.diff");
		cm.stop();
		new AutoCompare(path + "\\target\\local-storage-proxy\\no-frames\\coverageanalysis.json", WAMP);



		System.exit(0);
	}
}
