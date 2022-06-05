package main.java.com.Bots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.ClientNetWork.Heading_type;
import com.mygdx.tanks2d.ClientNetWork.Network;
import com.mygdx.tanks2d.Units.ListPlayers;

import main.java.com.GameServer;
import main.java.com.Units.ListPlayer.Player;

public class BehaviourBot { // поведение бота - вектор направлениея  - логика принятия решений

    final static float SPEED = 90f;
    final static float SPEED_ROTATION = 180f;

    final static float SPEED_BULLET = 700;



    public static void moveBot(float dt, DBBot db_bot) { // просто перемещение бота
        db_bot.getPosition().add(db_bot.getBody_rotation_angle().scl(dt * SPEED));
    }

    private static void rotation_body(float dt, DBBot db_bot) { // поворот тела
        if (MathUtils.isEqual(db_bot.getBody_rotation_angle().angleDeg(), db_bot.getTarget_body_rotation_angle().angleDeg(), .5f))
            return;
        if ((db_bot.getBody_rotation_angle().angleDeg(db_bot.getTarget_body_rotation_angle()) > 180)) {
            db_bot.getBody_rotation_angle().setAngleDeg(db_bot.getBody_rotation_angle().angleDeg() - dt * SPEED_ROTATION);
        } else
            db_bot.getBody_rotation_angle().setAngleDeg(db_bot.getBody_rotation_angle().angleDeg() + dt * SPEED_ROTATION);
    }


    public static void botShoot(GameServer gs, DBBot dbBot) { /// выстрел LAVEL_1
        Player bot = gs.getLp().getPlayerForId(dbBot.getId());
        Vector2 velBullet = new Vector2(SPEED_BULLET, 0).setAngleDeg(bot.getRotTower());
        Network.StockMessOut sm = new Network.StockMessOut();
        sm.p1 = dbBot.getPosition().x;
        sm.p2 = dbBot.getPosition().y;
        sm.p3 = dbBot.getAngle_rotation_tower().angleDeg();
        sm.p4 = 5000 + MathUtils.random(99999999);
        sm.tip = Heading_type.MY_SHOT;

        gs.getMainGame().getBullets().addBullet(new Vector2(bot.getXp(), bot.getYp()), velBullet, 44, bot.getId());
        gs.getServer().sendToAllTCP(sm);
    }

//    public static void updatePlayerInPlayList(ListPlayers listPlayers){
//        for (var entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }
//
//
//    }


}
