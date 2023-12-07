package ma.enset.kafka_consumer;

import ma.enset.entities.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@Service
public class PageEventService_kafkaConsumer {

    /*
    Consumer<PageEvent> fonction de type Consumer -> il faire automatiquement une subscribe dans topic
    topic meme nom que la fonction , pour changer aller a fichier proprties
    */
    @Bean
    public Consumer<PageEvent> pageEventConsumer(){
        return (input) -> {
            System.out.println("******---Consumer---************");
            System.out.println(input.toString());
            System.out.println("*******-----**********");
        };
    }



}
