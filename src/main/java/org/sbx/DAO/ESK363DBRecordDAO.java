package org.sbx.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sbx.interfaces.DBRecordDAO;
import org.sbx.objects.DBRecord;
import org.sbx.objects.ESK363DBRecord;
import org.sbx.utils.HibernateUtil;

import java.sql.SQLException;

/**
 * Created by aloginov on 21.10.16.
 */
public class ESK363DBRecordDAO implements DBRecordDAO {
    public void addDBRecord(DBRecord dbRecord) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(dbRecord);
        tx.commit();
        session.close();
    }

    public void updateDBRecord(DBRecord dbRecord) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(dbRecord);
        tx.commit();
        session.close();
    }

    public void deleteDBRecord() throws SQLException {

    }

    public ESK363DBRecord getDBRecord() throws SQLException {
        return null;
    }
}
