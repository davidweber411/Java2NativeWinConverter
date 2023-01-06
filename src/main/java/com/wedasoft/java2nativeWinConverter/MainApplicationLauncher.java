package com.wedasoft.java2nativeWinConverter;

import com.wedasoft.java.wedasoftCommonsLibrary.fileSystem.FileSystemUtils;

import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.wedasoft.java2nativeWinConverter.MainApplicationConfig.*;
import static java.util.Objects.requireNonNull;

public class MainApplicationLauncher {

    public static void main(String[] args) {
        createDirectoryForTheAppTmpData();
        createDirectoryForTheIncludedJdk17();
        extractZippedJdk17();
        MainApplication.main2(args);
    }

    private static void extractZippedJdk17() {
        for (String zipFilePath : List.of("/com/wedasoft/java2nativeWinConverter/included-openjdk-17-windows-x64-part1.zip", "/com/wedasoft/java2nativeWinConverter/included-openjdk-17-windows-x64-part2.zip")) {
            InputStream is = MainApplicationLauncher.class.getResourceAsStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(requireNonNull(is));
            try {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    String filePath = PATH_APP_ROOT_DATA_DIR + File.separator + entry.getName();
                    if (!entry.isDirectory()) {
                        // if the entry is a file, extract it
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                        byte[] bytesIn = new byte[1024];
                        int read = 0;
                        while ((read = zis.read(bytesIn)) != -1) {
                            bos.write(bytesIn, 0, read);
                        }
                        bos.close();
                    } else {
                        // if the entry is a directory, make the directory
                        File dir = new File(filePath);
                        //noinspection ResultOfMethodCallIgnored
                        dir.mkdirs();
                    }
                    zis.closeEntry();
                }
                zis.close();
                is.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not extract the zipped part of the JDK 17.");
            }
        }

        Path extractedZipPart1 = Path.of(PATH_APP_ROOT_DATA_DIR, "included-openjdk-17-windows-x64-part1");
        Path extractedZipPart2 = Path.of(PATH_APP_ROOT_DATA_DIR, "included-openjdk-17-windows-x64-part2");
        Path includedJdk17Path = Path.of(PATH_APP_INCLUDED_JDK_DEST);
        try {
            FileSystemUtils.copyDirContent(extractedZipPart1, includedJdk17Path, true);
            FileSystemUtils.copyDirContent(extractedZipPart2, includedJdk17Path, true);
            FileSystemUtils.deleteDir(extractedZipPart1, false);
            FileSystemUtils.deleteDir(extractedZipPart2, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not combine the extracted parts of the JDK 17.");
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({"UnusedReturnValue", "ResultOfMethodCallIgnored"})
    public static String createDirectoryForTheIncludedJdk17() {
        File tmpFileForCreatingDirectory = new File(PATH_APP_INCLUDED_JDK_DEST);
        if (!tmpFileForCreatingDirectory.exists()) {
            tmpFileForCreatingDirectory.mkdirs();
        }
        String directoryPath = PATH_APP_INCLUDED_JDK_DEST;
        System.out.println("Directory for the included jdk=" + directoryPath);
        return directoryPath;
    }

    @SuppressWarnings({"UnusedReturnValue", "ResultOfMethodCallIgnored"})
    public static String createDirectoryForTheAppTmpData() {
        File tmpFileForCreatingDirectory = new File(PATH_APP_TMP_DATA_DIR);
        if (!tmpFileForCreatingDirectory.exists()) {
            tmpFileForCreatingDirectory.mkdirs();
        }
        String directoryPath = PATH_APP_TMP_DATA_DIR;
        System.out.println("Directory for handling application related files=" + directoryPath);
        return directoryPath;
    }

}
