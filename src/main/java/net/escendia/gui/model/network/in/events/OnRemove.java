package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnRemoveEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: Send the packet if an element were removed
 * And post a {@link OnRemoveEvent}
 */
public class OnRemove extends PacketEvents {
    public OnRemove(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnRemoveEvent(getUuid(), getElement()));

    }
}