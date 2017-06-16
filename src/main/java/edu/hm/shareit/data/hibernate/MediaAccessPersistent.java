package edu.hm.shareit.data.hibernate;

import edu.hm.shareit.data.MediaAccess;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Persistent storage implementation of the MediaAccess-Interface.
 * Uses hibernate to persist data to a HSQLDB.
 */
public class MediaAccessPersistent implements MediaAccess {

    // SessionFactory used to access hibernate
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public boolean addMedium(Medium medium) {
        boolean success = false;
        Session session = sessionFactory.getCurrentSession();

        if (session != null) {
            Transaction transaction = session.beginTransaction();
            session.save(medium);
            transaction.commit();
            success = true;
        }

        return success;
    }

    @Override
    public boolean removeMedium(Medium medium) {
        boolean success = false;
        Session session = sessionFactory.getCurrentSession();

        if (session != null) {
            Transaction transaction = session.beginTransaction();
            session.delete(medium);
            transaction.commit();
            success = true;
        }

        return success;
    }

    @Override
    public boolean updateMedium(Medium medium) {
        boolean success = false;
        Session session = sessionFactory.getCurrentSession();

        if (session != null) {
            Transaction transaction = session.beginTransaction();
            session.merge(medium);
            transaction.commit();
            success = true;
        }

        return success;
    }

    @Override
    public Book getBook(String ISBN) {
        Book b = null;
        Session session = sessionFactory.getCurrentSession();
        if (session != null) {
            Transaction transaction = session.beginTransaction();
            b = session.get(Book.class, ISBN);
            transaction.commit();
        }

        return b;
    }

    @Override
    public Disc getDisc(String barcode) {
        Disc d = null;
        Session session = sessionFactory.getCurrentSession();
        if (session != null) {
            Transaction transaction = session.beginTransaction();
            d = session.get(Disc.class, barcode);
            transaction.commit();
        }

        return d;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = null;
        Session session = sessionFactory.getCurrentSession();
        if (session != null) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            Query<Book> q = session.createQuery(query);
            books = q.getResultList();
            transaction.commit();
        }

        return books;
    }

    @Override
    public List<Disc> getDiscs() {
        List<Disc> discs = null;
        Session session = sessionFactory.getCurrentSession();
        if (session != null) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Disc> query = builder.createQuery(Disc.class);
            Root<Disc> root = query.from(Disc.class);
            Query<Disc> q = session.createQuery(query);
            discs = q.getResultList();
            transaction.commit();
        }

        return discs;
    }

    @Override
    public void shutdown() {
        HibernateUtils.shutdown();
    }
}
