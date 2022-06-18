package com.mygdx.tanks2d.ParticleEffect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.ParticleEffect.StereoSmoke.Falling_element;
import com.mygdx.tanks2d.ParticleEffect.StereoSmoke.PasricalDeathSmoke;
import com.mygdx.tanks2d.ParticleEffect.StereoSmoke.Point_of_fire;
import com.mygdx.tanks2d.ParticleEffect.StereoSmoke.Smoke_element;
import com.mygdx.tanks2d.Screens.GamePlayScreen;
import com.mygdx.tanks2d.Utils.VectorUtils;


import java.util.ArrayDeque;


public class ParticleCustum {
    ArrayDeque<ParticleSmoke> particleDeque;// дым
    ArrayDeque<PasricalExplosion> pasricalExplosions; // мелкие взрывы
    ArrayDeque<PasricalExplosionBigParameter> pasricalExplosionsBigParam; // большие взрывы
    ArrayDeque<Garbage> pasricalGarbage; // большие взрывы
    ArrayDeque<Shard> shardsArr;
    ArrayDeque<Explosion_Death> explosion_Death; // взрыв из тотал анигилейшен
    ArrayDeque<Explosion_Death> explosion_Death_little; // взрыв из тотал анигилейшен
    ArrayDeque<Falling_element> falling_elements; //падаюшие элементы
    ArrayDeque<Smoke_element> smoke_elements; // Дым в лбьемк
    ArrayDeque<PasricalDeathSmoke> pasricalDeathSmokes;

    ArrayDeque<Point_of_fire> point_of_fires;

    Vector2 temp_V;
    private SpriteBatch sb;

    private Texture t;
    private Texture f;
    private Texture iron;
    private Texture shardsTex;// осколки
    private TextureAtlas textureAtlasDeathExplosion; /// атлес текстур взрыва тотала
    Point_of_fire point_of_fire;

    GamePlayScreen gps;

    public ParticleCustum(GamePlayScreen gps, Texture t, Texture f, Texture iron, TextureAtlas de, Texture shards) {
        this.t = t;
        this.f = f;
        this.iron = iron;
        this.textureAtlasDeathExplosion = de;
        this.shardsTex = shards;
        this.sb = gps.getBatch();

        temp_V = new Vector2(0, 0);
        this.shardsArr = new ArrayDeque<>();
        this.particleDeque = new ArrayDeque<>();
        this.pasricalExplosions = new ArrayDeque<>();
        this.pasricalExplosionsBigParam = new ArrayDeque<>();
        //this.pasricalGarbage = new ArrayDeque<>();
        this.explosion_Death = new ArrayDeque<>(); ///  текстур взрыва тотала
        this.explosion_Death_little = new ArrayDeque<>(); ///  текстур взрыва тотала__little

        this.falling_elements = new ArrayDeque<>();
        this.smoke_elements = new ArrayDeque<>();
        this.pasricalDeathSmokes = new ArrayDeque<>();
        this.point_of_fires = new ArrayDeque<>();

        for (int i = 0; i < 2000; i++) {
            Shard ed = new Shard();
            this.shardsArr.add(ed);
        }

        for (int i = 0; i < 30; i++) {
            Point_of_fire ed = new Point_of_fire(this);
            this.point_of_fires.add(ed);
        }

        for (int i = 0; i < 120; i++) {
            Falling_element falling_element = new Falling_element();
            this.falling_elements.add(falling_element);
        }

        for (int i = 0; i < 120; i++) {
            Smoke_element smoke_element = new Smoke_element();
            this.smoke_elements.add(smoke_element);
        }

        for (int i = 0; i < 120; i++) {
            Falling_element falling_element = new Falling_element();
            this.falling_elements.add(falling_element);
        }

        for (int i = 0; i < 4; i++) {
            Explosion_Death ed = new Explosion_Death();
            this.explosion_Death.add(ed);
        }

        for (int i = 0; i < 25; i++) {
            Explosion_Death ed = new Explosion_Death();
            this.explosion_Death_little.add(ed);
        }

        for (int i = 0; i < 350; i++) {
            this.particleDeque.add(new ParticleSmoke());
        }

        for (int i = 0; i < 12; i++) {
            this.pasricalExplosions.add(new PasricalExplosion());
        }

        for (int i = 0; i < 20; i++) {
            this.pasricalExplosionsBigParam.add(new PasricalExplosionBigParameter());
        }

//        for (int i = 0; i < 800; i++) {
//            this.pasricalGarbage.add(new Garbage());
//        }

        for (int i = 0; i < 18; i++) {
            this.pasricalDeathSmokes.add(new PasricalDeathSmoke());
        }


       // point_of_fire = new Point_of_fire(15, 300, 300, this);


        this.gps = gps;
        t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        shards.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        //f.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);

    }

