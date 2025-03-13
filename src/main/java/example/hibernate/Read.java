package example.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Read {
    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.addAnnotatedClass(Student.class);
        config.configure();

        try (SessionFactory factory = config.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Student stud = session.find(Student.class, 1);
                System.out.println("Student found: " + stud);
            } catch (HibernateException e) {
                System.out.println("Error during session operation: " + e);
            }
        } catch (HibernateException e) {
            System.out.println("Error building session factory: " + e);
        }
    }
}
