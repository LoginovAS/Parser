package org.sbx.builders;

import org.sbx.interfaces.Buildable;
import org.sbx.interfaces.Builder;
import org.sbx.objects.ESK363LogRecord;
import org.sbx.objects.LogRecord;

import java.util.Date;

/**
 * Created by aloginov on 20.10.16.
 */
public class ESK363RecordBuilder implements Builder {

    private ESK363LogRecord logRecord;
    private Date date;
    private int itemCount;
    private String logLevel;

    public ESK363RecordBuilder(){
        this.logRecord = new ESK363LogRecord();
    }

    public void addDate(Date date){
        logRecord.setDate(date);
    }

    public void addItemCount(int itemCount){
        logRecord.setItemCount(itemCount);
    }

    public void setLogLevel(String logLevel){
        logRecord.setLogLevel(logLevel);
    }

    public ESK363LogRecord build() {
        return logRecord;
    }
}
