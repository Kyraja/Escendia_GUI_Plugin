package net.escendia.gui.model.network.in.events;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.listeners.impl.OnKeyPressedEvent;
import org.spongepowered.api.Sponge;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Packet: If a key is pressed the key or the list of keys will be send.
 * And Post a {@link OnKeyPressedEvent}
 */
public class OnKeyPressed extends PacketEvents {
    public OnKeyPressed(UUID uuid, JsonObject body) {
        super(uuid, body);
        JsonArray jsonArray = body.get(GlobalScope.KEY_PRESSED_LIST).getAsJsonArray();

        ArrayList<Integer> listdata = new ArrayList<Integer>();
        if (jsonArray != null) {
            for (int i=0;i<jsonArray.size();i++){
                listdata.add(jsonArray.get(i).getAsInt());
            }
        }

        Sponge.getEventManager().post(new OnKeyPressedEvent(getUuid(), getElement(), listdata));

    }
}
