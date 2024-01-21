package cs3500.marblesolitaire.view.displayingsViews;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

public class DisplayEuropean {

  public static void main(String[] args) {
    MarbleSolitaireModel model = new EuropeanSolitaireModel(3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    System.out.println(view);
  }
}
