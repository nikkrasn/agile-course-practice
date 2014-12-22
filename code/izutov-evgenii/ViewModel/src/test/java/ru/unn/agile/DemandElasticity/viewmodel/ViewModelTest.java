package ru.unn.agile.DemandElasticity.viewmodel;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import ru.unn.agile.DemandElasticity.Model.DemandType;
import ru.unn.agile.DemandElasticity.Model.GoodType;
import ru.unn.agile.DemandElasticity.Model.GoodsPairType;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel testedViewModel;

    public void setTestedViewModel(final ViewModel viewModel) {
        this.testedViewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (testedViewModel == null) {
            testedViewModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        testedViewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals(DemandElasticityType.ByPrice.getFirstRangeName(),
                testedViewModel.firstRangeProperty().get());
        assertEquals(DemandElasticityType.ByPrice.getSecondRangeName(),
                testedViewModel.secondRangeProperty().get());
        assertEquals("", testedViewModel.firstRangeStartProperty().get());
        assertEquals("", testedViewModel.firstRangeFinishProperty().get());
        assertEquals("", testedViewModel.secondRangeStartProperty().get());
        assertEquals("", testedViewModel.secondRangeFinishProperty().get());
        assertEquals(DemandElasticityType.ByPrice,
                testedViewModel.demandElasticityTypeProperty().get());
        assertEquals("", testedViewModel.calcResultProperty().get());
        assertEquals(Status.WAITING.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        testedViewModel.calculate();

        assertEquals(Status.WAITING.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void canReportNotNumber() {
        testedViewModel.firstRangeStartProperty().set("t");

        assertEquals(Status.NOT_NUMBER.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        testedViewModel.firstRangeStartProperty().set("13");

        assertEquals(Status.WAITING.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void canReportNotPositive() {
        testedViewModel.secondRangeStartProperty().set("-5");

        assertEquals(Status.NOT_POSITIVE.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(testedViewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setCorrectInputData();
        testedViewModel.secondRangeFinishProperty().set("h");

        assertTrue(testedViewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        testedViewModel.secondRangeFinishProperty().set("121");

        assertTrue(testedViewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setCorrectInputData();

        assertFalse(testedViewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetByPriceDemandElasticityType() {
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);

        assertEquals(DemandElasticityType.ByPrice,
                testedViewModel.demandElasticityTypeProperty().get());
    }

    @Test
    public void rangeNamesChangedOnSetByIncomeDemandElasticityType() {
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByIncome);

        assertEquals(DemandElasticityType.ByIncome.getFirstRangeName(),
                testedViewModel.firstRangeProperty().get());
        assertEquals(DemandElasticityType.ByIncome.getSecondRangeName(),
                testedViewModel.secondRangeProperty().get());
    }

    @Test
    public void byPriceIsDefaultDemandElasticityType() {
        assertEquals(DemandElasticityType.ByPrice,
                testedViewModel.demandElasticityTypeProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setCorrectInputData();

        testedViewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void canSetNotNumberMessage() {
        testedViewModel.firstRangeFinishProperty().set("ue");

        assertEquals(Status.NOT_NUMBER.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void canSetNotPositiveMessage() {
        testedViewModel.secondRangeFinishProperty().set("-92");

        assertEquals(Status.NOT_POSITIVE.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void statusIsWrongArgumentsOnWrongDataCalculation() {
        testedViewModel.firstRangeStartProperty().set("0");
        testedViewModel.firstRangeFinishProperty().set("0");
        testedViewModel.secondRangeStartProperty().set("13");
        testedViewModel.secondRangeFinishProperty().set("11");
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);

        testedViewModel.calculate();

        assertEquals(Status.WRONG_ARGUMENTS.toString(),
                testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void descriptionIsUndefinedOnEqualDataCalculation() {
        testedViewModel.firstRangeStartProperty().set("12");
        testedViewModel.firstRangeFinishProperty().set("12");
        testedViewModel.secondRangeStartProperty().set("100");
        testedViewModel.secondRangeFinishProperty().set("100");
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);

        testedViewModel.calculate();

        assertEquals(DemandType.Undefined.toString(),
                testedViewModel.calcDescriptionProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setCorrectInputData();

        assertEquals(Status.READY.toString(), testedViewModel.calcStatusProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByPriceHasCorrectResult() {
        testedViewModel.firstRangeStartProperty().set("507");
        testedViewModel.firstRangeFinishProperty().set("132");
        testedViewModel.secondRangeStartProperty().set("1");
        testedViewModel.secondRangeFinishProperty().set("4");
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByPrice);
        double expectedValue = ((132d - 507d) / (507d + 132d)) / ((4d - 1d) / (1d + 4d));

        testedViewModel.calculate();

        assertEquals(Double.toString(expectedValue),
                testedViewModel.calcResultProperty().get());
        assertEquals(DemandType.Inelastic.toString(),
                testedViewModel.calcDescriptionProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByIncomeHasCorrectResult() {
        testedViewModel.firstRangeStartProperty().set("210");
        testedViewModel.firstRangeFinishProperty().set("37");
        testedViewModel.secondRangeStartProperty().set("7");
        testedViewModel.secondRangeFinishProperty().set("8");
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByIncome);
        double expectedValue = ((37d - 210d) / (210d + 37d)) / ((8d - 7d) / (7d + 8d));

        testedViewModel.calculate();

        assertEquals(Double.toString(expectedValue), testedViewModel.calcResultProperty().get());
        assertEquals(GoodType.Inferior.toString(), testedViewModel.calcDescriptionProperty().get());
    }

    @Test
    public void demandElasticityCalculatingByCrossPriceHasCorrectResult() {
        testedViewModel.firstRangeStartProperty().set("400");
        testedViewModel.firstRangeFinishProperty().set("200");
        testedViewModel.secondRangeStartProperty().set("4");
        testedViewModel.secondRangeFinishProperty().set("5");
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByCrossPrice);
        double expectedValue = ((200d - 400d) / (400d + 200d)) / ((5d - 4d) / (5d + 4d));

        testedViewModel.calculate();

        assertEquals(Double.toString(expectedValue), testedViewModel.calcResultProperty().get());
        assertEquals(GoodsPairType.Complementary.toString(),
                     testedViewModel.calcDescriptionProperty().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = testedViewModel.getFullLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() {
        setCorrectInputData();
        testedViewModel.calculate();
        String message = testedViewModel.getFullLog().get(0);

        assertTrue(message.matches(".*" + LoggerMessages.CALCULATE_WAS_COMPLETED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setCorrectInputData();

        testedViewModel.calculate();

        String message = testedViewModel.getFullLog().get(0);
        assertTrue(message.matches(".*" + testedViewModel.firstRangeStartProperty().get()
                + ".*" + testedViewModel.firstRangeFinishProperty().get()
                + ".*" + testedViewModel.secondRangeStartProperty().get()
                + ".*" + testedViewModel.secondRangeFinishProperty().get()
                + ".*"));
    }

    @Test
    public void argumentsInfoIsProperlyFormatted() {
        setCorrectInputData();

        testedViewModel.calculate();

        String message = testedViewModel.getFullLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": FirstRangeStart = " + testedViewModel.firstRangeStartProperty().get()
                + "; FirstRangeFinish = " + testedViewModel.firstRangeFinishProperty().get()
                + "; SecondRangeStart = " + testedViewModel.secondRangeStartProperty().get()
                + "; SecondRangeFinish = " + testedViewModel.secondRangeFinishProperty().get()
                + ".*"));
    }

    @Test
    public void demandElasticityTypeIsMentionedInTheLog() {
        setCorrectInputData();
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByCrossPrice);

        testedViewModel.calculate();

        String message = testedViewModel.getFullLog().get(0);
        assertTrue(message.matches(".*"
                + testedViewModel.demandElasticityTypeProperty().get().toString()
                + ".*"));
    }

    @Test
    public void demandElasticityInfoIsProperlyFormatted() {
        setCorrectInputData();
        testedViewModel.demandElasticityTypeProperty().set(DemandElasticityType.ByIncome);

        testedViewModel.calculate();

        String message = testedViewModel.getFullLog().get(0);
        assertTrue(message.matches(".*Demand elasticity type: "
                + testedViewModel.demandElasticityTypeProperty().get().toString()
                + ".*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setCorrectInputData();

        testedViewModel.calculate();
        testedViewModel.calculate();
        testedViewModel.calculate();
        testedViewModel.calculate();

        assertEquals(4, testedViewModel.getFullLog().size());
    }

    @Test
    public void canSeeDemandElasticityTypeChangeInLog() {
        setCorrectInputData();

        testedViewModel.onDemandElasticityTypeChanged(DemandElasticityType.ByIncome,
                DemandElasticityType.ByPrice);

        String message = testedViewModel.getFullLog().get(0);
        assertTrue(message.matches(".*"
                + LoggerMessages.DEMAND_ELASTICITY_TYPE_WAS_CHANGED
                + DemandElasticityType.ByPrice.toString()
                + ".*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        testedViewModel.onDemandElasticityTypeChanged(DemandElasticityType.ByCrossPrice,
                DemandElasticityType.ByPrice);

        testedViewModel.onDemandElasticityTypeChanged(DemandElasticityType.ByPrice,
                DemandElasticityType.ByPrice);

        assertEquals(1, testedViewModel.getFullLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setCorrectInputData();

        testedViewModel.onInputFieldChanged(Boolean.TRUE, Boolean.FALSE);

        String message = testedViewModel.getFullLog().get(0);
        assertTrue(message.matches(".*" + LoggerMessages.INPUT_WAS_UPDATED
                + "\\["
                + testedViewModel.firstRangeStartProperty().get() + "; "
                + testedViewModel.firstRangeFinishProperty().get() + "; "
                + testedViewModel.secondRangeStartProperty().get() + "; "
                + testedViewModel.secondRangeFinishProperty().get() + "\\]"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() {
        testedViewModel.calculate();

        assertTrue(testedViewModel.getFullLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        testedViewModel.firstRangeFinishProperty().set("39");
        testedViewModel.onInputFieldChanged(Boolean.TRUE, Boolean.FALSE);
        testedViewModel.firstRangeFinishProperty().set("39");
        testedViewModel.onInputFieldChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, testedViewModel.getFullLog().size());
    }

    private void setCorrectInputData() {
        testedViewModel.firstRangeStartProperty().set("14");
        testedViewModel.firstRangeFinishProperty().set("17");
        testedViewModel.secondRangeStartProperty().set("357");
        testedViewModel.secondRangeFinishProperty().set("124");
    }
}
