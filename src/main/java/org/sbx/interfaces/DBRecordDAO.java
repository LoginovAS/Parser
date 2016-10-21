package org.sbx.interfaces;

import org.sbx.objects.DBRecord;

import java.sql.SQLException;

/**
 * Created by aloginov on 21.10.16.
 */
public interface DBRecordDAO {

    public void addDBRecord(DBRecord dbRecord) throws SQLException;

    public void updateDBRecord(DBRecord dbRecord) throws SQLException;

    public void deleteDBRecord() throws SQLException;

    public DBRecord getDBRecord() throws SQLException;

}
