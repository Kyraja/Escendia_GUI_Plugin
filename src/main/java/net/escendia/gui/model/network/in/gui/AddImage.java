package net.escendia.gui.model.network.in.gui;

import com.google.gson.JsonObject;

/**
 * Packet: Gets the packet when a new gui is created from the client
 */
public class AddImage extends PacketGUI {

    public AddImage(JsonObject jsonObject) {
        super(jsonObject);
    }
}
