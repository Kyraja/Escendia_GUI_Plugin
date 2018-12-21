package net.escendia.gui.model.network.in;

import com.google.gson.JsonObject;
import net.escendia.gui.controll.PacketService;
import net.escendia.ioc.InversionOfControl;

import java.util.UUID;

public abstract class PacketConnection extends PacketIn {

    public PacketConnection(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static class Init extends PacketConnection{

        public Init(UUID uuid, JsonObject jsonObject) {
            super(jsonObject);
            InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(uuid, new net.escendia.gui.model.network.out.PacketConnection.Init());
        }
    }

    public static class Close extends PacketConnection{

        public Close(JsonObject jsonObject) {
            super(jsonObject);
        }
    }
}
