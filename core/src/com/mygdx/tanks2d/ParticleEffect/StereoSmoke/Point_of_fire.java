package com.mygdx.tanks2d.ParticleEffect.StereoSmoke;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.tanks2d.MainGame;
import com.mygdx.tanks2d.ParticleEffect.ParticleCustum;

public class Point_of_fire {// точка генерация поэара

    private float lifetime_initial = 0;
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

    public Point_of_fire(ParticleCustum particleCustum) {
        this.lifetime_initial = -1;
        this.time_life = MathUtils.random(7, 12);
        this.pc = particleCustum;
        this.x = -1000;
        this.y = -1000;
    }

    public void update(float dt) {
        if (!isLive()) return;
        this.time_life = time_life - dt;
       // System.out.println(time_life);
         generateSmoke();
    }

    public boolean isLive() {
        if (time_life < -.5f) return false;
        return true;
    }

    private void generateSmoke() {
        float s = MathUtils.map(lifetime_initial, 0, 0.95f, .000f, time_life);
        if (MathUtils.randomBoolean(s/100f))
            pc.addParticalsSmokeStereo(this.x, this.y, 15, true);
    }

    public void setParametors(float lifetime_initial, float x, float y){
        time_life = lifetime_initial;
        this.lifetime_initial = lifetime_initial;
        this.x = x;
        this.y = y;
    }

}
