package states;

import gfx.Assets;
import gfx.ColorSwitcher;
import gfx.ImageColorizer;

import java.awt.*;

public class SettingsState {
    public static boolean isOn;
	public void render(Graphics g, ColorSwitcher color1, ColorSwitcher color2) {

        g.drawImage(Assets.header, 428, 32, 352, 49, null);
        g.drawImage(Assets.headerBackground, 180, 0, 802, 105, null);
        g.drawImage(ImageColorizer.dye(Assets.malletTemplate, color1.getColor()), 250, 355, null);
        g.drawImage(ImageColorizer.dye(Assets.malletTemplate, color2.getColor()), 800, 355, null);
        g.drawImage(Assets.back, 410, 135, 388, 89, null);
        g.drawImage(Assets.settings, 0, 250, 1200, 320, null);

	}
}
