import bin.util.Read;
import bin.util.Save;

import java.util.*;

@SuppressWarnings("BusyWait")
public class PrisonBreakDebug {
    static String locate = "lib.lang.lang";
    static Locale cn = Locale.CHINA;
    static Locale us = Locale.US;
    public static ResourceBundle CN = ResourceBundle.getBundle(locate, cn);
    public static ResourceBundle US = ResourceBundle.getBundle(locate, us);
    static ResourceBundle lang = US;

    static String gameName;
    static String playerType;

    static int[] attrNum = new int[5];
    static String player;
    static String[] eventName;
    static int floor;
    static int packageLimit;
    static int maxHealth;
    static int maxEnergy;
    static int[] originalWeight;
    static int[] weight;
    static int[] weightOperator;
    static boolean visible;
    static int canInvisible;
    static boolean ifContinue = false;
    static int starve;
    static boolean goUp;
    static int[][] map;
    static int playP;

    static boolean skip = false;
    static boolean skipAnimation = false;
    static boolean shouldMurMur = true;

    static String[] attrNames = new String[]{lang.getString("health"), lang.getString("energy"), lang.getString("weapon"), lang.getString("money"), lang.getString("food")};
    static Random ra = new Random();
    static String stars = "**********************************************";
    static String illegalMove = "\n" + stars + "\n" + lang.getString("unsupported_move") + "\n" + stars + "\n";
    static boolean needNew = true;

    public PrisonBreakDebug() {
    }

