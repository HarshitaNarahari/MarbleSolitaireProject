// Kritika Uprety and Harshita Narahari
package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * to represent the controller implication for marble soliatire
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  final private Scanner userInput;

  /**
   * the constructor
   * @param model represents the model of the game
   * @param view represents the view of the game
   * @param userInput represents the input a user gives
   * @throws IllegalArgumentException if the mode, view, or user input is null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable userInput) throws IllegalArgumentException {
    this.model = model;
    this.view = view;
    this.userInput = new Scanner(userInput);

    if (model == null || view == null || userInput == null) {
      throw new IllegalArgumentException("Error: The provided model, view, or readable object is null");
    }
  }


  /**
   * to play the game
   * @throws IllegalStateException if user gives illegal move
   */
  @Override
  public void playGame() throws IllegalStateException {

    try {
      while (model.isGameOver() == false) {

        view.renderBoard();
        view.renderMessage(System.lineSeparator());
        view.renderMessage("Score: " + model.getScore());
        view.renderMessage(System.lineSeparator());


        Position from = getUserInput("from ");
        if(from.row == -1 && from.col == -1) {
          quitGame(this.model);
          return;
        }
        Position to = getUserInput("to ");
        if (to.row == -1 && to.col == -1) {
          //quit game
          quitGame(this.model);
          return;
        }



        try {
          model.move(from.row - 1, from.col - 1, to.row - 1, to.col - 1);

          view.renderMessage("That was a successful move." + System.lineSeparator());
          view.renderMessage("You moved from (row: " + (from.row) + " col: " + (from.col) + ") " +
                  "to (row : " + (to.row) + " col: " + (to.col) + ")" + System.lineSeparator());
        } catch (IllegalArgumentException e) {
          view.renderMessage("Illegal move, try again." + System.lineSeparator());
        }

      }

      view.renderMessage("Game over!");
      view.renderMessage(System.lineSeparator());
      view.renderBoard();
      view.renderMessage(System.lineSeparator());
      view.renderMessage("Score: " + model.getScore());
      view.renderMessage(System.lineSeparator());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * to quit the game
   * @param model represents the model of the game
   */
  private void quitGame(MarbleSolitaireModelState model) {
    try {
      view.renderMessage("Game quit!" + System.lineSeparator());
      view.renderMessage("State of game when quit:" + System.lineSeparator());
      view.renderBoard();
      view.renderMessage(System.lineSeparator());
      view.renderMessage("Score: " + model.getScore());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * to receive the user input
   * @param source represents the inout source
   * @return the position of the baord
   * @throws IOException if user gives invalid row or column amount
   */
  private Position getUserInput(String source) throws IOException {

    boolean gameOver = false;
    String input;
    int row = -1;
    int col = -1;

    while (!gameOver) {
      view.renderMessage("Enter the row you want to move " + source);
      input = userInput.next();

      if (input.equalsIgnoreCase("q")) {
        return new Position(-1, -1);
      }

      try {
        row = Integer.parseInt(input);
        if(row <= 0 || row > model.getBoardSize()) {
          throw new IllegalArgumentException();
        }
        break;
      } catch (Exception e) {
        view.renderMessage("Invalid row amount. Try again" + System.lineSeparator());

      }

    }

    while (!gameOver) {
      view.renderMessage("Enter the column you want to move " + source);
      input = userInput.next();

      if (input.equalsIgnoreCase("q")) {
        return new Position(-1, -1);
      }


      try {
        col = Integer.parseInt(input);
        if(col <= 0 || col > model.getBoardSize()) {
          throw new IllegalArgumentException();
        }
        break;
      } catch (Exception e) {
        view.renderMessage("Invalid column amount. Try again" + System.lineSeparator());

      }
    }
    return new Position(row, col);
  }


  /**
   * to represent the position in the board
   */
  private class Position {
    int row;
    int col;

    Position(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }


}
