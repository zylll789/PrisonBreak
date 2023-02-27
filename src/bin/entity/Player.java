package bin.entity;

import bin.util.LangOperator;

import java.util.ResourceBundle;
import java.util.Scanner;

import static run.PrisonBreakDebug.ra;

public class Player {

    public String gameName;

    public int[] attrNum;
    public String player;
    public String playerType;
    public int floor;
    public int packageLimit;
    public int maxHealth;
    public int maxEnergy;
    public int[] originalWeight;
    public int[] weight;
    public int[] weightOperator;
    public boolean visible;
    public int canInvisible;
    public boolean ifContinue;
    public int starve;
    public boolean goUp;
    public int tailorFright;
    public int playP;
    public int[][] map;
    public LangOperator langOperator;

    public Player(String gameName,int[] attrNum, String player, String playerType, int floor, int packageLimit, int maxHealth, int maxEnergy, int[] originalWeight, int[] weight, int[] weightOperator, boolean visible, int canInvisible, boolean ifContinue, int starve, boolean goUp, int[][] map, int playP, int tailorFright, LangOperator langOperator) {
        this.gameName=gameName;
        this.attrNum = attrNum;
        this.player = player;
        this.playerType = playerType;
        this.floor = floor;
        this.packageLimit = packageLimit;
        this.maxHealth = maxHealth;
        this.maxEnergy = maxEnergy;
        this.originalWeight = originalWeight;
        this.weight = weight;
        this.weightOperator = weightOperator;
        this.visible = visible;
        this.canInvisible = canInvisible;
        this.ifContinue = ifContinue;
        this.starve = starve;
        this.goUp = goUp;
        this.map = map;
        this.playP = playP;
        this.tailorFright = tailorFright;
        this.langOperator = langOperator;
        langOperator.setPlayer(this);
    }

    public static Player createCustomPlayer() {
        return new Player("",new int[5], "player", "0", 0, 0, 0, 0, new int[]{}, new int[]{}, new int[]{}, true, 0, false, 0, false, new int[3][5], 2, 89, new LangOperator(LangOperator.US));
    }

    public void lifeLimit() {
        if (attrNum[0] > maxHealth * 2) {
            attrNum[0] = maxHealth * 2;
            System.out.println(langOperator.getLang().getString("life_limit"));
        }
        if (attrNum[1] > maxEnergy * 5 / 2) {
            attrNum[1] = maxEnergy * 5 / 2;
            System.out.println(langOperator.getLang().getString("life_limit"));
        }
    }

    public void ifStarve() {
        if (attrNum[4] == 0 && goUp) {
            ifContinue = true;
        }
        if (attrNum[4] == 0 && ifContinue) {
            ++starve;
        } else if (attrNum[4] > 0) {
            starve = 0;
            ifContinue = false;
        }
        if (starve >= 3) {
            attrNum[0]--;
            System.out.println(langOperator.getLang().getString("starve"));
        }
    }

    public static String choosePlayer(Player player) {
        ResourceBundle lang = player.langOperator.getLang();
        System.out.println("\n" + lang.getString("choose_player"));
        System.out.println(lang.getString("choose_type"));
        Scanner scanner = new Scanner(System.in);
        String typeIn = scanner.next();
        player.playerType = typeIn;
        if ("1".equals(typeIn)) {
            player.attrNum = new int[]{14, 14, 3, 4, 5};
            return lang.getString("jack");
        } else if ("2".equals(typeIn)) {
            player.attrNum = new int[]{10, 12, 1, 10, 8};
            return lang.getString("mike");
        } else if ("3".equals(typeIn)) {
            player.attrNum = new int[]{9, 8, 8, 5, 6};
            return lang.getString("steven");
        } else if ("4".equals(typeIn)) {
            player.attrNum = new int[]{
                    ra.nextInt(3) + 9,
                    ra.nextInt(4) + 9,
                    ra.nextInt(3),
                    ra.nextInt(4) + 8,
                    ra.nextInt(4) + 9};
            return lang.getString("player");
        } else if ("zylll".equals(typeIn)) {
            player.attrNum = new int[]{1, 200, 1, 1, 1};
            return lang.getString("test");
        } else {
            player.attrNum = new int[]{10, 10, 0, 10, 10};
            return lang.getString("player");
        }
    }

    public void loadPlayer() {
        player = Player.choosePlayer(this);
        floor = 0;
        packageLimit = (attrNum[2] * 2 + attrNum[3] / 5 + attrNum[4] * 3 / 2) * 2;
        maxHealth = attrNum[0];
        maxEnergy = attrNum[1];
        originalWeight = new int[]{10, 10, 6, 6, 5, 6, 8, 5, 1};
        weight = originalWeight.clone();
        weightOperator = new int[]{0, 1, 0, 0, 0, 0, 0, 0, 0};
        visible = true;
        starve = 0;
        goUp = false;
        canInvisible = 0;
        tailorFright = 89;
        playP = 2;
    }

    public void negPunish() {
        lifeLimit();
        for (int i = 1; i <= 4; ++i) {
            if (attrNum[i] < 0) {
                System.out.println(langOperator.getLang().getString("neg_punish_1") + langOperator.attrNames[i] + langOperator.getLang().getString("neg_punish_2"));
                System.out.println(langOperator.getLang().getString("health") + " / 2 = " + attrNum[0] / 2);
                attrNum[i] = 0;
                attrNum[0] /= 2;
            }
        }
    }
}
