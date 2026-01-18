package pl.wsb.fitnesstracker.event.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
class MySpringEvent extends ApplicationEvent {

    private String myMessage;

    public MySpringEvent(final Object source, String myMessage) {
        super(source);
        this.myMessage = myMessage;
    }

}