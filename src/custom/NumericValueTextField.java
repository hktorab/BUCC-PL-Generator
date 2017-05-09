package custom;

import javafx.scene.control.TextField;

/**
 * Created by Torab on 05-Jan-17.
 */
public class NumericValueTextField extends TextField {
    public NumericValueTextField() {
        super.setPromptText("");
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if ((text.matches("[0-9]")) || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}