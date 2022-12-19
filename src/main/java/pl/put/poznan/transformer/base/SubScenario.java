package pl.put.poznan.transformer.base;

import com.google.gson.Gson;
import pl.put.poznan.transformer.logic.VisitorPattern.Visitable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SubScenario {
    public ArrayList<Object> content;
    public ArrayList<String> save;

    SubScenario() {
        content = new ArrayList<>();
        save = new ArrayList<>();
    }

    public ArrayList<Object> addContent(myInt start, ArrayList<String> list,int lvl)
    {
        String line;

        for(;start.getValue()<list.size();start.increment()) {
            line = list.get(start.getValue());
            line=line.replaceAll("\t+","");
            if (line.equals("<start>")) {
                start.increment();

                SubScenario sub = new SubScenario();
                sub.addContent(start,list,lvl+1);
                content.add(sub);

            } else if (Objects.equals(line, "<end>")) {
                return content;
            }
            else {
                Step s = new Step(lvl,line);
                content.add(s);
            }
        }

        return content;
    }

    public ArrayList<Object> lvlshow(myInt start, ArrayList<String> list,int lvl, int stop)
    {
        String line;

        for(;start.getValue()<list.size();start.increment()) {
            line = list.get(start.getValue());
            line=line.replaceAll("\\t+","");
            if (line.equals("<start>")) {
                start.increment();

                SubScenario sub = new SubScenario();
                sub.lvlshow(start,list,lvl+1, stop);
                content.add(sub);

            } else if (Objects.equals(line, "<end>")) {
                return content;
            }
            else {
                if (lvl >= stop){
                    continue;
                }
                else{
                    Step s = new Step(lvl,line);
                    content.add(s);
                }
            }
        }

        return content;
    }

    public ArrayList<Object> numerized(myInt start, ArrayList<String> list,int lvl, int stp, String dep)
    {
        String line;

        for(;start.getValue()<list.size();start.increment()) {
            line = list.get(start.getValue());
            line=line.replaceAll("\\t+","");
            if (line.equals("<start>")) {
                start.increment();
                if (dep == ""){
                    dep = dep + String.valueOf(stp-1);
                }
                else{
                    dep = dep + "." + String.valueOf((stp-1));
                }


                SubScenario sub = new SubScenario();
                sub.numerized(start,list,lvl+1, 1, dep);
                dep="";
                for(int i=0;i<sub.save.size();i++)
                {
                    save.add(sub.save.get(i));
                }
                content.add(sub);

            } else if (Objects.equals(line, "<end>")) {
                return content;
            }
            else if(!Objects.equals(line, "")) {
                if (stp == 0){
                    Step s = new Step(lvl,line);
                    save.add(line);
                    content.add(s);
                    stp+=1;
                }
                else {
                    if (lvl > 0){
                        line = dep + "." + String.valueOf(stp) + "." + " " + line;
                        for (int i=0; i<lvl*3; i++){
                            line = " " + line;
                        }
                        Step s = new Step(lvl,line);
                        save.add(line);
                        content.add(s);
                        stp+=1;
                    }
                    else{
                        line = String.valueOf(stp) + "." + " " + line;
                        Step s = new Step(lvl,line);
                        save.add(line);
                        content.add(s);
                        stp+=1;
                    }

                }

            }
        }
        return content;
    }


    public void accept(Visitable v){
        v.visit(this);
        for (Object s : this.content){
            if(s.getClass()==Step.class) ((Step) s).accept(v);
            else ((SubScenario) s).accept(v);
        }
    }

    public  void Save2JSON(ArrayList<Step> s,String fileJson){
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
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void Save2JSONint(int i,String fileJson){
        FileWriter file = null;
        //private static FileWriter file;
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("./files/"+fileJson);
            file.write(new Gson().toJson(i));

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}