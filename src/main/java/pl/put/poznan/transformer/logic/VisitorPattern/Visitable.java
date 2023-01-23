package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;
/*Visitable is an interface that defines the contract for objects that can be visited by a visitor class.*/
public interface Visitable {
    /*visit(Step step): This method is called when a Step object is visited by a visitor class.
    It should be implemented in the class that implements the Visitable interface to provide the desired behavior when a Step object is visited.
     */
    void visit(Step step);
    /*visit(SubScenario subScenario): This method is called when a SubScenario object is
    visited by a visitor class. It should be implemented in the class that implements the
     */
    void visit(SubScenario subScenario);

}
