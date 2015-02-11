import java.util.ArrayList;

import junit.framework.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.testscripts.Test3;

public class main {

	public static void main(String[] args) {
		git_commander g = new git_commander();
		System.out.println("1");
		CmdThread webServer = new CmdThread(g, "C:\\Users\\ext_jvs\\JScover\\examples\\localStorage-proxy", "sh web-server.sh");
		CmdThread jscoverProxy = new CmdThread(g, "C:\\Users\\ext_jvs\\JScover\\examples\\localStorage-proxy", "sh jscover-proxy.sh");
		System.out.println("2");
		Result result = JUnitCore.runClasses(Test3.class);
		System.out.println("3");
		//g.run("C:\\git\\qliktest", "git diff a7afc9227df4aba409e652054f0f92c992230784 > C:\\git\\qliktest\\QlikTest\\coverage\\commits.txt");
	}

}
