package net.escendia.gui.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.escendia.gui.GlobalScope;
import net.escendia.gui.controll.GUIService;
import net.escendia.gui.controll.PacketService;
import net.escendia.gui.controll.PlayerService;
import net.escendia.gui.model.listeners.impl.InitEvent;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.gui.model.network.out.PacketOut;
import net.escendia.gui.model.network.out.connection.Init;
import net.escendia.ioc.InversionOfControl;
import org.spongepowered.api.Sponge;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.UUID;

public class UserConnection implements Runnable{

    public EscendiaLogger logger;

    private UUID uuid;

    private final Socket socket;
    private boolean canSendCommand = true;
    private BufferedReader in;
    private DataOutputStream out;
    private String playerUUID = "";
    private long timeCreated = 0;

    public UserConnection(Socket socket) {
        logger = InversionOfControl.get().build(EscendiaLogger.class);
        this.timeCreated = System.currentTimeMillis();
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            logger.error("PlayerJoined - error creating Connection....", e);

        }

        logger.info("PlayerJoined - created Connection....");
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void add(String playerUUID) {
        logger.info("SetUUID and Added for " + playerUUID);
        this.setUuid(UUID.fromString(playerUUID));
        InversionOfControl.get().build(PlayerService.class).add(this);
        send(new Init());
    }



    public void remove() {
        InversionOfControl.get().build(GUIService.class).removeGUI(getUuid());
        InversionOfControl.get().build(PlayerService.class).remove(this);
    }


    public void send(PacketOut packetOut){
        try {
            out.writeBytes(packetOut.toJson().toString()+ "\n");
        } catch (Exception e) {
            logger.error("Error while sending packets...", e);
            remove();
        }
    }

    @Override
    public void run() {
        try{

            JsonParser parser = new JsonParser();
            boolean firstConnection = false;
            while(true){
                String message = in.readLine();
                JsonObject jsonObject = parser.parse(message).getAsJsonObject();

                if(getUuid()==null){
                    firstConnection = true;
                    JsonObject body = jsonObject.getAsJsonObject(GlobalScope.BODY);
                    logger.info("Added Connection first read: " + body.get(GlobalScope.UUID).getAsString());

                    add(body.get(GlobalScope.UUID).getAsString());
                    Sponge.getEventManager().post(new InitEvent(UUID.fromString(body.get(GlobalScope.UUID).getAsString())));
                }

                if(!firstConnection)InversionOfControl.get().build(PacketService.class).receivePacketFromPlayer(this.getUuid(), jsonObject);

            }
        }catch(Exception e){
            remove();
        }
    }

    public long getTimeCreated() {
        return this.timeCreated;
    }

    public void close() {
        try {
            logger.info("Player connection closed!");
            socket.close();
        } catch (IOException ex) {}
    }
}
