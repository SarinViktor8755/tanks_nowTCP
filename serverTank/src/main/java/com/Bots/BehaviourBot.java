package main.java.com.Bots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.ClientNetWork.Heading_type;
import com.mygdx.tanks2d.ClientNetWork.Network;

import main.java.com.GameServer;
import main.java.com.Units.ListPlayer.Player;

public class BehaviourBot { // поведение бота - вектор направлениея  - логика принятия решений

    final float SPEED = 90f;
    final float SPEED_ROTATION = 180f;


    public static void moveBot(float dt, DBBot db_bot) { // просто перемещение бота
        db_bot.getPosition().add(db_bot.getBody_rotation_angle());
    }

    private static void rotation_body(float dt, DBBot db_bot) { // поворот тела
        if (MathUtils.isEqual(db_bot.getBody_rotation_angle().angleDeg(), db_bot.getTarget_body_rotation_angle().angleDeg(), .5f))
            return;


        this.direction.setAngleDeg(direction.angleDeg() - Gdx.graphics.getDeltaTime() * SPEED_ROTATION);
    } else

    {
        this.direction.setAngleDeg(direction.angleDeg() + Gdx.graphics.getDeltaTime() * SPEED_ROTATION);
    }

}

    public void botShoot(GameServer gs, DBBot dbBot) { /// доделаааать
        Player bot = gs.getLp().getPlayerForId(dbBot.getId());
        Vector2 velBullet = new Vector2(700, 0).setAngleDeg(bot.getRotTower());
        Network.StockMessOut sm = new Network.StockMessOut();
        sm.p1 = dbBot.getPosition().x;
        sm.p2 = dbBot.getPosition().y;
        sm.p3 = dbBot.getAngle_rotation_tower().angleDeg();
        sm.p4 = 5000 + MathUtils.random(99999999);
        sm.tip = Heading_type.MY_SHOT;

        gs.getMainGame().getBullets().addBullet(new Vector2(bot.getXp(), bot.getYp()), velBullet, 44, bot.getId());
        gs.getServer().sendToAllTCP(sm);
    }


}
