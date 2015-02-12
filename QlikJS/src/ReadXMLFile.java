
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.PrintWriter;
 
public class ReadXMLFile {

	public ReadXMLFile(String path , String xmlName) {


		try {
			// DETTA ÄR EN ÄNDRING
			File fXmlFile = new File(path + xmlName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			/*dbFactory.setAttribute("http://xml.org/sax/features/namespaces", true);
			//dbFactory.setAttribute("http://xml.org/sax/features/validation", false);
			dbFactory.setAttribute("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbFactory.setAttribute("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			dbFactory.setNamespaceAware(true);
			dbFactory.setIgnoringElementContentWhitespace(false);
			dbFactory.setIgnoringComments(false);
			dbFactory.setValidating(false);
			*/
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("class");
			
			File jsonFile = new File(path + "coverageanalysis.txt");
			if (jsonFile.exists())
		         jsonFile.delete();
			PrintWriter writer = new PrintWriter(path + "coverageanalysis.txt", "UTF-8");
			writer.println("The following methods were covered: ");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				Node counter = nNode.getFirstChild();
				Element eCounter = (Element) counter;
				boolean covered = (Integer.parseInt(eCounter.getAttribute("covered")) > 0);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (covered) {
						writer.println("\n" + eElement.getAttribute("name"));
					}
				}
			}
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}