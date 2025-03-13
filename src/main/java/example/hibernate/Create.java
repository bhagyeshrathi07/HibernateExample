package example.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

public class Create {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setId(5);
        s1.setName("Hulk");
        s1.setMajor("CompSci");

        Configuration config = new Configuration();
        config.addAnnotatedClass(Student.class);
        config.configure();

        try (SessionFactory factory = config.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(s1);
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Error during session operation: " + e);
            }
        } catch (HibernateException e) {
            System.out.println("Error building session factory: " + e);
        }
    }
}