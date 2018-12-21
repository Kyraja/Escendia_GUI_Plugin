package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.gui.Mouse;
import net.escendia.gui.model.listeners.impl.OnMouseButtonUpEvent;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: Sends a event if the Mousebutton were clicked but then released
 * And post the {@link OnMouseButtonUpEvent}
 */
public class OnMouseButtonUp extends PacketEvents {
    public OnMouseButtonUp(UUID uuid, JsonObject body) {
        super(uuid, body);
        Mouse.Button clickedButton = Mouse.Button.valueOf(jsonObject.get(GlobalScope.BUTTON).getAsString());
        Sponge.getEventManager().post(new OnMouseButtonUpEvent(getUuid(), getElement(),clickedButton));

    }
}
