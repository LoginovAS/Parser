package org.sbx.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.managers.DataManager;
import org.sbx.messages.ApplicationInfoMessages;
import org.sbx.objects.ESK363DBRecord;


/**
 * Created by aloginov on 19.10.16.
 */
public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args){
        logger.debug(ApplicationInfoMessages.STARTING_MESSAGE);

        DataManager dataManager = new DataManager();

        dataManager.loadToDB("esk363");

        for (ESK363DBRecord record: dataManager.getByDateRange("10.14.2016 17:35:26", "10.14.2016 17:35:42"))
            logger.info(record);

        logger.debug(ApplicationInfoMessages.FINISHING_MESSAGE);
    }
}
