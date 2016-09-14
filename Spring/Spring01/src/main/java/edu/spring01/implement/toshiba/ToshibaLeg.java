package edu.spring01.implement.toshiba;

import edu.spring01.inrefaces.Leg;
import org.springframework.stereotype.Service;

/**
 * Created by ALex on 21.08.2016.
 */
@Service("toshibaLeg")
public class ToshibaLeg implements Leg {
@Override
    public void start() {
        System.out.println("Leg is Toshiba");
    }
}
