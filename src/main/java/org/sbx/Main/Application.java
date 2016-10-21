package org.sbx.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.builders.ESK363RecordBuilder;
import org.sbx.enums.ManagerClass;
import org.sbx.enums.Mode;
import org.sbx.factories.DMFactory;
import org.sbx.interfaces.DataManager;
import org.sbx.managers.DBManager;
import org.sbx.managers.FileManager;
import org.sbx.messages.ApplicationInfoMessages;
import org.sbx.objects.ESK363LogRecord;
import org.sbx.service.StringService;

import java.io.File;
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
        ESK363LogRecord esk363LogRecord;
        DMFactory dmFactory = new DMFactory();
        try {
            dmFactory.setManagerClass(Class.forName(ManagerClass.FILE.getClassName()));
        } catch (ClassNotFoundException ex){
            logger.fatal(ex);
        }

        FileManager fileManager = (FileManager) dmFactory.getManager(Mode.READ);
        ESK363RecordBuilder esk363RecordBuilder;
        ArrayList<ESK363LogRecord> esk363LogRecords = new ArrayList<ESK363LogRecord>();

        List<String> list;

        fileManager.setMode(Mode.READ);
        fileManager.setFile(fileName);
        fileManager.initConnection();
        fileManager.loadData();
        list = fileManager.getDataList();

        Parser parser = new Parser();

        for (int i = 1; i<list.size(); i++){
            parser.setTargetString(list.get(i));
            parser.setRegExp(StringService.getExecutionListenerRegExp());
            parser.parse();
            esk363RecordBuilder = new ESK363RecordBuilder();
            esk363RecordBuilder.addDate(parser.getDate("MM.dd.yyyy HH:mm:ss.SSS"));
            esk363RecordBuilder.addItemCount(parser.getItemCount());
            esk363RecordBuilder.setLogLevel(parser.getLogLevel());
            esk363LogRecord = esk363RecordBuilder.build();

            dmFactory = new DMFactory();
            try {
                dmFactory.setManagerClass(Class.forName(ManagerClass.DATABASE.getClassName()));
            } catch (ClassNotFoundException ex){
                logger.fatal(ex);
            }

            DBManager dbManager = (DBManager) dmFactory.getManager(Mode.READ);

            dbManager.initConnection();
            dbManager.saveData(esk363LogRecord);
            dbManager.closeConnection();

            System.out.println(parser.getDate("MM.dd.yyyy HH:mm:ss.SSS") + " - " + parser.getLogLevel() + " - " + parser.getItemCount());
        }


        logger.debug(ApplicationInfoMessages.FINISHING_MESSAGE);
    }
}
