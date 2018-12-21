package net.escendia.gui.model.network.out.connection;


import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;

/**
 * Packet: Send a close message to the client
 */
public class Close extends PacketConnection {

    public Close() {
        super(new JsonObject());
    }

    @Override
    public int getID() {
        return GlobalScope.PACKET_SERVER_CONNECTION_CLOSE;
    }
}
