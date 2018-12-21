package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.model.listeners.impl.OnValueChangeEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * If an element were change (Progressbar for example) the packet will be send.
 * And the packet post the {@link OnValueChangeEvent}
 */
public class OnValueChange extends PacketEvents {
    public OnValueChange(UUID uuid, JsonObject body) {
        super(uuid, body);
        Sponge.getEventManager().post(new OnValueChangeEvent(getUuid(), getElement()));

    }
}
