package ma.enset.kafka_producer_poller;

import ma.enset.entities.PageEvent;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import ma.enset.entities.PageEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;

import java.util.Date;
import java.util.Random;
import java.util.function.Function;
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
