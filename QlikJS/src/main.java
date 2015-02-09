

public class main {

	public static void main(String[] args) {
		new ReadXMLFile("coverage/new.xml");
		git_commander g = new git_commander("C:\\git\\qliktest", "git diff b1cf39e18a7110e658ffc04d8c7f834fc60357ed 450e5654b9be7115dfc590315cd8c6b0aaf35ae2 > C:\\git\\qliktest\\QlikTest\\coverage\\commits.txt");
		g.run();
		new AutoCompare("coverage/coverageanalysis.txt", "coverage/commits.txt");
	}
}