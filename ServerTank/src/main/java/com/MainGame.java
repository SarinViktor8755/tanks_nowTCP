package main.java.com;

import com.badlogic.gdx.math.MathUtils;

import main.java.com.Units.Bullet.IndexBullets;
import main.java.com.Units.SpaceMap.IndexMap;

public class MainGame {
    GameServer gameServer;
    IndexBullets bullets;
    IndexMap mapSpace;
    // IndexBot bot;

    private static float realTimeMath; // время матча
    private final static float MATH_LENGHT = 1000 * 60 * 2; // время матча

    public final long timer_tread_50 = 50; //ms поток таймер циклов , рассылвает координаты ботов ))
    public final long timer_tread_25 = 14; // таймер поведения ботов - 25

    public MainGame(GameServer gameServer, int number_of_bots) {

        // this.bot = new IndexBot(this,number_of_bots);
        realTimeMath = 0;
        this.gameServer = gameServer;
        this.bullets = new IndexBullets(this.gameServer);
        this.mapSpace = new IndexMap();

        startSecondaryThread_25();
        startSecondaryThread_50();

    }

    private void startSecondaryThread_50() { // выполняется каждые 50 мс
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        //updateGame();
                       // ---- System.out.println("serverLife :: " + gameServer.isServerLivePlayer());

                        if (!gameServer.isServerLivePlayer()) {
                            Thread.sleep(1000);
                            continue;
                        }
                     //   System.out.println("Thread 50 !!!");
                        Thread.sleep(timer_tread_50);
//
//                        поток 50 можно остоновить при отсутвии игрков
//                                нужно будет обнулить игру результаты

                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }


    private void startSecondaryThread_25() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        //   updateGame();
//                        System.out.print("! ");
//                        System.out.println();
                        //  System.out.println("--Thread " + timer_tread_25);
//                        if (!gameServer.isServerLivePlayer()) {
//                            Thread.sleep(1000);
//                            continue;
//                        }
                        long deltaTime = GameServer.getDeltaTime();
                        realTimeMath += deltaTime; // время матча

//                        System.out.println(deltaTime + "  " + realTimeMath + "  " + MATH_LENGHT);
//                        System.out.println(realTimeMath < MATH_LENGHT);

                        if (!gameServer.isServerLivePlayer()) {if(MathUtils.randomBoolean(.005f)) realTimeMath = 0;}

                        Thread.sleep(timer_tread_25);
                        float time = (float) (deltaTime * .001);


//     не останавливать поток все функции должны быть конечными )))


                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }
}
