package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnCopyEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: Send the packet if a users copys element from an element
 * And Post the {@link OnCopyEvent}
 */
public class OnCopy extends PacketEvents {
    public OnCopy(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnCopyEvent(getUuid(), getElement()));

    }
}
