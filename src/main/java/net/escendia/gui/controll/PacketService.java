package net.escendia.gui.controll;

import com.google.gson.*;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.UserConnection;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.gui.model.network.in.connection.Init;
import net.escendia.gui.model.network.in.events.*;
import net.escendia.gui.model.network.in.connection.PacketConnection;
import net.escendia.gui.model.network.out.PacketOut;
import net.escendia.ioc.Inject;
import net.escendia.ioc.InversionOfControl;
import net.escendia.ioc.Singleton;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.network.*;

import java.util.UUID;

@Singleton
public class PacketService{

    @Inject
    public EscendiaLogger logger;



    public PacketService(){
        logger = InversionOfControl.get().build(EscendiaLogger.class);
        logger.info("Escendianetwork loading...");
        logger.info("Escendianetwork loaded...");
    }


    private ChannelBinding.RawDataChannel  rawDataChannel;


    public void sendPacketToPlayer(UUID uuid, PacketOut packet) {
        sendPacketToPlayer(Sponge.getServer().getPlayer(uuid).get(), packet);
    }

    public void sendPacketToPlayer(Player player, PacketOut packet) {
        UserConnection userConnection = InversionOfControl.get().build(PlayerService.class).getUserConnect(player);
        userConnection.send(packet);
    }


    public void receivePacketFromPlayer(UUID uuid, JsonObject content) {
        int id =content.get(GlobalScope.PACKET_ID).getAsInt();
        JsonObject body = content.getAsJsonObject(GlobalScope.BODY);

        logger.info("Message Recived ID: " + id + " for Player(UUID) " + uuid.toString() + " | Name: " + Sponge.getServer().getPlayer(uuid).get().getName());


        switch (id){
            //TODO Received Packages
            //TODO as event handling
            //TODO All Events an added/removed etc events
            case GlobalScope.PACKET_CLIENT_CONNECTION_INIT:
                new Init(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONCLICK:
                new OnClick(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONMOUSELEAVE:
                new OnMouseLeave(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONMOUSEENTER:
                new OnMouseEnter(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONMOUSEBUTTONUP:
                new OnMouseButtonUp(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONMOUSEBUTTONDOWN:
                new OnMouseButtonDown(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONDOUBLECLICK:
                new OnDoubleClick(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONKEYPRESSED:
                new OnKeyPressed(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONINPUT:
                new OnInput(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONPASTE:
                new OnPaste(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONCOPY:
                new OnCopy(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONREMOVE:
                new OnRemove(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONVALUECHANGE:
                new OnValueChange(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONFOCUS:
                new OnFocus(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONBLUR:
                new OnBlur(uuid, body);
                break;
            default:

        }
    }

}
