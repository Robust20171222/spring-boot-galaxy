


/**
 *
 */
package com.galaxy.util;

/**
 * @author SMunikuntla
 */
public class FTPAuthenticationException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 6864463970227476719L;

    /**
     *
     */
    public FTPAuthenticationException() {
    }

    /**
     * @param message
     */
    public FTPAuthenticationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public FTPAuthenticationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public FTPAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

}
