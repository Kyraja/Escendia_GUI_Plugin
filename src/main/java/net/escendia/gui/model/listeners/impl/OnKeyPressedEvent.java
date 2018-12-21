package net.escendia.gui.model.listeners.impl;

import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.listeners.GuiEvents;

import java.util.ArrayList;
import java.util.UUID;

public class OnKeyPressedEvent extends GuiEvents {

    private ArrayList<Integer> keyPressed;

    public OnKeyPressedEvent(UUID playerUUID, Element element, ArrayList<Integer> keyPressed) {
        super(playerUUID, element);
        this.keyPressed = keyPressed;
    }

    public ArrayList<Integer> getKeyPressed() {
        return keyPressed;
    }
}
