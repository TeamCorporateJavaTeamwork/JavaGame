package Game;

import gfx.Assets;
import states.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener{

	@Override
	public void mousePressed(MouseEvent e) {

		int mouseX = e.getX();
		int mouseY = e.getY();

		if(MenuState.isOn) {
			if(mouseX >= 430 && mouseX <= 770) {
				if(mouseY >= 150 && mouseY <= 200) {
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
		if(SettingsState.isOn) {
			if(mouseX >= 430 && mouseX <= 770) {
				if (mouseY >= 150 && mouseY <= 200) {
					SettingsState.isOn = false;
					MenuState.isOn = true;
					GameEngine.State.setState(StateManager.STATES.MENU);
				}
			}
		}
		if(VictoryState.isOn) {
			if(mouseX >= 430 && mouseX <= 770) {
				if (mouseY >= 350 && mouseY <= 400) {
					VictoryState.resetValues();
					GameEngine.State.setState(StateManager.STATES.GAME);
				} else if (mouseY >= 450 && mouseY <= 500) {
					VictoryState.resetValues();
					MenuState.isOn = true;
					GameEngine.State.setState(StateManager.STATES.MENU);
				}
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
