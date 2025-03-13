package example.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Delete {
    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.addAnnotatedClass(Student.class);
        config.configure();

        try (SessionFactory factory = config.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();
                Student s1 = session.find(Student.class, 4);
                session.remove(s1);
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Error during session operation: " + e);
            }
        } catch (HibernateException e) {
            System.out.println("Error building session factory: " + e);
        }
    }
}
