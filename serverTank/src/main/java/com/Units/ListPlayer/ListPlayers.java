package main.java.com.Units.ListPlayer;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.ClientNetWork.Heading_type;
import com.mygdx.tanks2d.ClientNetWork.Network;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

import main.java.com.Bots.DBBot;
import main.java.com.GameServer;

public class ListPlayers {
    static public final int DEFULT_COUNT_BOT = 20;

    ConcurrentHashMap<Integer, Player> players;
    ConcurrentHashMap<String, Integer> playersTokken; // tooken/ id

    GameServer gameServer;
    Network.PleyerPositionNom pn = new Network.PleyerPositionNom();

    Vector2 temp1 = new Vector2();
    Vector2 temp2 = new Vector2();

    public ListPlayers(GameServer gameServer) {
        this.players = new ConcurrentHashMap<>();
        this.playersTokken = new ConcurrentHashMap<>();
        this.gameServer = gameServer;
        System.out.println("install_ListPlayers : " + GameServer.getDate());
    }

    public Player getPlayerForId(int id) { // почему то вызывается  иногда
        Player result = players.get(id);
        if (result == null) players.put(id, new Player(id));
        return players.get(id);
    }


    private boolean checkTokken(String tokken, int connct_id) { // проверяет был литакой токкен
        playersTokken.put(tokken, connct_id);
        Integer p = playersTokken.get(tokken);
        if (p == null) {
            playersTokken.put(tokken, connct_id);
            return false;
        } else {
            return true;
        }
    }

    public void addPlayer(int con) {
        this.players.put(con, new Player(con));
    }

    public void addPlayer(Player p) { // конструктоор для ботов
        this.players.put(p.getId(), p);
    }

    public void clearList() {
        this.players.clear();
    }


    public int getSize() {
        return this.players.size();
    }


    public void updatePosition(int id, Network.PleyerPosition pp) { // записать парметры Игрока
        Player p = players.get(id);
        if (p == null) players.put(id, new Player(id));
        p.setXp(pp.xp);
        p.setYp(pp.yp);
        p.setRotTower(pp.roy_tower);
//        System.out.println(pp.xp + "  "+ pp.yp);
//        if(MathUtils.randomBoolean()) System.out.println("___");  System.out.println("_");
    }

    public void sendToAllPlayerPosition(int id, Network.PleyerPosition pp) {
        pn.nom = id;
        pn.xp = pp.xp;
        pn.yp = pp.yp;
        pn.roy_tower = pp.roy_tower;
        gameServer.getServer().sendToAllExceptTCP(id, pn);
    }

    public void accept_bot(DBBot dbBot){
        Player p_bot = getPlayerForId(dbBot.getId());
        p_bot.xp = dbBot.getPosition().x;
        p_bot.yp = dbBot.getPosition().y;
        p_bot.rotTower = dbBot.getAngle_rotation_tower().angleDeg();
    }

    public void sendParametersPlayers(int aboutPlayerID) { // рассылка о характеристиках игрока по id
        Network.StockMessOut sm = new Network.StockMessOut();
        Player p = this.players.get(aboutPlayerID);
        try {
            sm.textM = p.nikName;
            sm.p1 = aboutPlayerID;
            sm.p2 = p.command;
            sm.p3 = p.hp;
            //sm.p4 = еще какой т опараметр;
            sm.tip = Heading_type.PARAMETERS_PLAYER;
            gameServer.getServer().sendToAllExceptTCP(aboutPlayerID, sm);
        } catch (NullPointerException e) {
        }
    }


    public int projectile_collide_with_players(int author_id, float xs, float ys){
        int res = -1;
        temp1.set(xs, ys);
        Iterator<Map.Entry<Integer, Player>> entries = players.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Player> entry = entries.next();
            temp2.set(entry.getValue().xp, entry.getValue().yp);
            if(((temp1.dst2(temp2) < 500) && author_id != entry.getValue().getId()))
                res = entry.getKey();

            if (res!=-1) return res;
        }
        return res;
    }


}
