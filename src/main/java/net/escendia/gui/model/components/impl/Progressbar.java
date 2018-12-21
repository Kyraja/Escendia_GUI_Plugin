package net.escendia.gui.model.components.impl;

import net.escendia.gui.model.components.ElementValuable;
import net.escendia.gui.model.form.Rectangle;
import net.escendia.gui.model.form.style.impl.RectangleStandard;

public class Progressbar extends ElementValuable<Double> {

    public Rectangle progressForm;
    public double barPercentage = 0;

    public Progressbar(){
        super();
        progressForm = new RectangleStandard();
    }

    public Rectangle getProgressForm() {
        return progressForm;
    }

    public double getBarPercentage() {
        return barPercentage;
    }

    public void setProgressForm(Rectangle progressForm) {
        this.progressForm = progressForm;
    }

    public void setBarPercentage(double barPercentage) {
        if(barPercentage < 0)
            this.barPercentage = 0;
        else if(barPercentage > 1)
            this.barPercentage = 1;
        else
            this.barPercentage = barPercentage;
    }

    @Override
    public Double getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Double value) {
        setBarPercentage(value);
    }

    public static class Horizontal extends Progressbar{
        public Horizontal(){
            super();
        }

        @Override
        public void setBarPercentage(double barPercentage) {
            if(getBarPercentage() != barPercentage) {
                super.setBarPercentage(barPercentage);
                getProgressForm().getFormStyle().setWidth(barPercentage);
            }
        }
    }

    public static class Vertical  extends Progressbar{
        public Vertical(){
            super();
        }

        @Override
        public void setBarPercentage(double barPercentage) {
            if(getBarPercentage() != barPercentage) {
                super.setBarPercentage(barPercentage);
                getProgressForm().getFormStyle().setHeight(barPercentage);
            }
        }
    }
}
