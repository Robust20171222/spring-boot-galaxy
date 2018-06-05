package com.galaxy.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Created by wangpeng
 * Date: 2018/6/5
 * Time: 22:11
 */
public class FTPUtil {

    private static Logger log = LogManager.getLogger(FTPUtil.class);

    private static final String FTP_SERVER = "localhost"; // FTP 服务器地址
    private static final String FTP_SERVER_PORT = "21"; // FTP 服务器端口
    private static final String FTP_SERVER_USER_NAME = "admin"; // FTP 服务器用户名
    private static final String FTP_SERVER_PASSWORD = "admin"; // FTP 服务器密码
    private static final String FTP_SERVER_REMOTE_PATH = "/home/image/";  // FTP 服务器上保存文件的目录
    private static FTPClient ftpClient;

    /**
     * 验证连接FTP服务器的参数是否合法，如非空验证
     *
     * @throws InvalidFTPParamsException
     */
    private static void validate() throws InvalidFTPParamsException {
        if (StringUtils.isEmpty(FTP_SERVER)
                || StringUtils.isEmpty(FTP_SERVER_USER_NAME)
                || StringUtils.isEmpty(FTP_SERVER_PASSWORD)) {
            throw new InvalidFTPParamsException("Invalid FTP Connection Params");
        }
    }

    /**
     * 连接FTP服务器
     *
     * @throws InvalidFTPParamsException
     */
    private static void connect() throws InvalidFTPParamsException {
        validate();
        ftpClient = new FTPClient();
        try {
            int reply;
            // 如果端口号合法，则用FTP服务器地址+端口号的形式连接
            if (StringUtils.isNumeric(FTP_SERVER_PORT)
                    && Integer.parseInt(FTP_SERVER_PORT) > 0) {
                ftpClient.connect(FTP_SERVER, Integer.parseInt(FTP_SERVER_PORT));
            } else {
                ftpClient.connect(FTP_SERVER);
            }
            System.out.println("Connected to " + FTP_SERVER + " on "
                    + ftpClient.getRemotePort());

            // 获取连接返回值
            reply = ftpClient.getReplyCode();

            // 根据返回值来判断连接是否成功，不成功则断开连接
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
            }
        } catch (IOException e) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException f) {
                    log.error(e.getMessage(), e);
                }
            }
            System.err.println("Could not connect to FTP server.");
            e.printStackTrace();
        }
    }

    /**
     * 使用用户名、密码登录FTP服务器
     *
     * @throws FTPAuthenticationException
     */
    private static void login() throws FTPAuthenticationException {
        try {
            if (StringUtils.isNotEmpty(FTP_SERVER_USER_NAME) && StringUtils.isNotEmpty(FTP_SERVER_PASSWORD)) {
                if (!ftpClient.login(FTP_SERVER_USER_NAME, FTP_SERVER_PASSWORD)) {
                    ftpClient.logout();
                    throw new FTPAuthenticationException(
                            "Credentials failed to Authenticate on the FTP server");
                }
                System.out.println("Remote system is " + ftpClient.getSystemType());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 上传文件到FTP服务器
     *
     * @param localFile 要上传的本地文件
     * @return 文件在远程FTP服务器上保存的路径
     */
    public static String uploadFile(File localFile) {
        String remote = null;
        boolean uploadComplete = false;
        try {
            connect();
            login();

            // 设置以二进制形式传输文件
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 设置被动模式
            ftpClient.enterLocalPassiveMode();
            ftpClient.setUseEPSVwithIPv4(false);

            InputStream input = new FileInputStream(localFile);

            remote = FTP_SERVER_REMOTE_PATH + localFile.getName();

            // 切换工作目录，如果切换不成功，则创建目录
            boolean result = ftpClient.changeWorkingDirectory(FTP_SERVER_REMOTE_PATH);
            if (!result) {
                ftpClient.makeDirectory(FTP_SERVER_REMOTE_PATH);
            }

            // 保存文件到FTP服务器
            uploadComplete = ftpClient.storeFile(remote, input);

            input.close();
        } catch (InvalidFTPParamsException e) {
            log.error("Invalid FTP Params to connect");
        } catch (FTPAuthenticationException e) {
            log.error(e.getMessage());
        } catch (FileNotFoundException e) {
            log.error("Not able to load/read the localFile");
        } catch (IOException e) {
            log.error("IOException during FTP upload process");
        } finally {
            // 如果上传出现异常，则返回空路径
            if (!uploadComplete) {
                remote = null;
            }
        }

        return remote;
    }

    /**
     * 下载文件
     *
     * @param filename 要下载的文件名称
     * @param location 文件在FTP服务器上的保存路径
     * @return
     */
    public static InputStream downloadFileStream(String filename, String location) {
        InputStream inputStream = null;
        try {
            String remote = ((StringUtils.isNotEmpty(location)) ? location : FTP_SERVER_REMOTE_PATH) + filename;
            connect();
            login();

            // 设置以二进制形式传输文件
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // Use passive mode as default because most of us are
            // behind firewalls these days.
            ftpClient.enterLocalPassiveMode();
            ftpClient.setUseEPSVwithIPv4(false);

            inputStream = ftpClient.retrieveFileStream(remote);
        } catch (InvalidFTPParamsException e) {
            log.error("Invalid FTP Params to connect");
        } catch (FTPAuthenticationException e) {
            log.error(e.getMessage());
        } catch (FileNotFoundException e) {
            log.error("Not able to load/read the localFile");
        } catch (IOException e) {
            log.error("IOException during FTP upload process");
        }

        return inputStream;
    }

    /**
     * 下载文件
     *
     * @param filename 要下载的文件名称
     * @return 下载文件输入流
     */
    public static InputStream downloadFile(String filename) {
        return downloadFileStream(filename, null);
    }

    /**
     * 删除文件
     *
     * @param filename 要删除的文件名称
     * @return true为成功，false为失败
     */
    public static boolean deleteFile(String filename) {
        return deleteFile(filename, null);
    }

    /**
     * 删除文件
     *
     * @param filename 要删除的文件名称
     * @param location 要删除文件在FTP服务器上的路径
     * @return true为成功，false为失败
     */
    public static boolean deleteFile(String filename, String location) {
        String remote = null;
        boolean ret = false;
        try {
            remote = ((StringUtils.isNotEmpty(location)) ? location : FTP_SERVER_REMOTE_PATH) + filename;
            connect();
            login();

            ret = ftpClient.deleteFile(remote);
        } catch (InvalidFTPParamsException e) {
            log.info("No FTP Params to connect");
        } catch (FTPAuthenticationException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error("IOException during FTP delete process for " + remote);
        }
        return ret;
    }
}
