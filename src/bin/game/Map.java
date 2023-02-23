package bin.game;

import bin.entity.Player;

import static run.PrisonBreakDebug.ra;

public class Map {

    public static int[][] newMap(){
        int[][] map = new int[3][5];
        for (int i = 0; i <= 2; ++i) {
            for (int j = 0; j <= 4; ++j) {
                map[i][j] = ra.nextInt(6) + 1;
            }
        }
        map[2][2]=0;
        return map;
    }

    public static void weightLine1(Player player) {
        int i;
        if (player.floor == 43 || player.floor == 58) {
            for (i = 0; i < 5; ++i) {
                player.map[0][i] = 10;
            }
            return;
        }
        int originalWeightCounter = 0;
        for (i = 0; i <= 8; ++i) {
            originalWeightCounter += player.originalWeight[i];
        }
        for (i = 0; i <= 4; ++i) {
            int weightCounter = 0;
            int j;
            for (j = 0; j <= 8; ++j) {
                weightCounter += player.weight[j];
            }
            if (0 == weightCounter) {
                for (j = 0; j <= 7; ++j) {
                    player.weight[j] = player.originalWeight[j] + ra.nextInt(3) - 1;
                }
            }
            do {
                j = ra.nextInt(9);
            } while (player.weight[j] <= 0);
            player.weight[j]--;
            player.map[0][i] = j + 1;
        }
        if (player.floor > 11 * player.weightOperator[1]) {
            player.originalWeight[0]++;
            player.originalWeight[1]++;
            player.weightOperator[1]++;
        }
        if ((double) player.originalWeight[1] > 0.5D * (double) originalWeightCounter) {
            i = ra.nextInt(3) + 1;
            player.originalWeight[0] -= i;
            player.originalWeight[1] -= i;
        }
        punishViolence(player);
        if (player.floor >= 28 && player.floor <= 48) {
            for (i = 0; i < 5; ++i) {
                if (ra.nextInt(100) >= player.tailorFright) {
                    player.map[0][i] = 11;
                    player.tailorFright += 5;
                }
            }
            player.tailorFright--;
        }
    }

    public static void punishViolence(Player player) {
        int i = 0;
        while (i <= 8) {
            switch (i) {
                case 2:
                case 3:
                case 5:
                case 6:
                    if (player.weightOperator[i] > 6) {
                        player.weightOperator[i] = 0;
                        if (player.originalWeight[i] > 0) {
                            player.originalWeight[i] -= ra.nextInt(3);
                        }
                    }
                default:
                    ++i;
            }
        }
    }

}
