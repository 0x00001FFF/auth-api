package dev.sunless.auth_api.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class RoleDeletedEvent extends ApplicationEvent {

    private final UUID id;

    public RoleDeletedEvent(Object source, UUID id) {
        super(source);
        this.id = id;
    }
}
