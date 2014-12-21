package ru.unn.agile.LengthConverter.viewmodel;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regex;

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regex);
    }

    public RegexMatcher(final String regex) {
        this.regex = regex;
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        RegexMatcher matcher = new RegexMatcher(regex);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedM = (Matcher<? super String>) matcher;
        return castedM;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regex);
    }
}
