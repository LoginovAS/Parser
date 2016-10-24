package org.sbx.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.builders.ESK363RecordBuilder;
import org.sbx.enums.ManagerClass;
import org.sbx.enums.Mode;
import org.sbx.factories.DMFactory;
import org.sbx.managers.DBManager;
import org.sbx.managers.FileManager;
import org.sbx.messages.ApplicationInfoMessages;
import org.sbx.objects.ESK363DBRecord;
import org.sbx.service.StringService;

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
        ESK363DBRecord esk363DBRecord = null;
        DMFactory dmFactory = new DMFactory();
        try {
            dmFactory.setManagerClass(Class.forName(ManagerClass.FILE.getClassName()));
        } catch (ClassNotFoundException ex){
            logger.fatal(ex);
        }

        FileManager fileManager = (FileManager) dmFactory.getManager(Mode.READ);
        ESK363RecordBuilder esk363RecordBuilder;
        ArrayList<ESK363DBRecord> esk363DBRecords = new ArrayList<ESK363DBRecord>();

        List<String> list;

        fileManager.setMode(Mode.READ);
        fileManager.setFile(fileName);
        fileManager.initConnection();
        fileManager.loadData();
        list = fileManager.getDataList();

        Parser parser = new Parser();
        dmFactory = new DMFactory();
        try {
            dmFactory.setManagerClass(Class.forName(ManagerClass.DATABASE.getClassName()));
        } catch (ClassNotFoundException ex){
            logger.fatal(ex);
        }

        DBManager dbManager = (DBManager) dmFactory.getManager(Mode.READ);

        dbManager.initConnection();


        for (int i = 1; i<list.size(); i++){
            parser.setTargetString(list.get(i));
            parser.setRegExp(StringService.getExecutionListenerRegExp());
            parser.parse();
            esk363RecordBuilder = new ESK363RecordBuilder();
            if (parser.getDate("MM.dd.yyyy HH:mm:ss.SSS") != null){
                esk363RecordBuilder.addDate(parser.getDate("MM.dd.yyyy HH:mm:ss.SSS"));
                esk363RecordBuilder.addItemCount(parser.getItemCount());
                esk363RecordBuilder.setLogLevel(parser.getLogLevel());
                esk363DBRecord = esk363RecordBuilder.build();
            }

            if (esk363DBRecord != null)
                dbManager.saveData(esk363DBRecord);

        }

        dbManager.closeConnection();
        logger.debug(ApplicationInfoMessages.FINISHING_MESSAGE);
    }
}
