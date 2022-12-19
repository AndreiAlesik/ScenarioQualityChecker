package pl.put.poznan.transformer.logic.filereading;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class ReadJSON {
    public static String title_2;
    public static ArrayList<String> actors_2;
    public static String systemActor;
    public static ArrayList<Object> mySubScenario;

    public static void main(String filename) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader readFile = new FileReader("/files/input/" + filename)) {
            try {
                Object object = jsonParser.parse(readFile);
                JSONArray employeeList = (JSONArray) object;
                JSONObject startObject = (JSONObject) employeeList.get(0);
                title_2 = title(startObject);
                actors_2 = actors(startObject);
                systemActor = systemActors(startObject);
                mySubScenario = step(startObject);
                info_txt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    private static String title(JSONObject scenarioObject) {
        String title = (String) scenarioObject.get("title");
        return title;
    }

    private static ArrayList<String> actors(JSONObject scenarioObject) {
        ArrayList<String> actorList = new ArrayList<>();
        JSONArray actors = (JSONArray) scenarioObject.get("actors");
        if (actors.size() > 0) {
            for (int i = 0; i < actors.size(); i++) {
                actorList.add(String.valueOf(actorList.get(i)));
            }
        } else System.out.println("Actors empty");
        return actorList;
    }

    private static String systemActors(JSONObject scenarioObject) {
        return (String) scenarioObject.get("systemActor");
    }

    private static ArrayList<Object> step(JSONObject scenarioObject) {
        JSONArray steps = (JSONArray) scenarioObject.get("steps");
        ArrayList<Object> tempList = new ArrayList<>();
        if (steps.size() > 0) {
            for (int i = 0; i < steps.size(); i++) {
                JSONObject newStep = (JSONObject) steps.get(i);
                ArrayList<Object> out = readStep(newStep);
                tempList.add(out);
            }
        }
        return tempList;
    }

    private static ArrayList<Object> readStep(JSONObject step) {
        ArrayList<Object> out = new ArrayList<>();
        out.add((String) step.get("content"));
        JSONArray subStep = (JSONArray) step.get("subSteps");
        if (subStep.size() < 1) {
            return out;
        } else {
            for (int i = 0; i < subStep.size(); i++) {
                JSONObject newStep = (JSONObject) subStep.get(i);
                ArrayList<Object> out2 = new ArrayList<>();
                out2 = readStep(newStep);
                out.add(out2);

            }
            return out;
        }
    }
    private static Boolean isEnd(String line, int y){
        return (y==line.length()-1) ? false : true;
    }
    private static String getLine(String line){
        String lin="";
        int y=0;
        for(int i=0; i< line.length();i++) {
            if (line.charAt(i) == '[') {
                for(y=i+1; y<line.length();y++) {
                    if (line.charAt(y) == ']') {
                        if (isEnd(line, y)){
                            for(int x=y+1; x<line.length(); x++){
                                lin = lin + "\n" + "<end>";
                            }
                            break;
                        }
                        else{break;}
                    }
                    lin = lin + line.charAt(y);
                }
                if (y == line.length())
                    lin = lin + "\n" + "<start>";
            }
        }
        return lin;
    }
    private static PrintWriter info_txt() throws FileNotFoundException{
        File file=new File("files=ReadJson.txt");
        PrintWriter out=new PrintWriter(file);
        out.println(title_2);
        out.println(actors_2);
        out.println(systemActor+"\n");
        for(Object objMySubScenario:mySubScenario){
            String l=objMySubScenario.toString();
            String[] line=l.split(",");
            for (Object objLine: line){
                String l2=objLine.toString();
                out.println(getLine(l2));
            }
        }
        out.close();
        return out;
    }
    public String returnTitle() {
        return title_2;
    }
    public ArrayList<String> getActors(){return actors_2;}
}


