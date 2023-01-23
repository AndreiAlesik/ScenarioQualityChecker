package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.ArrayList;
import java.util.Collections;

/*CheckVisitorsSteps is a class that implements the Visitor pattern,
which is a way of separating an algorithm from an object structure on which it operates.
This allows for adding new operations to existing object structures without modifying their classes.

The CheckVisitorsSteps class has two main responsibilities:

Checking if steps in a given scenario structure starts with a valid actor (system or predefined actors) or a valid keyword (IF:, ELSE:, FOR)
Returning the invalid steps*/
public class CheckVisitorsSteps implements Visitable {
    private ArrayList<Step> invalid_steps = new ArrayList<Step>();
    private ArrayList<String> actors = new ArrayList<String>();
    private String sys_actor;

    public CheckVisitorsSteps(ArrayList<String> actors, String sys_act) {
        this.actors = actors;
        this.sys_actor = sys_act;
    }

    /*visit(Step s): This method is called when a Step object is visited.
    It takes the value of the step, split it into an array of words,
     then it checks if the first word is a valid keyword or a valid actor and if it is not it adds the step to the invalid_steps ArrayList.

     */
    @Override
    public void visit(SubScenario sc) {

    }
    /*visit(SubScenario sc): This method is called when a SubScenario object is visited.
    In this class, it doesn't do anything, but it could be overridden in a subclass to implement additional functionality.
     */
    @Override
    public void visit(Step s) {
        boolean starting_with_actor = false;

        String[] temp = s.value.split(" ");
        ArrayList<String> words = new ArrayList<String>();

        Collections.addAll(words, temp);

        if (words.get(0).equals("IF:") || words.get(0).equals("ELSE:")) {
            words.remove(0);
        }

        for (String actor : actors) {
            if (words.get(0).equals(actor)) starting_with_actor = true;
        }

        if (words.get(0).equals(sys_actor)) starting_with_actor = true;
        if (words.get(0).equals("FOR") || words.get(0).equals("")) starting_with_actor = true;

        if (!starting_with_actor) this.invalid_steps.add(s);
    }
    /*getInvalidSteps(): This method returns the invalid steps and it also prints out the invalid steps on the console.
     */
    public ArrayList<Step> getInvalidSteps() {
        System.out.println("Kroki nie zaczynające sie od aktora: ");
        ArrayList<Step> out = new ArrayList<Step>();
        for (Step s : invalid_steps) {
            System.out.println(s.value);
            out.add(s);
        }
        //System.out.println("\nZerowanie invalid_steps...");

        this.invalid_steps.clear();
        return out;
    }

}
