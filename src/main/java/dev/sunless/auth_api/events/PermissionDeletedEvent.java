package dev.sunless.auth_api.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class PermissionDeletedEvent extends ApplicationEvent {

    private final UUID id;

    public PermissionDeletedEvent(Object source, UUID id) {
        super(source);
        this.id = id;
    }
}
