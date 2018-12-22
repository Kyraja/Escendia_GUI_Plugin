package net.escendia.gui.model.network.in.connection;

import com.google.gson.JsonObject;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.controll.PacketService;
import net.escendia.gui.model.listeners.impl.InitEvent;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.ioc.InversionOfControl;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * Packet: Get a message if the client is rdy to initialise
 */
public class Init extends PacketConnection{

    public Init(UUID uuid, JsonObject jsonObject) {
        super(jsonObject);
        Sponge.getEventManager().post(new InitEvent(uuid));
    }
}
