package com.mygdx.tanks2d.ParticleEffect;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

public class Smoke_element extends Falling_element{
    @Override
    protected void update(float dt) {
        position.z += dt * speed;
        alpha = MathUtils.map(MIN_H,MAX_H,1,0.03f,position.z);
        alpha = Interpolation.fade.apply(alpha);
    }
}
