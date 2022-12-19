package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

public class ShowVisitorScenario implements Visitable{
    @Override
    public void visit(Step step) {
        StringBuilder tmp=new StringBuilder();
        for(int i=0;i<step.level;i++) tmp.append("\t");

        System.out.println(tmp+step.value);
    }

    @Override
    public void visit(SubScenario subScenario) {

    }
}
