package com.galaxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by wangpeng
 * Date: 2018/5/29
 * Time: 15:32
 */
public class HostNameUtil {

    private static final Logger log = LogManager.getLogger(HostNameUtil.class);

    public static String getLocalHostAddress() throws UnknownHostException {
        try {
            String hostAddress = getLocalHostLANAddress().getHostAddress();
            if (log.isDebugEnabled()) {
                log.debug("hostAddress = [" + hostAddress + "]");
            }

            return hostAddress;
        } catch (UnknownHostException var1) {
            log.error("Couldn't get localhost address", var1);
            throw var1;
        }
    }

    public static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            Enumeration ifaces = NetworkInterface.getNetworkInterfaces();

            while(ifaces.hasMoreElements()) {
                NetworkInterface iface = (NetworkInterface)ifaces.nextElement();
                Enumeration inetAddrs = iface.getInetAddresses();

                while(inetAddrs.hasMoreElements()) {
                    InetAddress inetAddr = (InetAddress)inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            return inetAddr;
                        }

                        if (candidateAddress == null) {
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }

            if (candidateAddress != null) {
                return candidateAddress;
            } else {
                InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
                if (jdkSuppliedAddress == null) {
                    throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
                } else {
                    return jdkSuppliedAddress;
                }
            }
        } catch (Exception var5) {
            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + var5);
            unknownHostException.initCause(var5);
            throw unknownHostException;
        }
    }
}
