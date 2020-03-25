package com.charsharing.bootcamp.overview.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ValidationServiceTest {

    private ValidationService validationService = new ValidationService();

    @Test
    public void shouldTestIfTitleIsEmpty() {

        final boolean actual = validationService.isTitleEmpty("");
        final boolean expected = true;

        MatcherAssert.assertThat(actual, Matchers.is(expected));
    }

    @Test
    public void shouldTestIfTitleIsNotEmpty() {


        final boolean actual = validationService.isTitleEmpty("TestTitle");
        final boolean expected = false;

        MatcherAssert.assertThat(actual, Matchers.is(expected));
    }

    @Test
    public void shouldTestIfTitleIsEmptyWithOnlyBlanks() {

        final boolean actual = validationService.isTitleEmpty("    ");
        final boolean expected = true;

        MatcherAssert.assertThat(actual, Matchers.is(expected));
    }

    @Test
    public void shouldTestIfCreatorIsEmpty() {
        final boolean actual = validationService.isCreatorEmpty("");
        final boolean expected = true;

        MatcherAssert.assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void shouldTestIfCreatorIsNotEmpty() {
        final boolean actual = validationService.isCreatorEmpty("TestCreator");
        final boolean expected = false;

        MatcherAssert.assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void shouldTestIfCreatorIsEmptyWithOnlyBlanks() {

        final boolean actual = validationService.isCreatorEmpty("    ");
        final boolean expected = true;

        MatcherAssert.assertThat(actual, Matchers.is(expected));
    }
}