package main.java.com.Bots;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.Units.Tanks.OpponentsTanks;

import java.util.HashMap;

public class TowerRotationLogic { /// поворот любой башни ЛОГИКА - входит в класс танка одного
    private HashMap<Integer, OpponentsTanks> listOpponents;

    private Vector2 direction_body; // направление тела
    private Vector2 direction_tower; // направление башни
    private Vector2 myPosition; // позиция

    private boolean rotation; // вращение

    final float rast_to_target = 80_000; // растояние обноружения цели
    final float speed_rotation_towr = 120; // скорость вращения башни

    Integer nomTarget;
    int target_tank;
    float targetAlign;

    public void updateTowerRotation(float delta) {
//        turningTower(delta)


    }

//
//    private void makingDecisionTower(float delta) { // принятие решение башня
//
//
//        if (nomTarget == null) { // если нет целей
//            turningTower(this.direction.angleDeg(), delta);
//            target_tank = 0;
//            if (MathUtils.randomBoolean(.3f))
//           targetDetectionTower(this.myPosition); // ищем цели
//            nomTarget = selectTarget();
//        } else {    // если  цели есть
//            if(!listOpponents.get(nomTarget).isLive() ) {target_tank = 0; nomTarget=null; return;}
//            turningTower(returnAngle(listOpponents.get(nomTarget).getPosition(), myPosition), delta);
//            if (MathUtils.randomBoolean(.05f)) targetDetectionTower(this.myPosition); // ищем цели
//            if (checkLen()) nomTarget = null;
//        }
//    }
//
//
//    private void turningTower(float delta) { /// повернуть башню на градус
//        if (!MathUtils.isEqual(direction_tower.angleDeg(), targetAlign, 1.2f)) {
//            rotation = true;
//            if ((direction.cpy().setAngleDeg(targetAlign).angleDeg(direction_tower) > 180))
//                direction_tower.rotateDeg(-speed_rotation_towr * delta);
//            else
//                direction_tower.rotateDeg(speed_rotation_towr * delta);
//        }
//    }
//





}
