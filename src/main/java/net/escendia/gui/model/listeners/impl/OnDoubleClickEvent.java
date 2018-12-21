package net.escendia.gui.model.listeners.impl;

import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.listeners.GuiEvents;

import java.util.UUID;

public class OnDoubleClickEvent extends GuiEvents {
    public OnDoubleClickEvent(UUID playerUUID, Element element) {
        super(playerUUID, element);
    }
}
