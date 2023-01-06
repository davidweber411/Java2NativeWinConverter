package com.wedasoft.java2nativeWinConverter.controller;

import com.wedasoft.java.wedasoftCommonsLibrary.fileSystem.FileSystemUtils;
import com.wedasoft.java.wedasoftCommonsLibrary.javaFx.FxHelperFunctions;
import com.wedasoft.java2nativeWinConverter.config.AppConfig;
import com.wedasoft.java2nativeWinConverter.enums.AppTypeToCreate;
import com.wedasoft.java2nativeWinConverter.enums.MainClassReferenceType;
import com.wedasoft.java2nativeWinConverter.enums.PackageContentType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ChoiceBox<AppTypeToCreate> fileTypeToCreateChoiceBox;
    @FXML
    private ChoiceBox<PackageContentType> filePackageTypeChoiceBox;
    @FXML
    private Button directoryWithAllFilesToPackageButton;
    @FXML
    private TextField directoryWithAllFilesToPackageTextField;
    @FXML
    private TextField mainJarFileNameTextField;
    @FXML
    private ChoiceBox<MainClassReferenceType> mainClassTypeChoiceBox;
    @FXML
    private TextField mainClassNameTextField;
    @FXML
    private TextField outputFileDestinationDirectoryTextField;
    @FXML
    private TextField appNameTextField;
    @FXML
    private TextField appVersionTextField;
    @FXML
    private TextField iconFilePathTextField;
    @FXML
    private CheckBox showDirectoryChooserDialogForInstallationCheckbox;
    @FXML
    private CheckBox createDesktopShortcutCheckbox;
    @FXML
    private CheckBox addApplicationToSystemMenuCheckbox;
    @FXML
    private TextArea jPackageCommandTextArea;

    /**
     * <a href="https://docs.oracle.com/en/java/javase/14/docs/specs/man/jpackage.html">Link to the oracle docs!</a>
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            /* fileTypeToCreateChoiceBox */
            fileTypeToCreateChoiceBox.getItems().addAll(AppTypeToCreate.values());
            fileTypeToCreateChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(AppTypeToCreate object) {
                    return object.getLabel();
                }

                @Override
                public AppTypeToCreate fromString(String string) {
                    return null;
                }
            });
            fileTypeToCreateChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                if (newValue.equals(AppTypeToCreate.MSI)) {
                    showDirectoryChooserDialogForInstallationCheckbox.setDisable(false);
                    createDesktopShortcutCheckbox.setDisable(false);
                    addApplicationToSystemMenuCheckbox.setDisable(false);
                } else if (newValue.equals(AppTypeToCreate.EXE)) {
                    showDirectoryChooserDialogForInstallationCheckbox.setDisable(true);
                    createDesktopShortcutCheckbox.setDisable(true);
                    addApplicationToSystemMenuCheckbox.setDisable(true);
                }
            });
            fileTypeToCreateChoiceBox.setValue(AppTypeToCreate.EXE);

            /* filePackageTypeChoiceBox */
            filePackageTypeChoiceBox.getItems().addAll(PackageContentType.values());
            filePackageTypeChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(PackageContentType object) {
                    return object.getLabel();
                }

                @Override
                public PackageContentType fromString(String string) {
                    return null;
                }
            });
            filePackageTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                if (newValue.equals(PackageContentType.SINGLE_JAR_ONLY)) {
                    directoryWithAllFilesToPackageButton.setDisable(true);
                    directoryWithAllFilesToPackageTextField.setDisable(true);
                } else if (newValue.equals(PackageContentType.ALL_FILES_FROM_DIR)) {
                    directoryWithAllFilesToPackageButton.setDisable(false);
                    directoryWithAllFilesToPackageTextField.setDisable(false);
                }
            });
            filePackageTypeChoiceBox.setValue(PackageContentType.SINGLE_JAR_ONLY);

            /* mainClassTypeChoiceBox */
            mainClassTypeChoiceBox.getItems().addAll(MainClassReferenceType.values());
            mainClassTypeChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(MainClassReferenceType object) {
                    return object.getLabel();
                }

                @Override
                public MainClassReferenceType fromString(String string) {
                    return null;
                }
            });
            mainClassTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                if (newValue.equals(MainClassReferenceType.MAINCLASS_OF_MAINJAR)) {
                    System.out.println("MAIN CLASS OF MAIN JAR");
                    mainClassNameTextField.setDisable(true);
                } else if (newValue.equals(MainClassReferenceType.DEVIATING_MAINCLASS)) {
                    System.out.println("USE DIFFERENT MAIN CLASS");
                    mainClassNameTextField.setDisable(false);
                }
            });
            mainClassTypeChoiceBox.setValue(MainClassReferenceType.MAINCLASS_OF_MAINJAR);
        } catch (

                Exception e) {
            e.printStackTrace();
        }

    }

    public void handleJarLocationDirectoryChooser() {
        DirectoryChooser dc = new DirectoryChooser();
        Stage actualStage = (Stage) directoryWithAllFilesToPackageTextField.getScene().getWindow();
        File file = dc.showDialog(actualStage);
        if (file != null) {
            directoryWithAllFilesToPackageTextField.setText(file.getAbsolutePath());
        }
    }

    public void handleMainJarFileNameFileChooser() {
        FileChooser fc = new FileChooser();
        Stage actualStage = (Stage) mainJarFileNameTextField.getScene().getWindow();
        File file = fc.showOpenDialog(actualStage);
        if (file != null) {
            mainJarFileNameTextField.setText(file.getAbsolutePath());
        }
    }

    public void handleOutputFileDestinationDirectoryChooser() {
        DirectoryChooser dc = new DirectoryChooser();
        Stage actualStage = (Stage) outputFileDestinationDirectoryTextField.getScene().getWindow();
        File file = dc.showDialog(actualStage);
        if (file != null) {
            outputFileDestinationDirectoryTextField.setText(file.getAbsolutePath());
        }
    }

    public void handleIconFilePathFileChooser() {
        FileChooser fc = new FileChooser();
        Stage actualStage = (Stage) iconFilePathTextField.getScene().getWindow();
        File file = fc.showOpenDialog(actualStage);
        if (file != null) {
            iconFilePathTextField.setText(file.getAbsolutePath());
        }
    }

    public void showWarningAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(":|");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String createJPackageCommandString() {
        String jPackageCommand = "jpackage";

        /* application type to create */
        jPackageCommand += " --type " + "\"" + fileTypeToCreateChoiceBox.getValue().getJPackageArgumentValue() + "\"";

        /* output directory path */
        if (outputFileDestinationDirectoryTextField.getText().isEmpty()) {
            showWarningAlert("You must specify a directory, in which the generated files should be placed, when they are created.");
            return "";
        } else {
            jPackageCommand += " --dest " + "\"" + outputFileDestinationDirectoryTextField.getText() + "\"";
        }

        /* main jar file name path */
        if (mainJarFileNameTextField.getText().isEmpty()) {
            showWarningAlert("You must enter the path to the jar file which contains the main method, which shall be converted. This can be done with an absolute or a relative path.");
            return "";
        } else {
            jPackageCommand += " --main-jar " + "\"" + Path.of(mainJarFileNameTextField.getText()).getFileName().toString() + "\"";
        }

        /* directory containing files to be packaged path */
        if (filePackageTypeChoiceBox.getSelectionModel().getSelectedItem().equals(PackageContentType.SINGLE_JAR_ONLY)) {
            jPackageCommand += " --input " + "\"" + AppConfig.APP_DATA_DIRECTORY_PATH + "\"";
        } else if (directoryWithAllFilesToPackageTextField.getText().isEmpty()) {
            showWarningAlert("You must enter the path of the directory, in which all files including the main JAR, which shall be packaged, are located.");
            return "";
        } else {
            jPackageCommand += " --input " + "\"" + directoryWithAllFilesToPackageTextField.getText() + "\"";
        }

        /* main class name path */
        if (!mainClassTypeChoiceBox.getSelectionModel().getSelectedItem().equals(MainClassReferenceType.MAINCLASS_OF_MAINJAR)) {
            if (!mainClassNameTextField.getText().isEmpty()) {
                jPackageCommand += " --main-class " + "\"" + mainClassNameTextField.getText() + "\"";
            } else {
                showWarningAlert("If there is a deviating main class, you must specify it.");
            }
        }

        /* app name, app version, app icon */
        if (appNameTextField.getText().isEmpty()) {
            showWarningAlert("You must enter a name for your application.");
            return "";
        } else {
            jPackageCommand += " --name " + "\"" + appNameTextField.getText() + "\"";
        }
        if (appVersionTextField.getText().isEmpty()) {
            showWarningAlert("You must enter a version number for your application, like \"1.3\".");
            return "";
        } else {
            jPackageCommand += " --app-version " + "\"" + appVersionTextField.getText() + "\"";
        }
        if (iconFilePathTextField.getText().isEmpty()) {
            showWarningAlert("You do not need an icon, but we want to tell you, that you have not set the icon path. Icon files must have the filetype .ico.");
        } else {
            jPackageCommand += " --icon " + "\"" + iconFilePathTextField.getText() + "\"";
        }

        /* only for msi */
        if (fileTypeToCreateChoiceBox.getSelectionModel().getSelectedItem().equals(AppTypeToCreate.MSI)) {
            if (showDirectoryChooserDialogForInstallationCheckbox.isSelected()) {
                jPackageCommand += " --win-dir-chooser";
            }
            if (createDesktopShortcutCheckbox.isSelected()) {
                jPackageCommand += " --win-shortcut";
            }
            if (addApplicationToSystemMenuCheckbox.isSelected()) {
                jPackageCommand += " --win-menu";
            }
        }

        return jPackageCommand;
    }

    public void handleCreateJPackageCommand() {
        String jPackageCommand = createJPackageCommandString();
        jPackageCommandTextArea.setText(jPackageCommand);
    }

    public void handleCreateNativeApplication() {
        try {
            String jPackageCommand = createJPackageCommandString();
            if (jPackageCommand == null || jPackageCommand.isEmpty()) {
                return;
            }

            /* prepare environment for single jar packaging mode */
            Path appDataDirectoryPath = Path.of(AppConfig.APP_DATA_DIRECTORY_PATH);
            if (filePackageTypeChoiceBox.getSelectionModel().getSelectedItem().equals(PackageContentType.SINGLE_JAR_ONLY)) {
                FileSystemUtils.clearDir(appDataDirectoryPath);
                FileSystemUtils.copyFile(Path.of(mainJarFileNameTextField.getText()), appDataDirectoryPath, true);
            }

            /* create the native application */
            Path jPackageExePath = Path.of(FileSystemUtils.getPathOfJarContainingClass(getClass()).getParentFile().toString(), "included-jdk-17.0.2-open-jdk", "bin", "jpackage.exe");

            /* create the list of separated arguments for the process builder */
            String[] jPackageArgsWithValuesArray = jPackageCommand.substring("jpackage".length()).split(" --");
            List<String> jPackageArgsWithValuesList = new ArrayList<>();
            for (String string : jPackageArgsWithValuesArray) {
                if (!string.isBlank() && !string.isEmpty()) {
                    jPackageArgsWithValuesList.add("--" + string);
                }
            }
            List<String> jPackageSeparatedArgsAndValuesList = new ArrayList<>();
            jPackageSeparatedArgsAndValuesList.add(jPackageExePath.toString());
            for (String argWithValue : jPackageArgsWithValuesList) {
                Collections.addAll(jPackageSeparatedArgsAndValuesList, argWithValue.split(" ", 2));
            }

            /* start the creation process with process builder */
            jPackageCommandTextArea.setText(jPackageSeparatedArgsAndValuesList.toString());
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(jPackageSeparatedArgsAndValuesList);
            Process process = pb.start();
            String result = new String(process.getInputStream().readAllBytes());
            FxHelperFunctions.displayInformationDialog("The native application is created.");

            /* clear environment for single jar packaging mode */
            if (filePackageTypeChoiceBox.getSelectionModel().getSelectedItem().equals(PackageContentType.SINGLE_JAR_ONLY)) {
                FileSystemUtils.clearDir(appDataDirectoryPath);
            }
        } catch (Exception e) {
            FxHelperFunctions.displayErrorDialog("An error occurred while creating a native application.", e);
        }
    }

    /* WORKS; DIALOG MUST NOT BE BEFORE RUNTIME EXEC !!!! */
