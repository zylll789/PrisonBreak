package bin.screen;

import bin.entity.Player;

import java.util.ResourceBundle;

public class Display {

    public static ResourceBundle lang;

    public static void printUI(Player player) {
        ResourceBundle lang = player.langOperator.getLang();
        System.out.print("\n" + lang.getString("floor") + player.floor + "     ");
        System.out.print(player.langOperator.attrNames[0] + ": " + player.maxHealth * 2 + "/" + player.attrNum[0] + "  ");
        System.out.print(player.langOperator.attrNames[1] + ": " + player.maxEnergy * 5 / 2 + "/" + player.attrNum[1] + "  ");
        System.out.print(player.langOperator.attrNames[2] + ": " + player.attrNum[2] + "  ");
        System.out.print(player.langOperator.attrNames[3] + ": " + player.attrNum[3] + "  ");
        System.out.print(player.langOperator.attrNames[4] + ": " + player.attrNum[4] + "  ");
        System.out.print(lang.getString("canInvisible") + ":" + player.canInvisible);
        System.out.println();
        for (int i = 0; i <= 2; ++i) {
            for (int j = 0; j <= 4; ++j) {
                System.out.print("|  " + player.langOperator.eventName[player.map[i][j]] + "  ");
            }
            System.out.print("|\n");
        }
        System.out.println();
    }

    public static void printConnectUI(Player player,Player otherPlayer) {
        ResourceBundle lang = player.langOperator.getLang();
        System.out.print("\n" + lang.getString("floor") + player.floor + "     ");
        System.out.print(player.langOperator.attrNames[0] + ": " + player.maxHealth * 2 + "/" + player.attrNum[0] + "  ");
        System.out.print(player.langOperator.attrNames[1] + ": " + player.maxEnergy * 5 / 2 + "/" + player.attrNum[1] + "  ");
        System.out.print(player.langOperator.attrNames[2] + ": " + player.attrNum[2] + "  ");
        System.out.print(player.langOperator.attrNames[3] + ": " + player.attrNum[3] + "  ");
        System.out.print(player.langOperator.attrNames[4] + ": " + player.attrNum[4] + "  ");
        System.out.print(lang.getString("canInvisible") + ":" + player.canInvisible);
        System.out.println();
        System.out.print("other player floor : "+otherPlayer.floor);
        System.out.println();
        for (int i = 0; i <= 2; ++i) {
            for (int j = 0; j <= 4; ++j) {
                System.out.print("|  " + player.langOperator.eventName[player.map[i][j]] + "  ");
            }
            System.out.print("|\n");
        }
        System.out.println();
    }

}
