package org.sbx.interfaces;

import org.sbx.enums.Mode;

import java.util.List;

/**
 * Created by aloginov on 20.10.16.
 */
public interface DataManager {

    public void setMode(Mode mode);

    public void initConnection();

    public void saveData();

    public void loadData();

    public List<String> getDataList();
}
