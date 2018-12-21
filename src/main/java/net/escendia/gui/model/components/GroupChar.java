package net.escendia.gui.model.components;

import net.escendia.gui.model.form.style.FormStyle;

import java.util.ArrayList;

public class GroupChar extends ArrayList<Char> {

    private boolean wordGroup = true;
    private int sizeLastWidth = 0;
    private double width = 0;
    private int index;
    private String line;

    public GroupChar(){}

    public double getWidth(){
        return width;
    }

    public int getIndex() {
        return index;
    }

    public boolean isWordGroup() {
        return wordGroup;
    }

    public void setWordGroup(boolean wordGroup) {
        this.wordGroup = wordGroup;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setIndex(int g) {
        this.index = index;
    }

}
