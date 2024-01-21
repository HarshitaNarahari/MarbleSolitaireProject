package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TriangleSolitaireTextViewTest {

    @Test
    public void testException() {
        try {
            TriangleSolitaireTextView testingMarble = new TriangleSolitaireTextView(null);
            fail("Error: The provided model state is null");
        } catch (IllegalArgumentException e) {
        }

    }
    @Test
    public void testToString() {

        String expectedString =
                 "  _\n" +
                 " O O\n" +
                 "O O O";

        MarbleSolitaireModelState modelState = new TriangleSolitaireModel(3);
        TriangleSolitaireTextView textView = new TriangleSolitaireTextView(modelState);
        assertEquals(expectedString, textView.toString());

        String expectedStringBigger =
                        "    O\n" +
                        "   O O\n" +
                        "  O _ O\n" +
                        " O O O O\n" +
                        "O O O O O";

        MarbleSolitaireModelState modelStateBigger = new TriangleSolitaireModel(5, 2, 1);
        TriangleSolitaireTextView textViewBigger = new TriangleSolitaireTextView(modelStateBigger);

        assertEquals(expectedStringBigger, textViewBigger.toString());
    }

}
