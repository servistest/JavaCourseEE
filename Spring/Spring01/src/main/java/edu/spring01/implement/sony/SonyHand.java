package edu.spring01.implement.sony;


import edu.spring01.inrefaces.Hand;
import org.springframework.stereotype.Service;

/**
 * Created by ALex on 21.08.2016.
 */
@Service("sonyHand")
public class SonyHand implements Hand {
    @Override
    public void catchMe() {
        System.out.println("Catch Hand  Sony..");
    }
}
