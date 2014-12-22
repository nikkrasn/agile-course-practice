package ru.unn.agile.DemandElasticity.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class RegexMatcher extends BaseMatcher {
    private final String regex;

    public RegexMatcher(final String regex) {
        this.regex = regex;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regex);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regex);
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        RegexMatcher matcher = new RegexMatcher(regex);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }
}
