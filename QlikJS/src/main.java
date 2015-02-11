import java.util.ArrayList;

import junit.framework.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import com.testscripts.Test3;

public class main {

	public static void main(String[] args) {
		git_commander g = new git_commander();
		System.out.println("1");
		ArrayList<String> a =  g.run("C:\\Users\\ext_jvs\\JScover\\examples\\localStorage-proxy", "sh web-server.sh");
		ArrayList<String> b =  g.run("C:\\Users\\ext_jvs\\JScover\\examples\\localStorage-proxy", "sh jscover-proxy.sh");
		System.out.println(a.toString());
		System.out.println(b.toString());
		System.out.println("2");
		Result result = JUnitCore.runClasses(Test3.class);
		System.out.println("3");
		//g.run("C:\\git\\qliktest", "git diff a7afc9227df4aba409e652054f0f92c992230784 > C:\\git\\qliktest\\QlikTest\\coverage\\commits.txt");
	}

}
