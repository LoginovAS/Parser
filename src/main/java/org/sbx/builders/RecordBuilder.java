package org.sbx.builders;

import org.sbx.interfaces.Buildable;
import org.sbx.interfaces.Builder;
import org.sbx.objects.LogRecord;

import java.util.Date;

/**
 * Created by aloginov on 20.10.16.
 */
public class RecordBuilder implements Builder {

    private LogRecord logRecord;
    private Date date;
    private int itemCount;
    private String logLevel;

    public void setState(){
        logRecord.setDate(date);
        logRecord.setItemsCount(itemCount);
        logRecord.setLogLevel(logLevel);
    }

    public LogRecord build() {
        return logRecord;
    }
}
