package com.mygdx.tanks2d.ParticleEffect.StereoSmoke;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

public class Smoke_element extends Falling_element { /// это горит танк
    static final float MIN_H = 0;
    static final float MAX_H = 8;

//    @Override
//    protected boolean checkLimet() {
//        if (position.z > T.MAX_H) return false;
//        if (position.z < Smoke_element.MIN_H) return false;
//        return true;
//    }

    @Override
    protected void update(float dt, Camera camera) {
        dx = ((camera.position.x - position.x) / -10);
        dy = ((camera.position.y - position.y) / -10);
        wi = position.z + texture.getWidth() / 2;
        hi = position.z + texture.getHeight() / 2;

        position.z += dt * speed;
        color.a = MathUtils.map(Smoke_element.MIN_H, Smoke_element.MAX_H, .7f, -0.00f, position.z);
        color.a = Interpolation.pow3OutInverse.apply(color.a);
    }


}
