package com.wedasoft.java2nativeWinConverter;

import java.io.File;

public class MainApplicationConfig {

    public static final String COMPANY_NAME = "WedaSoft";
    public static final String APP_SIMPLE_NAME = "Java 2 Native Windows Converter";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_FULL_NAME = COMPANY_NAME + " " + APP_SIMPLE_NAME + " " + APP_VERSION;

    public static final String PATH_APP_ROOT_DATA_DIR = System.getProperty("user.home")
            + File.separator + "." + COMPANY_NAME.toLowerCase()
            + File.separator + APP_SIMPLE_NAME;

    public static final String PATH_APP_TMP_DATA_DIR = PATH_APP_ROOT_DATA_DIR
            + File.separator + "tmp";

    public static final String PATH_APP_INCLUDED_JDK_DEST = PATH_APP_ROOT_DATA_DIR
            + File.separator + "included-openjdk-17-windows-x64";

    public static final String PATH_APP_INCLUDED_JDK_JPACKAGE_EXE = PATH_APP_INCLUDED_JDK_DEST
            + File.separator + "bin"
            + File.separator + "jpackage.exe";

}
