package com.wedasoft.java2nativeWinConverter.helper;

import javafx.scene.Node;
import javafx.scene.control.Alert;

public class HelperFunctions {

    public static void showWarningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(":|");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void displayCustomAlertDialog(Alert.AlertType alertType, String frameTitle, String headerText, Node content) {
        Alert alert;
        if (alertType == Alert.AlertType.INFORMATION || alertType == Alert.AlertType.WARNING || alertType == Alert.AlertType.ERROR) {
            alert = new Alert(alertType);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(frameTitle);
        if (headerText != null && !headerText.isEmpty()) {
            alert.setHeaderText(headerText);
        }
        alert.getDialogPane().setContent(content);
        alert.showAndWait();
    }

}
