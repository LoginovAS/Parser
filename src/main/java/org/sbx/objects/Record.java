package org.sbx.objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Observable;

/**
 * Created by aloginov on 20.10.16.
 */
public abstract class Record extends Observable {

    private static final Logger logger = LogManager.getLogger(Record.class);

    protected int id;
    protected Date date;
    protected boolean isChanged;
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

    public void change(){
        if (isChanged == false) {
            isChanged = true;
            setChanged();
            notifyObservers();
            logger.trace("isChanged flag set " + isChanged);
        }

    }
}
