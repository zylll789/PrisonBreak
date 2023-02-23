package run;

import bin.entity.Player;
import bin.event.Welcome;
import bin.game.ConnectGame;
import bin.game.Map;
import bin.game.SelfGame;
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
    public static ResourceBundle lang = US;

    public static String[] eventName;
    public static String[] attrNames;

    public static boolean shouldMurMur = true;

    public static Random ra = new Random();
    public static String stars = "**********************************************";
    public static String illegalMove = "\n" + stars + "\n" + lang.getString("unsupported_move") + "\n" + stars + "\n";
    public static boolean needNew = true;

    /*
     *  TODO:
     *   4 8 6 e h i q s win * langT
     *   0 1 2 3 4 5 6 7 8   9  10
     */
    public static String[] key = new String[]{"4", "6", "8", "e", "h", "i", "q", "s", "win", "*", "2"};

    public PrisonBreakDebug() {
    }

    public static boolean isMore(Player player) {
        Read read = new Read();
        boolean more = read.readData();
        if (more) {
            key = read.key;
            if ("1".equals(key[10])) {
                player.langOperator.setLang(CN);
            } else {
                player.langOperator.setLang(US);
            }
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        Player customPlayer = Player.createCustomPlayer();
        boolean more = isMore(customPlayer);
        if(!more){
            customPlayer.langOperator.setLang(customPlayer.langOperator.language(key));
            Save.writeData(key);
        }
        customPlayer.langOperator.transLang();
        Welcome.beforeWelcome(customPlayer);
        Welcome.createWelcome(customPlayer);
        chooseGameType(customPlayer);
    }

    private static void chooseGameType(Player customPlayer) throws Exception {
        while (true){
            System.out.println("输入1单人，输入2联机");
            Scanner scanner = new Scanner(System.in);
            String typeIn = scanner.next();
            if("1".equals(typeIn)){
                SelfGame.useSave(customPlayer);
                if(needNew){
                    customPlayer.loadPlayer();
                    customPlayer.map= Map.newMap();
                }
                customPlayer.langOperator.transLang();
                SelfGame.createSelfGame(customPlayer);
                break;
            }  else if("2".equals(typeIn)){
                customPlayer.loadPlayer();
                customPlayer.map= Map.newMap();
                customPlayer.langOperator.transLang();
                ConnectGame.createConnectGame(customPlayer);
                break;
            } else {
                System.out.println(illegalMove);
                Thread.sleep(1000L);
            }
        }
    }
}
