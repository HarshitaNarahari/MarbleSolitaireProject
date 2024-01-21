package cs3500.marblesolitaire.view.displayingsViews;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

public class DisplayingGame {
  public static void main(String[] args) {

    MarbleSolitaireModel model = new TriangleSolitaireModel(5, 2, 2);

    MarbleSolitaireView view = new TriangleSolitaireTextView(model);
    MarbleSolitaireControllerImpl display = new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
    display.playGame();


  }
}
