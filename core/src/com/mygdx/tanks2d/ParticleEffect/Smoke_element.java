package com.mygdx.tanks2d.ParticleEffect;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

public class Smoke_element extends Falling_element {
    static final float MIN_H = 0;
    static final float MAX_H = 3;

    @Override
    protected boolean checkLimet() {
        if (position.z > Smoke_element.MAX_H) return false;
        if (position.z < Smoke_element.MIN_H) return false;
        return true;
    }

    @Override
    protected void update(float dt, Camera camera) {
        dx = ((camera.position.x - position.x) / -10);
        dy = ((camera.position.y - position.y) / -10);
        wi = position.z + texture.getWidth() / 2;
        hi = position.z + texture.getHeight() / 2;

        position.z += dt * speed;
        color.a = MathUtils.map(MIN_H, MAX_H, .7f, 0.03f, position.z);
        color.a = Interpolation.fade.apply(color.a);
    }


}
