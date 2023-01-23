package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

/*The ShowVisitorScenario class has one main responsibility:
Printing the value of each step in a given scenario structure in a structured way, i.e. with indentation based on the level of the step.
 */
public class ShowVisitorScenario implements Visitable {
    /*visit(Step step): This method is called when a Step object is visited.
    It creates a StringBuilder object to hold the indentation for the step, depending on the level of the step.
    It then prints out the step value with the indentation.

     */
    @Override
    public void visit(Step step) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < step.level; i++) tmp.append("\t");

        System.out.println(tmp + step.value);
    }


    @Override
    public void visit(SubScenario subScenario) {

    }
}
