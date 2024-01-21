// Kritika Uprety and Harshita Narahari
package cs3500.marblesolitaire.controller;

import java.io.IOException;

/**
 * This interface represents a controller for this game.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of Marble Solitaire
   *
   * @throws IllegalArgumentException only if the controller is unable to successfully
   *                                  read input or transmit output.
   */
  void playGame() throws IllegalArgumentException, IOException;

}
