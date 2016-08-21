package edu.spring01.start.Robot;

import edu.spring01.implement.robot.Model1000;
import edu.spring01.implement.sony.SonyHead;
import edu.spring01.implement.sony.SonyLeg;
import edu.spring01.implement.toshiba.ToshibaHand;
import edu.spring01.inrefaces.Robot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ALex on 21.08.2016.
 */
public class Start {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("springExample.xml");
        Model1000 model1000=(Model1000) applicationContext.getBean("model1000");
        model1000.dance();
    }






}
