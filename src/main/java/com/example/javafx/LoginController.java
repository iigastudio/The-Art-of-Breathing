package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;

    @FXML
    private AnchorPane loginPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String passWord = txtPassword.getText();

        if (validateLogin(userName, passWord)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Success as " + userName, ButtonType.OK);

            alert.showAndWait();

            if (null != alert.getResult()) {
                loginPane.getScene().getWindow().hide();
                Stage stage = new Stage();
                Scene scene = new Scene(loadFXML("BreathingFXML"));
                stage.setScene(scene);
                stage.setTitle("Buteyko Breathing Exercise");
                stage.setUserData(scene);
                stage.setResizable(false);
                stage.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Credentials", ButtonType.OK);
            alert.show();
        }

    }

    private boolean validateLogin(String userName, String password) {
        Properties props = loadPropsFile();
        boolean valid = false;
        if (props != null) {
            if (userName != null && password != null
                    && props.containsKey(userName) && password.equalsIgnoreCase(props.getProperty(userName))) {
                valid = true;
            }
        } else {
            if (userName != null && password != null
                    && userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                valid = true;

            }

            if (userName != null && password != null
                    && userName.equalsIgnoreCase("user") && password.equalsIgnoreCase("user")) {
                valid = true;
            }
        }

        return valid;
    }

    public static Parent loadFXML(String fxml) throws IOException {
        URL fxmlLocation = BreathingMain.class.getResource(fxml + ".fxml");
        System.out.println("fxmlLocation " + fxmlLocation);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        return fxmlLoader.load();
    }

    public static Properties loadPropsFile() {
        try (InputStream input = new FileInputStream("filedb/login.props")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}