    private static ResourceBundle language() {
        System.out.println(lang.getString("choose_lang") + "\n" + lang.getString("chinese") + "\n" + lang.getString("english") + "\n");
        Scanner scanner = new Scanner(System.in);
        String lang = scanner.next();
        if ("1".equals(lang)) {
            return CN;
        } else if ("2".equals(lang)) {
            return US;
        } else {
            System.out.println(PrisonBreakDebug.lang.getString("error_lang") + "\n");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return language();
    }

    private static void changeLang(int[][] map) {
        PrisonBreakDebug.lang = language();
        if ("1".equals(playerType)) {
            player = lang.getString("jack");
        } else if ("2".equals(playerType)) {
            player = lang.getString("mike");
        } else if ("3".equals(playerType)) {
            player = lang.getString("steven");
        } else {
            player = lang.getString("player");
        }
        transLang();
        printUI(map);
    }

    private static void transLang() {
        eventName = new String[]{player, lang.getString("space8"), lang.getString("guards"), lang.getString("chemists"), lang.getString("priest"), lang.getString("inmate"), lang.getString("cook"), lang.getString("drunkard"), lang.getString("gamblers"), lang.getString("magician"), lang.getString("vampires")};
        attrNames = new String[]{lang.getString("health"), lang.getString("energy"), lang.getString("weapon"), lang.getString("money"), lang.getString("food")};
        illegalMove = "\n" + stars + "\n" + lang.getString("unsupported_move") + "\n" + stars + "\n";
    }

    private static void beforeWelcome() {
        System.out.println("\n");
        for (String s : Arrays.asList("welcome_commemorate", "continue")) {
            System.out.println(lang.getString(s));
        }
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        System.out.println("\n");
    }

    private static void animateIntroduce() throws InterruptedException {
        skip = false;
        Thread thread = new Thread(() -> {
            while (!skip) {
                Scanner scanner = new Scanner(System.in);
                scanner.next();
                skipAnimation = true;
                skip = true;
            }
        });
        thread.start();
        for (int i = 1; i <= 9; ++i) {
            if (!skipAnimation) {
                System.out.println("\n" + lang.getString("welcome_intro_" + i));
                System.out.println(lang.getString("skip"));
                Thread.sleep(1000L);
            } else break;
        }
        if (!skipAnimation) {
            System.out.println("\n" + lang.getString("welcome_enjoy"));
            System.out.println(lang.getString("continue"));
            Thread.sleep(1000L);
        }
        do {
            Thread.sleep(100L);
        } while (!skipAnimation);
        skipAnimation = false;
        skip = true;
        System.out.println();
    }

    private static void welcome() throws Exception {
        System.out.println(lang.getString("welcome_play"));
        System.out.println(lang.getString("welcome_see_or_skip"));
        Scanner scanner = new Scanner(System.in);
        String typeIn = scanner.next();
        if ("1".equals(typeIn)) {
            animateIntroduce();
            welcome();
        } else if ("2".equals(typeIn)) {
            System.out.println("\n" + lang.getString("game_start"));
            System.out.println(lang.getString("continue"));
            scanner.next();
            System.out.println("\n");
        } else if ("3".equals(typeIn)) {
            helper();
            welcome();
        } else if ("4".equals(typeIn)) {
            lang = language();
            welcome();
        } else if ("5".equals(typeIn)) {
            System.out.println("\n" + lang.getString("confirm_exit"));
            System.out.println(lang.getString("back"));
            typeIn += scanner.next();
            System.out.println();
            if ("51".equals(typeIn)) {
                System.out.println(lang.getString("wise_choose"));
                Thread.sleep(1000L);
                welcome();
            }
            if (ra.nextInt(2) == 1) {
                System.out.println(lang.getString("sneer1"));
            } else {
                System.out.println(lang.getString("sneer2"));
            }
            Thread.sleep(1500L);
            System.exit(0);
        } else {
            System.out.println(illegalMove);
            Thread.sleep(1000L);
            welcome();
        }
    }

    private static void useSave() {
        System.out.println(lang.getString("choose_save"));
        Scanner scanner = new Scanner(System.in);
        String gameName = scanner.next();
        if (gameName.equals("t") || gameName.equals("T")) {
            System.out.println(lang.getString("save_name"));
            Scanner scanner1 = new Scanner(System.in);
            PrisonBreakDebug.gameName = scanner1.next();
        } else {
            PrisonBreakDebug.gameName = gameName;
            Read read = new Read();
            int exist = read.readXML("\\" + gameName + ".xml", lang);
            if (exist > 0) {
                lang = read.lang;
                attrNum = read.attrNum;
                playerType = read.playerType;
                if ("1".equals(playerType)) {
                    player = lang.getString("jack");
                } else if ("2".equals(playerType)) {
                    player = lang.getString("mike");
                } else if ("3".equals(playerType)) {
                    player = lang.getString("steven");
                } else {
                    player = lang.getString("player");
                }
                floor = read.floor;
                packageLimit = read.packageLimit;
                maxHealth = read.maxHealth;
                maxEnergy = read.maxEnergy;
                starve = read.starve;
                visible = read.visible;
                canInvisible = read.canInvisible;
                ifContinue = read.ifContinue;
                goUp = read.goUp;
                originalWeight = read.originalWeight;
                weight = read.weight;
                weightOperator = read.weightOperator;
                needNew = false;
                map = read.map;
                playP = read.playP;
            } else {
                useSave();
            }
        }

    }

    private static String choosePlayer() {
        System.out.println("\n" + lang.getString("choose_player"));
        System.out.println(lang.getString("choose_type"));
        Scanner scanner = new Scanner(System.in);
        String typeIn = scanner.next();
        playerType = typeIn;
        if ("1".equals(typeIn)) {
            attrNum = new int[]{14, 14, 3, 4, 5};
            return lang.getString("jack");
        } else if ("2".equals(typeIn)) {
            attrNum = new int[]{10, 12, 1, 10, 8};
            return lang.getString("mike");
        } else if ("3".equals(typeIn)) {
            attrNum = new int[]{9, 8, 8, 5, 6};
            return lang.getString("steven");
        } else if ("4".equals(typeIn)) {
            attrNum = new int[]{
                    ra.nextInt(3) + 9,
                    ra.nextInt(4) + 9,
                    ra.nextInt(3),
                    ra.nextInt(4) + 8,
                    ra.nextInt(4) + 9};
            return lang.getString("player");
        } else {
            attrNum = new int[]{10, 10, 0, 10, 10};
            return lang.getString("player");
        }
    }

    private static void printUI(int[][] map) {
        System.out.print("\n" + lang.getString("floor") + floor + "       ");
        int i;
        for (i = 0; i <= 4; ++i) {
            System.out.print(attrNames[i] + ":" + attrNum[i] + "  ");
        }
        System.out.println();
        for (i = 0; i <= 2; ++i) {
            for (int j = 0; j <= 4; ++j) {
                System.out.print("|  " + eventName[map[i][j]] + "  ");
            }
            System.out.print("|\n");
        }
        System.out.println();
    }

    private static void negPunish() {
        lifeLimit();
        for (int i = 1; i <= 4; ++i) {
            if (attrNum[i] < 0) {
                System.out.println(lang.getString("neg_punish_1") + attrNames[i] + lang.getString("neg_punish_2"));
                System.out.println(lang.getString("health") + " / 2 = " + attrNum[0] / 2);
                attrNum[i] = 0;
                attrNum[0] /= 2;
            }
        }
    }

    private static void attrOperatorRandom(int isTwo, int[] attrNum, int[][] attrChange, String[] attrName, boolean shouldRandom) throws InterruptedException {
        for (int i = 0; i <= 4; ++i) {
            int delta = 0;
            if (attrChange[isTwo][i] != 0) {
                delta = attrChange[isTwo][i];
                if (shouldRandom) {
                    delta += ra.nextInt(3) - 1;
                }
                attrNum[i] += delta;
            }
            if (delta > 0) {
                System.out.println(attrName[i] + "+" + delta + "=" + attrNum[i] + "   ");
                Thread.sleep(1000L);
            } else if (delta < 0) {
                System.out.println(attrName[i] + delta + "=" + attrNum[i] + "   ");
                Thread.sleep(1000L);
            }
        }
        System.out.println("\n");
    }

    private static int eventTriggerRandom(int target, int[][] attrChange, String[] eventChoose, int[][] map, boolean shouldRandom) throws InterruptedException {
        while (true) {
            System.out.println(lang.getString("event_info_1") + eventName[target] + lang.getString("event_info_2"));
            System.out.println(lang.getString("event_info_3") + eventChoose[0] + lang.getString("event_info_4") + eventChoose[1] + lang.getString("event_info_5"));
            Scanner scanner = new Scanner(System.in);
            shouldMurMur = true;
            murmur();
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
            attrOperatorRandom(isTwo, attrNum, attrChange, attrNames, shouldRandom);
            negPunish();
            packageLimit();
            printUI(map);
            return isTwo;
        }
    }

    private static int eventTrigger(int target, int[][] attrChange, String[] eventChoose, int[][] map) throws InterruptedException {
        return eventTriggerRandom(target, attrChange, eventChoose, map, true);
    }

    private static void packageLimit() {
        int currPackage = attrNum[2] * 2 + attrNum[3] / 5 + attrNum[4] * 3 / 2;
        while (currPackage > packageLimit) {
            System.out.println("\n" + lang.getString("package_limit_1"));
            Scanner scanner = new Scanner(System.in);
            shouldMurMur = true;
            murmur();
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
                case 0 -> attrNum[2] -= typeInInt;
                case 1 -> attrNum[3] -= typeInInt;
                case 2 -> attrNum[4] -= typeInInt;
                default -> System.out.println(illegalMove);
            }
            currPackage = attrNum[2] * 2 + attrNum[3] / 5 + attrNum[4] * 3 / 2;
            if (currPackage > packageLimit) {
                System.out.println("\n" + lang.getString("package_limit_4"));
            } else {
                System.out.println("\n" + lang.getString("package_limit_5"));
            }
        }

    }

