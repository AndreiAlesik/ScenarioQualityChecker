package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

public interface Visitable {
    void visit(Step step);
    void visit(SubScenario subScenario);

}
