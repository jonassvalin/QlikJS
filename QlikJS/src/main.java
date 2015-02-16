import java.util.ArrayList;

import junit.framework.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.testscripts.Test3;

public class main {
	
	private static String JONAS = "C:\\Users\\ext_jvs\\JScover";
	private static String AXEL = "C:\\jscover\\jscover- build";
	private static String WAMP = "C:\\wamp\\www";

	public static void main(String[] args) {
		
		String path = AXEL ;
		String Gitcommand = "git diff --pretty=oneline 11f91de8996b758302c39381e0425f32126eae1d";
		new MoveRemove(path + "\\target\\local-storage-proxy\\no-frames\\jscoverage.json");
		/*
		CmdThread webServer = new CmdThread(new Commander(), path + "\\examples\\localStorage-proxy", "sh web-server.sh");
		CmdThread jscoverProxy = new CmdThread(new Commander(), path + "\\examples\\localStorage-proxy", "sh jscover-proxy.sh");
		Result result = JUnitCore.runClasses(Test3.class);
		webServer.stopProcess();
		jscoverProxy.stopProcess();
		new ReadJSONtoJSON(path +"\\target\\local-storage-proxy\\no-frames\\");
		*/
		Commander cm = new Commander();
		String gitResult = cm.run(WAMP, Gitcommand +" > git.diff");
		cm.stop();
		new AutoCompare("", WAMP + ".\\git.diff");



		System.exit(0);
	}
}
