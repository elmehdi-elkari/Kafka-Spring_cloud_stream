package ma.enset.kafka_streams;

import ma.enset.entities.PageEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Function;

@Service
public class PageEventService_kafkaStreamFunction {

    @Bean
    public Function<KStream<String,PageEvent>, KStream<String,Long>> kStreamFunction(){
        return (input)-> input
                .filter((k,v)->v.getDuration()>100)
                .map((k,v)->new KeyValue<>(v.getName(),0L))
                .groupBy((k,v)->k, Grouped.with(Serdes.String(),Serdes.Long()))
                .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
                .count(Materialized.as("page-count"))
                .toStream()
                .map((k,v)->new KeyValue<>("=>"+k.window().startTime()+k.window().endTime()+":"+k.key(),v));

        /*return (input)->{

            KStream<String, Long> map = input
                    .filter((k, v) -> v.getDuration() > 100)
                    .map((k, v) -> new KeyValue<String, Long>(v.getName(), 0L))
                    .groupBy((k, y) -> k, Grouped.with(Serdes.String(), Serdes.Long()))
                    .windowedBy(TimeWindows.of(Duration.ofMillis(500)))
                    .count()
                    .toStream()
                    .map((k, v) -> new KeyValue<>("=> "+k.window()+k.window().startTime()+k.window().endTime(), v));
            return map;
        };*/
    }

}
