package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS userTable (id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(20), " +
                    "lastName VARCHAR(20), " +
                    "age TINYINT(100), " +
                    "PRIMARY KEY (id))")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Table creation error");
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS userTable").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Can't remove table");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Can't add user");
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Can't remove user");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            list = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
            for (User user: list) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println("Can't get user list");
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Can't remove user");
        }
    }
}
