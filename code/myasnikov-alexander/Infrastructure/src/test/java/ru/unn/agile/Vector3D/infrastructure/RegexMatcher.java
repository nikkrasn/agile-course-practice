package ru.unn.agile.Vector3D.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regex;

    public RegexMatcher(final String regex) {
        this.regex = regex;
    }

    public boolean matches(final Object obj) {
        return ((String) obj).matches(regex);
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        RegexMatcher matcher = new RegexMatcher(regex);
        @SuppressWarnings ("unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>) matcher;
        return castedMatcher;
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regex);
    }
}