//	String escapedJPackageArguments = jPackageCommand.substring("jpackage".length());
//	System.out.println("escapedJPackageArguments=" + escapedJPackageArguments);
//
//	String absoluteJPackageCommand = "\"" + jPackageExePath.toString() + "\"" + escapedJPackageArguments;
//	System.out.println("absoluteJPackageCommand=" + absoluteJPackageCommand);
//	jPackageCommandTextArea.setText(absoluteJPackageCommand);
//
//	Runtime.getRuntime().exec(absoluteJPackageCommand);
    /* WORKS END */

    public static void displayCustomAlertDialog(AlertType alertType, String frameTitle, String headerText, Node content) {
        Alert alert;
        if (alertType == AlertType.INFORMATION || alertType == AlertType.WARNING || alertType == AlertType.ERROR) {
            alert = new Alert(alertType);
        } else {
            alert = new Alert(AlertType.INFORMATION);
        }
        alert.setTitle(frameTitle);
        if (headerText != null && !headerText.isEmpty()) {
            alert.setHeaderText(headerText);
        }
        alert.getDialogPane().setContent(content);
//		alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void handleExitMenuItem() {
        FxHelperFunctions.displayExitProgramDialog();
    }

    public void handleHelpMenuItem() {
        FxHelperFunctions.displayInformationDialog("""
                # Working explanation\r
                This software is just a GUI application, that uses jpackage (software from Oracle JDK 17) under the hood.\r
                If you want to use this software, you must configure jpackage correctly. \r
                \r
                # Needed settings for using jpackage\r
                \r
                ## Install the WIX-Tools\r
                The WIX-Tools are needed and must be installed for executing jpackage.\r
                Download WiX 3.0 or later from https://wixtoolset.org and add it to the PATH.\r
                \r
                ## Install .NET-Framework 3.5.1\r
                The WIX-Tools need the .NET-Framework 3.5.1.\r
                Depending on your system, this must be configured manually after the installation at "Windows-Icon-Button > Windows-Features".\r
                To that, the PATH variable (from the environment variables) must be extended with the "/bin" path.\r
                .NET-Framework can be installed by installing "Visual Studio (Community Edition)". Make sure to choose .NET (App and C# Desktop) during the installation.\r
                \r
                # jpackage documentation\r
                All parameters for jpackage can be found here:\r
                https://docs.oracle.com/en/java/javase/14/docs/specs/man/jpackage.html""");
        System.out.println("handleHelpMenuItem!");
    }

    public void handleAboutMenuItem() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label appNameLabel = new Label("Application name:");
        GridPane.setConstraints(appNameLabel, 0, 0);
        Label appName = new Label("Java 2 Native Windows Converter");
        GridPane.setConstraints(appName, 1, 0);
        Label progLangLabel = new Label("Used programming language:");
        GridPane.setConstraints(progLangLabel, 0, 1);
        Label progLang = new Label("Java");
        GridPane.setConstraints(progLang, 1, 1);
        Label authorLabel = new Label("Created by:");
        GridPane.setConstraints(authorLabel, 0, 2);
        Label author = new Label("David Weber");
        GridPane.setConstraints(author, 1, 2);
        Label creationDateLabel = new Label("Creation date:");
        GridPane.setConstraints(creationDateLabel, 0, 3);
        Label creationDate = new Label("2022-05-29");
        GridPane.setConstraints(creationDate, 1, 3);

        gridPane.getChildren().addAll(appNameLabel, appName, progLangLabel, progLang, authorLabel, author, creationDateLabel, creationDate);

        displayCustomAlertDialog(AlertType.INFORMATION, "About", "About", gridPane);
    }

}
