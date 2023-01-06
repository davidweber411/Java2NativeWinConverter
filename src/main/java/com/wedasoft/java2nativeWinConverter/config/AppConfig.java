package com.wedasoft.java2nativeWinConverter.config;

import java.io.File;

public class AppConfig {

    /*
     * Application name configurations
     */
    public static final String COMPANY_NAME = "WedaSoft";
    public static final String APP_SIMPLE_NAME = "Java 2 Native Windows Converter";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_FULL_NAME = COMPANY_NAME + " " + APP_SIMPLE_NAME + " " + APP_VERSION;

    /*
     * File system configurations
     */
    public static final String APP_DATA_DIRECTORY_PATH = System.getProperty("user.home") + File.separator + COMPANY_NAME + File.separator + APP_SIMPLE_NAME;

}
