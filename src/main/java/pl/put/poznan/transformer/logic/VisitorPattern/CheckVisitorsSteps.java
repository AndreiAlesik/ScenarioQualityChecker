package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.ArrayList;
import java.util.Collections;

public class CheckVisitorsSteps implements Visitable {
    private ArrayList<Step> invalidSteps = new ArrayList<Step>();
    private ArrayList<String> actors = new ArrayList<String>();
    private String sysActor;

    public CheckVisitorsSteps(ArrayList<String> actors, String sysActor) {
        this.actors = actors;
        this.sysActor = sysActor;
    }

    @Override
    public void visit(Step step) {
        boolean startingWithActor = false;

        String[] temp = step.value.split(" ");
        ArrayList<String> words = new ArrayList<String>();

        Collections.addAll(words, temp);

        if (words.get(0).equals("IF:") || words.get(0).equals("ELSE:")) {
            words.remove(0);
        }

        for (String actor : actors) {
            if (words.get(0).equals(actor)) startingWithActor = true;
        }

        if (words.get(0).equals(sysActor)) startingWithActor = true;
        if (words.get(0).equals("FOR") || words.get(0).equals("")) startingWithActor = true;

        if (!startingWithActor) this.invalidSteps.add(step);
    }

    public ArrayList<Step> getInvalidSteps() {
        System.out.println("Kroki nie zaczynające sie od aktora: ");
        ArrayList<Step> out = new ArrayList<Step>();
        for (Step s : invalidSteps) {
            System.out.println(s.value);
            out.add(s);
        }


        this.invalidSteps.clear();
        return out;
    }

    @Override
    public void visit(SubScenario subScenario) {

    }

}
