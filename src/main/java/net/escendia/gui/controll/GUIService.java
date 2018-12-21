package net.escendia.gui.controll;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.JsonObject;
import net.escendia.gui.EscendiaGUIPlugin;
import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.gui.GeneralGUIData;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.gui.view.EscendiaGui;
import net.escendia.ioc.Inject;
import net.escendia.ioc.InversionOfControl;
import net.escendia.ioc.Singleton;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    @Inject
    public FileService fileService;


    public GUIService(){
        logger = InversionOfControl.get().build(EscendiaLogger.class);
        playerService = InversionOfControl.get().build(PlayerService.class);
        packetService = InversionOfControl.get().build(PacketService.class);
        fileService = InversionOfControl.get().build(FileService.class);
        guiMap = new HashMap<UUID, EscendiaGui>();
        createDefaultFiles();
    }


    /**
     * Adds a element and sends it to the client
     * @param playerUUID
     * @param element
     */
    public void addElement(UUID playerUUID, Element element) {
        getEscendiaGui(playerUUID).addElement(element);
    }

    /**
     * Removes a element and sends it to the client
     * @param playerUUID
     * @param element
     */
    public void removeElement(UUID playerUUID, Element element) {
        getEscendiaGui(playerUUID).remove(element);
    }

    /**
     * Update Elements from the current gui and sends a packet to the client
     * @param playerUUID
     * @param element
     */
    public void updateElement(UUID playerUUID, Element element) {
        getEscendiaGui(playerUUID).update(element);
    }

    /**
     * Get the current GUI.
     * If there is no current GUI a new one will be created and added to the service.
     * @param playerUUID
     * @return EscendiaGui
     */
    public EscendiaGui getEscendiaGui(UUID playerUUID) {
        if(guiMap.containsKey(playerUUID))return guiMap.get(playerUUID);
        else return createGUI(playerUUID);
    }

    /**
     * Creates a new GUI by a player uuid
     * @param uuid
     * @return
     */
    public EscendiaGui createGUI(UUID uuid) {
        EscendiaGui escendiaGUI = new EscendiaGui(uuid);
        this.guiMap.put(uuid, escendiaGUI);
        return escendiaGUI;
    }

    /**
     * Removes the current gui.
     * @param uuid
     */
    public void removeGUI(UUID uuid) {
        this.guiMap.remove(uuid);
    }



    /**
     * Create default files like GeneralGUIData, a TestGUI-FILE
     */
    private void createDefaultFiles() {
        String configDir = fileService.getConfigDir();

        GeneralGUIData generalGUIData = new GeneralGUIData();
        HashMap<String, String> images = new HashMap<>();
        images.put("testimage.jpg", "https://sample-videos.com/img/Sample-jpg-image-50kb.jpg");
        generalGUIData.setImages(images);

        fileService.writeFile(configDir + File.separator + "GeneralData.json", generalGUIData.toJson().toString());

    }

    /**
     * Creates a element by a json File.
     * The file hast to put into the config/escendiagui folder..
     * @param jsonFile
     */
    public Element createElementByJsonFile(String jsonFile, boolean fresh){
        try {
            InputStream stream = InversionOfControl.get().build(FileService.class).getFile("testgui.json");
            String json = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
            Element element = new Element().fromJson(json);
            if(fresh)element.setElementUUID(UUID.randomUUID());
        } catch (Exception e) {
            logger.error("Could not read/create Element from File! " + jsonFile, e);
        }
        return null;
    }


}
