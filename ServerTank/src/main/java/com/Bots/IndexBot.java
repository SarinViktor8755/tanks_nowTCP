package main.java.com.Bots;

import main.java.com.GameServer;
import main.java.com.Units.SpaceMap.IndexMap;

public class IndexBot extends Thread {
    private GameServer gameServer;
    //private HashMap<Integer, SimulationUnit> units;
    private IndexMap indexMap;
    private static int MIN_PLEYSES;
    private boolean interrupted; // если ТРУЕ - выключает поток

    public IndexBot(GameServer gameServer, int minPlayer) {
        //indexMap = new IndexMap();
        this.gameServer = gameServer;
        //  units = new HashMap<>();

        interrupted = false;
        System.out.println(" install_bots : " + GameServer.getDate());
        MIN_PLEYSES = minPlayer;


        ////////////////////////////////////////////
    }


}
