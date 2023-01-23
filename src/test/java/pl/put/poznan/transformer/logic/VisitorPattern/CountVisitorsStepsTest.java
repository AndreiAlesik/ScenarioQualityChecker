package pl.put.poznan.transformer.logic.VisitorPattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.base.Scenario;

import static org.junit.jupiter.api.Assertions.*;

class CountVisitorsStepsTest {
    private Scenario scenario;
    private CountVisitorsSteps countVisitorsSteps;

    @BeforeEach
    void setUp() {
        countVisitorsSteps=new CountVisitorsSteps();
        countVisitorsSteps.setQuantity(13);
        scenario=new Scenario("file4.json");
    }

    @Test
    void getStepCount() {
        assertEquals(13, countVisitorsSteps.getStepCount());
    }
}