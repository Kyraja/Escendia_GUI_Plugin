package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnBlurEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: If an element were focused but then relaesed the packet will be send.
 * And Post the {@link OnBlurEvent}
 */
public class OnBlur extends PacketEvents {
    public OnBlur(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnBlurEvent(getUuid(), getElement()));

    }
}
