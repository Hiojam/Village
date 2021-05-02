package util;

import lydark.api.api.Lydark_API;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitScheduler;
import village.Village;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    int taskID;
    long ticks;
    private final Village plugin;

    private final Lydark_API lydark = (Lydark_API) Bukkit.getServer().getPluginManager().getPlugin("Lydark_API");

    public Runner(Village plugin, long ticks) {
        this.plugin = plugin;
        this.ticks = ticks;
    }

    public void executeRunner() {
        BukkitScheduler sh = Bukkit.getServer().getScheduler();
        taskID = sh.scheduleSyncRepeatingTask(plugin, this::realizarAccion,0L,ticks);
    }

    public void realizarAccion() {
        FileConfiguration config = plugin.getConfig();

        ConfigurationSection sec = config.getConfigurationSection("Places");
        List<String> places = new ArrayList<>(sec.getKeys(false));
        OfflinePlayer alcalde = Bukkit.getOfflinePlayer("RoxxionYT");
        double total = 0;

        for (String place : places) {
            if (config.contains("Places." + place + ".recent-earnings")) {
                double amount = config.getDouble("Places." + place + ".recent-earnings");
                double ochentaporciento = (amount * 80) / 100;
                double veinteporciento = (amount * 20) / 100;
                total = total+veinteporciento;

                OfflinePlayer owner = Bukkit.getOfflinePlayer(config.getString("Places." + place + ".owner"));
                if (owner.isOnline()) {
                    owner.getPlayer().sendMessage(lydark.Chat.wallet + " §fHas ganado §a" + ochentaporciento + " §3§lVil§e§lCoins §fde tu negocio §3" + place + "§f.");
                    owner.getPlayer().sendMessage(lydark.Chat.wallet+ " §fHas pagado la comision de tu negocio §3"+place+"§f y se te han removido §a"+veinteporciento+" §3§lVil§e§lCoins.");
                }

                plugin.addVilCoins(owner, ochentaporciento);
                plugin.addVilCoins(alcalde, veinteporciento);
                config.set("Places." + place + ".recent-earnings", null);
            }
        }

        if(alcalde.isOnline() && total != 0){
            alcalde.getPlayer().sendMessage(lydark.Chat.wallet + " §fHas ganado §a" + total + " §3§lVil§e§lCoins §fpor ser alcalde.");
        }
        plugin.saveConfig();
    }
}
