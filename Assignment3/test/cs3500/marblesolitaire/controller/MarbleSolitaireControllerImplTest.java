package cs3500.marblesolitaire.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.*;

public class MarbleSolitaireControllerImplTest {

  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  MarbleSolitaireControllerImpl display;
  Readable userInput;
  String[] outputMessages;
  @Before
  public void setUp() {

    model = new EnglishSolitaireModel(3, 3,3);
    view = new MarbleSolitaireTextView(model);
    userInput = new InputStreamReader(System.in);
    display = new MarbleSolitaireControllerImpl(model, view, userInput);

  }

  // testing the initial board
  @Test
  public void testInitialBoard() throws IOException {

    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3,3);
    Readable readable = new StringReader("q");
    Appendable appendable = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireControllerImpl display = new MarbleSolitaireControllerImpl(model, view, readable);

    display.playGame();
    String output = appendable.toString();
    assertEquals("    0 0 0\n" +
                    "    0 0 0\n" +
                    "0 0 0 0 0 0 0\n" +
                    "0 0 0 _ 0 0 0\n" +
                    "0 0 0 0 0 0 0\n" +
                    "    0 0 0\n" +
                    "    0 0 0\n" +
                    "Score: 32\n" +
            "Enter the row you want to move from Game quit!\n" +
                    "State of game when quit:\n" +
                    "    0 0 0\n" +
                    "    0 0 0\n" +
                    "0 0 0 0 0 0 0\n" +
                    "0 0 0 _ 0 0 0\n" +
                    "0 0 0 0 0 0 0\n" +
                    "    0 0 0\n" +
                    "    0 0 0\n" +
                    "Score: 32",
            output);
    outputMessages = output.split("\n");
  }

  // testing for moving the board
  @Test
  public void testMovingBoard() throws IOException {

    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3,3);
    Readable readable = new StringReader("4 2 \n 4 4 \n q");
    Appendable appendable = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireControllerImpl display = new MarbleSolitaireControllerImpl(model, view, readable);

    display.playGame();
    String output = appendable.toString();
    assertEquals("    0 0 0\n" +
            "    0 0 0\n" +
            "0 0 0 0 0 0 0\n" +
            "0 0 0 _ 0 0 0\n" +
            "0 0 0 0 0 0 0\n" +
            "    0 0 0\n" +
            "    0 0 0\n" +
            "Score: 32\n" +
            "Enter the row you want to move from Enter the column you want to move from " +
                    "Enter the row you want to move to " +
                    "Enter the column you want to move to " +
                    "That was a successful move.\n" +
            "You moved from (row: 4 col: 2) to (row : 4 col: 4)\n" +
            "    0 0 0\n" +
            "    0 0 0\n" +
            "0 0 0 0 0 0 0\n" +
            "0 _ _ 0 0 0 0\n" +
            "0 0 0 0 0 0 0\n" +
            "    0 0 0\n" +
            "    0 0 0\n" +
            "Score: 31\n" +
            "Enter the row you want to move from Game quit!\n" +
            "State of game when quit:\n" +
            "    0 0 0\n" +
            "    0 0 0\n" +
            "0 0 0 0 0 0 0\n" +
            "0 _ _ 0 0 0 0\n" +
            "0 0 0 0 0 0 0\n" +
            "    0 0 0\n" +
            "    0 0 0\n" +
            "Score: 31",
            output);
    outputMessages = output.split("\n");
  }



  @Test
  public void testExceptions() {
    setUp();
    try {
      display = new MarbleSolitaireControllerImpl(null, view, userInput);
      fail("Error: The provided model, view, or readable object is null");
    } catch (IllegalArgumentException e) {
    }

    try {
      display = new MarbleSolitaireControllerImpl(model, null, userInput);
      fail("Error: The provided model, view, or readable object is null");
    } catch (IllegalArgumentException e) {
    }
  }


  }