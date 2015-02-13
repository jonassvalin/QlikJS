import java.util.ArrayList;

import junit.framework.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.testscripts.Test3;

public class main {

	public static void main(String[] args) {

		String jonas = "C:\\Users\\ext_jvs\\JScover";
		String axel = "C:\\jscover\\jscover- build";
		
		String pathWamp = "C:\\wamp\\www";
		String Gitcommand = "git diff --pretty=format:oneline 11f91de8996b758302c39381e0425f32126eae1d";

		String path = axel;
		new MoveRemove(path + "\\target\\local-storage-proxy\\no-frames\\jscoverage.json");
		
		CmdThread webServer = new CmdThread(new Commander(), path + "\\examples\\localStorage-proxy", "sh web-server.sh");
		CmdThread jscoverProxy = new CmdThread(new Commander(), path + "\\examples\\localStorage-proxy", "sh jscover-proxy.sh");
		Result result = JUnitCore.runClasses(Test3.class);
		webServer.stopProcess();
		jscoverProxy.stopProcess();
		CmdThread jsResults = new CmdThread(com, path, "java -cp target/dist/JSCover-all.jar jscover.report.Main --format=COBERTURAXML target/local-storage-proxy/no-frames/ target/local-storage-proxy/no-frames/original-src");
		jsResults.stopProcess();
		new ReadJSON(path +"\\target\\local-storage-proxy\\no-frames\\");
		String gitResult = new Commander().run( pathWamp , Gitcommandtest +" > git.diff");
		new AutoCompare("" ,pathWamp +".git.diff" );
		System.exit(0);
	}
}
