package edu.spring01.start;

import edu.spring01.implement.robot.Model1000;
import edu.spring01.implement.robot.ModelToshiba;
import edu.spring01.implement.sony.SonyHand;
import edu.spring01.implement.sony.SonyHead;
import edu.spring01.implement.toshiba.ToshibaLeg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ALex on 21.08.2016.
 */

public class Start {
    private final static Logger log= LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("springExample.xml");
        Model1000 model1000=(Model1000) applicationContext.getBean("model1000",Model1000.class);
        log.debug("Model 1000 = {}",model1000);
        model1000.setHand(new SonyHand());
        model1000.setHead(new SonyHead());
        model1000.setLeg(new ToshibaLeg());
        model1000.dance();
        model1000.go();

        ModelToshiba modelToshiba=(ModelToshiba) applicationContext.getBean(ModelToshiba.class);
        log.debug("Model Toshiba = {}",modelToshiba);
        modelToshiba.dance();

//        Model1000 model2000 =(Model1000) applicationContext.getBean("model2000");
//        model2000.dance();
    }






}
