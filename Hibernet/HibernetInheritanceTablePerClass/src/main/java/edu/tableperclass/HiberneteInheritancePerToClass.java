package edu.tableperclass;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ALex on 16.08.2016.
 */
public class HiberneteInheritancePerToClass {
    private static final Logger log = LoggerFactory.getLogger(HiberneteInheritancePerToClass.class);

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static void init(){
        Configuration configuration=new Configuration();
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(CreditPayment.class);
        configuration.addAnnotatedClass(CashPayment.class);

        configuration.configure("hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory=configuration.buildSessionFactory(serviceRegistry);
    }
    private static void destroy() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    public static void main(String[] args) {
        init();

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Payment payment=new Payment();
        payment.setAmount(150.0);
        session.save(payment);
        CashPayment cashPayment=new CashPayment(500.0,"Kassa-500");
        session.save(cashPayment);

        CreditPayment creditPayment=new CreditPayment(600.0,"6666152348501200");
        session.save(creditPayment);
        log.info("==============Payment AlL====================");
        Criteria criteria=session.createCriteria(Payment.class);
        List<Payment> listPayments=criteria.list();

        for(Payment pay:listPayments){
            log.info("Payments : class:{}, toString : {} ", pay.getClass().getCanonicalName(), pay.toString());
        }

        log.info("==============Payment Credit Card====================");
        Criteria creditCriteria=session.createCriteria(CreditPayment.class);
        List<Payment> listPaymentsCredit=creditCriteria.list();

        for(Payment pay:listPaymentsCredit){
            log.info("Payments Credit Card : class:{}, toString : {} ", pay.getClass().getCanonicalName(), pay.toString());
        }

        session.getTransaction().commit();
        destroy();

    }
}
