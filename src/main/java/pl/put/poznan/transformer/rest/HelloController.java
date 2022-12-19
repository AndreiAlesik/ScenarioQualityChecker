package pl.put.poznan.transformer.rest;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/first/{id}")
    public String Receiving(@PathVariable String id) {
        // log the parameters

        return id;
    }

}