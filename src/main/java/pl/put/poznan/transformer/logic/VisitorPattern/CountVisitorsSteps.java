package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.Objects;

public class CountVisitorsSteps implements Visitable{
    private int quantity=0;
    @Override
    public void visit(Step step) {
        if(!Objects.equals(step.value, "")) this.quantity+=1;
    }

    @Override
    public void visit(SubScenario subScenario) {

    }
    public int getStepCount(){
        System.out.println("Liczba krokow: " + (this.quantity));
        int out = this.quantity;
        this.quantity = 0;
        return out;
    }
}
