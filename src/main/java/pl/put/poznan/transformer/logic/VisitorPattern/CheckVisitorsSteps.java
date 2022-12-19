package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.ArrayList;
import java.util.Collections;

public class CheckVisitorsSteps implements Visitable {
    private ArrayList<Step> invalid_steps = new ArrayList<Step>();
    private ArrayList<String> actors = new ArrayList<String>();
    private String sys_actor;

    public CheckVisitorsSteps(ArrayList<String> actors, String sys_act){
        this.actors = actors;
        this.sys_actor = sys_act;
    }

    @Override
    public void visit(SubScenario sc) {

    }

    @Override
    public void visit(Step s) {
        boolean starting_with_actor= false;

        String [] temp = s.value.split(" ");
        ArrayList<String> words = new ArrayList<String>();

        Collections.addAll(words, temp);

        if(words.get(0).equals("IF:") || words.get(0).equals("ELSE:")){
            words.remove(0);
        }

          for (String actor : actors){
            if(words.get(0).equals(actor)) starting_with_actor = true;
        }

        if(words.get(0).equals(sys_actor)) starting_with_actor = true;
        if(words.get(0).equals("FOR") || words.get(0).equals("")) starting_with_actor = true;

        if (!starting_with_actor) this.invalid_steps.add(s);
    }

    public ArrayList<Step> getInvalidSteps(){
        System.out.println("Kroki nie zaczynające sie od aktora: ");
        ArrayList<Step> out = new ArrayList<Step>();
        for(Step s : invalid_steps){
            System.out.println(s.value);
            out.add(s);
        }
        //System.out.println("\nZerowanie invalid_steps...");

        this.invalid_steps.clear();
        return out;
    }

}
