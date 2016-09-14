package edu.secondlevel;

import javax.persistence.*;

/**
 * Created by ALex on 16.08.2016.
 */
@Entity
@Table(name = "jc_credit_payment")
@AttributeOverrides({
    @AttributeOverride(name = "amount",column = @Column(name = "amount"))
})
public class CreditPayment extends Payment{

    @Column(name = "credit_card")
    private String creditCard;

    public CreditPayment() {
    }

    public CreditPayment(String creditCard) {
        this.creditCard = creditCard;
    }

    public CreditPayment(Double amount, String creditCard) {
        super(amount);
        this.creditCard = creditCard;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "CreditPayment{" +
                "creditCard='" + creditCard + '\'' +
                '}';
    }
}
