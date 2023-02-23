package bin.game;

import bin.entity.Player;
import bin.event.Menu;
import bin.event.Murmur;
import bin.event.NPCMeet;
import bin.screen.Display;
import bin.util.Read;
import bin.util.Save;

import java.util.ResourceBundle;
import java.util.Scanner;

import static run.PrisonBreakDebug.*;

@SuppressWarnings("BusyWait")
public class SelfGame {

    public static void createSelfGame(Player player) throws Exception {
        while (true) {
            ResourceBundle lang = player.langOperator.getLang();
            Display.printUI(player);
            System.out.println(lang.getString("main_1_1") + key[6] + lang.getString("main_1_2") + key[7] + lang.getString("main_1_3"));
            Scanner scanner = new Scanner(System.in);
            shouldMurMur = true;
            Murmur.createMurmur(player);
            String typeIn = scanner.next();
            shouldMurMur = false;
            int target;
            int secondTypeIn;
            int[][] attrChange;
            if (key[7].equals(typeIn)) {
                Save save = new Save();
                save.writeXML(player.gameName, player.attrNum, player.playerType, lang, player.floor, player.packageLimit, player.maxHealth, player.maxEnergy, player.starve, player.visible, player.canInvisible, player.ifContinue, player.goUp, player.originalWeight, player.weight, player.weightOperator, player.map, player.playP, player.tailorFright);
                Thread.sleep(1000L);
                continue;
            }
            if (key[0].equals(typeIn)) {
                if (0 == player.playP) {
                    System.out.println(illegalMove);
                    continue;
                } else {
                    target = player.map[2][player.playP - 1];
                    player.map[2][player.playP - 1] = 0;
                    player.map[2][player.playP] = 1;
                    player.playP--;
                }
            } else if (key[1].equals(typeIn)) {
                if (4 == player.playP) {
                    System.out.println(illegalMove);
                    continue;
                } else {
                    target = player.map[2][player.playP + 1];
                    player.map[2][player.playP + 1] = 0;
                    player.map[2][player.playP] = 1;
                    player.playP++;
                }
            } else if (key[2].equals(typeIn)) {
                target = player.map[1][player.playP];
                player.floor++;
                player.goUp = true;
                player.attrNum[1]--;
                player.visible=true;
                player.negPunish();
                for (int i = 2; i >= 1; --i) {
                    System.arraycopy(player.map[i - 1], 0, player.map[i], 0, 5);
                }
                player.map[2][player.playP] = 0;
                Map.weightLine1(player);
            } else {
                Scanner secondScanner;
                if (!key[3].equals(typeIn)) {
                    if (!key[4].equals(typeIn)) {
                        if ((key[5].equals(typeIn)) && player.canInvisible > 0) {
                            System.out.println(lang.getString("main_2"));
                            player.visible=false;
                            player.canInvisible--;
                            continue;
                        } else if (key[9].equals(typeIn)) {
                            Menu.createMenu(player,key);
                            continue;
                        } else {
                            if (key[6].equals(typeIn)) {
                                System.out.println(lang.getString("main_3"));
                                Thread.sleep(1500L);
                                break;
                            }
                            if (key[8].equals(typeIn) && player.floor >= 150) {
                                System.out.println(lang.getString("main_4"));
                                Thread.sleep(1500L);
                                break;
                            }
                            System.out.println(illegalMove);
                            continue;
                        }
                    } else {
                        System.out.println(lang.getString("main_5"));
                        secondScanner = new Scanner(System.in);
                        shouldMurMur = true;
                        Murmur.createMurmur(player);
                        String trans = secondScanner.next();
                        try {
                            secondTypeIn = Integer.parseInt(trans);
                        } catch (NumberFormatException e) {
                            System.out.println(illegalMove);
                            Thread.sleep(1000L);
                            continue;
                        }
                        shouldMurMur = false;
                        int shouldHeal = secondTypeIn;
                        if (secondTypeIn > player.attrNum[1]) {
                            secondTypeIn = player.attrNum[1] + 1;
                            shouldHeal = player.attrNum[1];
                        }
                        int delta = 0;
                        for (int i = 1; i <= shouldHeal; ++i) {
                            delta += ra.nextInt(2) + 2;
                        }
                        attrChange = new int[][]{{delta, -secondTypeIn, 0, 0, 0}, {}};
                        NPCMeet.attrOperatorRandom(0, player, attrChange, attrNames, false);
                        player.negPunish();
                        continue;
                    }
                } else {
                    System.out.println(lang.getString("main_6"));
                    secondScanner = new Scanner(System.in);
                    shouldMurMur = true;
                    Murmur.createMurmur(player);
                    String trans = secondScanner.next();
                    try {
                        secondTypeIn = Integer.parseInt(trans);
                    } catch (NumberFormatException e) {
                        System.out.println(illegalMove);
                        Thread.sleep(1000L);
                        continue;
                    }
                    shouldMurMur = false;
                    int shouldEat = secondTypeIn;
                    if (secondTypeIn > player.attrNum[4]) {
                        secondTypeIn = player.attrNum[4] + 1;
                        shouldEat = player.attrNum[4];
                    }
                    int delta = 0;
                    for (int i = 1; i <= shouldEat; ++i) {
                        delta += ra.nextInt(2) + 2;
                    }
                    attrChange = new int[][]{{0, delta, 0, 0, -secondTypeIn}, {}};
                    NPCMeet.attrOperatorRandom(0, player, attrChange, attrNames, false);
                    player.negPunish();
                    continue;
                }
            }
            if (lang.getString("test").equals(player.player)) {
                player.visible=false;
            }
            Thread.sleep(500L);
            if (player.floor > 150) {
                System.out.println(lang.getString("main_7_1") + key[8] + lang.getString("main_7_2"));
            }
            if (target != 1) {
                Display.printUI(player);
            }
            NPCMeet.onMeet(player,target);
            player.ifStarve();
            if (player.attrNum[0] <= 0) {
                System.out.println(lang.getString("main_9"));
                Thread.sleep(1500L);
                break;
            }
        }
    }

