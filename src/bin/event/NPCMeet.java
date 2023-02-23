package bin.event;

import bin.entity.Player;

import java.util.ResourceBundle;
import java.util.Scanner;

import static run.PrisonBreakDebug.*;

public class NPCMeet {
    
    public static void onMeet(Player player, int target) throws InterruptedException {
        ResourceBundle lang = player.langOperator.getLang();
        int[][] attrChange;
        String[] eventChoose;
        int callBack;
        if (2 == target && player.visible) {
            attrChange = new int[][]{{-player.attrNum[0] / 3, -3, -2, 0, 0}, {0, 0, 0, -3 - player.weightOperator[1], 0}};
            eventChoose = new String[]{lang.getString("fight"), lang.getString("bribe")};
            eventTrigger(target, attrChange, eventChoose,player);
        } else if (3 == target && player.visible) {
            attrChange = new int[][]{{3, 0, 0, -player.attrNum[3] / 4, 0}, {4, -2, -1, 0, 0}};
            eventChoose = new String[]{lang.getString("purchase"), lang.getString("threaten")};
            callBack = eventTrigger(target, attrChange, eventChoose,player);
            if (1 == callBack) {
                player.weightOperator[2]++;
            }
        } else if (4 == target && player.visible) {
            attrChange = new int[][]{{0, 4, -2, 1, 1}, {-1, -2, 0, 5, 1}};
            eventChoose = new String[]{lang.getString("pray"), lang.getString("rob")};
            callBack = eventTrigger(target, attrChange, eventChoose,player);
            if (1 == callBack) {
                player.weightOperator[3]++;
            }
        } else if (5 == target && player.visible) {
            attrChange = new int[][]{{-1, -2, 1, 2, 1}, {0, 0, player.attrNum[2], -player.attrNum[3] / 2, -player.attrNum[4] / 2}};
            eventChoose = new String[]{lang.getString("hit"), lang.getString("recruit")};
            eventTrigger(target, attrChange, eventChoose,player);
        } else if (6 == target && player.visible) {
            attrChange = new int[][]{{0, 0, 0, -5, 5}, {-1, -player.attrNum[1] / 3, 1, 1, 5}};
            eventChoose = new String[]{lang.getString("buy"), lang.getString("beat")};
            callBack = eventTrigger(target, attrChange, eventChoose,player);
            if (1 == callBack) {
                player.weightOperator[5]++;
            }
        } else if (7 == target && player.visible) {
            attrChange = new int[][]{{-player.attrNum[0] / 5, -2, -1, 0, 0}, {0, -player.attrNum[1] / 4, 0, -2, -player.attrNum[4] / 4}};
            eventChoose = new String[]{lang.getString("punch"), lang.getString("brush_off")};
            callBack = eventTrigger(target, attrChange, eventChoose,player);
            if (0 == callBack) {
                player.weightOperator[6]++;
            }
        } else if (8 == target && player.visible) {
            attrChange = new int[2][5];
            int temp;
            for (temp = 2; temp <= 4; ++temp) {
                attrChange[0][temp] = -player.attrNum[temp] / 3;
            }
            temp = ra.nextInt(3) + 2;
            int luck = ra.nextInt(100) + 1;
            if (luck <= 20 + player.weightOperator[7]) {
                attrChange[1][temp] = player.attrNum[temp];
            } else {
                attrChange[1][temp] = -player.attrNum[temp] + 1;
            }
            eventChoose = new String[]{lang.getString("fair_play"), lang.getString("cheat")};
            callBack = eventTrigger(target, attrChange, eventChoose,player);
            if (0 == callBack) {
                player.weightOperator[7] += ra.nextInt(3) + 4;
            } else {
                player.weightOperator[7] += ra.nextInt(2);
            }
        } else if (9 == target) {
            attrChange = new int[2][5];
            boolean[] slot = new boolean[5];
            int i;
            int currSlot;
            for (currSlot = 0; currSlot <= 4; ++currSlot) {
                do {
                    i = ra.nextInt(5);
                } while (slot[i]);
                attrChange[0][currSlot] = -player.attrNum[currSlot] + player.attrNum[i];
                slot[i] = true;
            }
            eventChoose = new String[]{lang.getString("magic_switch"), lang.getString("learn_trick")};
            callBack = eventTrigger(target, attrChange, eventChoose,player);
            if (1 == callBack) {
                System.out.println(lang.getString("type_in") + key[5] + lang.getString("main_8") + "\n");
                player.canInvisible++;
            }
        } else if (10 == target && player.visible) {
            int temp = 0;
            for (int i = 2; i <= 4; ++i) {
                if (player.attrNum[i] == 0) {
                    temp -= 5;
                }
            }
            for (int i = 0; i < 5; ++i) {
                player.map[2][i] = 1;
            }
            player.map[2][player.playP] = 0;
            attrChange = new int[][]{{temp, 0, -player.attrNum[2], -player.attrNum[3], -player.attrNum[4]}, {temp, 0, -player.attrNum[2], -player.attrNum[3], -player.attrNum[4]}};
            eventChoose = new String[]{lang.getString("trade"), lang.getString("seal")};
            callBack = eventTriggerRandom(target, attrChange, eventChoose, false, player);
            if (callBack == 0) {
                player.maxHealth = (int) (player.maxHealth * 1.2);
                player.maxEnergy = (int) (player.maxEnergy * 1.5);
            } else {
                player.canInvisible += 3;
                player.weight[8]--;
            }
            System.out.println(lang.getString("run_away"));
        } else if (11 == target && player.visible) {
            attrChange = new int[][]{{0, 0, 0, -9, -1}, {player.attrNum[0] / 4 + 2, 0, 1, 4, 1}};
            eventChoose = new String[]{lang.getString("bag_expansion"), lang.getString("raid")};
            callBack = eventTrigger(target, attrChange, eventChoose,player);
            if (callBack == 0) {
                player.packageLimit = player.packageLimit * (100 + ra.nextInt(3) + 9) / 100;
                player.tailorFright++;
            } else {
                player.tailorFright += 3;
            }
            System.out.println(lang.getString("tailor_fright"));
        }
    }

