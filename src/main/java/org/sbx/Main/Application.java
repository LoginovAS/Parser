package org.sbx.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.exceptions.FilesException;
import org.sbx.managers.FileManager;
import org.sbx.messages.ApplicationDebugMessages;
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

        FileManager fileManager = new FileManager();
        List<String> list = new ArrayList<String>();

        try {
            fileManager.initFile(fileName);
        } catch (FilesException ex){
            logger.error(ex.getErrorMessage(), fileName);
            System.exit(1);
        }

            fileManager.openFileForInput();
            fileManager.readFileToList(list);

        if (list.size() > 0)
            logger.info(list.size());

        logger.debug(ApplicationInfoMessages.FINISHING_MESSAGE);
    }
}