    public void randerGarbage(SpriteBatch spriteBatch) {

        int k = 0;

//        for (Garbage g : pasricalGarbage) {  // oskolki
//            //if (!fd.isLife()) continue;
//            k++;
//
//
//            g.upDate();
//            spriteBatch.setColor(1, 1, 1, 1 - (MathUtils.map(0f, pasricalGarbage.size(), 0.3f, 1f, k)));
//
//
//            spriteBatch.draw(iron,
//                    g.getPos().x - iron.getWidth() / 2, g.getPos().y - iron.getHeight() / 2,
//                    f.getWidth(), f.getHeight(),
//                    iron.getWidth() / 2, iron.getHeight() / 2,
//                    1, 1,
//                    g.getRot(),
//                    0, 0,
//                    iron.getWidth(), iron.getHeight(),
//                    false, false);
//        }

        for (Shard s : shardsArr) {  // частицы
            s.upDate();
            spriteBatch.setColor(1, 1, 1, 1);
            // spriteBatch.draw(shardsTex,MathUtils.random(150,500),MathUtils.random(150,500));
            spriteBatch.draw(shardsTex, s.getPos().x, s.getPos().y);
        }


    }

    public void addAnimationDeath(float x, float y) {
        this.addPasricalDeath(x, y);
        for (int i = 0; i < 90; i++) {
            addShares(x, y);
        }
        gps.getGameSpace().getRadspurens().addCrater(x, y, MathUtils.random(0, 360)); // кратор
        gps.pc.addPasricalDeath_little(x, y, 2.7f);

    }


    public void render(SpriteBatch sb) {
        float dt = Gdx.graphics.getDeltaTime();
        rander_point_of_fires(dt);
        rander_particleDeque(); // кусочки мусора
        rander_explosion_Death_little(); // маленькие взрывы
//        rander_pasricalExplosions();
        //rander_pasricalExplosionsBigParam();
      // updatePoinsFire(dt); // обновление дыма
        rander_smoke_death(sb, Gdx.graphics.getDeltaTime());
        rander_explosion_Death(); // большой взрыв
//        rander_Falling_element(sb, dt);
        rander_smoke_element(sb, dt); // дым вылетающий от ранения

    }

    public void rander_explosion_Death(){
        for (Explosion_Death ed : explosion_Death) {
            if (!ed.isLife()) continue;
            ed.update();
            /////////////////
            TextureAtlas.AtlasRegion tex = textureAtlasDeathExplosion.findRegion(ed.getNameTextureRegion());
            float xw = MathUtils.map(100, 0, 100, 0, tex.getRegionWidth());
            float yw = MathUtils.map(100, 0, 100, 0, tex.getRegionHeight());
            /////////////////
            sb.draw(
                    tex,
                    ed.getPosition().x - (tex.getRegionWidth() / 2), ed.getPosition().y - (tex.getRegionHeight() / 2),
                    xw, yw
            );
        }
    }

