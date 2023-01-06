package com.wedasoft.java2nativeWinConverter;

import com.wedasoft.java.wedasoftCommonsLibrary.javaFx.FxHelperFunctions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static java.util.Objects.requireNonNull;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            FxHelperFunctions.displayExitProgramDialog();
        });
        primaryStage.setTitle(MainApplicationConfig.APP_FULL_NAME);

        Parent root = FXMLLoader.load(requireNonNull(getClass().getResource("/com/wedasoft/java2nativeWinConverter/main.fxml")));
        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    public static void main2(String[] args) {
        launch(args);
    }
}