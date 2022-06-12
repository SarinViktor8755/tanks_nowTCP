package com.mygdx.tanks2d.ParticleEffect;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

public class Smoke_element extends Falling_element{
    @Override
    protected void update(float dt, Camera camera) {
        dx = ((camera.position.x - position.x) / -300);
        dy = ((camera.position.y - position.y) / -300);
        wi = position.z + texture.getWidth()/2;
        hi = position.z + texture.getHeight()/2;

        position.z += dt * speed;
        color.a = MathUtils.map(MIN_H,MAX_H,1,0.03f,position.z);
        color.a = Interpolation.fade.apply(color.a);
    }
}
