package main.java.com.Units.ListPlayer;

import com.mygdx.tanks2d.ClientNetWork.Network;

import java.util.concurrent.ConcurrentHashMap;

import main.java.com.GameServer;

public class ListPlayers {
    ConcurrentHashMap<Integer, Player> players;
    ConcurrentHashMap<String, Integer> playersTokken; // tooken/ id
    GameServer gameServer;
    Network.PleyerPositionNom pn = new Network.PleyerPositionNom();


    public ListPlayers(GameServer gameServer) {
        this.players = new ConcurrentHashMap<>();
        this.playersTokken = new ConcurrentHashMap<>();
        this.gameServer = gameServer;
    }

    public Player getPlayerForId(int id) { // почему то вызывается  иногда
        Player result = players.get(id);
        if (result == null) players.put(id, new Player(id));
        return players.get(id);
    }


    public boolean checkTokken(String tokken, int connct_id) { // проверяет был литакой токкен
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

    @Override
    public String toString() {
        return "ListPlayers{" +
                "players=" + players +
                '}';
    }
}
