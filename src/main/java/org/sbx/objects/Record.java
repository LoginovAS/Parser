package org.sbx.objects;

import java.util.Date;
import java.util.Observable;

/**
 * Created by aloginov on 20.10.16.
 */
public abstract class Record extends Observable {

    protected Date date;
    protected String logLevel;
    protected int itemsCount;
    protected boolean isChanged;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    protected void change(){
        if (isChanged == false)
            isChanged = true;
    }

    protected void saved(){
        if (isChanged == true){
            isChanged = false;
        }
    }

    public abstract void save();

    public abstract void load();
}
