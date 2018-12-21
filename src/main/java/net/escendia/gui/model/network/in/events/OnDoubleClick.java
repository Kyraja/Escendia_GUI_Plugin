package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnDoubleClickEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;


/**
 * Packet: Receive a event, when a element is double clicked and Post a {@link OnDoubleClickEvent}
 */
public class OnDoubleClick extends PacketEvents {
    public OnDoubleClick(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnDoubleClickEvent(getUuid(), getElement()));
    }
}
