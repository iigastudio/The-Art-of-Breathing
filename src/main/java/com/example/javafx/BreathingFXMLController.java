package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class BreathingFXMLController implements Initializable {

    @FXML
    private ImageView imgBreathingImage;
    @FXML
    private Button btnNext;

    //LinkedList<Image> linkedListImage=new LinkedList<Image>();
    List<Image> imageList = new ArrayList<Image>();
    Iterator<Image> listIterator;
    Image previous;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadImages();
        listIterator = imageList.iterator();
        if (listIterator.hasNext()) {
            previous = listIterator.next();
            imgBreathingImage.setImage(previous);
        }
    }

    public void loadImages() {
        File folder = new File("images");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            Image image = new Image(file.toURI().toString());
            imageList.add(image);
        }
    }

    @FXML
    private void OnClickNext(ActionEvent event) {
        if (listIterator.hasNext()) {
            previous = listIterator.next();
            imgBreathingImage.setImage(previous);
        } else {
            listIterator = imageList.iterator();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "End of Tip - Restarting");
            alert.show();
        }
    }

}

