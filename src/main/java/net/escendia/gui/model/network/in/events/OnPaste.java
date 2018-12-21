package net.escendia.gui.model.network.in.events;


import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnPasteEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: If the user pasted some information from an element the packet will be send.
 * And post the  {@link OnPasteEvent}
 */
public class OnPaste extends PacketEvents {
    public OnPaste(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnPasteEvent(getUuid(), getElement()));

    }
}
