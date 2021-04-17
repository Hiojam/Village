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
import shop_gui.Restaurant_Shop;
import util.Item;
import util.Shop;
import village.Village;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Restaurant_Listener implements Listener {

    private final Village plugin;

    public Restaurant_Listener(Village plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void Restaurant_Shop(InventoryClickEvent event){
        if(event.getClickedInventory() == null){ return; }

        if(event.getView().getTopInventory().getHolder() instanceof Restaurant_Shop){
            event.setCancelled(true);
            if(event.getClickedInventory().getType() == InventoryType.PLAYER){ return; }
            if(event.getCurrentItem() == null) { return; }
            if(event.getCurrentItem().getType() == Material.AIR) { return; }
            Player jugador = (Player) event.getWhoClicked();

            if(event.getSlot() == 11) {
                jugador.closeInventory();
                double price = 20;

                if(plugin.compraVilCoins(jugador, price)){
                    jugador.getInventory().addItem(Item.itemGenerator("Restaurant", "Hamburguer", null));
                    Shop.payOwner("Restaurant_Shop", price);
                }
            }

            else if(event.getSlot() == 13){

                jugador.closeInventory();
                double price = 15;

                if(plugin.compraVilCoins(jugador, price)){
                    jugador.getInventory().addItem(Item.itemGenerator("Restaurant", "Fries", null));
                    Shop.payOwner("Restaurant_Shop", price);
                }
            }

            else if(event.getSlot() == 15){

                jugador.closeInventory();
                double price = 30;

                if(plugin.compraVilCoins(jugador, price)){
                    jugador.getInventory().addItem(Item.itemGenerator("Restaurant", "Pizza", null));
                    Shop.payOwner("Restaurant_Shop", price);
                }
            }

            else if(event.getSlot() == 29){

                jugador.closeInventory();
                double price = 15;

                if(plugin.compraVilCoins(jugador, price)){
                    jugador.getInventory().addItem(Item.itemGenerator("Restaurant", "Donut", null));
                    Shop.payOwner("Restaurant_Shop", price);
                }
            }

            else if(event.getSlot() == 31){

                jugador.closeInventory();
                double price = 15;

                if(plugin.compraVilCoins(jugador, price)){
                    jugador.getInventory().addItem(Item.itemGenerator("Restaurant", "Muffin", null));
                    Shop.payOwner("Restaurant_Shop", price);
                }

            }

            else if(event.getSlot() == 33) {

                jugador.closeInventory();
                double price = 12;

                if (plugin.compraVilCoins(jugador, price)) {
                    jugador.getInventory().addItem(Item.itemGenerator("Restaurant", "Drink", null));
                    Shop.payOwner("Restaurant_Shop", price);
                }
            }
        }
    }

    //INTERACCION
    @EventHandler
    public void onInteract (PlayerInteractEvent event){
        Player jugador = event.getPlayer();

        List<ItemStack> items = new ArrayList<>();
        items.add(Item.itemGenerator("Restaurant", "Hamburguer", null));
        items.add(Item.itemGenerator("Restaurant", "Fries", null));
        items.add(Item.itemGenerator("Restaurant", "Pizza", null));
        items.add(Item.itemGenerator("Restaurant", "Donut", null));
        items.add(Item.itemGenerator("Restaurant", "Muffin", null));
        items.add(Item.itemGenerator("Restaurant", "Drink", null));

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
