package net.escendia.gui.model.components.impl;

import net.escendia.gui.model.components.ElementValuable;
import net.escendia.gui.model.form.Form;

public class CheckBox extends ElementValuable<Boolean> {

    public boolean value = false;
    public Form onTrue;
    public Form onFalse;

    public CheckBox(){
        super();
    }

    public CheckBox(Form onTrue, Form onFalse) {
        super(onTrue);
        this.onTrue = onTrue;
        this.onFalse = onFalse;
    }

    public void setStatus(boolean value){
        this.value = value;
        if(this.value){
            setForm(onTrue);
        }else{
            setForm(onFalse);
        }
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}
