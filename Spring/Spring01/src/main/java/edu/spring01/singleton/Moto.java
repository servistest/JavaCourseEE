package edu.spring01.singleton;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Admin on 25.08.2016.
 */
@Service("moto")
public class Moto {

    private Auto auto;

    private Integer maxSpeed;

    public Moto() {
    }

    public Auto getAuto() {
        return auto;
    }
    @Resource(name = "auto")
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    @Value("${moto.maxSpeed}")
    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.spring01.singleton.Moto{");
        sb.append("auto=").append(auto);
        sb.append(", maxSpeed=").append(maxSpeed);
        sb.append('}');
        return sb.toString();
    }
}
