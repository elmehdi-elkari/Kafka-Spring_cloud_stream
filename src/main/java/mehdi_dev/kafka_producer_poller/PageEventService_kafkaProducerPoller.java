package mehdi_dev.kafka_producer_poller;

import mehdi_dev.entities.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class PageEventService_kafkaProducerPoller {

    /*
    * fonction de type supplier donc ça sera executer chaque seconde
    * on peut changer ces comportement dans la fichier .proprieties ( topic, duration ...)
    * */
    @Bean
    public Supplier<PageEvent> pageEventSupplier(){
        return ()->
                new PageEvent(
                    Math.random()>0.5?"P1":"P2",
                    Math.random()>0.5?"U1":"U2",
                    new Date(),
                    new Random().nextInt(1000)
            );
    }

}
