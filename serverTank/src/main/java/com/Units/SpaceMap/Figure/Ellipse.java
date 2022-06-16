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
    public Vector2 get_vector2_from_center(float x, float y) {
        return null;
    }

    public Vector2 get_vector2_from_center(float x, float y, float dt) { // дает вектор от центра кализии
        if (VectorUtils.getLen2(x, y, positionCenter.x, positionCenter.y) < radius2) {
            Vector2 pos = new Vector2(x, y);
            Vector2 result = new Vector2(0, 0);
           // result.set(positionCenter.sub(pos).scl(dt/1000f));
            System.out.println(dt/1000f);
            return result.set(pos.cpy().sub(this.positionCenter)).scl(dt/100);


        }
        return null;
    }

    public Vector2 get_vector2_from_center(Vector2 pos,float dt) { // дает вектор от центра кализии
//        if (pos.dst(positionCenter) < radius) {
//            Vector2 result = new Vector2(0, 0);
//            result.set(pos.cpy().sub(positionCenter).scl(dt));
//            return result;
//        }
        return get_vector2_from_center(pos.x,pos.y,dt);
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
