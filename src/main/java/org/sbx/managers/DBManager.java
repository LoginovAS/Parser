package org.sbx.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sbx.enums.Mode;
import org.sbx.interfaces.DataManager;
import org.sbx.objects.DBRecord;
import org.sbx.objects.ESK363DBRecord;
import org.sbx.objects.Record;
import org.sbx.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aloginov on 20.10.16.
 */
public class DBManager extends DataManager {
    private SessionFactory sessionFactory;
    private Session session = null;
    private Transaction tx = null;

    public void setMode(Mode mode) {

    }

    public void initConnection() {
        if (this.session == null)
            this.session = HibernateUtil.getSessionFactory().openSession();
        if (this.session != null)
            this.tx = session.beginTransaction();
    }

    public void saveData(Record record) {
        if (session != null)
            if (tx != null) {
                session.save(record);
            }
    }

    public void loadData() {

    }

    public void closeConnection(){
        if (session.isOpen())
            session.close();
    }

    public List<String> getDataList() {

        List<String> records = new ArrayList<String>();
        records = session.createCriteria(Record.class).list();

        return records;
    }
}
