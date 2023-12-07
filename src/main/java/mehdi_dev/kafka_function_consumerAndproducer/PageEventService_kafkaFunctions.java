package mehdi_dev.kafka_function_consumerAndproducer;

import mehdi_dev.entities.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PageEventService_kafkaFunctions {

    @Bean
    public Function<PageEvent,PageEvent> pageEventFunction(){
        return (input)->{
            input.setName("Page: "+input.getName()+"Length : "+input.getName().length());
            input.setUser("User=>1");
            return input;
        };
    }

}
