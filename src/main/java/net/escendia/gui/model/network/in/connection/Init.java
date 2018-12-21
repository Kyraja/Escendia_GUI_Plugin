package net.escendia.gui.model.network.in.connection;

import com.google.gson.JsonObject;
import net.escendia.gui.controll.PacketService;
import net.escendia.ioc.InversionOfControl;

import java.util.UUID;

/**
 * Packet: Get a message if the client is rdy to initialise
 */
public class Init extends PacketConnection{

    public Init(UUID uuid, JsonObject jsonObject) {
        super(jsonObject);
        InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(uuid, new net.escendia.gui.model.network.out.connection.Init());
    }
}
