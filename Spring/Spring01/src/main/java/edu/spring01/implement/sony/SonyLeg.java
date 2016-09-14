package edu.spring01.implement.sony;

import edu.spring01.inrefaces.Leg;
import org.springframework.stereotype.Service;

/**
 * Created by ALex on 21.08.2016.
 */
@Service("sonyLeg")
public class SonyLeg implements Leg {
@Override
    public void start() {
        System.out.println("Leg is Sony");
    }
}
