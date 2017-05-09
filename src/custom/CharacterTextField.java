package custom;

import javafx.scene.control.TextField;

/**
 * Created by Torab on 05-Jan-17.
 */
public class CharacterTextField extends TextField {
    CharacterTextField(){
        super.setPromptText("Please Type Only Text");
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if((text.matches("[a-zA-Z]")) || text.isEmpty()){
        super.replaceText(start, end, text);
        }
    }
    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}
