package main.java.com.Bots;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.Units.Tanks.OpponentsTanks;

import java.util.HashMap;

public class TowerRotationLogic { /// поворот любой башни ЛОГИКА - входит в класс танка одного
    private HashMap<Integer, OpponentsTanks> listOpponents;


    private boolean rotation; // вращение

    final static float rast_to_target = 80_000; // растояние обноружения цели
    final static float speed_rotation_towr = 120; // скорость вращения башни


    public static void updateTowerRotation(float delta,DBBot dbBot) {
           rotation_Tower(delta,dbBot);
               // ..............


    }

    private static void rotation_Tower(float delta, DBBot dbBot) { /// повернуть башню на градус
        if (!MathUtils.isEqual(dbBot.getTarget_angle_rotation_tower().angleDeg(), dbBot.getTargetAlign(), 1.2f)) { // сравнение угла цели , и угл аревльного
            if ((dbBot.getTarget_angle_rotation_tower().cpy().setAngleDeg(dbBot.getTargetAlign()).angleDeg(dbBot.getTarget_angle_rotation_tower()) > 180))
                dbBot.getTarget_angle_rotation_tower().rotateDeg(-speed_rotation_towr * delta);
            else
                dbBot.getTarget_angle_rotation_tower().rotateDeg(speed_rotation_towr * delta);
        }




    }
//


}
