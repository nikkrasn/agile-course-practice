package ru.unn.agile.NumberInPositionalNotation.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regMatch;

    public RegexMatcher(final String regex) {
        this.regMatch = regex;
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        RegexMatcher matcher = new RegexMatcher(regex);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regMatch);
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regMatch);
    }
}
