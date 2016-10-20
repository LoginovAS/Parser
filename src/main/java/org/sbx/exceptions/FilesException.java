package org.sbx.exceptions;

import org.sbx.interfaces.LoggingMessenger;

import java.io.IOException;

/**
 * Created by aloginov on 19.10.16.
 */
public class FilesException extends IOException {

    private LoggingMessenger loggingMessenger;

    public FilesException(){
        super();
    }

    public FilesException(String msg){
        super(msg);
    }

    public FilesException(LoggingMessenger loggingMessenger){
        this.loggingMessenger = loggingMessenger;
    }

    public String getErrorMessage(){
        return loggingMessenger.getMessage();
    }
}
