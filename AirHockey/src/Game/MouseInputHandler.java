package Game;

import gfx.Assets;
import states.MenuState;
import states.SettingsState;
import states.StateManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener{

	@Override
	public void mousePressed(MouseEvent e) {

		int mouseX = e.getX();
		int mouseY = e.getY();

		if(mouseX >= 430 && mouseX <= 770) {
			if(mouseY >= 150 && mouseY <= 200 && (MenuState.isOn ||  SettingsState.isOn)) {
				MenuState.isOn = false;

				GameEngine.player1.setName(GameEngine.getPlayerName(1));
				GameEngine.player1.convertNameCharsToKeys();
				GameEngine.player2.setName(GameEngine.getPlayerName(2));
				GameEngine.player2.convertNameCharsToKeys();
				GameEngine.State.setState(StateManager.STATES.GAME);

			} else if (mouseY >= 350 && mouseY <= 400 && MenuState.isOn) {
				MenuState.isOn = false;
				System.exit(1);
			} else if (mouseY >= 250 && mouseY <= 300 && MenuState.isOn) {
				GameEngine.State.setState(StateManager.STATES.SETTINGS);
				MenuState.isOn = false;
				SettingsState.isOn = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {    }

	@Override
	public void mouseEntered(MouseEvent e) {	}

	@Override
	public void mouseExited(MouseEvent e) {    }

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		if((GameEngine.State.getState() == StateManager.STATES.SETTINGS) && (point.getX() >= 250 && point.getX() <= 250 + Assets.malletTemplate.getWidth())) {
			if(point.getY() >= 325 && point.getY() <= 325 + Assets.malletTemplate.getHeight())
			GameEngine.color1.nextColor(GameEngine.color2.getColor());
		}
		if((GameEngine.State.getState() == StateManager.STATES.SETTINGS) && (point.getX() >= 800 && point.getX() <= 800 + Assets.malletTemplate.getWidth())) {
			if(point.getY() >= 325 && point.getY() <= 325 + Assets.malletTemplate.getHeight())
				GameEngine.color2.nextColor(GameEngine.color1.getColor());
		}
	}

}
