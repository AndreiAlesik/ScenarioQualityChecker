package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.base.Scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


@RestController
@RequestMapping("/{text}")
public class ScenarioshowController {
    private static final Logger logger = LoggerFactory.getLogger(Scenario.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text) throws FileNotFoundException {

        // log the parameters
        logger.debug(text);
        String out;

        File tempFile = new File("./files/input/"+text);
        boolean exists = tempFile.exists();
        if(exists)
        {
            Scenario s=new Scenario(text);
            s.Scenarioshow();
            File myFile = new File("./files/output/Scenarioshow.json");
            Scanner myReader = new Scanner(myFile);
            out=myReader.nextLine();
            myReader.close();
        }
        else {
            out="Error";
        }

        return out;

    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text, @RequestBody String out) throws FileNotFoundException {

        logger.debug(text);
        logger.debug(out);

        return out;
    }
}