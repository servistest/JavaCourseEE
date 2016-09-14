package edu.spring01.implement.robot;

import edu.spring01.inrefaces.Hand;
import edu.spring01.inrefaces.Head;
import edu.spring01.inrefaces.Leg;
import edu.spring01.inrefaces.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Admin on 23.08.2016.
 */
@Service("modelToshiba")
public class ModelToshiba implements Robot {
    private Hand hand;
    private Head head;
    private Leg leg;

    public ModelToshiba() {
    }

    public Hand getHand() {
        return hand;
    }
    @Autowired
    @Qualifier("toshibaHand")
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Head getHead() {
        return head;
    }

    @Autowired(required = false)
    @Qualifier("toshibaHead")
    public void setHead(Head head) {
        this.head = head;
    }

    public Leg getLeg() {
        return leg;
    }

//    @Autowired(required =false )
//    @Qualifier("toshibaLeg")
//    or
    @Resource(name = "toshibaLeg")
    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public ModelToshiba(Hand hand, Head head, Leg leg) {
        this.hand = hand;
        this.head = head;
        this.leg = leg;
    }

    @Override
    public void dance() {
        head.calc();
        hand.catchMe();
        leg.start();

    }

    @Override
    public void go() {
        System.out.println("ModelToshiba is go");

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.spring01.implement.robot.ModelToshiba{");
        sb.append("hand=").append(hand);
        sb.append(", head=").append(head);
        sb.append(", leg=").append(leg);
        sb.append('}');
        return sb.toString();
    }
}
