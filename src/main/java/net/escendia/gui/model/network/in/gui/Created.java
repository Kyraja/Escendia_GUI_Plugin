package net.escendia.gui.model.network.in.gui;

import com.google.gson.JsonObject;

/**
 * Packet: Gets the packet when a new gui is created from the client
 */
public class Created extends PacketGUI {

    public Created(JsonObject jsonObject) {
        super(jsonObject);
        //TODO POST GUICREATEDEVENT
    }
}
