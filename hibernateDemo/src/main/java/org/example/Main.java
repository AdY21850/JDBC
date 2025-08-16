package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

class Main{
    public static void main(String args[]){
        employee emp=new employee();
        emp.setId(12);
        emp.setName("krishna");
        emp.setJob("java full Stack");
        emp.setSalary(15000);

        Configuration config=new Configuration();
        config.addAnnotatedClass(org.example.employee.class);
        config.configure();


        SessionFactory factory= config.buildSessionFactory();
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        session.persist(emp);

        tx.commit();
    }
}
