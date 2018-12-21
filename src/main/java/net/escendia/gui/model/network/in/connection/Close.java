package net.escendia.gui.model.network.in.connection;

import com.google.gson.JsonObject;

/**
 * Packet: Receive a message from the client if the connection is closed
 */
public class Close extends PacketConnection{

    public Close(JsonObject jsonObject) {
        super(jsonObject);
    }
}