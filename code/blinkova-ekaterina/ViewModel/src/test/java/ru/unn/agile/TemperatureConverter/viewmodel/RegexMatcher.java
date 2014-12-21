package ru.unn.agile.TemperatureConverter.viewmodel;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regular;

    public RegexMatcher(final String regular) {
        this.regular = regular;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regular);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regular);
    }

    public static Matcher<? super String> matchesPattern(final String regular) {
        RegexMatcher matcher = new RegexMatcher(regular);
        @SuppressWarnings(value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }
}
