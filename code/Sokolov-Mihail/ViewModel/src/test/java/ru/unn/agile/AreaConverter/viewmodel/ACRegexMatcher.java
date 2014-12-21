package ru.unn.agile.AreaConverter.viewmodel;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ACRegexMatcher extends BaseMatcher {
    private final String regexAC;

    public ACRegexMatcher(final String regex) {
        this.regexAC = regex;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regexAC);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regexAC);
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        ACRegexMatcher matcher = new ACRegexMatcher(regex);
        //NOTE: this ugly cast is needed to workaround 'unchecked' Java warning
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }
}
