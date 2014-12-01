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
        assertEquals(DemandElasticityType.ByPrice.getFirstRangeName(),
                viewModel.firstRangeProperty().get());
        assertEquals(DemandElasticityType.ByPrice.getSecondRangeName(),
                viewModel.secondRangeProperty().get());
        assertEquals("", viewModel.firstRangeStartProperty().get());
        assertEquals("", viewModel.firstRangeFinishProperty().get());
        assertEquals("", viewModel.secondRangeStartProperty().get());
        assertEquals("", viewModel.secondRangeFinishProperty().get());
        assertEquals(DemandElasticityType.ByPrice, viewModel.demandElasticityTypeProperty().get());
        assertEquals("", viewModel.calcResultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void canReportNotNumber() {
        viewModel.firstRangeStartProperty().set("t");

        assertEquals(Status.NOT_NUMBER.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.firstRangeStartProperty().set("13");

        assertEquals(Status.WAITING.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void canReportNotPositive() {
        viewModel.secondRangeStartProperty().set("-5");

        assertEquals(Status.NOT_POSITIVE.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setCorrectInputData();
        viewModel.secondRangeFinishProperty().set("h");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.secondRangeFinishProperty().set("121");

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
    public void rangeNamesChangedOnSetByIncomeDemandElasticityType() {
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByIncome);

        assertEquals(DemandElasticityType.ByIncome.getFirstRangeName(),
                viewModel.firstRangeProperty().get());
        assertEquals(DemandElasticityType.ByIncome.getSecondRangeName(),
                viewModel.secondRangeProperty().get());
    }

    @Test
    public void byPriceIsDefaultDemandElasticityType() {
        assertEquals(DemandElasticityType.ByPrice, viewModel.demandElasticityTypeProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setCorrectInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void canSetNotNumberMessage() {
        viewModel.firstRangeFinishProperty().set("ue");

        assertEquals(Status.NOT_NUMBER.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void canSetNotPositiveMessage() {
        viewModel.secondRangeFinishProperty().set("-92");

        assertEquals(Status.NOT_POSITIVE.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void statusIsWrongArgumentsOnWrongDataCalculation() {
        viewModel.firstRangeStartProperty().set("0");
        viewModel.firstRangeFinishProperty().set("0");
        viewModel.secondRangeStartProperty().set("13");
        viewModel.secondRangeFinishProperty().set("11");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);

        viewModel.calculate();

        assertEquals(Status.WRONG_ARGUMENTS.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void descriptionIsUndefinedOnEqualDataCalculation() {
        viewModel.firstRangeStartProperty().set("12");
        viewModel.firstRangeFinishProperty().set("12");
        viewModel.secondRangeStartProperty().set("100");
        viewModel.secondRangeFinishProperty().set("100");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);

        viewModel.calculate();

        assertEquals(DemandType.Undefined.toString(), viewModel.calcDescriptionProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), viewModel.calcStatusProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByPriceHasCorrectResult() {
        viewModel.firstRangeStartProperty().set("507");
        viewModel.firstRangeFinishProperty().set("132");
        viewModel.secondRangeStartProperty().set("1");
        viewModel.secondRangeFinishProperty().set("4");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);
        double expectedValue = ((132d - 507d) / (507d + 132d)) / ((4d - 1d) / (1d + 4d));

        viewModel.calculate();

        assertEquals(Double.toString(expectedValue), viewModel.calcResultProperty().get());
        assertEquals(DemandType.Inelastic.toString(), viewModel.calcDescriptionProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByIncomeHasCorrectResult() {
        viewModel.firstRangeStartProperty().set("210");
        viewModel.firstRangeFinishProperty().set("37");
        viewModel.secondRangeStartProperty().set("7");
        viewModel.secondRangeFinishProperty().set("8");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByIncome);
        double expectedValue = ((37d - 210d) / (210d + 37d)) / ((8d - 7d) / (7d + 8d));

        viewModel.calculate();

        assertEquals(Double.toString(expectedValue), viewModel.calcResultProperty().get());
        assertEquals(GoodType.Inferior.toString(), viewModel.calcDescriptionProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByCrossPriceHasCorrectResult() {
        viewModel.firstRangeStartProperty().set("400");
        viewModel.firstRangeFinishProperty().set("200");
        viewModel.secondRangeStartProperty().set("4");
        viewModel.secondRangeFinishProperty().set("5");
        viewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByCrossPrice);
        double expectedValue = ((200d - 400d) / (400d + 200d)) / ((5d - 4d) / (5d + 4d));

        viewModel.calculate();

        assertEquals(Double.toString(expectedValue), viewModel.calcResultProperty().get());
        assertEquals(GoodsPairType.Complementary.toString(),
                     viewModel.calcDescriptionProperty().get());
    }

    private void setCorrectInputData() {
        viewModel.firstRangeStartProperty().set("14");
        viewModel.firstRangeFinishProperty().set("17");
        viewModel.secondRangeStartProperty().set("357");
        viewModel.secondRangeFinishProperty().set("124");
    }
}
