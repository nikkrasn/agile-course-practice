package ru.unn.agile.CalculateSquare.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegConform extends BaseMatcher {
    private final String regex;

    public RegConform(final String regex) {
        this.regex = regex;
    }

    public boolean matches(final Object object) {
        return ((String) object).matches(regex);
    }

    public void describeTo(final Description description) {
        description.appendText("Regex = ");
        description.appendText(regex);
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        RegConform matcher = new RegConform(regex);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }
}
