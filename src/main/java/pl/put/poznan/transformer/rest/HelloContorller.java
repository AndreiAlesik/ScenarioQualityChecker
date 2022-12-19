package pl.put.poznan.transformer.rest;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;
import pl.put.poznan.transformer.logic.Visitor;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@RestController
public class HelloController {
    @GetMapping("/first/{id}")
    public String Reciving(@PathVariable String id) {
        // log the parameters

        return id;
    }

}