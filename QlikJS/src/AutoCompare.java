
import java.io.*;
import java.util.Scanner;

public class AutoCompare {

	public AutoCompare(String covPath, String comPath) {
		File coverage = new File(covPath);
		File commits = new File(comPath);

		try {
			Scanner scanCoverage = new Scanner(coverage);
			while (scanCoverage.hasNextLine()) {
				String coverageLine = scanCoverage.nextLine();
				Scanner scanCommits = new Scanner(commits);
				while (scanCommits.hasNextLine()) {
					String commitsLine = scanCommits.nextLine();
					if(commitsLine.contains(coverageLine) && coverageLine.length() > 3) { 
						System.out.println("Method: " + coverageLine);
					}
				}

			} 
		} catch (Exception e) {
			System.out.println("SCANNER ISSUE " + e);

		}
	}
}
