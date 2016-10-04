package edu.validation.individualcheck;

/**
 * Created by ALex on 02.10.2016.
 */
public enum CustomerType {
    INDIVIDUAL("I"),
    CORPORATE("C");
    private String code;

    private CustomerType(String code){
        this.code=code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
