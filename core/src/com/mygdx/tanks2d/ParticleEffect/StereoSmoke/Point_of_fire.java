package com.mygdx.tanks2d.ParticleEffect.StereoSmoke;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.tanks2d.MainGame;
import com.mygdx.tanks2d.ParticleEffect.ParticleCustum;

public class Point_of_fire {// точка генерация поэара

    private  float lifetime_initial = 0;
    private float time_life = 0;
    private float x, y;

    private ParticleCustum pc;

    public Point_of_fire(float lifetime_initial, float xp, float yp, ParticleCustum particleCustum) {
        this.lifetime_initial = lifetime_initial;
        this.time_life = lifetime_initial;
        this.pc = particleCustum;
        this.x = xp;
        this.y = yp;
    }

    public void update(float dt) {
        if (!isLive()) return;
        this.time_life = time_life - dt;
        System.out.println(time_life);
        generateSmoke();
    }

    public boolean isLive() {
        //////////
        if (time_life < 2) time_life = lifetime_initial;
        //////////
        //// if(time_life < 0)return false; return true;
        return true;
    }

    private void generateSmoke() {
        float s = MathUtils.map(lifetime_initial, 0, 0.85f, .001f, time_life);
        if (MathUtils.randomBoolean(s))
            pc.addParticalsSmokeStereo(x, y, 15,true);

    }

}
