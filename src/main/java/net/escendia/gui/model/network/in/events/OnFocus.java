package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnFocusEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * If an element is focused by the user the packet will be send.
 * And the Packet Posts an {@link OnFocusEvent}
 */
public class OnFocus extends PacketEvents {
    public OnFocus(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnFocusEvent(getUuid(), getElement()));

    }
}