package net.escendia.gui.model.listeners.impl;

import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.gui.Mouse;
import net.escendia.gui.model.listeners.GuiEvents;

import java.util.UUID;

public class OnMouseButtonUpEvent extends GuiEvents {

    private Mouse.Button clickedButton;

    public OnMouseButtonUpEvent(UUID playerUUID, Element element, Mouse.Button clickedButton) {
        super(playerUUID, element);
        this.clickedButton = clickedButton;
    }

    public Mouse.Button getClickedButton() {
        return clickedButton;
    }
}
