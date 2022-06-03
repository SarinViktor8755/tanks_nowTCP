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
import main.java.com.Units.ListPlayer.Player;

public class IndexBot extends Thread {
    HashMap<Integer, Player> botList;
    HashMap<Integer, DBBot> dbBots;


    private static BehaviourBot botBehavior;

    private static int NOM_ID_BOT = -100;
    final float SPEED_ROTATION = 180f;
    private final Vector2 speed_constanta = new Vector2(90, 0);

    private Vector2 temp_position_vector;
    private int countBot;

    public static GameServer gs;


    private int sizeBot = 10;


    public IndexBot(GameServer gameServer, int number_bots) {
        this.countBot = number_bots;
        this.temp_position_vector = new Vector2();
        this.botList = new HashMap<Integer, Player>();
        this.gs = gameServer;
        this.dbBots = new HashMap<>();
        this.sizeBot = number_bots;
        // this.botBehavior = new BotBehavior(botList); // поведение бота - тут вся логика  )))
        // this.allPlayers = new HashMap<Integer, TowerRotation>();

        System.out.println("install_Bot : " + GameServer.getDate() + "  " + countBot);
    }


    private void addBot() {
        Player p = new Player(NOM_ID_BOT);
        p.setNikName(getNikNameGen());

        if (MathUtils.randomBoolean()) p.setCommand(Heading_type.RED_COMMAND);
        else p.setCommand(Heading_type.BLUE_COMMAND);


        p.setXp(MathUtils.random(200, 1100));
        p.setYp(MathUtils.random(200, 1100));
        p.setHp(MathUtils.random(80));


        this.botList.put(NOM_ID_BOT, p);
        NOM_ID_BOT--;
        System.out.println("add_bot+ : " + NOM_ID_BOT);


        this.botList.put(p.getId(),p); // добавляем в базу ботов
        gs.getLp().addPlayer(p); // добавляем в базу играков

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
//        allPlayers.put(nom,new TowerRotation(
//                new Vector2(0,1),
//                new Vector2(1,1).setAngleDeg(p.getR()),
//                new Vector2(p.getXp(),p.getYp())
//
//        ));
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////


    }

    public void updateDeltaTimeBots(float deltaTime){
        Iterator<Map.Entry<Integer, Player>> entries = this.botList.entrySet().iterator();
        while (entries.hasNext()) {
            Player p = entries.next().getValue();
          //  p.u
        }
    }

    public void send_bot_coordinates(){
      //  System.out.println("send_cor");
        Iterator<Map.Entry<Integer, Player>> entries = this.botList.entrySet().iterator();
        while (entries.hasNext()) {
            if(MathUtils.randomBoolean()) continue;
            Player p = entries.next().getValue();


//
//            float x = p1.getXp() + MathUtils.random(-1.5f,1.5f);
//            float y = p1.getYp()+ MathUtils.random(-1.5f,1.5f);
//            p1.setXp(x);
//            p1.setYp(y);

            Network.PleyerPosition pp = new Network.PleyerPosition();
            pp.xp = p.getXp();
            pp.yp = p.getYp();
            pp.roy_tower = pp.roy_tower;

            gs.getLp().sendToAllPlayerPosition(p.getId(), pp);
           // System.out.println(p);


//            if(MathUtils.randomBoolean(.006f)){
//                botShoot(p.getId());
//                System.out.println("shoot" + p.getId());
//            }

        }


    }

    public void botShoot(int idBot){ /// доделаааать
        Player bot = gs.getLp().getPlayerForId(idBot);

     //   Heading_type.MY_SHOT, x, y, alignShoot,  (5000 + MathUtils.random(999999) + x - y), null
//        pack.tip = tip;
//        pack.p1 = p1;
//        pack.p2 = p2;
//        pack.p3 = p3;
//        pack.p4 = p4;
//        pack.textM = text;
//        client.sendTCP(pack);
        Vector2 velBullet = new Vector2(700, 0).setAngleDeg(bot.getRotTower());
        Network.StockMessOut sm = new Network.StockMessOut();
        sm.p1 = 50;
        sm.p2 = 50;
        sm.p3 = 45;
        sm.p4 = 5000 + MathUtils.random(99999999);
        sm.tip = Heading_type.MY_SHOT;

        gs.getMainGame().getBullets().addBullet(new Vector2(bot.getXp(),bot.getYp()),velBullet,44,bot.getId());
        gs.getServer().sendToAllTCP(sm);
    }

    public void updateCountBot(int lPlayers, int target_plaers) {
        if ((lPlayers + botList.size() + 1) < target_plaers) addBot();
        if ((lPlayers + botList.size() + 1) > target_plaers) delBot();
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
