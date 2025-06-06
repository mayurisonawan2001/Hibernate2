package org.sample.mavensample;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.sample.mavensample.Student;

public class App
{
    public static void main( String[] args ) throws IOException{
    	System.out.println("Project started!");

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // Make sure file is in src/main/resources
                .buildSessionFactory();

        System.out.println(factory);
        System.out.println(factory.isClosed());

        Student st = new Student(1010, "Mayuri", "Dhule");

        //object of address
        Address ad = new Address();
        ad.setStreet("street1");
        ad.setCity("Dhule");
        ad.setOpen(true);
        ad.setAddDate(new Date());
        ad.setX(1234.234);
        
        //reading  image
        FileInputStream fis= new FileInputStream("src/main/java/image1.jpg");
        byte[] data=new byte[fis.available()];
        fis.read(data);
        ad.setImage(data);
        
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(st);
        session.save(ad);
        
        tx.commit();

        session.close();
        factory.close();
        
        System.out.println("Done..");
        
        
    }
}
