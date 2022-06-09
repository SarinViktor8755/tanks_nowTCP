package main.java.com.Units.SpaceMap.Figure;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.Utils.VectorUtils;

public class Ellipse implements Figure {
    private Vector2 positionCenter;
    private float radius;
    private float radius2;


    public Ellipse(Vector2 position, float width, float height) {
        positionCenter = new Vector2();
        this.positionCenter.set(position.x + width / 2, position.y + height / 2);
        this.radius = width / 2l;
        this.radius2 = (float) Math.pow(width / 2, 2);
    }

    @Override
    public boolean isPointCollision(float x, float y) {
        if (VectorUtils.getLen2(positionCenter, x, y) < radius2 + 230) return true;
        // System.out.println(positionCenter + "   " + radius2);
        return false;
    }

    @Override
    public Vector2 get_vector2_from_center(float x, float y) { // дает вектор от центра кализии
        if (VectorUtils.getLen2(x, y, positionCenter.x, positionCenter.y) > radius) {
            Vector2 pos = new Vector2(x, y);
            Vector2 result = new Vector2(0, 0);
            result.set(pos.cpy().sub(positionCenter).nor());
        }
        return null;
    }

    public Vector2 get_vector2_from_center(Vector2 pos) { // дает вектор от центра кализии
        if (pos.dst(positionCenter) > radius) {
            Vector2 result = new Vector2(0, 0);
            result.set(pos.cpy().sub(positionCenter).nor());
            return result;
        }
        return null;
    }

    public Vector2 getPositionCenter() {
        return positionCenter;
    }

    public float getRadius() {
        return radius;
    }

    public float getRadius2() {
        return radius2;
    }
}
