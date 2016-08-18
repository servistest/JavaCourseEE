package edu.tableperclass;

import javax.persistence.*;

/**
 * Created by ALex on 16.08.2016.
 */
@Entity
@Table(name = "jc_payment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Payment {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE )
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "amount")
    private Double amount;

    public Payment(){

    }

    public Payment(Double amount) {
        this.amount = amount;
    }

    public int getPaymountId() {
        return paymentId;
    }

    public void setPaymountId(int paymountId) {
        this.paymentId = paymountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymountId=" + paymentId +
                ", amount=" + amount +
                '}';
    }
}
