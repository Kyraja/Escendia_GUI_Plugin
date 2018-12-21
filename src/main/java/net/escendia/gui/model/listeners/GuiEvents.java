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

public abstract class GuiEvents extends AbstractEvent {

    private EscendiaLogger logger;
    private Element element;
    private UUID playerUUID;

    public GuiEvents(UUID playerUUID, Element element){
        logger = InversionOfControl.get().build(EscendiaLogger.class);
        logger.debug("CreateEvent for: " + this.getClass().toString());
        this.playerUUID = playerUUID;
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    @Override
    public Cause getCause() {
        EventContext context = EventContext.builder()
                .add(EventContextKeys.PLAYER, Sponge.getServer().getPlayer(playerUUID).get())
                .build();
        return Cause.builder().append(playerUUID).append(element).build(context);
    }
}
