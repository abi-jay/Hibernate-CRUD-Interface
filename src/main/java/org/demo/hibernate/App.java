package org.demo.hibernate;

import org.demo.hibernate.model.User;
import org.demo.hibernate.service.UserService;
import org.demo.hibernate.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;
import java.util.UUID;


public class App 
{
    public static void main( String[] args )
    {
        UserService userService = new UserServiceImpl();
        User user = new User(1, UUID.randomUUID().toString(), "June", "Valley", "flower@test.com", "204-913-0000") ;
        userService.createUser(user);
        userService.updateUser(user,"924-875-1041");
        List<User> list = userService.getAllUsers();
        for(User u : list){
            System.out.println(u.getId());
        }
        User userEmail = userService.findUserByEmail("flower@test.com");
        System.out.println("Found user "+userEmail.getFirstName()+" "+userEmail.getLastName()+ " with the email "+userEmail.getEmail());
        userService.delete(9);

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.getNamedQuery("getAllBooks");
        factory.close();
        session.close();
    }
}