    public void addParticalsSmokeStereo(float x, float y, float hp) {/// дым горения
        float black = MathUtils.map(50, 0, 0, 1, hp) + MathUtils.random(-.35f, +.35f);
        if (!checkViseble(x, y)) return;
        Smoke_element smoke_element = this.smoke_elements.pollLast();
        smoke_element.add(gps.getTank().getPosition().x + MathUtils.random(-5, 5), gps.getTank().getPosition().y + MathUtils.random(-5, 5),
                0f, MathUtils.random(.5f, 1f), t,
                1 - black, 1 - black, 1 - black, 1
        );
        //      smoke_element.add(gps.getTank().getPosition().x + 16 + MathUtils.random(-16, 16), gps.getTank().getPosition().y + 16 + MathUtils.random(-16, 16), MathUtils.random(2, 8), MathUtils.random(35, 40), t);
        smoke_elements.offerFirst(smoke_element);
        //add_Point_of_fire(x, y);
    }

    public void addParticalsSmokeStereo(float x, float y, float random, boolean a) {/// дым умершего
        Smoke_element smoke_element = this.smoke_elements.pollLast();
        smoke_element.add(x + MathUtils.random(-random, random), y + MathUtils.random(-random, random),
                0f, MathUtils.random(.5f, 3f), t,
                1, 0, 1, 1
        );
        //      smoke_element.add(gps.getTank().getPosition().x + 16 + MathUtils.random(-16, 16), gps.getTank().getPosition().y + 16 + MathUtils.random(-16, 16), MathUtils.random(2, 8), MathUtils.random(35, 40), t);
        smoke_elements.offerFirst(smoke_element);
    }


//    public void addDeathSmoke(float x, float y) {
//        PasricalDeathSmoke pasricalDeathSmoke = this.pasricalDeathSmokes.pollLast();
//        pasricalDeathSmoke.add(x, y, 0, 2, t);
//        pasricalDeathSmokes.offerFirst(pasricalDeathSmoke);
//    }

    private void rander_explosion_Death_little() {
        for (Explosion_Death ed : explosion_Death_little) {
            if (!ed.isLife()) continue;
            ed.update();

            /////////////////
            TextureAtlas.AtlasRegion tex = textureAtlasDeathExplosion.findRegion(ed.getNameTextureRegion());
            float xw = MathUtils.map(100, 0, 100, 0, tex.getRegionWidth());
            float yw = MathUtils.map(100, 0, 100, 0, tex.getRegionHeight());
            /////////////////
            sb.draw(
                    tex,
                    ed.getPosition().x - (tex.getRegionWidth() / 2 / ed.getKefm() * ed.getTime_life()), ed.getPosition().y - (tex.getRegionHeight() / 2 / ed.getKefm() * ed.getTime_life()),
                    xw / ed.getKefm() * ed.getTime_life(), yw / ed.getKefm() * ed.getTime_life()
            );

        }
    }

    public void addParticalsSmoke(int quantity, float x, float y, int hp) {
        // System.out.println(hp);
        if (VectorUtils.getLen2(gps.getTank().getPosition(), x, y) > 90_000) return;
        float black = MathUtils.map(50, 0, 0, 1, hp) + MathUtils.random(-.35f, +.35f);
        //  System.out.println(black);
        if (!checkViseble(x, y)) return;
        for (int i = 0; i < quantity; i++) {
            ParticleSmoke a = this.particleDeque.pollLast();
            a.setParameters(MathUtils.random(.5f, 2),
                    x + MathUtils.random(-7, 7),
                    y + MathUtils.random(-7, 7),
                    MathUtils.random(0, 360), MathUtils.random(-20, 20),
                    MathUtils.random(-.2f, .7f),
                    MathUtils.random(-.2f, .4f),
                    MathUtils.random(-10, 10), MathUtils.random(-10, 10),
                    1 - black, 1 - black, 1 - black, 1
            );
            this.particleDeque.offerFirst(a);
        }
    }

