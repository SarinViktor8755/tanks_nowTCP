package main.java.com.Bots;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.Units.Tanks.OpponentsTanks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.java.com.Units.ListPlayer.ListPlayers;
import main.java.com.Units.ListPlayer.Player;

public class TowerRotationLogic { /// поворот любой башни ЛОГИКА - входит в класс танка одного
    private HashMap<Integer, OpponentsTanks> listOpponents;


    private boolean rotation; // вращение

    public final static float rast_to_target = 80_000; // растояние обноружения цели
    final static float speed_rotation_towr = 120; // скорость вращения башни


    public static void updateTowerRotation(float delta, DBBot dbBot, Player p, ListPlayers listPlayers) {
        making_Decision_Tower(dbBot, p, listPlayers); // принятие решение башня
        rotation_Tower(delta, dbBot);/// повернуть башню на градус
        // ..............

    }

    private static void making_Decision_Tower(DBBot dbBot, Player p, ListPlayers lp) { // принятие решение башня
        if (dbBot.getNomTarget() == null) { // если нет целей
            dbBot.setTarget_tank(0);
            dbBot.setTarget_angle_rotation_tower(p.getBody_rotation().cpy().rotateDeg(180));
            //if (MathUtils.randomBoolean(.3f)) targetDetectionTower(this.myPosition); // ищем цели
            //nomTarget = selectTarget();
           // if (MathUtils.randomBoolean(.05f))
                scanning_the_terrain(dbBot, p, lp); // поиск цели
        } else {
            capturing_target(dbBot, p, lp);
            if(!lp.getPlayerForId(dbBot.getNomTarget()).isLive())dbBot.setNomTarget(null);
            if(lp.getPlayerForId(dbBot.getNomTarget()).getPosi().dst2(p.getPosi()) > rast_to_target) dbBot.setNomTarget(null);


        }
        System.out.println(dbBot.getNomTarget() + "@@" + p.getId());

    }

    private static void scanning_the_terrain(DBBot dbBot, Player p, ListPlayers lp) {
        Integer targetID = lp.targetTankForBotAttack(p.getPosi());
        if (targetID != null) dbBot.setNomTarget(targetID);
        else return;
    }

    private static void rotation_Tower(float delta, DBBot dbBot) { /// повернуть башню на градус
        if (!MathUtils.isEqual(dbBot.getTarget_angle_rotation_tower().angleDeg(), dbBot.getTargetAlign(), 1.2f)) { // сравнение угла цели , и угл аревльного
            if ((dbBot.getTarget_angle_rotation_tower().cpy().setAngleDeg(dbBot.getTargetAlign()).angleDeg(dbBot.getTarget_angle_rotation_tower()) > 180))
                dbBot.getTarget_angle_rotation_tower().rotateDeg(-speed_rotation_towr * delta);
            else
                dbBot.getTarget_angle_rotation_tower().rotateDeg(speed_rotation_towr * delta);
        }
    }

    private static boolean capturing_target(DBBot dbBot, Player p, ListPlayers lp){ //захват целт
        int idTarget = dbBot.getNomTarget();
        dbBot.setTarget_angle_rotation_tower(returnAngle(lp.getPlayerForId(idTarget).getPosi(),p.getPosi()));

        return false;
    }

    private static boolean ckeck_target(DBBot dbBot, Player p, ListPlayers lp){ // проверка цели
        if(!p.isLive()) return false;
       // if(MathUtils.randomBoolean(.005f)) return false;
     //   if (p.getPosi().dst2(lp.getPlayerForId(dbBot.getNomTarget()).getPosi()) > TowerRotationLogic.rast_to_target) return false;


        return true;
    }

    private static float returnAngle(Vector2 positionoOpponent, Vector2 positionMy) { /// определить угол поворота
        return positionoOpponent.cpy().sub(positionMy).angleDeg();
    }

//


}