    private static void lifeLimit() {
        if (attrNum[0] > maxHealth * 2) {
            attrNum[0] = maxHealth * 2;
            System.out.println(lang.getString("life_limit"));
        }
        if (attrNum[1] > maxEnergy * 5 / 2) {
            attrNum[1] = maxEnergy * 5 / 2;
            System.out.println(lang.getString("life_limit"));
        }

    }

    private static void weightLine1(int[][] map) {
        int i;
        if (floor == 43 || floor == 58) {
            for (i = 0; i < 5; ++i) {
                map[0][i] = 10;
            }
            return;
        }
        int originalWeightCounter = 0;
        for (i = 0; i <= 8; ++i) {
            originalWeightCounter += originalWeight[i];
        }
        for (i = 0; i <= 4; ++i) {
            int weightCounter = 0;
            int j;
            for (j = 0; j <= 8; ++j) {
                weightCounter += weight[j];
            }
            if (0 == weightCounter) {
                for (j = 0; j <= 7; ++j) {
                    weight[j] = originalWeight[j] + ra.nextInt(3) - 1;
                }
            }
            do {
                j = ra.nextInt(9);
            } while (weight[j] <= 0);
            weight[j]--;
            map[0][i] = j + 1;
        }
        if (floor > 11 * weightOperator[1]) {
            originalWeight[0]++;
            originalWeight[1]++;
            weightOperator[1]++;
        }
        if ((double) originalWeight[1] > 0.5D * (double) originalWeightCounter) {
            i = ra.nextInt(3) + 1;
            originalWeight[0] -= i;
            originalWeight[1] -= i;
        }
        punishViolence(originalWeight, weightOperator);
    }