    private void updatePoinsFire(float dt) {
        for (Point_of_fire pf : point_of_fires) {  // дым после смерти
            pf.update(dt);
        }
    }

    public void add_Point_of_fire(float x, float y) {
        Point_of_fire pf = this.point_of_fires.pollLast();
        pf.setParametors(MathUtils.random(7, 12), x, y);
        this.point_of_fires.offerFirst(pf);
    }


//    public void addParticalsSmokeOne(float x, float y) {
//        if (VectorUtils.getLen2(gps.getTank().getPosition(), x, y) > 90_000) return;
//        ParticleSmoke a;
//        a = this.particleDeque.pollLast();
//        a.setParameters(1.0f,
//                x, y,
//                MathUtils.random(0, 360), MathUtils.random(-5, 5),
//                .5f,
//                1.2f,
//                -.4f, -.4f,
//                MathUtils.random(.7f, 1), .4f, .4f,
//                .2f
//        );
//        this.particleDeque.offerFirst(a);
//    }

    public void addParticalsSmokeOneBullet(float x, float y) {
        if (!checkViseble(x, y)) return;
        ParticleSmoke a;
        a = this.particleDeque.pollLast();
        a.setParameters(.4f,
                x, y,
                MathUtils.random(0, 360), MathUtils.random(-5, 5),
                .3f,
                00.1f,
                -.4f, -.4f,
                .4f, .4f, MathUtils.random(.7f, 1),
                1f
        );
        this.particleDeque.offerFirst(a);
    }



    public void addPasricalDeath(float x, float y) {
        if (!checkViseble(x, y)) return;
        Explosion_Death a = this.explosion_Death.pollLast();
        a.setParameters(x, y);
        //System.out.println("Vzriv smerti");
       // addDeathSmoke(x, y);
        this.explosion_Death.offerFirst(a);
    }

    public void addPasricalDeath_little(float x, float y, float kefM) {
        if (!checkViseble(x, y)) return;
        Explosion_Death a = this.explosion_Death_little.pollLast();
        a.setParameters(x, y, kefM);
        this.explosion_Death_little.offerFirst(a);
    }

//    public void addGarbage(float x, float y) {
//        Garbage g = this.pasricalGarbage.pollLast();
//        g.setParametors(x, y);
//        this.pasricalGarbage.offerFirst(g);
//    }


    public void addShares(float x, float y) {
        temp_V.set(1, 1).setLength2(MathUtils.random(150, 230));
        temp_V.setAngleDeg(MathUtils.random(0, 360));
        addShares(x, y, temp_V.x, temp_V.y);
        // System.out.println("!");

    }

    public void addShares(float x, float y, float vx, float vy) {
        Shard g = this.shardsArr.pollLast();
        g.setParametors(x, y);
        g.setParametors(x, y, vx, vy);
        this.shardsArr.offerFirst(g);
    }


    public void addPasricalExplosionDeath(float x, float y) {
        if (!checkViseble(x, y)) return;
        PasricalExplosionBigParameter f;
        f = this.pasricalExplosionsBigParam.pollLast();
        f.setParameters(x, y);
        this.pasricalExplosionsBigParam.offerFirst(f);

        for (int i = 0; i < 2; i++) {
            f = this.pasricalExplosionsBigParam.pollLast();
            f.setParameters(x + MathUtils.random(-40, +40), y + MathUtils.random(-40, +40));
            this.pasricalExplosionsBigParam.offerFirst(f);
        }


    }

    private boolean checkViseble(float x, float y) {
        if (!MathUtils.isEqual(gps.getCameraGame().getCameraPosition().x, x, 500)) return false;
        if (!MathUtils.isEqual(gps.getCameraGame().getCameraPosition().y, y, 350)) return false;
        return true;
    }


