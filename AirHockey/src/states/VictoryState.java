package states;

import display.Display;
import gfx.SpriteSheet;

import java.awt.*;

public class VictoryState {
    private int[] charKeyVictory;

    public void render(Graphics g, SpriteSheet alphabet) {
        getVictoryChar();
        for (int i = 0; i < this.charKeyVictory.length; i++) {
            g.drawImage(alphabet.crop(this.charKeyVictory[this.charKeyVictory.length - 1 - i], 0), (Display.WIDTH / 2) - i * 30, (Display.HEIGHT / 2), null);
        }
    }

    private void getVictoryChar() {
        this.charKeyVictory = new int["Victory".length()];
        String upperName = "VICTORY";

        for (int i = 0; i < upperName.length(); i++) {
            this.charKeyVictory[i] = ((int)upperName.charAt(i)) - 65;
            if(this.charKeyVictory[i] < 0) {
                this.charKeyVictory[i] = ((int)upperName.charAt(i)) - 21;
                if(this.charKeyVictory[i] < 25) {
                    this.charKeyVictory[i] = 26;
                }
            }
        }
    }
}
