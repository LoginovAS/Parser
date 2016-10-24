package org.sbx.builders;

import org.sbx.interfaces.Builder;
import org.sbx.objects.ESK363DBRecord;

import java.util.Date;

/**
 * Created by aloginov on 20.10.16.
 */
public class ESK363RecordBuilder implements Builder {

    private ESK363DBRecord dbRecord;
    private Date date;
    private int itemCount;
    private String logLevel;

    public ESK363RecordBuilder(){
        this.dbRecord = new ESK363DBRecord();
    }

    public void addDate(Date date){
        dbRecord.setDate(date);
    }

    public void addItemCount(int itemCount){
        dbRecord.setItemCount(itemCount);
    }

    public void setLogLevel(String logLevel){
        dbRecord.setLogLevel(logLevel);
    }

    public ESK363DBRecord build() {
        return dbRecord;
    }
}
