package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.Visitor;

public class Step {

    public String value;
    public int level;


    public Step(int a, String tmp) {
        value = tmp;
        level = a;
    }

    public void accept(Visitor v){

        v.visit(this);

    }
}