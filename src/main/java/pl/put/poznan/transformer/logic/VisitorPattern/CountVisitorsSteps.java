package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.Objects;
/*
CountVisitorsSteps is a class that implements the Visitor pattern,
 which is a way of separating an algorithm from an object structure on which it operates.
 This allows for adding new operations to existing object structures without modifying their classes.
*/
public class CountVisitorsSteps implements Visitable{
    private int quantity=0;
    /*visit(Step step): This method is called when a Step object is visited.
    It checks if the step has a value, and if it does, it increments the quantity of steps by 1.
     */
    @Override
    public void visit(Step step) {
        if(!Objects.equals(step.value, "")) this.quantity+=1;
    }

    @Override
    public void visit(SubScenario subScenario) {

    }
    /*getStepCount(): This method returns the final count of steps and it also prints out the number of steps.
     */
    public int getStepCount(){
        System.out.println("Liczba krokow: " + (this.quantity));
        int out = this.quantity;
        this.quantity = 0;
        return out;
    }
    /*setQuantity(int quantity): This method sets the quantity of steps.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
