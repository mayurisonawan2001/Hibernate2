pom.xml

<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.sample</groupId>
  <artifactId>mavensample</artifactId>
  <version>0.0.1-SNAPSHOT</version>

  <name>mavensample</name>
  <description>A simple mavensample.</description>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.15.Final</version> <!-- or compatible version -->
    </dependency>
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.30</version>
    </dependency>
    <dependency>
      <!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
    </dependency>
  </dependencies>

  <build>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.4.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.12.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-project-info-reports-plugin</artifactId>
          <version>3.6.1</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_jar_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.3.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.13.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>3.3.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.4.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>3.1.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>3.1.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>

  <reporting>
    <plugins>
      <plugin>
        <artifactId>maven-project-info-reports-plugin</artifactId>
      </plugin>
    </plugins>
  </reporting>
</project>




App.java

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




AppTest.java

package org.sample.mavensample;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}



hibernate.cfg.xml

<?xml version="1.0" encoding="UTF-8"?>

<hibernate-configuration>
    <session-factory>

        <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/myhiber</property>
        <property name="connection.username">root</property>
        <property name="connection.password">1234</property>

        <property name="dialect">org.hibernate.dialect.MySQL8Dialect</property>
        <property name="hbm2ddl.auto">create</property>
        <property name="show_sql">true</property>

        <mapping class="org.sample.mavensample.Student"/>
        <mapping class="org.sample.mavensample.Address"/>
    </session-factory>
</hibernate-configuration>




Student.java

package org.sample.mavensample;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
 @Id
 private int id;
 private String name;
 private String city;

 public Student() {
     // Default constructor required by Hibernate
 }

 public Student(int id, String name, String city) {
     this.id = id;
     this.name = name;
     this.city = city;
 }

 public int getId() {
     return id;
 }

 public void setId(int id) {
     this.id = id;
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getCity() {
     return city;
 }

 public void setCity(String city) {
     this.city = city;
 }

 @Override
 public String toString() {
     return id + " : " + name + " from " + city;
 }
 
}



Address.java

package org.sample.mavensample;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name ="student_address")
public class Address {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int addressId;
 
 @Column(length =50, name="STREET")
 private String street;
 
 @Column(length= 100, name="CITY")
 private String city;
 
 @Column(name = "is_open")
 private boolean isOpen;
 
 @Transient
 private double x;
 
 @Column(name="added_date")
 @Temporal(TemporalType.DATE)
 private Date AddDate;
 
 @Lob
 private byte[] image;
 
public Address() {
	super();
	// TODO Auto-generated constructor stub
}
public Address(int addressId, String street, String city, boolean isOpen, double x, Date addDate, byte[] image) {
	super();
	this.addressId = addressId;
	this.street = street;
	this.city = city;
	this.isOpen = isOpen;
	this.x = x;
	this.AddDate = addDate;
	this.image = image;
}
public int getAddressId() {
	return addressId;
}
public void setAddressId(int addressId) {
	this.addressId = addressId;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public boolean isOpen() {
	return isOpen;
}
public void setOpen(boolean isOpen){
	this.isOpen = isOpen;
}
public double getX() {
	return x;
}
public void setX(double x) {
	this.x = x;
}
public Date getAddDate() {
	return AddDate;
}
public void setAddDate(Date addDate) {
	this.AddDate = addDate;
}
public byte[] getImage() {
	return image;
}
public void setImage(byte[] image) {
	this.image = image;
}
 
 
}
