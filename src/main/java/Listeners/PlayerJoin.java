package Listeners;

import lydark.api.api.Lydark_API;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final Lydark_API lydark = (Lydark_API) Bukkit.getServer().getPluginManager().getPlugin("Lydark_API");

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player jugador = event.getPlayer();
        Document data = lydark.MongoDB.findPlayer(jugador);

        if(data != null && !data.containsKey("vilcoins")){
            lydark.MongoDB.updateDouble(jugador, "vilcoins", 0);
        }
    }

}
