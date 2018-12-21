package net.escendia.gui.model.network.in.element;

import com.google.gson.JsonObject;

/**
 * Packet: Receive a message if th element was updated
 */
public class Updated extends  PacketElement{

    public Updated(JsonObject jsonObject) {
        super(jsonObject);
        //TODO UPDATED Event
    }
}
