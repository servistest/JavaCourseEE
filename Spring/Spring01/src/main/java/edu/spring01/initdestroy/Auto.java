package edu.spring01.initdestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 25.08.2016.
 */
@Service("auto")
public class Auto {
//    @Value("#{autoProperties.name}")
//    or
    @Value("${auto.mame}")
    private String name;

//    @Value("#{autoProperties.price}")
//    or
    @Value("${auto.price}")
    private Float price;

    public void auto(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Auto {");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
