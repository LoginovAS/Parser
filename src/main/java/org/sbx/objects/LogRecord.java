package org.sbx.objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by aloginov on 20.10.16.
 */
public abstract class LogRecord {

    private static final Logger logger = LogManager.getLogger(LogRecord.class);

    protected int id;
    protected Date date;
    protected String logLevel;

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        logger.trace("Date set " + date);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
