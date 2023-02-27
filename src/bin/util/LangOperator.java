package bin.util;

import bin.entity.Player;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LangOperator {

    static String locate = "lib.lang.lang";
    static Locale cn = Locale.CHINA;
    static Locale us = Locale.US;
    public static ResourceBundle CN = ResourceBundle.getBundle(locate, cn);
    public static ResourceBundle US = ResourceBundle.getBundle(locate, us);

    ResourceBundle lang;

    Player player;

    public String stars = "**********************************************";
    public String illegalMove;
    public String[] eventName;
    public String[] attrNames;

    public ResourceBundle getLang() {
        return lang;
    }

    public void setLang(ResourceBundle lang) {
        this.lang = lang;
    }

    public LangOperator(ResourceBundle lang){
        this.lang=lang;
    }

    public void setPlayer(Player player){
        this.player=player;
    }

    public ResourceBundle language(String[] key) {
        ResourceBundle lang = this.getLang();
        System.out.println(lang.getString("choose_lang") + "\n" + lang.getString("chinese") + "\n" + lang.getString("english") + "\n");
        Scanner scanner = new Scanner(System.in);
        String langType = scanner.next();
        key[10] = langType;
        Save.writeData(key);
        if ("1".equals(langType)) {
            return CN;
        } else if ("2".equals(langType)) {
            return US;
        } else {
            System.out.println(this.getLang().getString("error_lang") + "\n");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return language(key);
    }

    public void transLang() {
        ResourceBundle lang = this.getLang();
        attrNames = new String[]{lang.getString("health"), lang.getString("energy"), lang.getString("weapon"), lang.getString("money"), lang.getString("food")};
        illegalMove = "\n" + stars + "\n" + lang.getString("unsupported_move") + "\n" + stars + "\n";
        if ("1".equals(player.playerType)) {
            player.player = lang.getString("jack");
        } else if ("2".equals(player.playerType)) {
            player.player = lang.getString("mike");
        } else if ("3".equals(player.playerType)) {
            player.player = lang.getString("steven");
        } else if ("zylll".equals(player.playerType)) {
            player.player = lang.getString("test");
        } else {
            player.player = lang.getString("player");
        }
        eventName = new String[]{player.player, lang.getString("space8"), lang.getString("guards"), lang.getString("chemists"), lang.getString("priest"), lang.getString("inmate"), lang.getString("cook"), lang.getString("drunkard"), lang.getString("gamblers"), lang.getString("magician"), lang.getString("vampires"), lang.getString("tailor")};
    }

}
