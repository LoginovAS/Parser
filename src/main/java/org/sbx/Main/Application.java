package org.sbx.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.enums.ManagerClass;
import org.sbx.enums.Mode;
import org.sbx.exceptions.FilesException;
import org.sbx.factories.DMFactory;
import org.sbx.interfaces.DataManager;
import org.sbx.managers.FileManager;
import org.sbx.messages.ApplicationInfoMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aloginov on 19.10.16.
 */
public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args){
        logger.debug(ApplicationInfoMessages.STARTING_MESSAGE);

        String fileName = "/home/aloginov/workarea/downloads/esk363-16-10-09-05-06-35.log";

        DMFactory dmFactory = new DMFactory();
        try {
            dmFactory.setManagerClass(Class.forName(ManagerClass.FILE.getClassName()));
        } catch (ClassNotFoundException ex){
            logger.fatal(ex);
        }


        DataManager fileManager = dmFactory.makeManager(Mode.READ);
        List<String> list;

            fileManager.setMode(Mode.READ);
            fileManager.initConnection();
            fileManager.loadData();
            list = fileManager.getDataList();

        if (list.size() > 0)
            logger.info(list.size());

        logger.debug(ApplicationInfoMessages.FINISHING_MESSAGE);
    }
}
