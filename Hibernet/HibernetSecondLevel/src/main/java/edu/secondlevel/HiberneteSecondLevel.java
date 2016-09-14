package edu.secondlevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ALex on 16.08.2016.
 */
public class HiberneteSecondLevel {
    private static final Logger log = LoggerFactory.getLogger(HiberneteSecondLevel.class);

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

    public static void checkOneFirst(Session session){

        log.info("Session hash : {}",session.hashCode());
        Payment payment=session.get(Payment.class,1);
        log.info("Payment = {}",payment);

    }
    public static void checkOneSecond(SessionFactory sessionFactory){
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        log.info("Session hash : {}",session.hashCode());
        Payment payment=session.get(Payment.class,2);
        log.info("Payment = {}",payment);

        session.getTransaction().commit();

    }

    public static void checkQuery(SessionFactory sessionFactory){
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query= session.createQuery("from Payment ");
        query.setCacheable(true);

        List<Payment> paymentList=(List<Payment>) query.list();

        for (Payment p:paymentList){
            log.info("Payment = {}",p);
        }

        session.getTransaction().commit();

    }
    public static void main(String[] args) {
        init();

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();

        log.info("==============Payment Session First Level====================");
        checkOneFirst(session);

        Session session1=sessionFactory.getCurrentSession();
        log.info("==============Payment Session First Level====================");
        checkOneFirst(session1);

        session.getTransaction().commit();


        log.info("==============Payment Session Second Level====================");
        checkOneSecond(sessionFactory);
        log.info("==============Payment Session Second Level====================");
        checkOneSecond(sessionFactory);

        log.info("==============Payment Query Cache 1 ====================");
        checkQuery(sessionFactory);

        log.info("==============Payment Query Cache 2 ====================");
        checkQuery(sessionFactory);

        destroy();




    }
}
