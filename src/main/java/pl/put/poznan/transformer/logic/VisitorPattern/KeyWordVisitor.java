package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

public class KeyWordVisitor implements Visitable{
    private int keyWords;

    @Override
    public void visit(Step step) {
        String[] temp = step.value.split(" ");

        for (String words : temp) {
            if (words.equals("IF:") || words.equals("ELSE:") || words.equals("FOR")) {
                keyWords += 1;
            }
        }
    }
    public int getKeyWords(){
        System.out.println("Liczba słów kluczowych: "+ this.keyWords);
        int output=this.keyWords=0;
        this.keyWords=0;
        return output;
    }
    @Override
    public void visit(SubScenario subScenario) {

    }
}
