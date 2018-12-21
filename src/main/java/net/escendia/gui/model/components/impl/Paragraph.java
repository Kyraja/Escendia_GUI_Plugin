package net.escendia.gui.model.components.impl;

import net.escendia.gui.model.components.Element;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

public class Paragraph extends Text implements ClipboardOwner {

    public Element buttonLineBefore;
    public Element buttonLineAfter;

    public Paragraph(String text, Element buttonLineAfter, Element buttonLineBefore){
        super(text);
        addChildrenElement(buttonLineAfter);
        addChildrenElement(buttonLineBefore);
        this.buttonLineAfter = buttonLineAfter;
        this.buttonLineBefore = buttonLineBefore;

    }

    public Paragraph(){
        super("");
    }

    public Element getButtonLineAfter() {
        return buttonLineAfter;
    }

    public Element getButtonLineBefore() {
        return buttonLineBefore;
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {}

    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
    }
}
