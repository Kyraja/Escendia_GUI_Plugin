package net.escendia.gui.model.network.in.element;

import com.google.gson.JsonObject;

/**
 * Packet: Receive a message if a gui element is added
 */
public class Added extends PacketElement{

    public Added(JsonObject jsonObject) {
        super(jsonObject);
        //TODO Added Event
    }
}
