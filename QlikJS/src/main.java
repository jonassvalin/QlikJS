import java.util.ArrayList;

import junit.framework.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.testscripts.Test3;

public class main {

	public static void main(String[] args) {
		Commander com = new Commander();
		CmdThread webServer = new CmdThread(com, "C:\\Users\\ext_jvs\\JScover\\examples\\localStorage-proxy", "sh web-server.sh");
		CmdThread jscoverProxy = new CmdThread(com, "C:\\Users\\ext_jvs\\JScover\\examples\\localStorage-proxy", "sh jscover-proxy.sh");
		Result result = JUnitCore.runClasses(Test3.class);
		webServer.stopProcess();
		jscoverProxy.stopProcess();
		CmdThread jsResults = new CmdThread(com, "C:\\Users\\ext_jvs\\JScover", "java -cp target/dist/JSCover-all.jar jscover.report.Main --format=COBERTURAXML coverage target/local-storage-proxy/no-frames/original-src");
		jsResults.stopProcess();
		System.exit(0);
	}
}
