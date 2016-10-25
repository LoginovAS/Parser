package org.sbx.factories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.enums.ManagerClass;
import org.sbx.enums.Mode;
import org.sbx.interfaces.DataManager;
import org.sbx.managers.DBManager;
import org.sbx.managers.FileManager;

/**
 * Created by aloginov on 20.10.16.
 */
public class DMFactory {

    private static final Logger logger = LogManager.getLogger(DMFactory.class);
    private DataManager dataManager;
    private Class<DataManager> managerClass;

    public void setManagerClass(Class clazz){
            initDataManager(clazz);
    }

    private void initDataManager(Class<DataManager> clazz){

        try {
            dataManager = clazz.newInstance();
        } catch (IllegalAccessException ex){
            logger.fatal(ex);
        } catch (InstantiationException ex){
            logger.fatal(ex);
        }

    }

    public DataManager getManager(Mode mode){
        this.dataManager.setMode(mode);

        return dataManager;
    }
}
