package village;

import Listeners.Exchange_Listener;
import Listeners.IceCream_Listener;
import Listeners.PlayerJoin;
import Listeners.Restaurant_Listener;
import lydark.api.Lydark_API;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import util.Item;
import util.Runner;
import util.Shop;

import java.io.File;

public final class Village extends JavaPlugin {

    PluginDescriptionFile pdffile = getDescription();
    public String rutaConfig;

    public String version = "§f"+pdffile.getVersion();
    private final Lydark_API lydark = (Lydark_API) Bukkit.getServer().getPluginManager().getPlugin("Lydark_API");


    public void onEnable() {
        registerConfig();
        registerEvents();
        registerCommands();
        new Item(this);
        new Shop(this);

        Runner runner = new Runner(this, 36000);
        runner.executeRunner();

        Bukkit.getConsoleSender().sendMessage("§aEl plugin §fVillage Core §aha sido activado!");
        Bukkit.getConsoleSender().sendMessage("§aVersion: "+version);
    }


    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§cEl plugin §fVillage Core §cha sido desactivado!");
        Bukkit.getConsoleSender().sendMessage("§aVersion: "+version);
        saveConfig();
    }


    public void registerCommands(){
        this.getCommand("shop").setExecutor(new Command_Shop(this));
        this.getCommand("vilcoins").setExecutor(new VilCoins_Command(this));
        this.getCommand("npctalk").setExecutor(new NpcTalk_Command(this));
    }


    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new IceCream_Listener(this), this);
        pm.registerEvents(new Restaurant_Listener(this), this);
        pm.registerEvents(new Exchange_Listener(this), this);
        pm.registerEvents(new PlayerJoin(), this);
    }


    //CONFIG.YML
    public void registerConfig() {
        File config = new File(this.getDataFolder(),"config.yml");
        rutaConfig = config.getPath();
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
    //CONFIG.YML


    //ECONOMIA

    public double getVilCoins(OfflinePlayer jugador){

        double dinero;
        Document data = lydark.mongo.findPlayer(jugador);

        if(data != null && data.containsKey("vilcoins")) {
            dinero = data.getDouble("vilcoins");
        }
        else {
            dinero = 0;
        }

        return dinero;
    }

    public void addVilCoins(OfflinePlayer jugador, double amount) {

        Document data = lydark.mongo.findPlayer(jugador);

        if(data != null && data.containsKey("vilcoins")) {
            lydark.mongo.updateDouble(jugador, "vilcoins", data.getDouble("vilcoins")+amount);
        }else {
            lydark.mongo.updateDouble(jugador, "vilcoins", amount);
        }

    }

    public void removeVilCoins(OfflinePlayer jugador, double amount) {

        Document data = lydark.mongo.findPlayer(jugador);

        if(data != null && data.containsKey("vilcoins")) {

            double a = data.getDouble("vilcoins");

            if(a <= amount) {

                if(!(a == 0)) {
                    lydark.mongo.updateDouble(jugador, "vilcoins", 0);
                }

            }else {
                lydark.mongo.updateDouble(jugador, "vilcoins", a-amount);
            }
        }
    }


    public boolean compraVilCoins (OfflinePlayer jugador, double price){

        if(getVilCoins(jugador) >= price){

            removeVilCoins(jugador, price);
            if(jugador.isOnline()){
                jugador.getPlayer().sendMessage(lydark.chat.wallet+" §fSe han removido §a"+price+" §3§lVil§e§lCoins§f de tu cuenta.");
            }
            return true;

        }else{
            if(jugador.isOnline()){
                jugador.getPlayer().sendMessage(lydark.chat.wallet+" §cNo tienes dinero suficiente.");
            }
            return false;
        }

    }

}
