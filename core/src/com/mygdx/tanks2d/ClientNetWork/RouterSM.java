package com.mygdx.tanks2d.ClientNetWork;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.tanks2d.MainGame;
import com.mygdx.tanks2d.Units.Tanks.OpponentsTanks;

public class RouterSM {

    MainGame mainGame;

    static Vector2 velocity;
    static Vector2 position;


    static Vector2 positionTemp;


    public RouterSM(MainGame mainGame) {
        velocity = new Vector2();
        position = new Vector2();
        positionTemp = new Vector2();
        this.velocity.set(45, 1);
        this.position.set(1, 1);

        this.mainGame = mainGame;
    }

    public void routeSM(Network.StockMessOut sm) {
        if (Heading_type.MY_SHOT == sm.tip) {
            position.set(sm.p1, sm.p2);
            velocity.set(0, 400);
            velocity.setAngleDeg(sm.p3); /// навправление
            try {
                mainGame.getGamePlayScreen().playAnimation(position, velocity, (int) sm.p4);
            }catch (NullPointerException e){e.printStackTrace();}
            return;
        }

        if (Heading_type.SHELL_RUPTURE == sm.tip) { // РАЗРЫВ СНАРЯДА
            // System.out.println("BOOOOOOOOM!!!!!!!!!!!  " + sm.p1 + "  " + sm.p2 + "  " +  ((int)sm.p3));
            Vector2 pp = new Vector2(sm.p1, sm.p2);
//            positionTemp.set(sm.p1 ,sm.p2 );
            try {


            mainGame.getGamePlayScreen().playExplosion(pp, velocity);
            mainGame.getGamePlayScreen().getBullets().removeBullet((int) sm.p3);
            }catch (NullPointerException e){e.printStackTrace();}
            return;
        }

        if (Heading_type.PARAMETERS_PLAYER == sm.tip) {
            try {
//                stockMessOut.tip = Heading_type.PARAMETERS_PLAYER; c SERVER
//                stockMessOut.p1 = aboutPlayer; // ХП
//                stockMessOut.p2 = Heading_type.RED_COMMAND;// КОМАНДА
//                stockMessOut.p3 = HP; // номер игрока
//                stockMessOut.p4 = HP; // номер игрока
//                stockMessOut.textM = nikName; // ник нейм
                int id = mainGame.getMainClient().getClient().getID();
                if (id == (int) sm.p1) {
                    saveParametrsMtTank(sm);

                } else {
                    OpponentsTanks opponentsTanks = mainGame.getGamePlayScreen().getTanksOther().getTankForID((int) sm.p1);
                    System.out.println(opponentsTanks);
                    opponentsTanks.hp = (int) sm.p3;
                    opponentsTanks.command = (int) sm.p2;
                    opponentsTanks.setNikPlayer(sm.textM);
                    if (opponentsTanks.hp < 0)
                        mainGame.getGamePlayScreen().getPc().addAnimationDeath(mainGame.getGamePlayScreen().getTanksOther().getTankForID((int) sm.p1).getPosition().x, mainGame.getGamePlayScreen().getTanksOther().getTankForID((int) sm.p1).getPosition().y);
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return;
        }


        if (Heading_type.DISCONECT_PLAYER == sm.tip) {
            OpponentsTanks opponentsTanks = mainGame.getGamePlayScreen().getTanksOther().getTankForID((int) sm.p3);
//            opponentsTanks.hp = (int) sm.p1;

           // mainGame.getGamePlayScreen().playAnimation(position, velocity, (int) sm.p4);
            return;
        }

        //Heading_type.SHELL_RUPTURE


    }

    private void saveParametrsMtTank(Network.StockMessOut sm) {
        mainGame.getGamePlayScreen().getTank().setHp((int) sm.p3);
        mainGame.getGamePlayScreen().getTank().setCommand(Heading_type.RED_COMMAND);
        if (mainGame.getGamePlayScreen().getTank().getHp() < 0)
            mainGame.getGamePlayScreen().getPc().addAnimationDeath(mainGame.getGamePlayScreen().getTank().getPosition().x, mainGame.getGamePlayScreen().getTank().getPosition().y);
    }
}
