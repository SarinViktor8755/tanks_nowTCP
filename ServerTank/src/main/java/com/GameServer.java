package main.java.com;

import static com.mygdx.tanks2d.ClientNetWork.Network.register;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.tanks2d.ClientNetWork.Heading_type;
import com.mygdx.tanks2d.ClientNetWork.Network;


import java.io.IOException;

public class GameServer {

    Server server;

    public final long timerTact = 50; //ms поток таймер циклов , рассылвает координаты ботов ))
    public final long timerLogic = (long) (this.timerTact / 3f); // таймер поведения ботов

    Network.PleyerPositionNom ppn = new Network.PleyerPositionNom();
    long previousStepTime; // шаг для дельты
    ListPlayers lp = new ListPlayers(this);

    public GameServer(String[] args, ServerLauncher serverLauncher) throws IOException {
        server = new Server();
        register(server);
        server.bind(Network.tcpPort, Network.udpPort);
        server.start();

        previousStepTime = System.currentTimeMillis();
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
                                       lp.updatePosition(connection.getID(),(Network.PleyerPosition) object);
                                       if(MathUtils.randomBoolean())lp.sendToAllPlayerPosition(connection.getID(),(Network.PleyerPosition) object);
                                       return;
                                   }

                                   if (object instanceof Network.StockMessOut) {// полученеи сообщения
                                       Network.StockMessOut sm = (Network.StockMessOut)object;
                                       System.out.println(sm);
                                       routerMassege(sm,connection.getID());
                                   }


                               }
                           }
        );
    }

    public Server getServer() {
        return server;
    }

    private void routerMassege(Network.StockMessOut sm, int id_coonect){
        if(Heading_type.MY_SHOT == sm.tip){

//            System.out.println(sm.textM);
//            lp.getPlayerForId(id_coonect).setTokken(sm.textM);
            return;
        }

        if(Heading_type.BUTTON_STARTGAME == sm.tip){
            lp.getPlayerForId(id_coonect).setNikName(sm.textM);
            return;
        }
        if(Heading_type.MY_TOKKEN == sm.tip){
            System.out.println(sm.textM);
            lp.getPlayerForId(id_coonect).setTokken(sm.textM);
            return;
        }


    }
}
