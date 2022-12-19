package pl.put.poznan.transformer.base;

import com.google.gson.Gson;
import pl.put.poznan.transformer.logic.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Scenario {
    public String title;
    public ArrayList<String> actors;
    public String systemActor;
    public SubScenario mySubScenario;
    public myInt startInt;
    public myInt numInt;
    public ArrayList<String> list;

    public JsonReader json_scenario;
    public Scenario(String file){
        title = "";
        actors = new ArrayList<>();
        systemActor = "";
        mySubScenario = new SubScenario();
        startInt = new myInt(0);
        numInt = new myInt(3);
        json_scenario = new JsonReader();

        json_scenario.main(file);

        this.title = json_scenario.return_title();
        this.actors = json_scenario.return_actors();
        this.systemActor = json_scenario.systemActor;

        list = FileReader.read("ReadJson.txt");
    }

    public Scenario(Scenario tmp){
        this.title=tmp.title;
        this.actors=tmp.actors;
        this.systemActor=tmp.systemActor;
        this.mySubScenario=tmp.mySubScenario;
        this.startInt=tmp.startInt;
        this.numInt= tmp.numInt;
        this.list=tmp.list;
    }


    public void Scenarioshow() {
        Scenario tmp=new Scenario(this);
        tmp.mySubScenario=new SubScenario();
        tmp.startInt.reset();
        tmp.mySubScenario.addContent(startInt, list, 0);
        tmp.mySubScenario.accept(new ShowScenarioVisitor());
        this.mySubScenario=tmp.mySubScenario;
        this.Save2JSON(tmp,"./json/Scenarioshow.json");
    }

    public void Scenarionumershow() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("./files/out.txt");

        Scenario tmp=new Scenario(this);
        tmp.mySubScenario=new SubScenario();
        tmp.startInt.reset();
        tmp.mySubScenario.numerized(numInt, list, 0, 0, "");
        tmp.mySubScenario.accept(new ShowScenarioVisitor());

        tmp.Savetofile(pw);
        this.Save2JSON(tmp,"./json/Scenarionumershow.json");
        pw.close();
    }

    public void Scenariolvlshow(int stop){
        Scenario tmp=new Scenario(this);
        tmp.mySubScenario=new SubScenario();
        tmp.startInt.reset();
        tmp.mySubScenario.lvlshow(startInt, list, 0,stop);
        tmp.mySubScenario.accept(new ShowScenarioVisitor());
        this.Save2JSON(tmp,"./json/Scenariolvlshow.json");
    }

    public int Stepscount() {
        this.startInt.reset();
        this.mySubScenario=new SubScenario();
        this.mySubScenario.addContent(startInt, list, 0);

        StepCountVisitor v = new StepCountVisitor();
        this.mySubScenario.accept(v);
        int value = v.getStepCount();

//        this.mySubScenario.step_counter(this.mySubScenario);
//        int value = this.mySubScenario.get_steps_count();
        this.mySubScenario.Save2JSONint(value,"./json/steps_count.json");
        return value;
    }

    public void Keywords()
    {
        this.startInt.reset();
        this.mySubScenario=new SubScenario();
        this.mySubScenario.addContent(startInt, list, 0);

        //this.mySubScenario.key_word_counter(this.mySubScenario);
        //this.mySubScenario.get_key_words_count();
        KeyWordsVisitor v = new KeyWordsVisitor();
        this.mySubScenario.accept(v);
        int value = v.getKeyWords();
        this.mySubScenario.Save2JSONint(value,"./json/Keywords.json");
    }

    public void Stepscheck(){
        this.startInt.reset();
        this.mySubScenario=new SubScenario();
        this.mySubScenario.addContent(startInt, list, 1);

        //this.mySubScenario.step_check(this.mySubScenario, actors, systemActor);
        //this.mySubScenario.get_invalid_steps();

        StepCheckVisitor v = new StepCheckVisitor(this.actors, this.systemActor);
        this.mySubScenario.accept(v);

        ArrayList<Step> steps = v.getInvalidSteps();
        this.mySubScenario.Save2JSON(steps,"./json/Stepscheck.json");
    }

    public  void Save2JSON(Scenario s,String fileJson){
        FileWriter file = null;
        //private static FileWriter file;
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("./files/"+fileJson);
            file.write(new Gson().toJson(s));

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                assert file != null;
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Savetofile(PrintWriter output){
        output.println("Tytuł: "+this.title);
        output.println("Aktorzy: "+this.actors);
        output.println("Aktor systemowy: "+this.systemActor);

        //this.mySubScenario.numerized(startInt, list, 0, 0, "");

        for (int y=0; y<this.mySubScenario.save.size(); y++){
            output.println(this.mySubScenario.save.get(y));
        }
    }
}