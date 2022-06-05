package main.java.com.Bots;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class DBBot {
    private static final float SPEED_RUN_1 = 90f;
    private int id;
    private Vector2 position;
    private Vector2 body_rotation_angle;
    private Vector2 angle_rotation_tower;
    private float hp;

    private String nikName;

    private float timerShoot; // время с последней аттаки

    //////////////////////Target

    private Vector2 target_position;
    private Vector2 target_body_rotation_angle;
    private Vector2 target_angle_rotation_tower;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getBody_rotation_angle() {
        return body_rotation_angle.setLength(SPEED_RUN_1);
    }

    public void setBody_rotation_angle(Vector2 body_rotation_angle) {
        this.body_rotation_angle = body_rotation_angle;
    }

    public Vector2 getAngle_rotation_tower() {
        return angle_rotation_tower;
    }

    public void setAngle_rotation_tower(Vector2 angle_rotation_tower) {
        this.angle_rotation_tower = angle_rotation_tower;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public String getNikName() {
        return nikName;
    }

    public void setNikName(String nikName) {
        this.nikName = nikName;
    }

    public float getTimerShoot() {
        return timerShoot;
    }

    public void setTimerShoot(float timerShoot) {
        this.timerShoot = timerShoot;
    }

    public Vector2 getTarget_position() {
        return target_position;
    }

    public void setTarget_position(Vector2 target_position) {
        this.target_position = target_position;
    }

    public Vector2 getTarget_body_rotation_angle() {
        return target_body_rotation_angle;
    }

    public void setTarget_body_rotation_angle(Vector2 target_body_rotation_angle) {
        this.target_body_rotation_angle = target_body_rotation_angle;
    }

    public Vector2 getTarget_angle_rotation_tower() {
        return target_angle_rotation_tower;
    }

    public void setTarget_angle_rotation_tower(Vector2 target_angle_rotation_tower) {
        this.target_angle_rotation_tower = target_angle_rotation_tower;
    }
}