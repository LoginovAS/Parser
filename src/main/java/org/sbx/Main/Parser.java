package org.sbx.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.messages.ApplicationDebugMessages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aloginov on 19.10.16.
 */
public class Parser {

    private String targetString;
    private String regExp;
    private List<String> list;

    private static final Logger logger = LogManager.getLogger(Parser.class);
    public Date getDateByString(String stringDate, String format){
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(format); //MM.dd.yyyy HH:mm:ss.SSS
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException ex){
            logger.error(ApplicationDebugMessages.CANNOT_PARSE_DATE.getMessage(), stringDate);
        }

        return date;
    }

    public void setTargetString(String targetString){
        this.targetString = targetString;
    }

    public void setRegExp(String regExp){
        this.regExp = regExp;
    }

    public Date getDate(String format){
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(format); //MM.dd.yyyy HH:mm:ss.SSS
        String stringDate = list.get(0);
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException ex){
            logger.error(ApplicationDebugMessages.CANNOT_PARSE_DATE.getMessage(), stringDate);
        }

        return date;
    }

    public String getLogLevel(){
        return list.get(9);
    }

    public int getItemCount(){
        return Integer.parseInt(list.get(11));
    }

    public void parse(){
        this.list = new ArrayList<String>();
        final Matcher matcher = Pattern.compile(regExp).matcher(targetString);
        if (matcher.find()){
            for (int i = 1; i <= matcher.groupCount(); i++)
                list.add(matcher.group(i));
        }
    }
}
