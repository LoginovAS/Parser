package org.sbx.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.DAO.DBRecordDAO;
import org.sbx.DAO.FileDAO;
import org.sbx.Main.Parser;
import org.sbx.builders.ESK363RecordBuilder;
import org.sbx.enums.ManagerClass;
import org.sbx.enums.Mode;
import org.sbx.factories.DMFactory;
import org.sbx.objects.DBRecord;
import org.sbx.objects.ESK363DBRecord;
import org.sbx.service.StringService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aloginov on 26.10.16.
 */
public class DataManager {

    private static final Logger logger = LogManager.getLogger(DataManager.class);

    private DMFactory dmFactory;
    private DBRecordDAO dbRecordDAO;

    public void loadToDB(String jobName){

        Date date = null;
        Date tmpDate = null;
        Date mainDate = null;
        int itemCount = 0;
        int tmpIC = 0;
        String logLevel;
        String tmpLL = "";

        dmFactory  = new DMFactory();
        List<String> names = new ArrayList<String>();
        ESK363DBRecord esk363DBRecord = null;

        try {
            dmFactory.setManagerClass(Class.forName(ManagerClass.FILE.getClassName()));
        } catch (ClassNotFoundException ex){
            logger.fatal(ex);
        }

        FileDAO fileDAO = (FileDAO) dmFactory.getManager(Mode.READ);
        fileDAO.setMode(Mode.READ);

        ESK363RecordBuilder esk363RecordBuilder;
        ArrayList<ESK363DBRecord> esk363DBRecords = new ArrayList<ESK363DBRecord>();

        for (String name: fileDAO.getLogNames("/home/aloginov/private/java/Parser/files")){
            if (name.contains("/" + jobName)){
                fileDAO.setFile(name);
                fileDAO.initConnection();
                fileDAO.loadData();
                fileDAO.closeConnection();
            }
        }

        List<String> list = fileDAO.getDataList();

        Parser parser = new Parser();
        dmFactory = new DMFactory();

        try {
            dmFactory.setManagerClass(Class.forName(ManagerClass.DATABASE.getClassName()));
        } catch (ClassNotFoundException ex){
            logger.fatal(ex);
        }

        dbRecordDAO = (DBRecordDAO) dmFactory.getManager(Mode.READ);

        dbRecordDAO.initConnection();

        parser.setRegExp(StringService.getExecutionListenerRegExp());
        for (int i = 0; i<list.size(); i++){
            parser.setTargetString(list.get(i));

            parser.parse();
            esk363RecordBuilder = new ESK363RecordBuilder();
            date = parser.getDate("MM.dd.yyyy HH:mm:ss");

            if (date == null) continue;

            if (tmpDate != null)
                if (date.equals(tmpDate)) {
                    tmpIC += parser.getItemCount();
                    continue;
                } else {
                    mainDate = tmpDate;
                    logLevel = tmpLL;
                    itemCount = tmpIC;

                    tmpDate = date;
                    tmpLL = parser.getLogLevel();
                    tmpIC = parser.getItemCount();
                }
            else {
                tmpDate = date;
                tmpLL = parser.getLogLevel();
                tmpIC = parser.getItemCount();
                continue;
            }

            esk363RecordBuilder.addDate(mainDate);
            esk363RecordBuilder.setLogLevel(logLevel);
            esk363RecordBuilder.addItemCount(itemCount);
            esk363DBRecord = esk363RecordBuilder.build();

            dbRecordDAO.saveData(esk363DBRecord);
        }

        dbRecordDAO.closeConnection();
    }

    public List<ESK363DBRecord> getByDateRange(String dateFrom, String dateTo){
        dmFactory = new DMFactory();

        try {
            dmFactory.setManagerClass(Class.forName(ManagerClass.DATABASE.getClassName()));
        } catch (ClassNotFoundException ex){
            logger.fatal(ex);
        }

        dbRecordDAO = (DBRecordDAO) dmFactory.getManager(Mode.READ);
        dbRecordDAO.initConnection();

        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
        Date startDate = null;
        Date endDate = null;

        try {
            startDate = dateFormat.parse(dateFrom);
            endDate = dateFormat.parse(dateTo);
        } catch (ParseException ex){
            logger.error(ex);
        }

        List recordList = null;

        if ((startDate != null) && (endDate != null))
            recordList = dbRecordDAO.getDateRange(startDate, endDate);

        dbRecordDAO.closeConnection();

        return recordList;
    }
}
