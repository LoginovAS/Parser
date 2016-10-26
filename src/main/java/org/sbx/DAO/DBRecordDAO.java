package org.sbx.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.sbx.enums.EnumDateFormat;
import org.sbx.enums.Mode;
import org.sbx.interfaces.RecordDAO;
import org.sbx.objects.ESK363DBRecord;
import org.sbx.objects.LogRecord;
import org.sbx.utils.HibernateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aloginov on 20.10.16.
 */
public class DBRecordDAO extends RecordDAO {

    private static final Logger logger = LogManager.getLogger(DBRecordDAO.class);
    private Session session = null;
    private Transaction tx = null;

    public void setMode(Mode mode) {

    }

    public void initConnection() {
        if ((this.session == null) || (!this.session.isOpen()))
            this.session = HibernateUtil.getSessionFactory().openSession();
        if (this.session != null)
            if (this.session.isOpen())
                this.tx = session.beginTransaction();
    }

    public void saveData(LogRecord record) {
        if (session != null)
            if (tx != null) {
                session.save(record);
            }
    }

    public void loadData() {

    }

    public void closeConnection(){
        if (tx != null){
            tx.commit();
            if (session.isOpen())
                session.close();
        }
    }

    public List<String> getDataList() {

        List<String> records = new ArrayList<String>();
        records = session.createCriteria(LogRecord.class).list();

        return records;
    }

    public List<ESK363DBRecord> getDateRange(Date dateFrom, Date dateTo){
        if (session != null){

            Date startDate = null;
            Date endDate = null;
            EnumDateFormat dateFormat = EnumDateFormat.DATABASE;
            DateFormat format = new SimpleDateFormat(dateFormat.getFormat());

            try{
                startDate = format.parse(format.format(dateFrom));
                endDate = format.parse(format.format(dateTo));
            } catch (ParseException ex) {
                logger.error(ex);
            }

            Criteria criteria = null;

            if ((startDate != null) && (endDate != null)){
                criteria = session.createCriteria(ESK363DBRecord.class).
                        add(Restrictions.between("date", dateFrom, dateTo));
            }

            if (criteria != null)
                return criteria.list();
        }

        return null;
    }
}
