package bin.event;

import bin.entity.Player;

import java.util.ResourceBundle;
import java.util.Scanner;

import static run.PrisonBreakDebug.*;

public class Limit {

    public static void packageLimit(Player player) {
        ResourceBundle lang = player.langOperator.getLang();
        int currPackage = player.attrNum[2] * 2 + player.attrNum[3] / 5 + player.attrNum[4] * 3 / 2;
        while (currPackage > player.packageLimit) {
            System.out.println("\n" + lang.getString("package_limit_1"));
            Scanner scanner = new Scanner(System.in);
            shouldMurMur = true;
            Murmur.createMurmur(player);
            System.out.println(lang.getString("package_limit_2"));
            String typeIn = scanner.next();
            System.out.println(lang.getString("package_limit_3"));
            int typeInInt = scanner.nextInt();
            shouldMurMur = false;
            byte type = -1;
            switch (typeIn.hashCode()) {
                case 102:
                    if (typeIn.equals("f")) {
                        type = 2;
                    }
                    break;
                case 109:
                    if (typeIn.equals("m")) {
                        type = 1;
                    }
                    break;
                case 119:
                    if (typeIn.equals("w")) {
                        type = 0;
                    }
            }
            switch (type) {
                case 0 -> player.attrNum[2] -= typeInInt;
                case 1 -> player.attrNum[3] -= typeInInt;
                case 2 -> player.attrNum[4] -= typeInInt;
                default -> System.out.println(illegalMove);
            }
            currPackage = player.attrNum[2] * 2 + player.attrNum[3] / 5 + player.attrNum[4] * 3 / 2;
            if (currPackage > player.packageLimit) {
                System.out.println("\n" + lang.getString("package_limit_4"));
            } else {
                System.out.println("\n" + lang.getString("package_limit_5"));
            }
        }
    }

}
