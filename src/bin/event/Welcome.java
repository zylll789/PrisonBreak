package bin.event;

import bin.entity.Player;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import static run.PrisonBreakDebug.*;

public class Welcome {

    public static void beforeWelcome(Player player) {
        System.out.println("\n");
        for (String s : Arrays.asList("welcome_commemorate", "continue")) {
            System.out.println(player.langOperator.getLang().getString(s));
        }
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        System.out.println("\n");
    }

    public static void introduce(Player player) {
        ResourceBundle lang = player.langOperator.getLang();
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 9; ++i) {
            if (i == 2) {
                System.out.println("\n" + lang.getString("welcome_intro_2_1") + key[2] + lang.getString("welcome_intro_2_2") + key[0] + lang.getString("welcome_intro_2_3") + key[1] + lang.getString("welcome_intro_2_4") + key[3] + lang.getString("welcome_intro_2_5") + key[4] + lang.getString("welcome_intro_2_6"));
                System.out.println(lang.getString("continue"));
                scanner.next();
                continue;
            }
            System.out.println("\n" + lang.getString("welcome_intro_" + i));
            System.out.println(lang.getString("continue"));
            scanner.next();
        }
        System.out.println("\n" + lang.getString("welcome_enjoy"));
        System.out.println(lang.getString("continue"));
        scanner.next();
        System.out.println();
    }

    //    public static void animateIntroduce() throws InterruptedException {
//        skip = false;
//        Thread thread = new Thread(() -> {
//            while (!skip) {
//                Scanner scanner = new Scanner(System.in);
//                scanner.next();
//                skipAnimation = true;
//                skip = true;
//            }
//        });
//        thread.start();
//        for (int i = 1; i <= 9; ++i) {
//            if (!skipAnimation) {
//                System.out.println("\n" + lang.getString("welcome_intro_" + i));
//                System.out.println(lang.getString("skip"));
//                Thread.sleep(1000L);
//            } else break;
//        }
//        if (!skipAnimation) {
//            System.out.println("\n" + lang.getString("welcome_enjoy"));
//            System.out.println(lang.getString("continue"));
//            Thread.sleep(1000L);
//        }
//        do {
//            Thread.sleep(100L);
//        } while (!skipAnimation);
//        skipAnimation = false;
//        skip = true;
//        System.out.println();
//    }

    public static void createWelcome(Player player) throws Exception {
        ResourceBundle lang = player.langOperator.getLang();
        System.out.println(lang.getString("welcome_play"));
        System.out.println(lang.getString("welcome_see_or_skip"));
        Scanner scanner = new Scanner(System.in);
        String typeIn = scanner.next();
        if ("1".equals(typeIn)) {
            introduce(player);
            //animateIntroduce();
            createWelcome(player);
        } else if ("2".equals(typeIn)) {
            System.out.println("\n" + lang.getString("game_start"));
            System.out.println(lang.getString("continue"));
            scanner.next();
            System.out.println("\n");
        } else if ("3".equals(typeIn)) {
            Menu.createMenu(player,key);
            createWelcome(player);
        } else if ("4".equals(typeIn)) {
            System.out.println("\n" + lang.getString("confirm_exit"));
            System.out.println(lang.getString("back"));
            typeIn += scanner.next();
            System.out.println();
            if ("41".equals(typeIn)) {
                System.out.println(lang.getString("wise_choose"));
                Thread.sleep(1000L);
                createWelcome(player);
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
            createWelcome(player);
        }
    }

}
