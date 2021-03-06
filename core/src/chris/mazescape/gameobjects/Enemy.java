package chris.mazescape.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import chris.mazescape.Controls;
import chris.mazescape.Game;
import chris.mazescape.Generation;
import chris.mazescape.Main;

public class Enemy extends GameObject {
	public Enemy() {
		super();
		health = 20;
		texture = new Sprite[] {new Sprite(new Texture("enemystill.png")), new Sprite(new Texture("enemymove1.png")), new Sprite(new Texture("enemymove2.png"))};
		speed = 22;
		maxSpeed = 34;
		aSwitchTime = 0.2f;
	}
	@Override
	public void draw(int i, SpriteBatch batch, float px, float py, int playerIndex) {
		texture[(int) Main.state.game.generation.animation.get(i)[Controls.INDEX]].setRotation(Main.state.game.generation.animation.get(i)[Controls.ROTATION]);
		super.draw(i, batch, px, py, playerIndex);
	}
	@Override
	public byte[] move(int index) {
		if (!Main.state.game.buttons[Game.ITEMS].buttonState) {
			Generation gen = Main.state.game.generation;
			byte[] direction = new byte[2];
			float ex = gen.coordinates.get(index)[Generation.X];
			float ey = gen.coordinates.get(index)[Generation.Y];
			short[] target = gen.manager.targets.get(gen.manager.enemies.indexOf(gen.uIndexList.get(index)));
			if (target[Generation.X] * Game.size > (int) ex) direction[Generation.X] = 1;
			else if (target[Generation.X] * Game.size < (int) ex) direction[Generation.X] = -1;
			else if (target[Generation.Y] * Game.size < (int) ey) direction[Generation.Y] = -1;
			else if (target[Generation.Y] * Game.size > (int) ey) direction[Generation.Y] = 1;
			return direction;
		} else {
			return new byte[] {0, 0};
		}
	}
}