    private static void punishViolence(int[] originalWeight, int[] weightOperator) {
        int i = 0;
        while (i <= 8) {
            switch (i) {
                case 2:
                case 3:
                case 5:
                case 6:
                    if (weightOperator[i] > 6) {
                        weightOperator[i] = 0;
                        if (originalWeight[i] > 0) {
                            originalWeight[i] -= ra.nextInt(3);
                        }
                    }
                default:
                    ++i;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        lang = language();
        transLang();
        beforeWelcome();
        welcome();
        useSave();
        int[][] map = new int[3][5];
        int playP = 2;
        if (needNew) {
            loadPlayer();
            System.out.println();
            for (int i = 0; i <= 2; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    map[i][j] = ra.nextInt(6) + 1;
                }
            }
            map[2][playP] = 0;
        } else {
            map = PrisonBreakDebug.map;
            playP = PrisonBreakDebug.playP;
        }
        transLang();
        printUI(map);
        while (true) {
            System.out.println(lang.getString("main_1"));
            Scanner scanner = new Scanner(System.in);
            shouldMurMur = true;
            murmur();
            String typeIn = scanner.next();
            shouldMurMur = false;
            int target = 0;
            int secondTypeIn;
            int callBack;
            int[][] attrChange;
            String[] eventChoose;
            if ("l".equals(typeIn)) {
                changeLang(map);
                continue;
            }
            if ("s".equals(typeIn)) {
                Save save = new Save();
                save.writeXML(gameName, attrNum, playerType, lang, floor, packageLimit, maxHealth, maxEnergy, starve, visible, canInvisible, ifContinue, goUp, originalWeight, weight, weightOperator, map, playP);
                Thread.sleep(1000L);
                printUI(map);
                continue;
            }
            if ("4".equals(typeIn)) {
                if (0 == playP) {
                    System.out.println(illegalMove);
                } else {
                    target = map[2][playP - 1];
                    map[2][playP - 1] = 0;
                    map[2][playP] = 1;
                    --playP;
                }
            } else if ("6".equals(typeIn)) {
                if (4 == playP) {
                    System.out.println(illegalMove);
                } else {
                    target = map[2][playP + 1];
                    map[2][playP + 1] = 0;
                    map[2][playP] = 1;
                    ++playP;
                }
            } else if ("8".equals(typeIn)) {
                target = map[1][playP];
                ++floor;
                attrNum[1]--;
                goUp = true;
                visible = true;
                negPunish();
                for (int i = 2; i >= 1; --i) {
                    System.arraycopy(map[i - 1], 0, map[i], 0, 5);
                }
                map[2][playP] = 0;
                weightLine1(map);
            } else {
                Scanner secondScanner;
                if (!"e".equals(typeIn) && !"E".equals(typeIn)) {
                    if (!"h".equals(typeIn) && !"H".equals(typeIn)) {
                        if (("i".equals(typeIn) || "I".equals(typeIn)) && canInvisible>0) {
                            System.out.println(lang.getString("main_2"));
                            visible = false;
                            canInvisible--;
                        } else if ("*".equals(typeIn)) {
                            helper();
                        } else {
                            if ("q".equals(typeIn) || "Q".equals(typeIn)) {
                                System.out.println(lang.getString("main_3"));
                                Thread.sleep(1500L);
                                break;
                            }
                            if ("win".equals(typeIn) && floor >= 150) {
                                System.out.println(lang.getString("main_4"));
                                Thread.sleep(1500L);
                                break;
                            }
                            System.out.println(illegalMove);
                        }
                    } else {
                        System.out.println(lang.getString("main_5"));
                        secondScanner = new Scanner(System.in);
                        shouldMurMur = true;
                        murmur();
                        secondTypeIn = secondScanner.nextInt();
                        shouldMurMur = false;
                        int shouldHeal = secondTypeIn;
                        if (secondTypeIn > attrNum[1]) {
                            secondTypeIn = attrNum[1] + 1;
                            shouldHeal = attrNum[1];
                        }
                        int delta = 0;
                        for (int i = 1; i <= shouldHeal; ++i) {
                            delta += ra.nextInt(2) + 2;
                        }
                        attrChange = new int[][]{{delta, -secondTypeIn, 0, 0, 0}, {}};
                        attrOperatorRandom(0, attrNum, attrChange, attrNames, false);
                        negPunish();
                    }
                } else {
                    System.out.println(lang.getString("main_6"));
                    secondScanner = new Scanner(System.in);
                    shouldMurMur = true;
                    murmur();
                    secondTypeIn = secondScanner.nextInt();
                    shouldMurMur = false;
                    int shouldEat = secondTypeIn;
                    if (secondTypeIn > attrNum[4]) {
                        secondTypeIn = attrNum[4] + 1;
                        shouldEat = attrNum[4];
                    }
                    int delta = 0;
                    for (int i = 1; i <= shouldEat; ++i) {
                        delta += ra.nextInt(2) + 2;
                    }
                    attrChange = new int[][]{{0, delta, 0, 0, -secondTypeIn}, {}};
                    attrOperatorRandom(0, attrNum, attrChange, attrNames, false);
                    negPunish();
                }
            }
            Thread.sleep(500L);
            if (floor > 150) {
                System.out.println(lang.getString("main_7"));
            }
            printUI(map);
            if (2 == target && visible) {
                attrChange = new int[][]{{-attrNum[0] / 3, -3, -2, 0, 0}, {0, 0, 0, -3 - weightOperator[1], 0}};
                eventChoose = new String[]{lang.getString("fight"), lang.getString("bribe")};
                eventTrigger(target, attrChange, eventChoose, map);
            } else if (3 == target && visible) {
                attrChange = new int[][]{{3, 0, 0, -attrNum[3] / 4, 0}, {4, -2, -1, 0, 0}};
                eventChoose = new String[]{lang.getString("purchase"), lang.getString("threaten")};
                callBack = eventTrigger(target, attrChange, eventChoose, map);
                if (1 == callBack) {
                    weightOperator[2]++;
                }
            } else if (4 == target && visible) {
                attrChange = new int[][]{{0, 4, -2, 1, 1}, {-1, -2, 0, 5, 1}};
                eventChoose = new String[]{lang.getString("pray"), lang.getString("rob")};
                callBack = eventTrigger(target, attrChange, eventChoose, map);
                if (1 == callBack) {
                    weightOperator[3]++;
                }
            } else if (5 == target && visible) {
                attrChange = new int[][]{{-1, -2, 1, 2, 1}, {0, 0, attrNum[2], -attrNum[3] / 2, -attrNum[4] / 2}};
                eventChoose = new String[]{lang.getString("hit"), lang.getString("recruit")};
                eventTrigger(target, attrChange, eventChoose, map);
            } else if (6 == target && visible) {
                attrChange = new int[][]{{0, 0, 0, -5, 5}, {-1, -attrNum[1] / 3, 1, 1, 5}};
                eventChoose = new String[]{lang.getString("buy"), lang.getString("beat")};
                callBack = eventTrigger(target, attrChange, eventChoose, map);
                if (1 == callBack) {
                    weightOperator[5]++;
                }
            } else if (7 == target && visible) {
                attrChange = new int[][]{{-attrNum[0] / 5, -2, -1, 0, 0}, {0, -attrNum[1] / 4, 0, -2, -attrNum[4] / 4}};
                eventChoose = new String[]{lang.getString("punch"), lang.getString("brush_off")};
                callBack = eventTrigger(target, attrChange, eventChoose, map);
                if (0 == callBack) {
                    weightOperator[6]++;
                }
            } else if (8 == target && visible) {
                attrChange = new int[2][5];
                int temp;
                for (temp = 2; temp <= 4; ++temp) {
                    attrChange[0][temp] = -attrNum[temp] / 3;
                }
                temp = ra.nextInt(3) + 2;
                int luck = ra.nextInt(100) + 1;
                if (luck <= 20 + weightOperator[7]) {
                    attrChange[1][temp] = attrNum[temp];
                } else {
                    attrChange[1][temp] = -attrNum[temp] + 1;
                }
                eventChoose = new String[]{lang.getString("fair_play"), lang.getString("cheat")};
                callBack = eventTrigger(target, attrChange, eventChoose, map);
                if (0 == callBack) {
                    weightOperator[7] += ra.nextInt(3) + 4;
                } else {
                    weightOperator[7] += ra.nextInt(2);
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
                    attrChange[0][currSlot] = -attrNum[currSlot] + attrNum[i];
                    slot[i] = true;
                }
                eventChoose = new String[]{lang.getString("magic_switch"), lang.getString("learn_trick")};
                callBack = eventTrigger(target, attrChange, eventChoose, map);
                if (1 == callBack) {
                    System.out.println(lang.getString("main_8") + "\n");
                    canInvisible++;
                }
            } else if(10 == target && visible){
                int temp = 0;
                for(int i=2;i<=4;++i){
                    if(attrNum[i]==0){
                        temp-=5;
                    }
                }
                for(int i=0;i<5;++i){
                    map[2][i]=1;
                }
                map[2][playP]=0;
                attrChange = new int[][]{{temp,0,-attrNum[2],-attrNum[3],-attrNum[4]},{temp,0,-attrNum[2],-attrNum[3],-attrNum[4]}};
                eventChoose = new String[]{lang.getString("trade"), lang.getString("seal")};
                callBack = eventTriggerRandom(target, attrChange, eventChoose, map, false);
                if(callBack==0){
                    maxHealth = (int) (maxHealth*1.2);
                    maxEnergy = (int) (maxEnergy*1.5);
                } else {
                    canInvisible+=3;
                    weight[8]--;
                }
                System.out.println(lang.getString("run_away"));
            }
            ifStarve();
            if (attrNum[0] <= 0) {
                System.out.println(lang.getString("main_9"));
                Thread.sleep(1500L);
                break;
            }
        }
    }

    public static void ifStarve() {
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
            System.out.println(lang.getString("starve"));
        }

    }

