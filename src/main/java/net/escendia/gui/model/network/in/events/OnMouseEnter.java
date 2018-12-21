package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnMouseEnterEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: Received a message if the mouse enter a element
 * And post a {@link OnMouseEnterEvent}
 */
public class OnMouseEnter extends PacketEvents {
    public OnMouseEnter(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnMouseEnterEvent(getUuid(), getElement()));
    }
}
