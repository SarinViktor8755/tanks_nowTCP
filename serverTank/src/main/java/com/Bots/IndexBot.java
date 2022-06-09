package main.java.com.Bots;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.ClientNetWork.Heading_type;
import com.mygdx.tanks2d.ClientNetWork.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.java.com.GameServer;
import main.java.com.Units.ListPlayer.ListPlayers;
import main.java.com.Units.ListPlayer.Player;

public class IndexBot extends Thread {
    public static GameServer gs;
    HashMap<Integer, DBBot> dbBots;


    private static BehaviourBot botBehavior;

    private static int NOM_ID_BOT = -100;

    private final Vector2 speed_constanta = new Vector2(90, 0);

    private Vector2 temp_position_vector;
    private int countBot;


    private int sizeBot = 10;


    public IndexBot(GameServer gameServer, int number_bots) {
        this.countBot = number_bots;
        this.temp_position_vector = new Vector2();

        this.gs = gameServer;
        this.dbBots = new HashMap<>();
        this.sizeBot = number_bots;
        // this.botBehavior = new BotBehavior(botList); // поведение бота - тут вся логика  )))
        // this.allPlayers = new HashMap<Integer, TowerRotation>();

        System.out.println("install_Bot : " + GameServer.getDate() + "  " + countBot);
    }


    private void addBot() {
        Player p = new Player(NOM_ID_BOT);

        if (MathUtils.randomBoolean()) p.setCommand(Heading_type.RED_COMMAND);
        else p.setCommand(Heading_type.BLUE_COMMAND);

        p.setPosition(MathUtils.random(200, 1100), MathUtils.random(200, 1100));
        p.setHp(100);
        p.setNikName(getNikNameGen());

        NOM_ID_BOT--;
        System.out.println("add_bot+ : " + NOM_ID_BOT);

        gs.getLp().addPlayer(p); // добавляем в базу играков

        DBBot bot = new DBBot(p.getId());
        dbBots.put(p.getId(), bot);

    }

//    public void upateMainPlayerList(ListPlayers listPlayers) { //обносить основй список играков )_)) закидуть ботов в листПлаер
//        Iterator<Map.Entry<Integer, DBBot>> bot = this.dbBots.entrySet().iterator();
//        while (bot.hasNext()) {
//            listPlayers.accept_bot((DBBot) bot);
//        }
//
//    }

    public void send_bot_coordinates() {
        gs.getLp().send_bot_coordinates();
    }

    public void updaeteBot(float deltaTime) {

        movetBot(deltaTime);
        send_bot_coordinates();
    }

    //////////////////////////////
    public void movetBot(float deltaTime) { // перемещения поля
        for (Map.Entry<Integer, DBBot> tank : dbBots.entrySet()) {


            Player p = gs.getLp().getPlayerForId(tank.getValue().getId());

            if (gs.getMainGame().getMapSpace().in_dimensions_terrain(p.getPosi().x, p.getPosi().y)) {
                p.getPosi().add(p.getBody_rotation().scl(deltaTime * .001f));
               // System.out.println("move");
            }
            ///////////
            collisinOtherTanksTrue(p.getPosi(),deltaTime); /// calisiion tanks
            ////////////

            p.getBody_rotation().setLength(90);
            rotation_body(deltaTime, tank.getValue(), p);

            gs.getMainGame().getMapSpace().resolving_conflict_with_objects(p.getPosi(),deltaTime);
            // if(MathUtils.randomBoolean(.005f))tank.getValue().getTarget_body_rotation_angle().setAngleDeg(MathUtils.random(-180,180));
        }
    }

    private void collisinOtherTanksTrue(Vector2 position,float dt) {
        Vector2 ct = gs.getLp().isCollisionsTanks(position);
        if (ct != null) {  // танки другие
            position.add(ct.scl(360 * dt * .001f));
        }
    }


    private static void rotation_body(float dt, DBBot db_bot, Player p) { // поворот тела
        if (MathUtils.isEqual(p.getBody_rotation().angleDeg(), db_bot.getTarget_body_rotation_angle().angleDeg(), .5f))
         //   System.out.println("isEqual");
        return;
//        if ((p.getBody_rotation().angleDeg(db_bot.getTarget_body_rotation_angle()) > 180)) {
//            p.getBody_rotation().setAngleDeg(p.getBody_rotation().angleDeg() - dt * BehaviourBot.SPEED_ROTATION);
//        } else
//            p.getBody_rotation().setAngleDeg(p.getBody_rotation().angleDeg() + dt * BehaviourBot.SPEED_ROTATION);
    }


    /////////////////////////////////////////////////


    public void updateCountBot(int lPlayers, int target_plaers) {
        if ((dbBots.size() + lPlayers) < target_plaers) addBot();
        if ((dbBots.size() + lPlayers) > target_plaers) delBot();
        //    System.out.println(lPlayers + "  " + dbBots.size());
    }

    private void delBot() {

    }


    private void delBot(int id) {

    }


    static String getNikNameGen() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Bubba");
        names.add("Honey");
        names.add("Bo");
        names.add("Sugar");
        names.add("Doll");
        names.add("Peach");
        names.add("Snookums");
        names.add("Queen");
        names.add("Ace");
        names.add("Punk");
        names.add("Sugar");
        names.add("Gump");
        names.add("Rapunzel");
        names.add("Teeny");
        names.add("MixFix");
        names.add("BladeMight");
        names.add("Rubogen");
        names.add("Lucky");
        names.add("Tailer");
        names.add("IceOne");
        names.add("Sugar");
        names.add("Gump");
        names.add("Rapunzel");
        names.add("Teeny");
        names.add("MixFix");
        names.add("BladeMight");
        names.add("Rubogen");
        names.add("Lucky");
        names.add("Tailer");
        names.add("IceOne");
        names.add("TrubochKa");
        names.add("HihsheCKA");
        names.add("R2-D2");
        names.add("Breha Organa");
        names.add("Yoda");
        names.add("Obi-Wan Kenob");
        names.add("C-3PO");
        names.add("Darth Sidious");
        names.add("Darth Vader");
        names.add("Boba Fett");
        names.add("Sarin");
        names.add("Sasha");
        return names.get(MathUtils.random(names.size() - 1)) + "@Bot";
    }


}