    public static void useSave(Player player) {
        ResourceBundle lang = player.langOperator.getLang();
        System.out.println(lang.getString("choose_save"));
        Scanner scanner = new Scanner(System.in);
        String gameName = scanner.next();
        if (gameName.equals("t") || gameName.equals("T")) {
            System.out.println(lang.getString("save_name"));
            Scanner scanner1 = new Scanner(System.in);
            player.gameName = scanner1.next();
        } else {
            player.gameName = gameName;
            Read read = new Read();
            int exist = read.readXML("\\" + gameName + ".xml", lang);
            if (exist > 0) {
                lang = read.lang;
                player.attrNum = read.attrNum;
                player.playerType = read.playerType;
                if ("1".equals(player.playerType)) {
                    player.player = lang.getString("jack");
                } else if ("2".equals(player.playerType)) {
                    player.player = lang.getString("mike");
                } else if ("3".equals(player.playerType)) {
                    player.player = lang.getString("steven");
                } else {
                    player.player = lang.getString("player");
                }
                player.floor = read.floor;
                player.packageLimit = read.packageLimit;
                player.maxHealth = read.maxHealth;
                player.maxEnergy = read.maxEnergy;
                player.starve = read.starve;
                player.visible = read.visible;
                player.canInvisible = read.canInvisible;
                player.ifContinue = read.ifContinue;
                player.goUp = read.goUp;
                player.originalWeight = read.originalWeight;
                player.weight = read.weight;
                player.weightOperator = read.weightOperator;
                needNew = false;
                player.map = read.map;
                player.playP = read.playP;
                player.tailorFright = read.tailorFright;
            } else {
                useSave(player);
            }
        }

    }
}