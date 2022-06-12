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
    float speed;
    static final float MIN_H =0;
    static final float MAX_H =100;
    float align;
    Color color;
    float  dx , dy, wi , hi;



    public Falling_element() {
        this.position = new Vector3(-1000, -1000, -1);
        this.align = MathUtils.random(-360,360);
        color = Color.WHITE;

    }

    public void add(float x, float y, float h, float speed, Texture tex) {
        this.position.set(x, y, h);
        this.speed = speed;
        this.texture = tex;
        this.color.a = .7f;
    }

    public void add(float x, float y, float h, float speed, Texture tex,float r, float g , float b, float a) {
        this.position.set(x, y, h);
        this.speed = speed;
        this.texture = tex;
        this.color = new Color(r,g,b,a);
    }

    protected void update(float dt, Camera camera) {
        position.z -= dt * speed;
        dx = ((camera.position.x - position.x) / -300);
        dy = ((camera.position.y - position.y) / -300);
        wi = position.z + texture.getWidth();
        hi = position.z + texture.getHeight();
    }

    public void rander(float dt, Camera camera, SpriteBatch spriteBatch) {

        if (position.z > MAX_H) return; if(MIN_H > position.z) return;
        update(dt, camera);

        spriteBatch.setColor(this.color);
        spriteBatch.draw(texture, (position.x + this.position.z * dx) -(wi/2) , (position.y + position.z * dy) -(hi/2), position.z, position.z);
        spriteBatch.setColor(1, 1, 1, 1);



    }
}
