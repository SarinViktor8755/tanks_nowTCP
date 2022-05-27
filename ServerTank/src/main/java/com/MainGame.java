package main.java.com;

import main.java.com.Units.Bullet.IndexBullets;
import main.java.com.Units.SpaceMap.IndexMap;

public class MainGame {
    GameServer gameServer;
    IndexBullets bullets;
    IndexMap mapSpace;
   // IndexBot bot;

    public MainGame(GameServer gameServer, int number_of_bots) {

       // this.bot = new IndexBot(this,number_of_bots);
        this.gameServer = gameServer;
        this.bullets = new IndexBullets(this.gameServer);
        this.mapSpace = new IndexMap();

    }

}
