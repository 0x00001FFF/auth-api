package dev.sunless.auth_api.listeners;

import dev.sunless.auth_api.events.PermissionDeletedEvent;
import dev.sunless.auth_api.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PermissionEventListener {

    private final RoleService roleService;

    @EventListener
    public void handlePermissionDeletion(PermissionDeletedEvent event) {
        UUID id = event.getId();
        roleService.removePermissionFromRoles(id);
    }

}
