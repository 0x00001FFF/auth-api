package dev.sunless.auth_api.listeners;

import dev.sunless.auth_api.events.RoleDeletedEvent;
import dev.sunless.auth_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleEventListener {

    private final UserService userService;

    @EventListener
    public void handleRoleDeletion(RoleDeletedEvent event) {
        UUID id = event.getId();
        userService.removeRolesFromUsers(id);
    }
}
