package PaooGame.Objects;

public class Spike extends GameObject
{
    public Spike(int x, int y, int type)
    {
        super(x, y, type);
        initHitbox(64, 18);
        xDrawOffset = 0;
        yDrawOffset = 46;
        hitbox.y += yDrawOffset;
    }
}
