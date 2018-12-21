package net.escendia.gui.model.network.in.gui;


import com.google.gson.JsonObject;

/**
 * Packet: Gets the packet when a old gui is deleted from the client
 */
public class Deleted extends PacketGUI{

    public Deleted(JsonObject jsonObject) {
        super(jsonObject);
        //TODO POST GUIDELETEDEVENT
    }
}
