package edu.spring01.implement.robot;

import edu.spring01.inrefaces.Hand;
import edu.spring01.inrefaces.Head;
import edu.spring01.inrefaces.Leg;

/**
 * Created by ALex on 21.08.2016.
 */
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
}
