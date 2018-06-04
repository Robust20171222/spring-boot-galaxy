package com.galaxy.ftp;

import javafx.scene.shape.Line;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Ftp operation test
 */
public class FTPUploadFileDemo {

    String server = "server-1";
    int port = 21;
    String user = "root";
    String pass = "HandleData2017";
    FTPClient ftpClient = null;

    @Before
    public void init() {
        ftpClient = new FTPClient();
    }

    @Test
    public void testUpload() {
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("/Users/pengwang/Documents/TestProject/apache-tomcat-6.0.44.tar.gz");

            String firstRemoteFile = "apache-tomcat-6.0.44.tar.gz";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }

            // APPROACH #2: uploads second file using an OutputStream
            File secondLocalFile = new File("/Users/pengwang/Documents/TestProject/apache-tomcat-8.0.26.tar.gz");
            String secondRemoteFile = "apache-tomcat-8.0.26.tar.gz";
            inputStream = new FileInputStream(secondLocalFile);

            System.out.println("Start uploading second file");
            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0;

            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();

            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @see http://www.codejava.net/java-se/networking/ftp/creating-nested-directory-structure-on-a-ftp-server
     */
    @Test
    public void testMakeNestedDirectory() {
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            // use local passive mode to pass firewall
            ftpClient.enterLocalPassiveMode();

            System.out.println("Connected");

            String dirPath = "/home/java/ftp2";

            FTPUtil.makeDirectories(ftpClient, dirPath);

            // log out and disconnect from the server
            ftpClient.logout();
            ftpClient.disconnect();

            System.out.println("Disconnected");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testAppendStringToFile() {
        // File location.
        String filePath = "/Users/pengwang/Documents/TestProject/aone/test2/file.txt";

        // Content to append.
        String contentToAppend = "\nThis发的说法是的 line was added at the end of the file !";

        try {
            Path path = Paths.get(filePath);
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }

            Files.write(Paths.get(filePath), contentToAppend.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Problem occurs when deleting the directory : " + ExceptionUtils.getMessage(e));
            e.printStackTrace();
        }
    }

    @Test
    public void FileWriteUTF8Method() {
        try {
            // File location.
            String filePath = "/Users/pengwang/Documents/TestProject/aone/test2/file.txt";
            // Content to append.
            String contentToAppend = "\nThis发的说法是的 line was added at the end of the file !";

            Path path = Paths.get(filePath);
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }

            BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
            writer.write(contentToAppend);
            writer.flush();
            writer.close();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

}