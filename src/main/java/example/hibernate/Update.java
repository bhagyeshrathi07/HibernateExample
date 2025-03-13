package example.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Update {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setId(4);
        s1.setName("Black Widow");
        s1.setMajor("Undecided");

        Configuration config = new Configuration();
        config.addAnnotatedClass(Student.class);
        config.configure();

        try (SessionFactory factory = config.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.merge(s1);
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Error during session operation: " + e);
            }
        } catch (HibernateException e) {
            System.out.println("Error building session factory: " + e);
        }
    }
}
