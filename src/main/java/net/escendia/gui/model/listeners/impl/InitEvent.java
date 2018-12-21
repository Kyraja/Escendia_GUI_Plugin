package net.escendia.gui.model.listeners.impl;

import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.listeners.ConnectionEvent;
import net.escendia.gui.model.listeners.GuiEvents;

import java.util.UUID;

/**
 * Event to handle connection initialisation
 */
public class InitEvent extends ConnectionEvent {
    public InitEvent(UUID playerUUID) {
        super(playerUUID);
    }
}
