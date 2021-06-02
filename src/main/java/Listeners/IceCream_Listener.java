package Listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.lydark.api.Api;
import org.lydark.api.chat.ChatUtil;
import org.lydark.api.data.players.IStats;
import org.lydark.api.data.players.LydarkPlayer;
import shop_gui.IceCream_Shop;
import util.Item;
import util.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IceCream_Listener implements Listener {

    @EventHandler
    public void IceCream_Shop(InventoryClickEvent event){
        if(event.getClickedInventory() == null){ return; }

        if(event.getView().getTopInventory().getHolder() instanceof IceCream_Shop){
            event.setCancelled(true);
            if(event.getClickedInventory().getType() == InventoryType.PLAYER){ return; }
            if(event.getCurrentItem() == null) { return; }
            if(event.getCurrentItem().getType() == Material.AIR) { return; }

            Player p = (Player) event.getWhoClicked();
            LydarkPlayer lyplayer = Api.getInstance().getPlayers().getPlayer(p.getName());

            event.setCancelled(true);

            if(event.getSlot() == 11) {
                p.closeInventory();
                double price = 10;
                IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("IceCream", "Chocolate"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("IceCream_Shop", price);
                    return;
                }
                p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente.");
            }

            else if(event.getSlot() == 13){

                p.closeInventory();
                double price = 10;
                IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("IceCream", "Vainilla"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("IceCream_Shop", price);
                    return;
                }
                p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente.");
            }

            else if(event.getSlot() == 15) {

                p.closeInventory();
                double price = 10;
                IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("IceCream", "Strawberry"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("IceCream_Shop", price);
                    return;
                }
                p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente.");
            }
        }
    }

    //INTERACCION
    @EventHandler
    public void onInteract (PlayerInteractEvent event){
        Player jugador = event.getPlayer();

        List<ItemStack> items = new ArrayList<>();
        items.add(Item.itemGenerator("IceCream", "Chocolate"));
        items.add(Item.itemGenerator("IceCream", "Vainilla"));
        items.add(Item.itemGenerator("IceCream", "Strawberry"));

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) && jugador.getItemInHand() != null && items.contains(jugador.getItemInHand())){

            if(jugador.getItemInHand().getAmount() == 1){
                jugador.setItemInHand(null);
            }else{
                jugador.getItemInHand().setAmount(jugador.getItemInHand().getAmount() - 1);
            }
            jugador.playSound(jugador.getLocation(), Sound.EAT, 1f, 1f);

            Random r = new Random();
            int numero = r.nextInt(5);
            if(numero == 1){
                jugador.sendMessage(" §7[§bYo§7] §dYummy! §fEl mejor helado de todos!");
            }
            else if(numero == 2){
                jugador.sendMessage(" §7[§bYo§7] §fDefinitivamente quiero otro!");
            }
            else if(numero == 3){
                jugador.sendMessage(" §7[§bYo§7] §fNecesito helados infinitititititos!");
            }
            else if(numero == 4){
                jugador.sendMessage(" §7[§bYo§7] §fTa potente!");
            }
            else{
                jugador.sendMessage(" §7[§bYo§7] §fEste sabor me cautiva!");
            }
        }

    }

}
