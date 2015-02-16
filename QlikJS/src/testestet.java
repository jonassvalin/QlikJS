import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class testestet {

	public static void main(String[] args) {
		
		Pattern p = Pattern.compile("^\100\100\\s.([0-9]+).([0-9]+)\\s.([0-9]+).([0-9]+)\\s\100\100.*");
		Matcher m = p.matcher("@@ -0,0 +1,6 @@");
		System.out.println(p.toString());
		
		if(m.matches()){
			System.out.println("FUNKAR");
		}else{
			System.out.println("NEJ + ^\100\100\\s\055([0-9]+).([0-9]+)\\s\053([0-9]+).([0-9]+)\\s\100\100.*");
		}

	}

}
