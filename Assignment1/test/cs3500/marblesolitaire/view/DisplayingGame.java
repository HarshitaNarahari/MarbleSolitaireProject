package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class DisplayingGame {
  public static void main(String[] args) {
    MarbleSolitaireModelState modelState = new EnglishSolitaireModel(3, 3,3);
    System.out.println(modelState.toString());
    //MarbleSolitaireModelState modelState = new EnglishSolitaireModel(3, 3,3);
    MarbleSolitaireTextView textView = new MarbleSolitaireTextView(modelState);

    System.out.print(textView.toString());


  }
}
