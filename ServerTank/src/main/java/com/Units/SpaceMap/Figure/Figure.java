package main.java.com.Units.SpaceMap.Figure;

import com.badlogic.gdx.math.Vector2;

public interface Figure {
    Vector2 position = new Vector2(Vector2.Zero);
    public boolean isPointCollision(int x, int y);
}
