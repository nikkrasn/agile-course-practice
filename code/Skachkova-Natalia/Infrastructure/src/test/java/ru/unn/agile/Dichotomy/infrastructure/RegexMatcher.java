package ru.unn.agile.Dichotomy.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regexMatcher;

    public RegexMatcher(final String regexMatcher) {
        this.regexMatcher = regexMatcher;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regexMatcher);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regexMatcher);
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        RegexMatcher matcherOfRegex = new RegexMatcher(regex);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcherOfRegex;
        return castedMatcher;
    }
}
