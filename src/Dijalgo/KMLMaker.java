package Dijalgo;

import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.Polyline;
import javafx.stage.FileChooser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by a00987765 on 3/22/2017.
 */
public class KMLMaker extends Calculate {


    //sets initial file directory
    final private File homedir = new File("H:\\var\\gist\\8100\\Mod4");

    public void createInputPoints() {
        try {
            ArrayList nList = nodePoints();
            Document kmlDoc;
            kmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element kml, document;

            kml = kmlDoc.createElement("kml");

            kml.setAttribute("xmlns", "http://www.opengis.net/kml/2.2");
            kmlDoc.appendChild(kml);

            document = kmlDoc.createElement("Document");
            kml.appendChild(document);

            Element placemark, name, description, kPoint, coordinates;


            Point googlePoint;
            for (int i = 0; i < nList.size(); i++) {
                googlePoint = (Point) nList.get(i);

                placemark = kmlDoc.createElement("Placemark");
                name = kmlDoc.createElement("name");
                description = kmlDoc.createElement("description");
                kPoint = kmlDoc.createElement("Point");
                coordinates = kmlDoc.createElement("coordinates");

                coordinates.setTextContent(
                        String.format("%s,%s", googlePoint.getX(), googlePoint.getY())
                );

                document.appendChild(placemark);
                placemark.appendChild(name);
                placemark.appendChild(description);
                placemark.appendChild(kPoint);
                kPoint.appendChild(coordinates);

                name.setTextContent(String.valueOf(i));
                description.setTextContent("This is point " + String.valueOf(i));
            }


            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(homedir);
            File kmlFile = fileChooser.showSaveDialog(pane.getScene().getWindow());
            if (kmlFile != null)

            {
                saveDomAsXmlOnDisc(kmlDoc, kmlFile);
                taLog.appendText("XML Created\n");
                tfInputToKML.setText(String.valueOf(kmlFile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void createEdgeLines() {
        try {
            LinkedHashMap<String, Double> edgeList = createEdges();
            ArrayList nList = nodePoints();

            /** ===========================================================
             * import org.w3c.dom.Document;
             * import org.w3c.dom.Element;
             * =========================================================== */
            Document kmlDoc;
            kmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element kml, document;

            kml = kmlDoc.createElement("kml");
            kml.setAttribute("xmlns", "http://www.opengis.net/kml/2.2");
            kmlDoc.appendChild(kml);

            document = kmlDoc.createElement("Document");
            kml.appendChild(document);

            Element placemark, name, description, kLineString, coordinates;


            Polyline googleLine;

            placemark = kmlDoc.createElement("Placemark");
            name = kmlDoc.createElement("name");
            description = kmlDoc.createElement("description");
            kLineString = kmlDoc.createElement("LineString");
            coordinates = kmlDoc.createElement("coordinates");


            String nodeLink;
            StringBuilder bigString = new StringBuilder();
            for (int j = 0; j < edgeList.size(); j++) {
                //somehow get the first edge string then use that string to provide the two points then create the line for KML
                nodeLink = edgeList.get();
                bigString.append(String.format("%s,%s,0%n", googleLine.getPoint(j).getX(), googleLine.getPoint(j).getY()));
            }
            coordinates.setTextContent(bigString.toString());


            document.appendChild(placemark);
            placemark.appendChild(name);
            placemark.appendChild(description);
            placemark.appendChild(kLineString);
            kLineString.appendChild(coordinates);

            name.setTextContent(tmpGraphic.getAttributeValue("name").toString());
            description.setTextContent(tmpGraphic.getAttributeValue("description").toString());

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new

    File("H:/var/gist/8010/"));
    File kmlFile = fileChooser.showSaveDialog(null);
            if(kmlFile !=null)

    {

        saveDomAsXmlOnDisc(kmlDoc, kmlFile);
    }


    private static void saveDomAsXmlOnDisc(Document doc, File file) {
        try {
            /** ===========================================================
             * set up a new source for transforming
             * =========================================================== */
            Source source = new DOMSource(doc);
            /** ===========================================================
             * set up a new target and in this case a file on disc
             * ===========================================================
             */
            Result result = new StreamResult(new PrintStream(file));
            /** ===========================================================
             * make a new transformer to go from DOM to DISC
             * ===========================================================
             */
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            /** ================================================================
             * indent the XML --> more human readable, parser ignore whitespace
             * ================================================================
             */
            xformer.setOutputProperty(OutputKeys.INDENT, "yes");
            xformer.setOutputProperty("{http:*xml.apache.org/xslt}indent-amount", "2");
            /** ===========================================================
             * write the DOM in memory to the file on disc
             * ===========================================================
             */
            xformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of saveDomAsXmlOnDisc

}







