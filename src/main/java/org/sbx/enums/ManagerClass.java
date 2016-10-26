package org.sbx.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by aloginov on 20.10.16.
 */
public enum ManagerClass {

    DATABASE("org.sbx.DAO.DBRecordDAO"),
    FILE("org.sbx.DAO.FileDAO");

    private String className;
    private final Logger logger = LogManager.getLogger(ManagerClass.class);
    ManagerClass(String className){
        this.className = className;
    }
    public String getClassName(){
        return className;
    }
}
