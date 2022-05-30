package com.mygdx.tanks2d.ClientNetWork;

import com.badlogic.gdx.math.Vector2;

public class RouterSM {
    MainClient mainClient;

    static Vector2 velocity;
    static Vector2 position;


    public RouterSM(MainClient mainClient) {
        this.velocity = new Vector2(0,0);
        this.position = new Vector2(0,1);
        this.mainClient = new MainClient();
    }

    public void routeSM(Network.StockMessOut sm){

            if (Heading_type.MY_SHOT == sm.tip) {
                position.set(sm.p1, sm.p2);






                return;
            }


    }
}
