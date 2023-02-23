package bin.event;

import bin.entity.Player;
import bin.util.LangOperator;
import bin.util.Save;

import java.util.ResourceBundle;
import java.util.Scanner;

import static run.PrisonBreakDebug.illegalMove;

public class Menu {

    public static void createMenu(Player player, String[] key) throws Exception {
        LangOperator langOperator = player.langOperator;
        System.out.println(langOperator.getLang().getString("menu"));
        Scanner scanner = new Scanner(System.in);
        String typeIn = scanner.next();
        System.out.println();
        if ("1".equals(typeIn)) {
            Helper.createHelper(player);
            createMenu(player, key);
        } else if ("2".equals(typeIn)) {
            langOperator.setLang(langOperator.language(key));
            player.langOperator = langOperator;
            langOperator.transLang();
            createMenu(player, key);
        } else if ("3".equals(typeIn)) {
            exchangeTypeIn(player.langOperator.getLang(), key);
            createMenu(player, key);
        }
    }

    private static int exchangeTypeIn(ResourceBundle lang, String[] key) throws InterruptedException {
        System.out.println();
        for (int i = 1; i <= 10; ++i) {
            System.out.println(lang.getString("exchangeType_" + i) + key[i - 1]);
        }
        System.out.println(lang.getString("make_change"));
        System.out.println(lang.getString("type_in") + key[7] + lang.getString("save_change"));
        Scanner scanner = new Scanner(System.in);
        String typeIn = scanner.next();
        if (typeIn.equals(key[7])) {
            Save.writeData(key);
            return 1;
        }
        int choose;
        try {
            choose = Integer.parseInt(typeIn);
        } catch (NumberFormatException e) {
            System.out.println(illegalMove);
            Thread.sleep(1000L);
            return exchangeTypeIn(lang, key);
        }
        if (choose < 1 || choose > 10) {
            System.out.println(illegalMove);
            Thread.sleep(1000L);
        } else {
            System.out.println(lang.getString("type_in_change"));
            String secondTypeIn = scanner.next();
            key[choose - 1] = secondTypeIn;
        }
        return exchangeTypeIn(lang, key);
    }

}
