package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.lydark.api.chat.ChatUtil;
import org.lydark.api.commands.Command;
import org.lydark.api.commands.CommandContext;
import shop_gui.IceCream_Shop;
import shop_gui.Restaurant_Shop;
import village.Village;

import java.util.Random;

public class Command_Shop {

    @Command(name = "shop", permission = "lydark.admin")
    public boolean shopCommand(CommandContext context){

        if(!(context.getSender() instanceof Player)){
            context.getSender().sendMessage(ChatUtil.prefix() +" §cNo puedes ejecutar comandos desde la consola");
            return true;
        }

        if(context.getArgs().length == 0){
            return false;
        }

        Player p = (Player) context.getSender();

        if(context.getArg(0).equalsIgnoreCase("icecream")){

            Random r = new Random();
            int numero = r.nextInt(5);

            if(numero == 0){
                context.getSender().sendMessage(" §7[§eHeladero§7] §aCompra, compra!");
            }else if(numero == 1){
                context.getSender().sendMessage(" §7[§eHeladero§7] §aBienvenido a la mejor tienda de helados de la ciudad!");
            }else if(numero == 2) {
                context.getSender().sendMessage(" §7[§eHeladero§7] §aBienvenido, forastero!");
            }else if(numero == 3){
                context.getSender().sendMessage(" §7[§eHeladero§7] §aDinero, dinero, dinero");
            }else {
                context.getSender().sendMessage(" §7[§eHeladero§7] §a¿Te gustan los helados?");
            }

            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
            wait(p, "icecream");

            return true;
        }
        else if(context.getArg(0).equalsIgnoreCase("restaurant")){

            Random r = new Random();
            int numero = r.nextInt(5);

            if(numero == 0){
                context.getSender().sendMessage(" §7[§eRestaurante§7] §aCompra, compra!");
            }else if(numero == 1){
                context.getSender().sendMessage(" §7[§eRestaurante§7] §aBienvenido a el mejor restaurante de la ciudad!");
            }else if(numero == 2) {
                context.getSender().sendMessage(" §7[§eRestaurante§7] §aBienvenido, forastero!");
            }else if(numero == 3){
                context.getSender().sendMessage(" §7[§eRestaurante§7] §aDinero, dinero, dinero");
            }else {
                context.getSender().sendMessage(" §7[§eRestaurante§7] §a¿Te gusta la comida?");
            }
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
            wait(p, "restaurant");

            return true;
        }

        return false;
    }

    private void wait(Player jugador, String shop) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Village.getInstance(), () -> {
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
