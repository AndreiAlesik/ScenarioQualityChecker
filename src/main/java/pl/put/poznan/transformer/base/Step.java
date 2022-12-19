package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.VisitorPattern.Visitable;

public class Step {

    public String value;
    public int level;


    public Step(int a, String tmp) {
        value = tmp;
        level = a;
    }

    public void accept(Visitable v){

        v.visit(this);

    }
}