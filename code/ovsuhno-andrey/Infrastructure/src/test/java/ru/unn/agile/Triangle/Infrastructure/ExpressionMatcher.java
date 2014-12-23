package ru.unn.agile.Triangle.Infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ExpressionMatcher extends BaseMatcher {
    private final String expression;

    public ExpressionMatcher(final String expression) {
        this.expression = expression;
    }

    public boolean matches(final Object obj) {
        return ((String) obj).matches(expression);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(expression);
    }

    public static Matcher<? super String> matchesPattern(final String expression) {
        ExpressionMatcher matcher = new ExpressionMatcher(expression);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>) matcher;
        return castedMatcher;
    }
}
