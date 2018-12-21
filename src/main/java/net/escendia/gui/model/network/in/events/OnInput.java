package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.listeners.impl.OnInputEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Send a event if some information were inputed into the element and post a {@link OnInputEvent}
 */
public class OnInput extends PacketEvents {
    public OnInput(UUID uuid, JsonObject body) {
        super(uuid, body);
        char input = body.get(GlobalScope.INPUT).getAsCharacter();
        Sponge.getEventManager().post(new OnInputEvent(getUuid(), getElement(), input));

    }
}
