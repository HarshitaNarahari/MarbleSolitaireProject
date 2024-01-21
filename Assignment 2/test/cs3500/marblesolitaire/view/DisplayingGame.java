package cs3500.marblesolitaire.view;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class DisplayingGame {
  public static void main(String[] args) {

    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3,3);

    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireControllerImpl display = new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
    display.playGame();



  }


}
