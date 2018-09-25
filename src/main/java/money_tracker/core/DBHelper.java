package money_tracker.core;

import money_tracker.api.behaviours.IDB;
import money_tracker.db.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DBHelper {

    protected static Session session;
    protected static Transaction transaction;

    public static void save(Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try{transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete (Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    protected static <T> void deleteAll(Class<T> searchingClass){
        List<T> allItems = getAll(searchingClass);
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            for(T item : allItems){
                session.delete(item);
            }
            transaction.commit();
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    protected static <T extends Object> List<T> getAll(Class<T> searchingClass){
        List<T> results = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria cr = session.createCriteria(searchingClass);
            results = cr.list();
        } catch(Throwable e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    protected static <T extends Object> T find(int id, Class<T> searchingClass){
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;

        try {
            Criteria cr = session.createCriteria(searchingClass);
            cr.add(Restrictions.eq("id", id));
            result = (T) cr.uniqueResult();
        } catch(Throwable e){
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    protected static <T extends Object> List<T> orderByCriterion(String columnName, Class<T> searchingClass, boolean isAscending){
        List<T> results = null;
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria cr = session.createCriteria(searchingClass);
            if(isAscending){
                cr.addOrder(Order.asc(columnName));
            }else {
                cr.addOrder(Order.desc(columnName));
            }
            results = cr.list();
        }catch (Throwable e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    protected static <T> double getAverageQuantity(String columnName, Class<T> searchingClass){
        Double average = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try{
            Criteria cr = session.createCriteria(searchingClass);
            cr.setProjection(Projections.avg(columnName));
            average = (Double) cr.uniqueResult();
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return average;
    }

    protected static <OBJECT extends IDB, ASSOCIATION> List<ASSOCIATION> getAssociationsForAnObject(OBJECT object, Class<ASSOCIATION> associationClass, String associationRelationshipParameter){
        List<ASSOCIATION> results = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria cr = session.createCriteria(associationClass);
            cr.createAlias(associationRelationshipParameter, "single_object");
            cr.add(Restrictions.eq("single_object.id", object.getId()));
            results = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }

    protected static <OBJECT extends IDB, ASSOCIATION> ASSOCIATION getAnAssociationForAnObject(OBJECT object, Class<ASSOCIATION> associationClass, String associationsRelationshipList){
        ASSOCIATION result = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria cr = session.createCriteria(associationClass);
            cr.createAlias(associationsRelationshipList, "single_object");
            cr.add(Restrictions.eq("single_object.id", object.getId()));
            result = (ASSOCIATION)cr.uniqueResult();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }


    protected static <OBJECT extends IDB, ASSOCIATION> Map<Integer, List<ASSOCIATION>> getMapOfAssociationsForObjects(Class<OBJECT> objectClass, Class<ASSOCIATION> associationClass, String associationsRelationshipList){
        List<OBJECT> objects = getAll(objectClass);
        Map<Integer, List<ASSOCIATION>> objectsAndAssociations = new HashMap<>();
        for(OBJECT object : objects){
            List<ASSOCIATION> associations = getAssociationsForAnObject(object, associationClass, associationsRelationshipList);
            objectsAndAssociations.put(object.getId(), associations);
        }
        return objectsAndAssociations;
    }

    protected static <T> List<T> findAllObjectsInList(String[] arrayOfIdsAsStrings, Class<T> searchingClass){
        List<T> objects = new ArrayList<>();
        for(String objectId : arrayOfIdsAsStrings){
            int id = Integer.parseInt(objectId);
            T object = find(id, searchingClass);
            objects.add(object);
        }
        return objects;
    }

    protected static <T> List<T> searchForAll(String searchTerm, Class<T> searchingClass, String propertyName){
        List<T> results = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria cr = session.createCriteria(searchingClass);
            cr.add(Restrictions.ilike(propertyName, searchTerm, MatchMode.ANYWHERE));
            results = cr.list();
        } catch(Throwable e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
}
