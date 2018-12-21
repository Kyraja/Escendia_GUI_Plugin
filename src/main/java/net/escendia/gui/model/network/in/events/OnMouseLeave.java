package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnMouseLeaveEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: Receive a message if an element were hovered but the mouse "leave" the element.
 * Also Post an {@link OnMouseLeaveEvent}
 */
public class OnMouseLeave extends PacketEvents {
    public OnMouseLeave(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnMouseLeaveEvent(getUuid(), getElement()));
    }
}
