package Dijalgo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller extends Application{

    @FXML
    Pane pane;

    @FXML
    TextArea taLog;

    @FXML
    TextField tfInputToKML;


    @FXML
    TextField tfLoadPath;
    @FXML
    TextField tfAdjLoadPath;

    @FXML
    TextField tfSaveFullPath;

    @FXML
    TextField tfSaveOnlyPath;


    //sets initial file directory
    final private File homedir = new File("H:\\var\\gist\\8100\\Mod4");

    @Override
    public void start(Stage primaryStage) throws Exception {


    }

    public void btnLoadFile() {
        try {
            //opens up the file chooser window
            //sets extension files types
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Coordinates files (*.txt)", "*.txt");
            FileChooser fileChooser = new FileChooser();
            //gets the extension files types from first object
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setTitle("Open coordinate file");
            fileChooser.setInitialDirectory(homedir);
            File input = fileChooser.showOpenDialog(pane.getScene().getWindow());
            //if invalid file selected, warning message appears
            if (input != null) {
                tfLoadPath.setText(String.valueOf(input));
            } else {
                //sends out error dialog box
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid File", ButtonType.CLOSE);
                alert.showAndWait();
                tfLoadPath.setText("Invalid File Selected");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    public void btnLoadAdjFile() {
        try {
            //opens up the file chooser window
            //sets extension files types
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Adj file (*.txt)", "*.txt");
            FileChooser fileChooser = new FileChooser();
            //gets the extension files types from first object
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setTitle("Open adj file");
            fileChooser.setInitialDirectory(homedir);
            File input = fileChooser.showOpenDialog(pane.getScene().getWindow());
            //if invalid file selected, warning message appears
            if (input != null) {
                tfAdjLoadPath.setText(String.valueOf(input));
            } else {
                //sends out error dialog box
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid File", ButtonType.CLOSE);
                alert.showAndWait();
                tfAdjLoadPath.setText("Invalid File Selected");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.CLOSE);
            alert.showAndWait();
        }
    }
    public void btnSaveFull() {



    }

    public void btnSaveOnlyPath() {

    }

    public String filePath() {
        String path = tfLoadPath.getText();
        return path;
    }
    public String adjFilePath() {
        String path = tfAdjLoadPath.getText();
        return path;
    }


}
