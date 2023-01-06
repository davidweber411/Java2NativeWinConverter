package com.wedasoft.java2nativeWinConverter.config;

import java.io.File;

public class AppDataHandler {

    @SuppressWarnings({"UnusedReturnValue", "ResultOfMethodCallIgnored"})
    public static String createDirectoryForHandlingApplicationRelatedFiles() {
        File tmpFileForCreatingDirectory = new File(AppConfig.APP_DATA_DIRECTORY_PATH);
        if (!tmpFileForCreatingDirectory.exists()) {
            tmpFileForCreatingDirectory.mkdirs();
        }
        String directoryPath = AppConfig.APP_DATA_DIRECTORY_PATH;
        System.out.println("AppDataHandler: Directory for handling application related files=" + directoryPath);
        return directoryPath;
    }

}
