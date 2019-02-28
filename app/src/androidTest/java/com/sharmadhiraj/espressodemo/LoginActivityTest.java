package com.sharmadhiraj.espressodemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.sharmadhiraj.espressodemo.Utils.VALID_PASSWORD;
import static com.sharmadhiraj.espressodemo.Utils.VALID_USERNAME;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @BeforeClass
    public static void setUp() {
        Intents.init();
    }

    @Test
    public void emptyUsername() {
        clickLogin();
        editTextHasError(R.id.edt_username, R.string.txt_empty_username);
    }

    @Test
    public void emptyPassword() {
        enterUsername(VALID_USERNAME);
        clickLogin();
        editTextHasError(R.id.edt_password, R.string.txt_empty_password);
    }

    @Test
    public void invalidUsername() {
        enterUsername("random");
        clickLogin();
        editTextHasError(R.id.edt_username, R.string.txt_invalid_username);
    }

    @Test
    public void invalidPassword() {
        enterUsername(VALID_USERNAME);
        enterPassword("random");
        clickLogin();
        editTextHasError(R.id.edt_password, R.string.txt_invalid_password);
    }

    @Test
    public void validUsernameAndPassword() {
        enterUsername(VALID_USERNAME);
        enterPassword(VALID_PASSWORD);
        clickLogin();
        homeScreenLaunched();
    }

    private void enterUsername(String username) {
        onView(withId(R.id.edt_username))
                .perform(typeText(username));
    }

    private void editTextHasError(int editText, int errorString) {
        onView(withId(editText))
                .check(matches(hasErrorText(getString(errorString))));
    }

    private void enterPassword(String password) {
        onView(withId(R.id.edt_password))
                .perform(typeText(password));
    }

    private void clickLogin() {
        onView(withId(R.id.btn_login))
                .perform(click());
    }

    private String getString(int id) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        return targetContext.getResources().getString(id);
    }

    private void homeScreenLaunched() {
        intended(hasComponent(HomeActivity.class.getName()));
        assertTrue(loginActivityActivityTestRule.getActivity().isFinishing());
    }
}