    public void rander_Falling_element(SpriteBatch spriteBatch, float deltaTimme) {
        for (Falling_element falling_element : falling_elements) {
            falling_element.rander(deltaTimme, gps.getCameraGame().getCamera(), spriteBatch);

        }
    }

    private void  rander_point_of_fires(float dt){
        for (Point_of_fire pf : point_of_fires) {
            pf.update(dt);

        }
    }

    private void rander_particleDeque() {
        for (ParticleSmoke u : particleDeque) {
            if (!u.isLife()) continue;
//            k++;
            u.update();
            u.setAlpha(MathUtils.clamp(u.getTime_life(), -1, .7f));
            sb.setColor(u.getColor());
            sb.draw(t,
                    u.getPosition().x - t.getWidth() / 2, u.getPosition().y - t.getHeight() / 2,
                    t.getWidth() / 2, t.getHeight() / 2,
                    t.getWidth(), t.getHeight(),
                    u.getScale(), u.getScale(),
                    u.getRotation(),
                    0, 0,
                    t.getWidth(), t.getHeight(),
                    false, false);
        }
        sb.setColor(1, 1, 1, 1);

    }

    public void addPasricalExplosion(float timeLife, float x, float y) {
        if (!checkViseble(x, y)) return;
        PasricalExplosion a = this.pasricalExplosions.pollLast();
        a.setParameters(timeLife, x, y);
        this.pasricalExplosions.offerFirst(a);
    }

    private void rander_pasricalExplosions() {  // взрывы вроде мелкие
        for (PasricalExplosion u : pasricalExplosions) {
          //  System.out.println("Fire 1  " + pasricalExplosions.size());
            if (!u.isLife()) continue;
            u.update();
       //     System.out.println("Fire 2");
            sb.setColor(1, 1, 1, MathUtils.clamp(u.getTime_life(), -1, 1) * 10);
            sb.draw(f,
                    u.getPosition().x - f.getWidth() * u.getScale() / 2, u.getPosition().y - f.getWidth() * u.getScale() / 2,
                    0, 0,
                    f.getWidth(), f.getHeight(),
                    u.getScale(), u.getScale(),
                    0,
                    0, 0,
                    f.getWidth(), f.getHeight(),
                    false, false);

        }
    }

    private void rander_pasricalExplosionsBigParam() {
        for (PasricalExplosionBigParameter fd : pasricalExplosionsBigParam) {  // смерть большие
            if (!fd.isLife()) continue;
            fd.update(this);
            sb.setColor(1, 1, 1, fd.getAlpha());
            //System.out.println(fd.getAlpha());
            sb.draw(f,
                    fd.getPosition().x - t.getWidth() / 2, fd.getPosition().y - t.getHeight() / 2,
                    f.getWidth() / 2, f.getHeight() / 2,
                    f.getWidth(), f.getHeight(),
                    fd.getScale(), fd.getScale(),
                    fd.getRot(),
                    0, 0,
                    f.getWidth(), f.getHeight(),
                    false, false);
        }
    }


    public void rander_smoke_element(SpriteBatch spriteBatch, float deltaTimme) {
        for (Smoke_element smoke_element : smoke_elements) {
            smoke_element.rander(deltaTimme, gps.getCameraGame().getCamera(), spriteBatch);

        }
    }

    public void rander_smoke_death(SpriteBatch spriteBatch, float deltaTimme) {
        for (PasricalDeathSmoke par : pasricalDeathSmokes) {
            par.rander(deltaTimme, gps.getCameraGame().getCamera(), spriteBatch);

        }
    }


    public void generatorSmoke(float hp, float x, float y) { // генератор Дыма для танка
        float dhp = hp;
        if(hp > 95) return;
        dhp = MathUtils.map(0, 90, 1, 0.0f, dhp);
        dhp = Interpolation.fade.apply(dhp);
       //System.out.println(dhp);
        if (MathUtils.randomBoolean(dhp / 3f))
            addParticalsSmokeStereo(x, y, hp);
    }


}
