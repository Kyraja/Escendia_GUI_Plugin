package net.escendia.gui.controll;

import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.gui.model.UserConnection;
import net.escendia.ioc.Inject;
import net.escendia.ioc.InversionOfControl;
import net.escendia.ioc.Singleton;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.network.PlayerConnection;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

@Singleton
public class PlayerService {

    @Inject
    private EscendiaLogger logger;


    private final Map<UUID, UserConnection> userConnections = Collections.synchronizedMap(new HashMap<UUID, UserConnection>());
    private final List<UserConnection> userConnectionsWaitingList = Collections.synchronizedList(new ArrayList<UserConnection>());

    public ServerSocket serverSocket;

    public PlayerService(){
        logger = InversionOfControl.get().build(EscendiaLogger.class);
        logger.info("PlayerService loading...");

        try{
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(20000));
            startServerListener();
        }catch(Exception ex){
            logger.error("serverSocket loading error...", ex);
        }
        logger.info("PlayerService loaded...");

    }


    private void startServerListener(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("Server is start listening....");
                    //startCleaningConnectionThread();
                    while (true){
                        logger.info("UserConnectionsWating().... " +userConnectionsWaitingList);


                                UserConnection userConnection = new UserConnection(serverSocket.accept());
                                userConnectionsWaitingList.add(userConnection);
                                logger.info("UserConnection().... " + userConnection.getUuid());
                                new Thread(userConnection).start();
                    }
                }catch (Exception ex){
                    logger.error("Error by socket bind.", ex);

                }
            }
        }, "Server for Guis").start();
    }

    private void startCleaningConnectionThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!serverSocket.isClosed()){
                    long time = System.currentTimeMillis();
                    int connectionClosed = 0;

                    for(UserConnection userConnection : userConnectionsWaitingList){
                        if(time - userConnection.getTimeCreated() > 5000){
                            userConnectionsWaitingList.remove(userConnection);

                            if(userConnection.getUuid()==null){
                                userConnection.close();
                                connectionClosed++;
                            }
                        }
                    }
                    logger.info("There was "+userConnectionsWaitingList.size()+" connection waiting UUID and "+connectionClosed+" have been closed.");
                    logger.info("Currently there is "+userConnections.size()+" player connected.");

                    //System.out.println("There was "+clone.size()+" connection waiting UUID and "+connectionClosed+" have been closed.");
                    //System.out.println("Currently there is "+playerConnections.size()+" player connected.");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        logger.error("InterruptedException()" , ex);
                    }
                }
            }
        }, "Server MinecraftGUI cleaning Thread").start();
    }

    @Listener
    public void onPlayerLoginEvent(ClientConnectionEvent.Login event){
    }



    @Listener
    public void onPlayerDisconnectEvent(ClientConnectionEvent.Disconnect event){
        try{
            userConnections.get(event.getTargetEntity().getUniqueId()).remove();
        }catch (Exception ex){
            InversionOfControl.get().build(EscendiaLogger.class).error(ex.getMessage());
        }
    }

    public void remove(UserConnection connection){
        logger.info("Remove Connection for " + connection.getUuid());
        userConnections.remove(connection.getUuid());
    }

    public void add(UserConnection connection){
        logger.info("Add Connection for " + connection.getUuid());
        userConnections.put(connection.getUuid(), connection);
    }

    public UserConnection getUserConnect(Player player) {
        return userConnections.get(player.getUniqueId());
    }
}
