package Game;


import states.StateManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener{

	@Override
	public void mousePressed(MouseEvent e) {

		int mouseX = e.getX();
		int mouseY = e.getY();

		if(mouseX >= 300 && mouseX <= 700) {
			if(mouseY >= 150 && mouseY <= 200) {

				Game.player1.setName(Game.getPlayerName(1));
				Game.player2.setName(Game.getPlayerName(2));

				Game.State.setState(StateManager.STATES.GAME);
			} else if (mouseY >= 350 && mouseY <= 400) {
				System.exit(1);
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
	public void mouseClicked(MouseEvent e) {	}

}
