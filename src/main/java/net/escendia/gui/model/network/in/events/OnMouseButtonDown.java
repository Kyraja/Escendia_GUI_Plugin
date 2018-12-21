package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.gui.Mouse;
import net.escendia.gui.model.listeners.impl.OnMouseButtonDownEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: If a mouse button is pressed down and post an {@link net.escendia.gui.model.listeners.impl.OnMouseButtonDownEvent}
 */
public class OnMouseButtonDown extends PacketEvents {
    public OnMouseButtonDown(UUID uuid, JsonObject body) {
        super(uuid, body);
        Mouse.Button clickedButton = Mouse.Button.valueOf(jsonObject.get(GlobalScope.BUTTON).getAsString());
        Sponge.getEventManager().post(new OnMouseButtonDownEvent(getUuid(), getElement(),clickedButton));
    }
}
