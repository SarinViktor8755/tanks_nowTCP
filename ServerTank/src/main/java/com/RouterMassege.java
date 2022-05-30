package main.java.com;

import com.mygdx.tanks2d.ClientNetWork.Heading_type;
import com.mygdx.tanks2d.ClientNetWork.Network;

public class RouterMassege {



    public static void routeSM(Network.StockMessOut sm,int id_coonect, GameServer gameServer){
        if (Heading_type.MY_SHOT == sm.tip) {
//            System.out.println(sm.textM);
//            lp.getPlayerForId(id_coonect).setTokken(sm.textM);
            return;
        }

        if (Heading_type.BUTTON_STARTGAME == sm.tip) {
            gameServer.lp.getPlayerForId(id_coonect).setNikName(sm.textM);
            // ответ массив имен играков )))

            return;
        }
        if (Heading_type.MY_TOKKEN == sm.tip) {
           // System.out.println(sm.textM);
            gameServer.lp.getPlayerForId(id_coonect).setTokken(sm.textM);
            return;
        }


    }
}
