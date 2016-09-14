package edu.spring01.implement.toshiba;

import edu.spring01.inrefaces.Head;
import org.springframework.stereotype.Service;

/**
 * Created by ALex on 21.08.2016.
 */
@Service("toshibaHead")
public class ToshibaHead implements Head{
    @Override
    public void calc() {
        System.out.println("Head is Toshiba");
    }
}
