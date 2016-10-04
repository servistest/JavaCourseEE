package validation.model;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 04.10.2016.
 */
@Service(value = "anotherContactService")

public class ContactService {
    @Scheduled(fixedDelay = 5000)
    public void contactWrite(){
        System.out.println("work scheduler ...");
    }
}