    private static void helper() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + lang.getString("help_1"));
        Thread.sleep(1000L);
        label136:
        while (true) {
            System.out.println("\n\n" + lang.getString("help_2"));
            String typeIn = scanner.next();
            if ("1".equals(typeIn)) {
                System.out.println(lang.getString("help_3"));
                typeIn = typeIn + scanner.next();
                if ("11".equals(typeIn)) {
                    System.out.println("\n\n\n");
                    System.out.println(lang.getString("help_4"));
                    Thread.sleep(1000L);
                    System.out.println(lang.getString("help_6"));
                    typeIn = typeIn + scanner.next();
                    byte type = -1;
                    switch (typeIn.hashCode()) {
                        case 48657:
                            if (typeIn.equals("111")) {
                                type = 0;
                            }
                            break;
                        case 48658:
                            if (typeIn.equals("112")) {
                                type = 1;
                            }
                            break;
                        case 48659:
                            if (typeIn.equals("113")) {
                                type = 2;
                            }
                            break;
                        case 48660:
                            if (typeIn.equals("114")) {
                                type = 3;
                            }
                            break;
                        case 48661:
                            if (typeIn.equals("115")) {
                                type = 4;
                            }
                            break;
                        case 48662:
                            if (typeIn.equals("116")) {
                                type = 5;
                            }
                            break;
                        case 48663:
                            if (typeIn.equals("117")) {
                                type = 6;
                            }
                            break;
                        case 48664:
                            if (typeIn.equals("118")) {
                                type = 7;
                            }
                    }
                    switch (type) {
                        case 0:
                            System.out.println("\n\n" + lang.getString("help_10_1"));
                            Thread.sleep(1000L);
                            System.out.println(lang.getString("help_7"));
                            typeIn = typeIn + scanner.next();
                            if ("1112".equals(typeIn)) {
                                System.out.println("\n\n" + lang.getString("help_10_2"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                System.out.println("\n" + lang.getString("help_10_3"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                if (!"114514".equals(typeIn)) {
                                    System.out.println("\n\n\n");
                                }
                            }
                            break;
                        case 1:
                            System.out.println("\n\n" + lang.getString("help_11_1"));
                            Thread.sleep(1000L);
                            System.out.println(lang.getString("help_7"));
                            typeIn = typeIn + scanner.next();
                            if ("1122".equals(typeIn)) {
                                System.out.println("\n\n" + lang.getString("help_11_2"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                System.out.println("\n" + lang.getString("help_11_3"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                if (!"114514".equals(typeIn)) {
                                    System.out.println("\n\n\n");
                                }
                            }
                            break;
                        case 2:
                            System.out.println("\n\n" + lang.getString("help_12_1"));
                            Thread.sleep(1000L);
                            System.out.println(lang.getString("help_7"));
                            typeIn = typeIn + scanner.next();
                            if ("1132".equals(typeIn)) {
                                System.out.println("\n\n" + lang.getString("help_12_2"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                System.out.println("\n" + lang.getString("help_12_3"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                if (!"114514".equals(typeIn)) {
                                    System.out.println("\n\n\n");
                                }
                            }
                            break;
                        case 3:
                            System.out.println("\n\n" + lang.getString("help_13_1"));
                            Thread.sleep(1000L);
                            System.out.println(lang.getString("help_7"));
                            typeIn = typeIn + scanner.next();
                            if ("1142".equals(typeIn)) {
                                System.out.println("\n\n" + lang.getString("help_13_2"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                System.out.println("\n" + lang.getString("help_13_3"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                if (!"114514".equals(typeIn)) {
                                    System.out.println("\n\n\n");
                                }
                            }
                            break;
                        case 4:
                            System.out.println("\n\n" + lang.getString("help_14_1"));
                            Thread.sleep(1000L);
                            System.out.println(lang.getString("help_7"));
                            typeIn = typeIn + scanner.next();
                            if ("1152".equals(typeIn)) {
                                System.out.println("\n\n" + lang.getString("help_14_2"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                System.out.println("\n" + lang.getString("help_14_3"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                if (!"114514".equals(typeIn)) {
                                    System.out.println("\n\n\n");
                                }
                            }
                            break;
                        case 5:
                            System.out.println("\n\n" + lang.getString("help_15_1"));
                            Thread.sleep(1000L);
                            System.out.println(lang.getString("help_7"));
                            typeIn = typeIn + scanner.next();
                            if ("1162".equals(typeIn)) {
                                System.out.println("\n\n" + lang.getString("help_15_2"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                System.out.println("\n" + lang.getString("help_15_3"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                if (!"114514".equals(typeIn)) {
                                    System.out.println("\n\n\n");
                                }
                            }
                            break;
                        case 6:
                            System.out.println("\n\n" + lang.getString("help_16_1"));
                            Thread.sleep(1000L);
                            System.out.println(lang.getString("help_7"));
                            typeIn = typeIn + scanner.next();
                            if ("1172".equals(typeIn)) {
                                System.out.println("\n\n" + lang.getString("help_16_2"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                System.out.println("\n" + lang.getString("help_16_3"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                if (!"114514".equals(typeIn)) {
                                    System.out.println("\n\n\n");
                                }
                            }
                            break;
                        case 7:
                            System.out.println("\n\n" + lang.getString("help_17_1"));
                            Thread.sleep(1000L);
                            System.out.println(lang.getString("help_7"));
                            typeIn = typeIn + scanner.next();
                            if ("1182".equals(typeIn)) {
                                System.out.println("\n\n" + lang.getString("help_17_2"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                System.out.println("\n" + lang.getString("help_17_3"));
                                System.out.println(lang.getString("continue"));
                                typeIn = typeIn + scanner.next();
                                if (!"114514".equals(typeIn)) {
                                    System.out.println("\n\n\n");
                                }
                            }
                            break;
                        default:
                            break label136;
                    }
                } else {
                    if (!"12".equals(typeIn)) {
                        break;
                    }
                    System.out.println("\n\n\n");
                    System.out.println(lang.getString("help_8"));
                    Thread.sleep(1000L);
                    System.out.println(lang.getString("help_6"));
                    typeIn = typeIn + scanner.next();
                    if ("121".equals(typeIn)) {
                        System.out.println(lang.getString("help_9"));
                        typeIn = typeIn + scanner.next();
                        if ("1211".equals(typeIn)) {
                            System.out.println("\n" + lang.getString("help_10"));
                            System.out.println(lang.getString("continue"));
                            typeIn = typeIn + scanner.next();
                            if (!"114514".equals(typeIn)) {
                                System.out.println("\n\n\n");
                            }
                        } else if ("1212".equals(typeIn)) {
                            System.out.println("\n" + lang.getString("help_11"));
                            System.out.println(lang.getString("continue"));
                            typeIn = typeIn + scanner.next();
                            if (!"114514".equals(typeIn)) {
                                System.out.println("\n\n\n");
                            }
                        }
                    } else {
                        if (!"122".equals(typeIn)) {
                            break;
                        }
                        System.out.println("\n\n\n");
                        System.out.println(lang.getString("help_12"));
                        typeIn = typeIn + scanner.next();
                        if ("1221".equals(typeIn)) {
                            System.out.println("\n" + lang.getString("help_13"));
                            System.out.println(lang.getString("continue"));
                            typeIn = typeIn + scanner.next();
                            if (!"114514".equals(typeIn)) {
                                System.out.println("\n\n\n");
                            }
                        } else if ("1222".equals(typeIn)) {
                            System.out.println("\n" + lang.getString("help_14"));
                            System.out.println(lang.getString("continue"));
                            typeIn = typeIn + scanner.next();
                            System.out.println("\n" + lang.getString("help_15"));
                            System.out.println(lang.getString("continue"));
                            typeIn = typeIn + scanner.next();
                            if (!"114514".equals(typeIn)) {
                                System.out.println("\n\n\n");
                            }
                        }
                    }
                }
            } else if ("weight".equals(typeIn)) {
                int i = 0;
                while (i <= 8) {
                    System.out.print("weight[" + i + "]=" + weight[i] + "   ");
                    switch (i) {
                        case 2:
                        case 5:
                            System.out.println();
                        default:
                            ++i;
                    }
                }
            } else {
                if (!"cheatEXP".equals(typeIn)) {
                    break;
                }
                System.out.println(lang.getString("help_you_have") + (20 + weightOperator[7]) + lang.getString("help_chance"));
            }
        }
        System.out.println("\n" + lang.getString("help_16"));
        Thread.sleep(1000L);
    }

    private static void loadPlayer() {
        player = choosePlayer();
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
    }

    private static void murmur() {
        Thread thread = new Thread(() -> {
            murmurLabel:
            while (shouldMurMur) {
                long time = ra.nextLong(1000L) + 2500L;
                for (int i = 0; i < time; ++i) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!shouldMurMur) {
                        break murmurLabel;
                    }
                }
                int chance = ra.nextInt(10) + 1;
                System.out.println(lang.getString("murmur") + lang.getString("murmur_" + chance));
            }
        });
        thread.start();
    }
}
