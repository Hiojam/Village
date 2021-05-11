package village;

import lydark.api.Lydark_API;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shop_gui.IceCream_Shop;
import shop_gui.Restaurant_Shop;

import java.util.Random;

public class Command_Shop implements CommandExecutor {

    private final Village plugin;
    private final Lydark_API lydark = (Lydark_API) Bukkit.getServer().getPluginManager().getPlugin("Lydark_API");

    public Command_Shop(Village plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        if(!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(lydark.chat.prefix+" §cNo puedes ejecutar comandos desde la consola");
            return false;
        }else {
            Player jugador = (Player) sender;

            if(jugador.isOp()){

                if(args.length > 0){

                    if(args[0].equalsIgnoreCase("icecream")){

                        Random r = new Random();
                        int numero = r.nextInt(5);

                        if(numero == 0){
                            jugador.sendMessage(" §7[§eHeladero§7] §aCompra, compra!");
                        }else if(numero == 1){
                            jugador.sendMessage(" §7[§eHeladero§7] §aBienvenido a la mejor tienda de helados de la ciudad!");
                        }else if(numero == 2) {
                            jugador.sendMessage(" §7[§eHeladero§7] §aBienvenido, forastero!");
                        }else if(numero == 3){
                            jugador.sendMessage(" §7[§eHeladero§7] §aDinero, dinero, dinero");
                        }else {
                            jugador.sendMessage(" §7[§eHeladero§7] §a¿Te gustan los helados?");
                        }
                        jugador.playSound(jugador.getLocation(), Sound.LEVEL_UP, 1, 1);
                        wait(jugador, "icecream");

                        return true;
                    }

                    else if(args[0].equalsIgnoreCase("restaurant")){

                        Random r = new Random();
                        int numero = r.nextInt(5);

                        if(numero == 0){
                            jugador.sendMessage(" §7[§eRestaurante§7] §aCompra, compra!");
                        }else if(numero == 1){
                            jugador.sendMessage(" §7[§eRestaurante§7] §aBienvenido a el mejor restaurante de la ciudad!");
                        }else if(numero == 2) {
                            jugador.sendMessage(" §7[§eRestaurante§7] §aBienvenido, forastero!");
                        }else if(numero == 3){
                            jugador.sendMessage(" §7[§eRestaurante§7] §aDinero, dinero, dinero");
                        }else {
                            jugador.sendMessage(" §7[§eRestaurante§7] §a¿Te gusta la comida?");
                        }
                        jugador.playSound(jugador.getLocation(), Sound.LEVEL_UP, 1, 1);
                        wait(jugador, "restaurant");

                        return true;
                    }

                    else{
                        jugador.sendMessage(lydark.chat.nopermission);
                        return false;
                    }

                }else{
                    jugador.sendMessage(lydark.chat.nopermission);
                    return false;
                }

            }else{
                jugador.sendMessage(lydark.chat.nopermission);
                return false;
            }
        }
    }

    public void wait(Player jugador, String shop) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if(shop.equals("icecream")){
                IceCream_Shop inv = new IceCream_Shop();
                jugador.openInventory(inv.getInventory());
            }
            else if(shop.equals("restaurant")){
                Restaurant_Shop inv = new Restaurant_Shop();
                jugador.openInventory(inv.getInventory());
            }
        }, 20); //(20 ticks = 1 second)
    }

}
