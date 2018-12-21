package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnClickEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: Receive a message if a element were clicked and post a {@link OnClickEvent}
 */
public class OnClick extends PacketEvents {
    public OnClick(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnClickEvent(getUuid(), getElement()));
    }
}
