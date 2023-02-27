package bin.event;

import bin.entity.Player;

import static run.PrisonBreakDebug.ra;

public class Murmur {

    public static boolean shouldMurMur = true;

    @SuppressWarnings("BusyWait")
    public static void createMurmur(Player player) {
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
                System.out.println(player.langOperator.getLang().getString("murmur") + player.langOperator.getLang().getString("murmur_" + chance));
            }
        });
        thread.start();
    }

}
