package com.example.dbService.DAO;

import com.example.dbService.Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserDAOImpl implements UserDAO{
    private SessionFactory factory;

    public UserDAOImpl(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void createUser(User user) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private User getUser(String username) {
        User user = null;
        try (Session session = factory.openSession()) {
            System.out.println("username = " + username);
            user = session.byNaturalId(User.class).using("username", username).load();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return user;
        }
        return user;
    }

    @Override
    public boolean validateUser(String username, String password) {
        System.out.println(username);
        try {
            User user = getUser(username);
            if (user == null) {
                return false;
            }

            return password != null && password.equals(user.getPassword());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
