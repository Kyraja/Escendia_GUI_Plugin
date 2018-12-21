package net.escendia.gui.model.components.impl;

import net.escendia.gui.model.components.Char;
import net.escendia.gui.model.components.ElementValuable;
import net.escendia.gui.model.components.GroupChar;
import net.escendia.gui.model.components.TextLine;
import net.escendia.gui.model.form.style.FormStyle;

import java.awt.*;
import java.util.ArrayList;

public class Text extends ElementValuable<String> {



    private transient ArrayList<GroupChar> groupChars = new ArrayList<>();

    private String text = "";

    /*
    Temp Variables
     */

    private transient String value;

    public Text(){
        super();
    }

    public Text(String text){
        super();
        this.setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /*
    ------- Cursor Methods ---------
     */




    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
