package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

public final class MarbleSolitaire {
  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("Error: Board type is missing. You must pass one of english, european, or triangular.");
      return;
    }

    String boardType = args[0];

    // choosing board model
    MarbleSolitaireModel model;
    switch (boardType) {
      case "english":
        int size;
        if (args.length > 1 && args[1].equals("-size") && args.length > 2) {
          size = Integer.parseInt(args[2]);
          model = new EnglishSolitaireModel(size);
        } else if (args.length > 1 && args[1].equals("-hole") && args.length > 3) {
          int row = Integer.parseInt(args[2]);
          int col = Integer.parseInt(args[3]);
          model = new EnglishSolitaireModel(row, col);
        }
        else {
          model = new EnglishSolitaireModel();
        }
      break;

      case "triangular":
        int dimension;
        if (args.length > 1 && args[1].equals("-size") && args.length > 2) {
          dimension = Integer.parseInt(args[2]);
          model = new TriangleSolitaireModel(dimension);
        } else if (args.length > 1 && args[1].equals("-hole") && args.length > 3) {
          int row = Integer.parseInt(args[2]);
          int col = Integer.parseInt(args[3]);
          model = new TriangleSolitaireModel(row, col);
        }
        else {
          model = new TriangleSolitaireModel();
        }
        break;

      case "european":
        if (args.length > 1 && args[1].equals("-size") && args.length > 2) {
          size = Integer.parseInt(args[2]);
          model = new EuropeanSolitaireModel(size);
        } else if (args.length > 1 && args[1].equals("-hole") && args.length > 3) {
          int row = Integer.parseInt(args[2]);
          int col = Integer.parseInt(args[3]);
          model = new EuropeanSolitaireModel(row, col);
        }
        else {
          model = new EuropeanSolitaireModel();
        }
        break;

      default:
        System.out.println("Error: Invalid board type. You must pass one of english, european, or triangular.");
        return;
    }

    // choosing board view
    MarbleSolitaireView view;
    switch (boardType) {
      case "english":
      case "european":
        view = new MarbleSolitaireTextView(model);
        break;
      case "triangular":
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        System.out.println("Error: Invalid board type. You must pass one of english, european, or triangular.");
        return;
    }

    MarbleSolitaireControllerImpl display = new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
    display.playGame();
  }
}
