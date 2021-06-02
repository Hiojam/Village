package util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitScheduler;
import org.lydark.api.Api;
import org.lydark.api.chat.ChatUtil;
import org.lydark.api.data.players.IStats;
import org.lydark.api.data.players.LydarkPlayer;
import village.Village;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    int taskID;
    long ticks;
    private final Village plugin;

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

        LydarkPlayer lyalcalde = Api.getInstance().getPlayers().getOrCreatePlayer(alcalde.getName());
        IStats alcaldeStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyalcalde);

        double total = 0;

        for (String place : places) {
            if (config.contains("Places." + place + ".recent-earnings")) {
                double amount = config.getDouble("Places." + place + ".recent-earnings");
                double ochentaporciento = (amount * 80) / 100;
                double veinteporciento = (amount * 20) / 100;
                total = total+veinteporciento;

                OfflinePlayer owner = Bukkit.getOfflinePlayer(config.getString("Places." + place + ".owner"));
                LydarkPlayer lyowner = Api.getInstance().getPlayers().getOrCreatePlayer(owner.getName());
                IStats ownerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyowner);

                ownerStats.setCoins(ownerStats.getCoins()+ochentaporciento);
                alcaldeStats.setCoins(alcaldeStats.getCoins()+veinteporciento);

                if (owner.isOnline()) {
                    owner.getPlayer().sendMessage(ChatUtil.wallet() + " §fHas ganado §a" + ochentaporciento + " §3§lVil§e§lCoins §fde tu negocio §3" + place + "§f.");
                    owner.getPlayer().sendMessage(ChatUtil.wallet() + " §fHas pagado la comision de tu negocio §3"+place+"§f y se te han removido §a"+veinteporciento+" §3§lVil§e§lCoins.");
                }else{
                    Api.getInstance().getPlayers().unloadPlayer(lyowner.getName());
                }

                config.set("Places." + place + ".recent-earnings", null);
            }
        }

        if(alcalde.isOnline() && total != 0){
            alcalde.getPlayer().sendMessage(ChatUtil.wallet() + " §fHas ganado §a" + total + " §3§lVil§e§lCoins §fpor ser alcalde.");
        }
        if(!alcalde.isOnline()){
            Api.getInstance().getPlayers().unloadPlayer(alcalde.getName());
        }
        plugin.saveConfig();
    }
}
