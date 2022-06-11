package com.mygdx.tanks2d.ParticleEffect;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;

public class Falling_element {
    Vector3 position;
    Texture texture;
    SpriteBatch spriteBatch;
    float speed , alpha;
    static final float MIN_H =0;
    static final float MAX_H =90;
    float align;
    Color color;

    public Falling_element() {
        this.position = new Vector3(-1000, -1000, -1);
        this.align = MathUtils.random(-360,360);
        color = Color.WHITE;

    }

    public void add(float x, float y, float h, float speed, Texture tex) {
        this.position.set(x, y, h);
        this.speed = speed;
        this.texture = tex;
        this.alpha = .7f;

    }

    protected void update(float dt) {
        position.z -= dt * speed;
    }

    public void rander(float dt, Camera camera, SpriteBatch spriteBatch) {

        if (position.z > MAX_H) return; if(MIN_H > position.z) return;
        update(dt);

//        this.position.x += xw * dt;
//        this.position.y += yw * dt;

        float dx = ((camera.position.x - position.x) / -300);

        float dy = ((camera.position.y - position.y) / -300);
        float wi = position.z + texture.getWidth();
        float hi = position.z + texture.getHeight();


        spriteBatch.setColor(1, 1, 1, alpha);
        spriteBatch.draw(texture, (position.x + this.position.z * dx) -(wi/2) , (position.y + position.z * dy) -(hi/2), position.z, position.z);
        spriteBatch.setColor(1, 1, 1, 1);

//        u.getPosition().x - f.getWidth() * u.getScale() / 2, u.getPosition().y - f.getWidth() * u.getScale() / 2,
//                0, 0,
//                f.getWidth(), f.getHeight(),
//                u.getScale(), u.getScale(),
//                0,
//                0, 0,
//                f.getWidth(), f.getHeight(),
//                false, false);

    }
}
