package util;

import org.bukkit.configuration.file.FileConfiguration;
import village.Village;

public class Shop {

    private static Village plugin;

    public Shop(Village plugin){
        Shop.plugin = plugin;
    }

    public static void payOwner(String place, double amount){
        FileConfiguration config = plugin.getConfig();

        config.set("Places."+place+".total-earnings", config.getDouble("Places."+place+".total-earnings")+amount);
        if(!config.contains("Places."+place+".recent-earnings")){
            config.set("Places."+place+".recent-earnings", amount);
        }else{
            config.set("Places."+place+".recent-earnings", config.getDouble("Places."+place+".recent-earnings")+amount);
        }
    }
}
