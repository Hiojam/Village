package Listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
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
import shop_gui.Restaurant_Shop;
import util.Item;
import util.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Restaurant_Listener implements Listener {

    @EventHandler
    public void Restaurant_Shop(InventoryClickEvent event){
        if(event.getClickedInventory() == null){ return; }

        if(event.getView().getTopInventory().getHolder() instanceof Restaurant_Shop){
            event.setCancelled(true);
            if(event.getClickedInventory().getType() == InventoryType.PLAYER){ return; }
            if(event.getCurrentItem() == null) { return; }
            if(event.getCurrentItem().getType() == Material.AIR) { return; }

            Player p = (Player) event.getWhoClicked();
            LydarkPlayer lyplayer = Api.getInstance().getPlayers().getPlayer(p.getName());
            IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);

            if(event.getSlot() == 11) {
                p.closeInventory();
                double price = 20;

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("Restaurant", "Hamburguer"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("Restaurant_Shop", price);
                    return;
                }
                p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente.");
            }

            else if(event.getSlot() == 13){

                p.closeInventory();
                double price = 15;

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("Restaurant", "Fries"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("Restaurant_Shop", price);
                    return;
                }
                p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente.");
            }

            else if(event.getSlot() == 15){

                p.closeInventory();
                double price = 30;

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("Restaurant", "Pizza"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("Restaurant_Shop", price);
                    return;
                }
                p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente.");
            }

            else if(event.getSlot() == 29){

                p.closeInventory();
                double price = 15;

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("Restaurant", "Donut"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("Restaurant_Shop", price);
                    return;
                }
                p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente.");
            }

            else if(event.getSlot() == 31){

                p.closeInventory();
                double price = 15;

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("Restaurant", "Muffin"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("Restaurant_Shop", price);
                    return;
                }
                p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente.");

            }

            else if(event.getSlot() == 33) {

                p.closeInventory();
                double price = 12;

                if(playerStats.getCoins() >= price){
                    playerStats.setCoins(playerStats.getCoins()-price);
                    p.getInventory().addItem(Item.itemGenerator("Restaurant", "Drink"));
                    p.sendMessage(ChatUtil.wallet()+" §fSe te han removido §a" + price + " §3§lVil§e§lCoins§f de tu cuenta.");
                    Shop.payOwner("Restaurant_Shop", price);
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
        items.add(Item.itemGenerator("Restaurant", "Hamburguer"));
        items.add(Item.itemGenerator("Restaurant", "Fries"));
        items.add(Item.itemGenerator("Restaurant", "Pizza"));
        items.add(Item.itemGenerator("Restaurant", "Donut"));
        items.add(Item.itemGenerator("Restaurant", "Muffin"));
        items.add(Item.itemGenerator("Restaurant", "Drink"));

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
                jugador.sendMessage(" §7[§bYo§7] §dYummy! §fLa mejor comida de todas!");
            }
            else if(numero == 2){
                jugador.sendMessage(" §7[§bYo§7] §fDefinitivamente quiero otra comida!");
            }
            else if(numero == 3){
                jugador.sendMessage(" §7[§bYo§7] §fNecesito masmasmas!");
            }
            else if(numero == 4){
                jugador.sendMessage(" §7[§bYo§7] §fTa potente!");
            }
            else{
                jugador.sendMessage(" §7[§bYo§7] §fCHEF! DAME MAS!");
            }
        }

    }


}
