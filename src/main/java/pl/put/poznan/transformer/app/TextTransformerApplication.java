package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.base.Scenario;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, FileNotFoundException {
        SpringApplication.run(TextTransformerApplication.class, args);
        String file="file4.json";
        //Obiekty scenariusze
        Scenario x = new Scenario(file);

        ////Wyświetlenie scenariusza
        System.out.println("\n"+"Wyświetlenie scenariusza");
        x.Scenarioshow();

        ////Wyświetlenie scenariusza do określonego poziomu zagłębienia (poziom główny to 1)
        System.out.println("\n"+"Scenariusz do poziomu zagłebienia");
        x.Scenariolvlshow(1);

        ////Wyświetlenie scenariusza z numeracją korków
        System.out.println("\n"+"Scenariusz z numeracją kroków");
        x.Scenarionumershow();

        ////Wyświetlenie liczby kroków scenariusza
        System.out.println("\n" + "Liczba kroków");
        x.Stepscount();

        ////Wyświetlanie liczby słów kluczowych
        System.out.println("\n" + "Słowa kluczowe");
        x.Keywords();

        ////Wyświetlanie niepoprawnych kroków scenariusza
        System.out.println("\n" + "Niepoprawne kroki");
        x.Stepscheck();

    }
}
