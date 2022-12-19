package pl.put.poznan.transformer.logic.filereading;
import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public  class ReadJSON {
    public static String title_2;
    public static ArrayList<String> actors_2;
    public static String systemActor;
    public static ArrayList<Object> mySubScenario;
    @SuppressWarnings("unchecked")
    public  static void main(String file)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("./files/input/"+file))
        {
            //Read JSON file
            try{
                Object obj = jsonParser.parse(reader);
                JSONArray employeeList = (JSONArray) obj;
                //System.out.println(employeeList);
                JSONObject startObject = (JSONObject) employeeList.get(0);
                title_2 = title(startObject);
                actors_2 = actors(startObject);
                systemActor = systemActors(startObject);
                mySubScenario = step(startObject);
                into_txt();
            } catch (Exception ignored) {
                System.out.println(ignored.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String title(JSONObject scenario){
        //Get employee object within list
        String title = (String) scenario.get("title");
        return  title;
    }

    private static ArrayList<String> actors(JSONObject scenario){
        //Get employee object within list
        ArrayList<String> actor_list = new ArrayList<>();
        JSONArray actors = (JSONArray) scenario.get("actors");
        if(actors.size() > 0 ){
            for( int i=0; i< actors.size(); i++) {
                actor_list.add(String.valueOf(actors.get(i)));
            }}else{
            System.out.println("Actors : []" );

        }
        return actor_list;
    }
    private static String systemActors(JSONObject scenario){
        //Get employee object within list
        String systemActor1 = (String) scenario.get("systemActor");
        return systemActor1;
    }

    private static ArrayList<Object> step(JSONObject scenario)
    {
        JSONArray Steps = (JSONArray) scenario.get("steps");

        ArrayList<Object> temp = new ArrayList<>();
        if(Steps.size() > 0 ){
            for( int i=0; i< Steps.size(); i++) {
                JSONObject newStep = (JSONObject) Steps.get(i);
                ArrayList<Object> out = read_step(newStep);
                temp.add(out);
            }
        }

        return temp;

        }
    private static ArrayList<Object> read_step(JSONObject step){
        String content = (String) step.get("content");
        ArrayList<Object> out = new ArrayList<>();
        out.add(content);
        JSONArray subStep = (JSONArray) step.get("subSteps");
        if(subStep.size() < 1){
            return out;
        }else{
            for( int i=0; i< subStep.size(); i++) {
                JSONObject newStep = (JSONObject) subStep.get(i);
                ArrayList<Object> out2 = new ArrayList<>();
                out2 = read_step(newStep);
                out.add(out2);

            }
        return out;
        }


    }

    private static Boolean czyend(String line, int y){
        if (y == line.length()-1){return false;}
        else{return true;}
    }

    private static String getline(String line){
        String lin = "";
        int y = 0;
        for(int i=0; i< line.length();i++) {
            if (line.charAt(i) == '[') {
                for(y=i+1; y<line.length();y++) {
                    if (line.charAt(y) == ']') {
                        if (czyend(line, y)){
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

    private static PrintWriter into_txt() throws FileNotFoundException {
        File file = new File("files/ReadJson.txt");

        PrintWriter out = new PrintWriter(file);

        out.println(title_2);
        out.println(actors_2);
        out.println(systemActor + "\n");

        for (Object o : mySubScenario) {
            String l = o.toString();
            String[] line = l.split(",");
            for (Object s : line){
                String l2 = s.toString();
                //System.out.println(l2);
                out.println(getline(l2));
            }
            }

        out.close();
        return out;
    }


    public String return_title(){
        return title_2;
    }
    public ArrayList<String> return_actors(){
        return actors_2;
    }

    }
