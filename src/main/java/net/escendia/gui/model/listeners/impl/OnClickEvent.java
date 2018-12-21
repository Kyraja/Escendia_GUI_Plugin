package net.escendia.gui.model.listeners.impl;

import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.listeners.GuiEvents;

import java.util.UUID;

public class OnClickEvent extends GuiEvents {
    public OnClickEvent(UUID playerUUID, Element element) {
        super(playerUUID, element);
    }
}
