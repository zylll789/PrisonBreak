package bin.event;

import bin.entity.Player;

import java.util.ResourceBundle;
import java.util.Scanner;

@SuppressWarnings("BusyWait")
public class Helper {

    public static void createHelper(Player player) throws Exception {
        ResourceBundle lang = player.langOperator.getLang();
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
                    System.out.print("weight[" + i + "]=" + player.weight[i] + "   ");
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
                System.out.println(lang.getString("help_you_have") + (20 + player.weightOperator[7]) + lang.getString("help_chance"));
            }
        }
        System.out.println("\n" + lang.getString("help_16"));
        Thread.sleep(1000L);
    }


}
