package com.mygdx.tanks2d.ClientNetWork;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.MainGame;

public class RouterSM {

    MainGame mainGame;

    static Vector2 velocity;
    static Vector2 position;


    public RouterSM(MainGame mainGame) {
        velocity = new Vector2();
        position = new Vector2();
        this.velocity.set(45,1);
        this.position.set(1,1);

        this.mainGame = mainGame;
    }

    public void routeSM(Network.StockMessOut sm){

            if (Heading_type.MY_SHOT == sm.tip) {
                position.set(sm.p1, sm.p2);
                velocity.set(0, 400);
                velocity.setAngleDeg(sm.p3); /// навправление
                System.out.println(sm);

                mainGame.getGamePlayScreen().playAnimation(position,velocity,22);

                //mainGame.getGamePlayScreen().getBullets().addBullet(position,velocity.cpy(), (int) sm.p4);

                //                ////////////
                //  if (m.getP().p5 != gsp.getMainGame().getMainClient().myIdConnect) {
//                mainGame.getGsp().getAudioEngine().pleySoundKickStick();
//                gsp.pc.addPasricalExplosion(.3f, m.getP().p1, m.getP().p2);
//                gsp.pc.addParticalsSmokeOne(m.getP().p1, m.getP().p2);
            //    mainGame.getGamePlayScreen().pc.addPasricalDeath_little(sm.p1, sm.p2, 2.7f);
              //  mainGame.getGsp().getGameSpace().getLighting().getBuletFlash().newFlesh(sm.p1, sm.p2);



//                temp.set(m.getP().p1, m.getP().p2);
//                tRotation.set(0, 400);
//                tRotation.setAngleDeg(m.getP().p3); /// навправление
//                // System.out.println(tempr.len());
//
//                gsp.getBullets().addBullet(temp, tRotation.cpy(), m.getP().p4);
//
//                ////////////
//                //  if (m.getP().p5 != gsp.getMainGame().getMainClient().myIdConnect) {
//                gsp.getAudioEngine().pleySoundKickStick();
////                gsp.pc.addPasricalExplosion(.3f, m.getP().p1, m.getP().p2);
////                gsp.pc.addParticalsSmokeOne(m.getP().p1, m.getP().p2);
//                gsp.pc.addPasricalDeath_little(m.getP().p1, m.getP().p2, 2.7f);
//                gsp.getGameSpace().getLighting().getBuletFlash().newFlesh(m.getP().p1, m.getP().p2);




                return;
            }

        if (Heading_type.SHELL_RUPTURE == sm.tip) {
            System.out.println("BOOOOOOOOM!!!!!!!!!!!  " + sm.p1 + "  " + sm.p2);
            mainGame.getGamePlayScreen().getBullets().removeBullet((int)sm.p3);


            return;
        }

            //Heading_type.SHELL_RUPTURE


    }
}
