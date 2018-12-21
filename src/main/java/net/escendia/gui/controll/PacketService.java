package net.escendia.gui.controll;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import net.escendia.gui.EscendiaGUIPlugin;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.model.UserConnection;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.gui.model.network.Packet;
import net.escendia.gui.model.network.in.PacketEvents;
import net.escendia.gui.model.network.out.PacketOut;
import net.escendia.ioc.Inject;
import net.escendia.ioc.InversionOfControl;
import net.escendia.ioc.Singleton;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.network.*;

import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
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
            case GlobalScope.PACKET_CLIENT_CONNECTION_INIT:
                new net.escendia.gui.model.network.in.PacketConnection.Init(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONCLICK:
                new PacketEvents.Post.OnClick(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONMOUSELEAVE:
                new PacketEvents.Post.OnMouseLeave(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONMOUSEENTER:
                new PacketEvents.Post.OnMouseEnter(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONMOUSEBUTTONUP:
                new PacketEvents.Post.OnMouseButtonUp(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONMOUSEBUTTONDOWN:
                new PacketEvents.Post.OnMouseButtonDown(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONDOUBLECLICK:
                new PacketEvents.Post.OnDoubleClick(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONKEYPRESSED:
                new PacketEvents.Post.OnKeyPressed(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONINPUT:
                new PacketEvents.Post.OnInput(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONPASTE:
                new PacketEvents.Post.OnPaste(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONCOPY:
                new PacketEvents.Post.OnCopy(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONREMOVE:
                new PacketEvents.Post.OnRemove(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONVALUECHANGE:
                new PacketEvents.Post.OnValueChange(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONFOCUS:
                new PacketEvents.Post.OnFocus(uuid, body);
                break;
            case GlobalScope.PACKET_CLIENT_EVENT_POST_ONBLUR:
                new PacketEvents.Post.OnBlur(uuid, body);
                break;
            default:

        }
    }

}
