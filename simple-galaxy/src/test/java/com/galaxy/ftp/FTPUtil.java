package com.galaxy.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.IOException;

/**
 * This utility class provides a method that creates a nested directory
 * structure on a FTP server, based on Apache Commons Net library.
 * @author www.codejava.net
 *
 */
public class FTPUtil {
    /**
     * Creates a nested directory structure on a FTP server
     * @param ftpClient an instance of org.apache.commons.net.ftp.FTPClient class.
     * @param dirPath Path of the directory, i.e /projects/java/ftp/demo
     * @return true if the directory was created successfully, false otherwise
     * @throws IOException if any error occurred during client-server communication
     */
    public static boolean makeDirectories(FTPClient ftpClient, String dirPath)
            throws IOException {
        String[] pathElements = dirPath.split(File.pathSeparator);
        if (pathElements != null && pathElements.length > 0) {
            for (String singleDir : pathElements) {
                boolean existed = ftpClient.changeWorkingDirectory(singleDir);
                if (!existed) {
                    boolean created = ftpClient.makeDirectory(singleDir);
                    if (created) {
                        System.out.println("CREATED directory: " + singleDir);
                        ftpClient.changeWorkingDirectory(singleDir);
                    } else {
                        System.out.println("COULD NOT create directory: " + singleDir);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}