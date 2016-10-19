package org.sbx.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.messages.ApplicationDebugMessages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aloginov on 19.10.16.
 */
public class Parser {

    private static final Logger logger = LogManager.getLogger(Parser.class);
    public Date getDateByString(String stringDate){
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss.SSS");
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException ex){
            logger.error(ApplicationDebugMessages.CANNOT_PARSE_DATE.getMessage(), stringDate);
        }

        return date;
    }
}
