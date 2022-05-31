package main.java.com;

import static com.mygdx.tanks2d.ClientNetWork.Network.register;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.tanks2d.ClientNetWork.Heading_type;
import com.mygdx.tanks2d.ClientNetWork.Network;


import java.io.IOException;

import main.java.com.Units.ListPlayer.ListPlayers;

public class GameServer {

    Server server;
    MainGame mainGame;


    Network.PleyerPositionNom ppn = new Network.PleyerPositionNom();
    static long previousStepTime; // шаг для дельты
    ListPlayers lp = new ListPlayers(this);

    public GameServer(String[] args, ServerLauncher serverLauncher) throws IOException {
        server = new Server();
        register(server);
        server.bind(Network.tcpPort, Network.udpPort);
        server.start();
        previousStepTime = System.currentTimeMillis();

        mainGame = new MainGame(this, getSizeBot(args));
        server.addListener(new Listener() {
                               @Override
                               public void connected(Connection connection) {
                                   lp.addPlayer(connection.getID());
                               }

                               @Override
                               public void disconnected(Connection connection) {
                                   super.disconnected(connection);
                               }

                               @Override
                               public void received(Connection connection, Object object) {
                                   if (object instanceof Network.PleyerPosition) {
                                       lp.updatePosition(connection.getID(), (Network.PleyerPosition) object);
                                       lp.sendToAllPlayerPosition(connection.getID(), (Network.PleyerPosition) object);
                                       return;
                                   }

                                   if (object instanceof Network.StockMessOut) {// полученеи сообщения
                                       Network.StockMessOut sm = (Network.StockMessOut) object;
                                       System.out.println(sm);
                                       RouterMassege.routeSM(sm,connection.getID(), getMainGame().gameServer);
                                   }


                               }
                           }
        );
    }

    public Server getServer() {
        return server;
    }

//    private void routerMassege(Network.StockMessOut sm, int id_coonect) {
//
//
////        if (Heading_type.MY_SHOT == sm.tip) {
////
//////            System.out.println(sm.textM);
//////            lp.getPlayerForId(id_coonect).setTokken(sm.textM);
////            return;
////        }
////
////        if (Heading_type.BUTTON_STARTGAME == sm.tip) {
////            lp.getPlayerForId(id_coonect).setNikName(sm.textM);
////            return;
////        }
////        if (Heading_type.MY_TOKKEN == sm.tip) {
////            System.out.println(sm.textM);
////            lp.getPlayerForId(id_coonect).setTokken(sm.textM);
////            return;
////        }
////
//
//
//
//    }

    public void sendSHELL_RUPTURE(float x, float y, int nom){
        Network.StockMessOut stockMessOut = new Network.StockMessOut();
        stockMessOut.tip = Heading_type.SHELL_RUPTURE;
        stockMessOut.p1 = x;
        stockMessOut.p2 = y;
        stockMessOut.p3 = nom;
        this.server.sendToAllTCP(stockMessOut);
        System.out.println("BOOOOOOOOOOOOOOOOOOOOOOOM");
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    private int getSizeBot(String args[]) {
        try {
            return Integer.valueOf(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ListPlayers.DEFULT_COUNT_BOT;
        }
    }

    public static long getDeltaTime() {
        long time = System.currentTimeMillis();
        long raz = (time - previousStepTime);
        previousStepTime = time;
        return raz;
    }

    public boolean isServerLivePlayer() {
        //System.out.println(server.getConnections().length > 0);
        if (server.getConnections().length > 0) return true;
        else return false;
    }


}

