package com.mygdx.tanks2d.ParticleEffect.StereoSmoke;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Flying_stereo_elements_base {
    Vector3 position;  // позиция
    Texture texture;   // текстура
    SpriteBatch spriteBatch;   // батч
    float speed;      // скорость полета
    static final float MIN_H = 0;   // минимальнаявысота
    static final float MAX_H = 1;     // максимальнаявысота
    float align; // угол текстуры
    Color color; // цвет ендера
    float dx, dy, wi, hi;// зачем не помню

    public Flying_stereo_elements_base(SpriteBatch spriteBatch) {
        this.position = new Vector3(-1000, -1000, -100);
        this.align = MathUtils.random(-360, 360);
        color = Color.WHITE;
    }

    public void add(float x, float y, float h, float speed, Texture tex, float r, float g, float b, float a) {
        this.position.set(x, y, h);
        this.speed = speed;
        this.texture = tex;
        this.color = new Color(r, g, b, a);
    }

    protected void update(float dt, Camera camera) {
        position.z -= dt * speed;
        dx = ((camera.position.x - position.x) / -300);
        dy = ((camera.position.y - position.y) / -300);
        wi = position.z + texture.getWidth();
        hi = position.z + texture.getHeight();
    }

    protected boolean checkLimet(){
        if (position.z > this.MAX_H -1) return false;
        if (position.z < this.MIN_H) return false;
        return true;
    }

    public void rander(float dt, Camera camera) {
        if(!checkLimet()) return;
        update(dt, camera);
        spriteBatch.setColor(this.color);
        spriteBatch.draw(
                texture, position.x + dx, position.y + dy,
                wi / 2,
                hi / 2,
                texture.getWidth(), texture.getHeight(),
                position.z+.4f, position.z +.4f, align, 0, 0
                , texture.getWidth(), texture.getHeight(), false, false

        );
        spriteBatch.setColor(1, 1, 1, 1);
    }


}
