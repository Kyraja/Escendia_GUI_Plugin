package net.escendia.gui.controll;

import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.gui.view.EscendiaGui;
import net.escendia.ioc.Inject;
import net.escendia.ioc.InversionOfControl;
import net.escendia.ioc.Singleton;

import java.util.HashMap;
import java.util.UUID;

@Singleton
public class GUIService {

    private HashMap<UUID, EscendiaGui> guiMap;

    @Inject
    public EscendiaLogger logger;

    @Inject
    public PlayerService playerService;

    @Inject
    public PacketService packetService;


    public GUIService(){
        logger = InversionOfControl.get().build(EscendiaLogger.class);
        playerService = InversionOfControl.get().build(PlayerService.class);
        packetService = InversionOfControl.get().build(PacketService.class);
        guiMap = new HashMap<UUID, EscendiaGui>();

    }


    public void addElement(UUID playerUUID, Element element) {
        getEscendiaGui(playerUUID).addElement(element);
    }

    public void removeElement(UUID playerUUID, Element element) {
        getEscendiaGui(playerUUID).remove(element);
    }

    public void updateElement(UUID playerUUID, Element element) {
        getEscendiaGui(playerUUID).update(element);
    }

    public EscendiaGui getEscendiaGui(UUID playerUUID) {
        return guiMap.get(playerUUID);
    }

    public void createGUI(UUID uuid) {
        this.guiMap.put(uuid, new EscendiaGui(uuid));
    }

    public void removeGUI(UUID uuid) {
        this.guiMap.remove(uuid);
    }
}