    public static int eventTriggerRandom(int target, int[][] attrChange, String[] eventChoose, boolean shouldRandom, Player player) throws InterruptedException {
        ResourceBundle lang = player.langOperator.getLang();
        while (true) {
            System.out.println(lang.getString("event_info_1") + eventName[target] + lang.getString("event_info_2"));
            System.out.println(lang.getString("event_info_3") + eventChoose[0] + lang.getString("event_info_4") + eventChoose[1] + lang.getString("event_info_5"));
            Scanner scanner = new Scanner(System.in);
            shouldMurMur = true;
            Murmur.createMurmur(player);
            String typeIn = scanner.next();
            shouldMurMur = false;
            int isTwo;
            if ("1".equals(typeIn)) {
                isTwo = 0;
            } else {
                if (!"2".equals(typeIn)) {
                    System.out.println(illegalMove);
                    continue;
                }
                isTwo = 1;
            }
            attrOperatorRandom(isTwo, player, attrChange, attrNames, shouldRandom);
            player.negPunish();
            Limit.packageLimit(player);
            return isTwo;
        }
    }

    public static int eventTrigger(int target, int[][] attrChange, String[] eventChoose, Player player) throws InterruptedException {
        return eventTriggerRandom(target, attrChange, eventChoose, true, player);
    }

    public static void attrOperatorRandom(int isTwo, Player player, int[][] attrChange, String[] attrName, boolean shouldRandom) throws InterruptedException {
        for (int i = 0; i <= 4; ++i) {
            int delta = 0;
            if (attrChange[isTwo][i] != 0) {
                delta = attrChange[isTwo][i];
                if (shouldRandom) {
                    delta += ra.nextInt(3) - 1;
                }
                player.attrNum[i] += delta;
            }
            if (delta > 0) {
                System.out.println(attrName[i] + "+" + delta + "=" + player.attrNum[i] + "   ");
                Thread.sleep(1000L);
            } else if (delta < 0) {
                System.out.println(attrName[i] + delta + "=" + player.attrNum[i] + "   ");
                Thread.sleep(1000L);
            }
        }
        System.out.println("\n");
    }
    
}
