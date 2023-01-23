package pl.put.poznan.transformer.logic.VisitorPattern;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

public class KeyWordVisitor implements Visitable{
    private int keyWords;

    /*visit(Step step): This method is called when a Step object is visited.
    It takes the value of the step and splits it into an array of words, then it checks each word in the array to see
    if it's "IF:", "ELSE:" or "FOR" and if it is it increments the keyWords variable by 1.
     */
    @Override
    public void visit(Step step) {
        String[] temp = step.value.split(" ");

        for (String words : temp) {
            if (words.equals("IF:") || words.equals("ELSE:") || words.equals("FOR")) {
                keyWords += 1;
            }
        }
    }
    /*getKeyWords(): This method returns the final count of keywords and it also prints out the number of keywords.
     */
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
