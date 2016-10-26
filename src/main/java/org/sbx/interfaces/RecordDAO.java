package org.sbx.interfaces;

import org.sbx.enums.Mode;
import org.sbx.objects.LogRecord;

import java.util.List;

/**
 * Created by aloginov on 20.10.16.
 */
public abstract class RecordDAO {

    public void setMode(Mode mode) {}

    public void initConnection() {}

    public void saveData(LogRecord record) {}

    public void loadData() {}

    public void closeConnection() {}

    public abstract List<String> getDataList();
}
