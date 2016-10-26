package org.sbx.factories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.enums.Mode;
import org.sbx.interfaces.RecordDAO;

/**
 * Created by aloginov on 20.10.16.
 */
public class DMFactory {

    private static final Logger logger = LogManager.getLogger(DMFactory.class);
    private RecordDAO dmManager;
    private Class<RecordDAO> managerClass;

    public void setManagerClass(Class clazz){
            initDataManager(clazz);
    }

    private void initDataManager(Class<RecordDAO> clazz){

        try {
            dmManager = clazz.newInstance();
        } catch (IllegalAccessException ex){
            logger.fatal(ex);
        } catch (InstantiationException ex){
            logger.fatal(ex);
        }

    }

    public RecordDAO getManager(Mode mode){
        this.dmManager.setMode(mode);

        return dmManager;
    }
}
