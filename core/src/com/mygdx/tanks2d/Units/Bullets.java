package com.mygdx.tanks2d.Units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.tanks2d.Screens.GamePlayScreen;
import com.mygdx.tanks2d.Units.BulletPool.Bullet;
import com.mygdx.tanks2d.Units.BulletPool.BulletPool;


public class Bullets {
    Texture img;

    private final Array<Bullet> activeBullets = new Array<>();
    private final BulletPool bp = new BulletPool();
    private final int MAX_distribution_smoke = 5;

    private GamePlayScreen gpl;

    public Bullets(GamePlayScreen gpl) {
        this.gpl = gpl;
        img = gpl.getAssetsManagerGame().get("bullet.png", Texture.class);
    }

    public void addBullet(Vector2 pos, Vector2 vel, int nomer) {
        //System.out.println("addBULET !!!!!!!!!");
        // получи пулю из нашего бассейна
        Bullet b = bp.obtain();
        /// стреляйте пулей с того места, на которое мы нажимаем, в направлении прямо вверх
        b.fireBullet(pos.x, pos.y, vel.x, vel.y, nomer);
        // добавьте в наш массив маркеры, чтобы мы могли получить к ним доступ в нашем методе визуализации
        activeBullets.add(b);

        // System.out.println(activeBullets.size);
    }

    public void randerBullets(float delta) {
        float width = 5;
        float height = 13;
        SpriteBatch sb = gpl.getBatch();
        sb.setColor(1, MathUtils.random(0,255),  MathUtils.random(0,255), 1);
        for (Bullet b : activeBullets) {

            if (!checkingGoingAbroad(b.position.x, b.position.y)) {
                removeBullet(b);
                continue;
            }


            for (int i = 0; i < MathUtils.random(1, 4); i++) {
                if (MathUtils.randomBoolean(.7f))
                   // System.out.println(b.getNamber() + " - -- - -");
                    gpl.pc.addParticalsSmokeOneBullet(b.position.x + MathUtils.random(-MAX_distribution_smoke, +MAX_distribution_smoke), b.position.y + MathUtils.random(-MAX_distribution_smoke, +MAX_distribution_smoke));
            }
            b.update(delta); // update bullet
//                if(i ==1)
            sb.draw(img,
                    b.position.x - width / 2,
                    b.position.y - height / 2,
                    width / 2, height / 2,
                    width, height,
                    1, 1,
                    b.direction.angleDeg() - 90,
                    1, 1,
                    (int) width, (int) height,
                    false, false

            );

            sb.setColor(1, 1, 1, 1);

        }
    }

    public void removeBullet(Bullet b){
        activeBullets.removeValue(b, true);
        bp.free(b);
    }

    public void removeBullet(int nomBullet){  // удаление по номеру
      //  System.out.println("FINE:");
        Bullet b;
        for (int i = 0; i < activeBullets.size; i++) {
            b = activeBullets.get(i);
            if(b.namber == nomBullet){
                //bp.free(b);
                b.position.set(-20_000,-20_000);

            }
        }


//        activeBullets.removeValue(b, true);
//        bp.free(b);
    }

    private boolean checkingGoingAbroad(float x, float y) {
        return gpl.getGameSpace().inPointLocation(x, y);
    }




    private boolean checkingTerrainObstacles(float x, float y) { /// н ерабочий метод
        return gpl.getGameSpace().checkMapBorders(x, y);
    }

//    private boolean checkingGoingTanks(Vector2 pos) {
//        if(gpl.getTanksOther().isCollisionsTanks(pos)!= null) return true;
//        return true;
//    }


}
