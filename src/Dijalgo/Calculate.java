package Dijalgo;

import com.esri.arcgisruntime.geometry.Point;
import com.sun.javafx.geom.Edge;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by a00987765 on 3/22/2017.
 */
public class Calculate extends Controller {


    //creates a list of points from input
    public ArrayList nodePoints() throws IOException {

        ArrayList<Point> nodeList = new ArrayList<>();
        String path = filePath();

        double x, y;

        StringTokenizer st;
        File bufferFile = new File(path);

        //seaches for any numbers with a space separating them which tells us it is the coordinates
        String regex = "\\b[0-9].*\\s\\b[0-9].*";
        //searches for any 3 lettered word in any case this tells us where END is
        String strregex = "[a-zA-Z]{3}";
        //complies the the regex pattern for numbers
        Pattern pattern = Pattern.compile(regex);
        //complies the regex pattern for END
        Pattern strPattern = Pattern.compile(strregex);

        BufferedReader reader = new BufferedReader(new FileReader(bufferFile));
        String line;

        int i = 0;
        //cycles through the text file
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line.trim());
            Matcher strMatcher = strPattern.matcher(line.trim());
            if (strMatcher.find()) {
                taLog.appendText("END OF FILE\n");
                break;

            } else if (matcher.find()) {
                st = new StringTokenizer(line.trim(), " ");
                x = Double.valueOf(st.nextToken());
                taLog.appendText("x" + String.valueOf(i++) + ": " + String.valueOf(x) + "\n");
                y = Double.valueOf(st.nextToken());
                taLog.appendText("y" + String.valueOf(i++) + ": " + String.valueOf(y) + "\n");
                Point point = new Point(x, y);
                nodeList.add(point);
            }
        }
        return nodeList;
    }

    public void costs(ArrayList points) {

    }

    public void createAdjList() throws IOException {
        String path = adjFilePath();


        int start, end;

        StringTokenizer st;
        File bufferFile = new File(path);

        //seaches for any numbers with a space separating them which tells us it is the coordinates
        String regex = "\\b[0-9].*\\s\\b[0-9].*";
        //searches for any 3 lettered word in any case this tells us where END is
        String strregex = "[a-zA-Z]{3}";
        //complies the the regex pattern for numbers
        Pattern pattern = Pattern.compile(regex);
        //complies the regex pattern for END
        Pattern strPattern = Pattern.compile(strregex);

        BufferedReader reader = new BufferedReader(new FileReader(bufferFile));
        String line;

        int i = 0;
        ArrayList nodeList = nodePoints();
        double length = 0,x1=0,x2=0,y1=0,y2=0;
        Point point;

        //cycles through the text file
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line.trim());
            Matcher strMatcher = strPattern.matcher(line.trim());
            if (strMatcher.find()) {
                taLog.appendText("END OF ADJFILE\n");
                break;

            } else if (matcher.find()) {
                st = new StringTokenizer(line.trim(), " ");
                start = Integer.valueOf(st.nextToken());
                end = Integer.valueOf(st.nextToken());
                //sets the point gets first set of values
                point= (Point) nodeList.get(start-1);
                x1=point.getX();
                y1=point.getY();
                //sets second set and gets the second pair of values
                point= (Point) nodeList.get(end-1);
                x2=point.getX();
                y2=point.getY();
                //creates the length between values which is the cost
                length = Math.sqrt(Math.pow((x2 - x1), 2.0) + Math.pow((y2 - y1), 2.0));
                //creates a string value pair of the points and cost



                taLog.appendText("The edge of " + String.valueOf(start) +String.valueOf(end)+" is "+ "\n");
            }
        }

    }


    public void test() throws IOException {
        ArrayList nodePoints = nodePoints();
        AdjList adjList = new AdjList(nodePoints);

    }

}
