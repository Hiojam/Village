package village;

import Commands.Command_NpcTalk;
import Commands.Command_Shop;
import Commands.Command_VilCoins;
import Listeners.Exchange_Listener;
import Listeners.IceCream_Listener;
import Listeners.Restaurant_Listener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.lydark.api.Api;
import util.Item;
import util.Runner;
import util.Shop;

import java.io.File;

public final class Village extends JavaPlugin {

    PluginDescriptionFile pdffile = getDescription();
    public String rutaConfig;
    public String version = "§f"+pdffile.getVersion();

    private static Village instance;

    public void onEnable() {
        Api.getInstance().getGameModes().register(new VillageGameMode());
        instance = this;

        registerConfig();
        registerEvents();
        registerCommands();
        new Item(this);
        new Shop(this);

        Runner runner = new Runner(this, 36000);
        runner.executeRunner();

        Bukkit.getConsoleSender().sendMessage("§aEl plugin §fVillage §aha sido activado!");
        Bukkit.getConsoleSender().sendMessage("§aVersion: "+version);
    }


    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§cEl plugin §fVillage Core §cha sido desactivado!");
        Bukkit.getConsoleSender().sendMessage("§aVersion: "+version);
        saveConfig();
    }

    public static Village getInstance(){ return instance; }


    public void registerCommands(){
        Api.getCommandService().registerCommands(new Command_Shop());
        Api.getCommandService().registerCommands(new Command_NpcTalk());
        Api.getCommandService().registerCommands(new Command_VilCoins());
    }


    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new IceCream_Listener(), this);
        pm.registerEvents(new Restaurant_Listener(), this);
        pm.registerEvents(new Exchange_Listener(), this);
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

}
