package net.escendia.gui.model.components.impl;


import net.escendia.gui.model.components.Element;

public class TextArea extends EditableText{


    private Element buttonLineBefore;
    private Element buttonLineAfter;
    private boolean canUpdateText;

    public TextArea(){
        super("");

    }

    public TextArea(String text, Element buttonLineBefore, Element buttonLineAfter){
        super(text);
        this.add(buttonLineAfter);
        this.add(buttonLineBefore);
        this.add(buttonLineBefore);
        this.add(buttonLineAfter);
        this.buttonLineBefore = buttonLineBefore;
        this.buttonLineAfter = buttonLineAfter;

      }

      public void add(Element element){
        if(buttonLineAfter != element && buttonLineBefore != element)
            super.addChildrenElement(element);
    }

    @Override
    public String getValue() {
        return getText();
    }

    @Override
    public void setValue(String value) {
        setText(value);
    }
}
