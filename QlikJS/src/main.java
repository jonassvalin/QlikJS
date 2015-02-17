import java.util.ArrayList;

import junit.framework.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.testscripts.Test3;

public class main {
	
	private static String JONAS = "C:\\Users\\ext_jvs\\JScover";
	private static String AXEL = "C:\\jscover\\jscover- build";
	private static String WAMP = "C:\\wamp\\www";
	private static String ORIGINAL  = "C:\\wamp\\gitcheckout\\QlikJSTest";

	public static void main(String[] args) {
		
		String path = AXEL ;
		
		String gitCommand = "git diff --pretty=oneline 11f91de8996b758302c39381e0425f32126eae1d";
		
		new MoveRemove(WAMP + "\\jscoverage.json");
		
		Commander movefile = new Commander();
		movefile.run(path +"\\", "java -jar target/dist/JSCover-all.jar -fs " +ORIGINAL + " " + WAMP + "\\");
		movefile.stop();

		JUnitCore.runClasses(Test3.class);

		new ReadJSONtoJSON(WAMP + "\\");
		Commander cm = new Commander();
		cm.run(ORIGINAL, gitCommand +" > "+ WAMP +"/git.diff");
		cm.stop();
		new AutoCompare(WAMP + "\\coverageanalysis.json", WAMP);
		System.exit(0);
	}
}
