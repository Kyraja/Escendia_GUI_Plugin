package net.escendia.gui.model.network.in.element;

import com.google.gson.JsonObject;

/**
 * Packet: Receive a message if the element were removed
 */
public class Removed extends  PacketElement{

    public Removed(JsonObject jsonObject) {
        super(jsonObject);
        //TODO Removed Event
    }
}