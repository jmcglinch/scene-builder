package util;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLConfig {
    private Document config;

    public void setConfig(Document config) {
        this.config = config;
    }

    public Document getConfig() {

        return config;
    }

    public static Document loadConfig(String pathname) {
        Document config = null;
        try {
            File inputFile = new File(pathname);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            config = dBuilder.parse(inputFile);
            config.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    public XMLConfig(String pathname) {
        this.setConfig(loadConfig(pathname));
    }
}
