package edu.spring01.implement.sony;

import edu.spring01.inrefaces.Hand;
import edu.spring01.inrefaces.Head;
import org.springframework.stereotype.Service;

/**
 * Created by ALex on 21.08.2016.
 */
@Service("sonyHead")
public class SonyHead implements Head{
    @Override
    public void calc() {
        System.out.println("Head is Sony");
    }
}
