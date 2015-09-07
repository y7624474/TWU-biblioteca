package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainMenuTest {
    private MainMenu menu;

    @Before
    public void setUp() {
        menu = new MainMenu();
    }

    @Test
    public void testSeeCheckoutOptionInMainMenu() {
        StringBuilder checkoutMessage = new StringBuilder();
        checkoutMessage.append("2: Checkout Book\n");

        ByteArrayOutputStream output = setSystemOutput();
        menu.show();

        assertTrue(isOutputContainExpect(output, checkoutMessage));
    }

    @Test
    public void testSeeListMovieOptionInMainMenu() {
        StringBuilder message = new StringBuilder();
        message.append("4: Show Movie List\n");

        ByteArrayOutputStream output = setSystemOutput();
        menu.show();
        assertTrue(isOutputContainExpect(output, message));
    }

    @Test
    public void testSeeCheckoutMovieOptionInMainMenu() {
        StringBuilder checkoutMessage = new StringBuilder();
        checkoutMessage.append("5: Checkout Movie(type movie name)\n");

        ByteArrayOutputStream output = setSystemOutput();
        menu.show();
        assertTrue(isOutputContainExpect(output, checkoutMessage));
    }

    @Test
    public void testSeeShowUserInformationOptionInMainMenu() {
        StringBuilder message = new StringBuilder();
        message.append("6: Show My Profile\n");

        ByteArrayOutputStream output = setSystemOutput();
        menu.show();
        assertTrue(isOutputContainExpect(output, message));
    }

    private ByteArrayOutputStream setSystemOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }

    private boolean isOutputContainExpect(ByteArrayOutputStream output, StringBuilder expect) {
        return output.toString().indexOf(expect.toString()) != -1;
    }
}