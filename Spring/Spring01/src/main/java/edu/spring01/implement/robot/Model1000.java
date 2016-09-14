package edu.spring01.implement.robot;

import edu.spring01.inrefaces.Hand;
import edu.spring01.inrefaces.Head;
import edu.spring01.inrefaces.Leg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Created by ALex on 21.08.2016.
 */
@Service("model1000")
public class Model1000 implements edu.spring01.inrefaces.Robot {
    private Hand hand;
    private Head head;
    private Leg leg;

    public Model1000() {
    }

    public Hand getHand() {
        return hand;
    }


    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Head getHead() {
        return head;
    }


    public void setHead(Head head) {
        this.head = head;
    }

    public Leg getLeg() {
        return leg;
    }


    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public Model1000(Hand hand, Head head, Leg leg) {
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
        System.out.println("Model1000 is go");

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.spring01.implement.robot.Model1000{");
        sb.append("hand=").append(hand);
        sb.append(", head=").append(head);
        sb.append(", leg=").append(leg);
        sb.append('}');
        return sb.toString();
    }
}
