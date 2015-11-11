package states;

import gfx.Assets;
import gfx.ColorSwitcher;
import gfx.ImageColorizer;

import java.awt.*;

public class SettingsState {
    public static boolean isOn;
	public void render(Graphics g, ColorSwitcher color1, ColorSwitcher color2) {

        g.drawImage(ImageColorizer.dye(Assets.malletTemplate, color1.getColor()), 250, 325, null);
        g.drawImage(ImageColorizer.dye(Assets.malletTemplate, color2.getColor()), 800, 325, null);
        g.drawImage(Assets.playGame, 410, 135, 388, 89, null);

	}
}
