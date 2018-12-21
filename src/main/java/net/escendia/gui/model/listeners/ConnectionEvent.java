package net.escendia.gui.model.listeners;

import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.ioc.InversionOfControl;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.impl.AbstractEvent;

import java.util.UUID;

public abstract class ConnectionEvent extends AbstractEvent {

    private UUID playerUUID;

    public ConnectionEvent(UUID playerUUID){
        this.playerUUID = playerUUID;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    @Override
    public Cause getCause() {
        EventContext context = EventContext.builder()
                .add(EventContextKeys.PLAYER, Sponge.getServer().getPlayer(playerUUID).get())
                .build();
        return Cause.builder().append(playerUUID).build(context);

    }

}
