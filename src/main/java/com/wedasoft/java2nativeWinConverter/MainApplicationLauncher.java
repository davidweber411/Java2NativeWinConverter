package com.wedasoft.java2nativeWinConverter;

import com.wedasoft.java2nativeWinConverter.config.AppDataHandler;

public class MainApplicationLauncher {

    public static void main(String[] args) {
        AppDataHandler.createDirectoryForHandlingApplicationRelatedFiles();
        MainApplication.main2(args);
    }

}
