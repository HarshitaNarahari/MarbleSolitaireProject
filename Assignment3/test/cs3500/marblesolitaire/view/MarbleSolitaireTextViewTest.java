package cs3500.marblesolitaire.view;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MarbleSolitaireTextViewTest {

  @Test
  public void testException() {
    try {
      MarbleSolitaireTextView testingMarble = new MarbleSolitaireTextView(null);
      fail("Error: The provided model state is null");
    } catch (IllegalArgumentException e) {
    }

  }
  @Test
  public void testToString() {

    String expectedString = "    0 0 0\n" +
                            "    0 0 0\n" +
                            "0 0 0 0 0 0 0\n" +
                            "0 0 0 _ 0 0 0\n" +
                            "0 0 0 0 0 0 0\n" +
                            "    0 0 0\n" +
                            "    0 0 0";

    MarbleSolitaireModelState modelState = new EnglishSolitaireModel(3, 3,3);
    MarbleSolitaireTextView textView = new MarbleSolitaireTextView(modelState);
    assertEquals(expectedString, textView.toString());

    String expectedStringBigger =
                    "        0 0 0 0 0\n" +
                    "        0 0 0 0 0\n" +
                    "        0 0 0 0 0\n" +
                    "        0 0 0 0 0\n" +
                    "0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                    "0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                    "0 0 0 0 0 0 _ 0 0 0 0 0 0\n" +
                    "0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                    "0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                    "        0 0 0 0 0\n" +
                    "        0 0 0 0 0\n" +
                    "        0 0 0 0 0\n" +
                    "        0 0 0 0 0";

    MarbleSolitaireModelState modelStateBigger = new EnglishSolitaireModel(5);
    MarbleSolitaireTextView textViewBigger = new MarbleSolitaireTextView(modelStateBigger);

    assertEquals(expectedStringBigger, textViewBigger.toString());
  }

}
