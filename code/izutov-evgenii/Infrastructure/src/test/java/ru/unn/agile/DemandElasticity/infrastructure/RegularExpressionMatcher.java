package ru.unn.agile.DemandElasticity.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class RegularExpressionMatcher extends BaseMatcher {
    private final String regularExpression;

    public RegularExpressionMatcher(final String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regularExpression);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regularExpression = ");
        description.appendText(regularExpression);
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        RegularExpressionMatcher localMatcher = new RegularExpressionMatcher(regex);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>) localMatcher;
        return castedMatcher;
    }
}
