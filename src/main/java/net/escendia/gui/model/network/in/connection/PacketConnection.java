package net.escendia.gui.model.network.in.connection;

import com.google.gson.JsonObject;
import net.escendia.gui.controll.PacketService;
import net.escendia.gui.model.network.in.PacketIn;
import net.escendia.ioc.InversionOfControl;

import java.util.UUID;

/**
 * Abstract class for connection packets
 */
public abstract class PacketConnection extends PacketIn {

    public PacketConnection(JsonObject jsonObject) {
        super(jsonObject);
    }

}
