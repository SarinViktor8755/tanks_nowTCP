package com.mygdx.tanks2d.ClientNetWork;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.tanks2d.ClientNetWork.VoiceChat.VoiceChatClient;
import com.mygdx.tanks2d.MainGame;
import com.mygdx.tanks2d.Units.Tanks.OpponentsTanks;
import com.mygdx.tanks2d.Utils.VectorUtils;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.TreeMap;


public class MainClient {
    public float aplphaConnect;
    private Client client;   //клиент
    private boolean onLine;
    private MainGame mg;
    private int myIdConnect;
    private RouterSM routerSM;


    private NetworkPacketStock networkPacketStock;
    public TreeMap<Integer, Network.PleyerPositionNom> otherPlayer;
    public HashMap<Integer, Boolean> frameUpdates; //Обновления кадра для играков
//    public ArrayDeque<PacketModel> inDequePacket; // входящие пакеты для обработки;

    public MainClient(MainGame mg) {
        this.mg = mg;
        routerSM = new RouterSM(mg);
//        // pre java 8 lambdas
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                // your code here ...
//                System.out.println("111111");
//            }
//        });
//
//        t.start();

        client = new Client();
        client.start();



        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(client);

        frameUpdates = new HashMap<Integer, Boolean>();
        try {
            client.connect(5000, Network.host, Network.tcpPort, Network.udpPort);
            // Server communication after connection can go here, or in Listener#connected().
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        otherPlayer = new TreeMap<>();
        onLine = true;


        this.networkPacketStock = new NetworkPacketStock(client);
        // this.startClient();

        client.addListener(new Listener() {
            public void connected(Connection connection) {
                setMyIdConnect(connection.getID());
            }

            public void received(Connection connection, Object object) {
                router(object);
            }

            public void disconnected(Connection connection) {
            }
        });
     //   System.out.println(client.isConnected() + "!!!!!!!!!!!!!!!1");
    }

    private void startClient() {
        System.out.println(client.isConnected());
        this.client = new Client();
        Network.register(client);
        this.client.start();
    }


    public int getMyIdConnect() {
        return myIdConnect;
    }

    public void setMyIdConnect(int myIdConnect) {
        this.myIdConnect = myIdConnect;
    }

    public Client getClient() {
        return client;
    }

    public void router(Object object) {
        if (!onLine) return;
        if (object instanceof Network.PleyerPositionNom) { // полученеи позиции играков

            Network.PleyerPositionNom pp = (Network.PleyerPositionNom) object;
            frameUpdates.put(pp.nom, true);
            if (pp.nom == client.getID()) return;

            try {
                OpponentsTanks t = mg.getGamePlayScreen().getTanksOther().getTankForID(pp.nom);


                mg.getGamePlayScreen().getTanksOther().setTankPosition(pp, mg.getMainClient().frameUpdates.get(pp.nom));
                //mg.getMainClient().frameUpdates.put(pp.nom, false); /// закрывает флаг о рендере __
            } catch (NullPointerException e) {
                 e.printStackTrace();
            }

            return;
        }

        if (object instanceof Network.StockMessOut) {
            System.out.println((Network.StockMessOut) object);
            routerSM.routeSM((Network.StockMessOut) object);
//            Network.StockMessOut sm = (Network.StockMessOut) object;
//            System.out.println(sm);
//            if (Heading_type.MY_SHOT == sm.tip) {
//
//
//
//
//                return;
//            }

        }


    }


    public boolean isOnLine() {
        return true;
    }

    public void upDateClient() {

    }

    public NetworkPacketStock getNetworkPacketStock() {
        return this.networkPacketStock;
    }

    public void checkConnect() {
        System.out.println(NetworkPacketStock.required_to_send_tooken);
        getNetworkPacketStock().toSendMyNikAndTokken(); // отправка ника и токкена
        if (!getClient().isConnected()) NetworkPacketStock.required_to_send_tooken = true;

        if (!getClient().isConnected()) {

            if (MathUtils.randomBoolean(.05f)) {
                try {

                    System.out.println("reconect");
                    getClient().reconnect(5000);
                    NetworkPacketStock.required_to_send_tooken = true;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
