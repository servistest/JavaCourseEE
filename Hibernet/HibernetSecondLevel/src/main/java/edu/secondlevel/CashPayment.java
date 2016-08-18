package edu.secondlevel;

import javax.persistence.*;

/**
 * Created by ALex on 16.08.2016.
 */
@Entity
@Table(name = "jc_cash_payment")
@AttributeOverrides({
    @AttributeOverride(name = "amount",column = @Column(name = "amount"))
})
public class CashPayment extends Payment {

    @Column(name = "cash_desk")
    private String cashDesk;

    public CashPayment(){
    }

    public CashPayment( String cashDesk) {
        this.cashDesk = cashDesk;
    }

    public CashPayment(Double amount, String cashDesk) {
        super(amount);
        this.cashDesk = cashDesk;
    }
    public String getCashDesk() {
        return cashDesk;
    }

    public void setCashDesk(String cashDesk) {
        this.cashDesk = cashDesk;
    }

    @Override
    public String toString() {
        return "CashPayment{" +
                "cashDesk='" + cashDesk + '\'' +
                '}';
    }
}
