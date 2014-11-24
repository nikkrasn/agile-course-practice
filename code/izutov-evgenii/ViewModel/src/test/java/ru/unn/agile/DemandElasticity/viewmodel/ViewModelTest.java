package ru.unn.agile.DemandElasticity.viewmodel;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import ru.unn.agile.DemandElasticity.Model.DemandType;
import ru.unn.agile.DemandElasticity.Model.GoodType;
import ru.unn.agile.DemandElasticity.Model.GoodsPairType;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.start1Property().get());
        assertEquals("", viewModel.finish1Property().get());
        assertEquals("", viewModel.start2Property().get());
        assertEquals("", viewModel.finish2Property().get());
        assertEquals(DemandElasticityType.ByPrice, viewModel.demandElasticityTypeProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportNotNumber() {
        viewModel.start1Property().set("t");

        assertEquals(Status.NOT_NUMBER.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.start1Property().set("13");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportNotPositive() {
        viewModel.start2Property().set("-5");

        assertEquals(Status.NOT_POSITIVE.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setCorrectInputData();
        viewModel.finish2Property().set("h");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.finish2Property().set("121");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setCorrectInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetByPriceDemandElasticityType() {
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);

        assertEquals(DemandElasticityType.ByPrice, viewModel.demandElasticityTypeProperty().get());
    }

    @Test
    public void byPriceIsDefaultDemandElasticityType() {
        assertEquals(DemandElasticityType.ByPrice, viewModel.demandElasticityTypeProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setCorrectInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetNotNumberMessage() {
        viewModel.finish1Property().set("ue");

        assertEquals(Status.NOT_NUMBER.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetNotPositiveMessage() {
        viewModel.finish2Property().set("-92");

        assertEquals(Status.NOT_POSITIVE.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWrongArgumentsOnWrongDataCalculation() {
        viewModel.start1Property().set("0");
        viewModel.finish1Property().set("0");
        viewModel.start2Property().set("13");
        viewModel.finish2Property().set("11");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);

        viewModel.calculate();

        assertEquals(Status.WRONG_ARGUMENTS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void descriptionIsUndefinedOnEqualDataCalculation() {
        viewModel.start1Property().set("12");
        viewModel.finish1Property().set("12");
        viewModel.start2Property().set("100");
        viewModel.finish2Property().set("100");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);

        viewModel.calculate();

        assertEquals("Demand is undefined", viewModel.descriptionProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByPriceHasCorrectResult() {
        viewModel.start1Property().set("507");
        viewModel.finish1Property().set("132");
        viewModel.start2Property().set("1");
        viewModel.finish2Property().set("4");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);
        double expectedValue = ((132d - 507d) / (507d + 132d)) / ((4d - 1d) / (1d + 4d));

        viewModel.calculate();

        assertEquals(Double.toString(expectedValue), viewModel.resultProperty().get());
        assertEquals(DemandType.Inelastic.toString(), viewModel.descriptionProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByIncomeHasCorrectResult() {
        viewModel.start1Property().set("210");
        viewModel.finish1Property().set("37");
        viewModel.start2Property().set("7");
        viewModel.finish2Property().set("8");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByIncome);
        double expectedValue = ((37d - 210d) / (210d + 37d)) / ((8d - 7d) / (7d + 8d));

        viewModel.calculate();

        assertEquals(Double.toString(expectedValue), viewModel.resultProperty().get());
        assertEquals(GoodType.Inferior.toString(), viewModel.descriptionProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByCrossPriceHasCorrectResult() {
        viewModel.start1Property().set("400");
        viewModel.finish1Property().set("200");
        viewModel.start2Property().set("4");
        viewModel.finish2Property().set("5");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByCrossPrice);
        double expectedValue = ((200d - 400d) / (400d + 200d)) / ((5d - 4d) / (5d + 4d));

        viewModel.calculate();

        assertEquals(Double.toString(expectedValue), viewModel.resultProperty().get());
        assertEquals(GoodsPairType.Complementary.toString(), viewModel.descriptionProperty().get());
    }

    private void setCorrectInputData() {
        viewModel.start1Property().set("14");
        viewModel.finish1Property().set("17");
        viewModel.start2Property().set("357");
        viewModel.finish2Property().set("124");
    }
}