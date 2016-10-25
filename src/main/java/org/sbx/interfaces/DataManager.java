package org.sbx.interfaces;

import org.sbx.enums.Mode;
import org.sbx.objects.Record;

import java.util.List;

/**
 * Created by aloginov on 20.10.16.
 */
public abstract class DataManager {

    public void setMode(Mode mode) {}

    public void initConnection() {}

    public void saveData(Record record) {}

    public void loadData() {}

    public void closeConnection() {}

    public abstract List<String> getDataList();
}
