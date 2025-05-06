import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    // Data members
    private List<Variable> variableList;

    // Constructor
    public XMLParser(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            Element root = doc.getDocumentElement();
            setVariableList(root);
        } catch (ParserConfigurationException e) {
            System.out.println("Failed to create DocumentBuilder");
        } catch (IOException e) {
            System.out.println("No such file or directory");
        } catch (SAXException e) {
            System.out.println("Failed to parse XML");
        }
    }

    // Getters and setters
    public List<Variable> getVariableList() {
        return variableList;
    }

    private void setVariableList(Element root) {
        NodeList variables = root.getElementsByTagName("VARIABLE");
        NodeList definitions = root.getElementsByTagName("DEFINITION");
        variableList = new ArrayList<>();
        for (int i = 0; i < variables.getLength(); i++) {
            variableList.add(new Variable(variables.item(i), definitions.item(i)));
        }
    }
}
