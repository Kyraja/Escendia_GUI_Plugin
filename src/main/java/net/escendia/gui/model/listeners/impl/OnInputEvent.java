package net.escendia.gui.model.listeners.impl;

import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.listeners.GuiEvents;

import java.util.UUID;

public class OnInputEvent extends GuiEvents {

    private char input;

    public OnInputEvent(UUID playerUUID, Element element, char input) {
        super(playerUUID, element);
        this.input = input;
    }

    public char getInput() {
        return input;
    }
}
