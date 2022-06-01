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

    public void send_bot_coordinates(){
        System.out.println("send_cor");
//        Iterator<Map.Entry<Integer, Player>> entries = this.botList.entrySet().iterator();
//        while (entries.hasNext()) {
//            Network.PleyerPosition pp = new Network.PleyerPosition();
//         //   pp.yp = entries.g.
//            System.out.println();
//
//            gs.getLp().sendToAllPlayerPosition(-1, (Network.PleyerPosition) pp);
//
////            Map.Entry<Integer, Player> entry = entries.next();
////            temp2.set(entry.getValue().xp, entry.getValue().yp);
////            res = ((temp1.dst2(temp2) < 500) && author_id != entry.getValue().getId());
////            if (res) return true;
//        }
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
