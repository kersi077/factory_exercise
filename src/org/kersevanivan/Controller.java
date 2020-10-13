package org.kersevanivan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kersevanivan.request.VAGRequest;
import org.kersevanivan.vag.responseStationSearch.HaltestellenAPIResponse;
import org.kersevanivan.vag.responseStationSearch.Json;
import org.kersevanivan.writer.Writer;
import org.kersevanivan.writer.WriterFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Controller implements Observer {
    @FXML private GridPane pane;
    @FXML public TextField station;
    @FXML public TextArea response;
    @FXML public Label metadata_version;
    @FXML public Label metadata_timestamp;
    // Our call to the rest api.
    private final VAGRequest request = new VAGRequest();
    private String jsonResult = null;
    private Stage stage;

    public void searchStation(MouseEvent mouseEvent) throws IOException {
        request.register(this);
        request.requestStations( station.getText() );
        request.unregister(this);
    }

    @Override
    public void update(String value) {
        this.jsonResult = value;
        try {
            Json json = new Json();
            HaltestellenAPIResponse stations = json.getStation(value);
            response.setText( stations.getHaltestellenAsString() );
            metadata_timestamp.setText("Abfragezeitpunkt: " + stations.getMetadata().getTimestamp()); //TODO: That is not nice! Any Idea why?
            metadata_version.setText("Version: " + stations.getMetadata().getVersion());
        } catch (IOException e) {
            e.printStackTrace(); // TODO: Log the error somehow.
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void doSave(ActionEvent actionEvent) {
        if( jsonResult != null ) {
            Optional<String> filePath = showFileChooser();
            if (filePath.isPresent()) { // If there is no filePath than cancel was presed.
                WriterFactory factory = new WriterFactory();
                //TODO: get the appropriate writer and write!
            }
        }
    }

    private Optional<String> showFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save station");
        // Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
        File file = fileChooser.showSaveDialog(stage);
        if( file != null ) { // This happens when cancel is pressed.
            String path = file.getAbsolutePath();
            List<String> selectedFilter = fileChooser.getSelectedExtensionFilter().getExtensions();
            return Optional.of( this.addExtention(path, selectedFilter) );
        }
        return Optional.empty();
    }

    private String addExtention(String path, List<String> selectedFilters) {
        if( selectedFilters.size() > 0) {
            for (String selectedFilter : selectedFilters) {
                if (path.endsWith(selectedFilter.replace("*", "")))
                    return path;
            }
            return path + selectedFilters.get(0).replace("*", "");
        }
        return path;
    }
}
