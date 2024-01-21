package cs3500.marblesolitaire.view.displayingsViews;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DisplayTriangle {

  @Test
  public void test() {
    MarbleSolitaireModelState modelState = new TriangleSolitaireModel(3, 2, 1);
    TriangleSolitaireTextView textView = new TriangleSolitaireTextView(modelState);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelState.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelState.getSlotAt(2, 1));

    String boardRepresentation = textView.toString();
    System.out.println(boardRepresentation);
  }

}
