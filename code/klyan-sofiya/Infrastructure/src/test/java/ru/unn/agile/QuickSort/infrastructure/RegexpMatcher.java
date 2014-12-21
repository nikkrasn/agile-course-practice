package ru.unn.agile.QuickSort.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexpMatcher extends BaseMatcher {
    private final String regexp;

    public RegexpMatcher(final String regexp) {
        this.regexp = regexp;
    }

    public void describeTo(final Description descr) {
        descr.appendText("matches regex = ");
        descr.appendText(regexp);
    }

    public static Matcher<? super String> matchesPattern(final String regexp) {
        RegexpMatcher matcher = new RegexpMatcher(regexp);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regexp);
    }

}
