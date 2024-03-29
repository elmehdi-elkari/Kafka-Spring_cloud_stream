package mehdi_dev.kafka_event_producer_rest_client;
import mehdi_dev.entities.PageEvent;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventController {
    private StreamBridge streamBridge;


    public PageEventController(StreamBridge streamBridge, InteractiveQueryService interactiveQueryService) {
        this.streamBridge = streamBridge;
    }

    @GetMapping("publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String name, @PathVariable String topic){
        PageEvent pageEvent=new PageEvent();
        pageEvent.setName(name);
        pageEvent.setDate(new Date());
        pageEvent.setDuration(new Random().nextInt(1000));
        pageEvent.setUser(Math.random()>0.5?"U1":"U2");
        streamBridge.send(topic,pageEvent);
        return pageEvent;
    }

}
