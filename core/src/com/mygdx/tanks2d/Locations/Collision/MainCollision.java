package com.mygdx.tanks2d.Locations.Collision;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.Screens.GamePlayScreen;

import java.util.ArrayList;


public class MainCollision {
    GamePlayScreen gsp;

    Vector2 tempVector;
    private ArrayList<BoxCollision> box = new ArrayList<>();
    private ArrayList<CircleCollision> circle = new ArrayList<>();


    public MainCollision(GamePlayScreen gsp) {
        this.gsp = gsp;
        this.circle = new ArrayList<>();
        this.box = new ArrayList<>();
        tempVector = new Vector2();
    }

    public void addRectangleMapObject(Vector2 rn, Vector2 lu) {
        this.box.add(new BoxCollision(rn, lu));

    }

    public Vector2 isCollisionsRectangle(Vector2 pos) {
        for (BoxCollision b : box) {
            if (!b.isCollisionTank(pos)) {
                tempVector.set(pos.cpy().sub(b.center));
                if (Math.abs(tempVector.x) >= Math.abs(tempVector.y)) {
                    if (tempVector.x > 0) return new Vector2(1, 0);
                    else return new Vector2(-1, 0);
                } else {
                    if (tempVector.y > 0) return new Vector2(0, 1);
                    else return new Vector2(0, -1);
                }
            }
        }
        return null;
    }

    ////////////////////////

    public void addCircleeMapObject(Vector2 c, float r) {
        this.circle.add(new CircleCollision(c, r));
    }

    public Vector2 isCircleCircle(Vector2 pos) {
        for (CircleCollision c : circle) {
            if (!c.isCollisionCircle(pos)) {
                return tempVector.set(pos.cpy().sub(c.circule).nor());
            }
        }

        return null;
    }

}




