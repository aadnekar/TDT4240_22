package ntnu.gruppe22.game.states.weapons;

import ntnu.gruppe22.game.scenes.MainGame;

public class SpineyBall extends Weapon {
    public SpineyBall(MainGame screen, int pos) {
        super(screen, pos, "weapons/spiney_ball.png");
        setDamage(7);
    }
}
