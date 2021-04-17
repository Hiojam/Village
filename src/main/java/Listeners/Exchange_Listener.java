package Listeners;

import lydark.api.api.Lydark_API;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import shop_gui.Exchange_Shop;
import util.Shop;
import village.Village;

public class Exchange_Listener implements Listener {

    private final Village plugin;
    private final Lydark_API lydark = (Lydark_API) Bukkit.getServer().getPluginManager().getPlugin("Lydark_API");

    public Exchange_Listener(Village plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void IceCream_Shop(InventoryClickEvent event){
        if(event.getClickedInventory() == null){ return; }

        if(event.getView().getTopInventory().getHolder() instanceof Exchange_Shop){
            event.setCancelled(true);
            if(event.getClickedInventory().getType() == InventoryType.PLAYER){ return; }
            if(event.getCurrentItem() == null) { return; }
            if(event.getCurrentItem().getType() == Material.AIR) { return; }
            Player jugador = (Player) event.getWhoClicked();

            Document data = lydark.MongoDB.findPlayer(jugador);

            event.setCancelled(true);

            if(event.getSlot() == 11) {
                jugador.closeInventory();
                double price = 50;

                if(data != null && data.getString("coins").equals("lycoins")){
                    if(lydark.Economy.compraLyCoins(jugador, price)){
                        plugin.addVilCoins(jugador, 1000);
                        jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a1000 §3§lVil§e§lCoins§f a tu cuenta.");
                        Shop.payOwner("Bank", 500);
                    }
                }
                else if(data != null && data.getString("coins").equals("darkcoins")){
                    if(lydark.Economy.compraDarkCoins(jugador, price)){
                        plugin.addVilCoins(jugador, 1000);
                        jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a1000 §3§lVil§e§lCoins§f a tu cuenta.");
                        Shop.payOwner("Bank", 500);
                    }
                }
                else{

                    if(event.isLeftClick()){
                        if(lydark.Economy.compraLyCoins(jugador, price)){
                            plugin.addVilCoins(jugador, 1000);
                            jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a1000 §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", 500);
                        }
                    }else{
                        if(lydark.Economy.compraDarkCoins(jugador, price)){
                            plugin.addVilCoins(jugador, 1000);
                            jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a1000 §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", 500);
                        }
                    }

                }
            }

            else if(event.getSlot() == 13){

                jugador.closeInventory();
                double price = 250;

                if(data != null && data.getString("coins").equals("lycoins")){
                    if(lydark.Economy.compraLyCoins(jugador, price)){
                        plugin.addVilCoins(jugador, 5000);
                        jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a5000 §3§lVil§e§lCoins§f a tu cuenta.");
                        Shop.payOwner("Bank", 2500);
                    }
                }
                else if(data != null && data.getString("coins").equals("darkcoins")){
                    if(lydark.Economy.compraDarkCoins(jugador, price)){
                        plugin.addVilCoins(jugador, 5000);
                        jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a5000 §3§lVil§e§lCoins§f a tu cuenta.");
                        Shop.payOwner("Bank", 2500);
                    }
                }
                else{

                    if(event.isLeftClick()){
                        if(lydark.Economy.compraLyCoins(jugador, price)){
                            plugin.addVilCoins(jugador, 5000);
                            jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a5000 §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", 2500);
                        }
                    }else{
                        if(lydark.Economy.compraDarkCoins(jugador, price)){
                            plugin.addVilCoins(jugador, 5000);
                            jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a5000 §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", 2500);
                        }
                    }

                }

            }

            else if(event.getSlot() == 15){

                jugador.closeInventory();
                double price = 1250;

                if(data != null && data.getString("coins").equals("lycoins")){
                    if(lydark.Economy.compraLyCoins(jugador, price)){
                        plugin.addVilCoins(jugador, 25000);
                        jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a25000 §3§lVil§e§lCoins§f a tu cuenta.");
                        Shop.payOwner("Bank", 12500);
                    }
                }
                else if(data != null && data.getString("coins").equals("darkcoins")){
                    if(lydark.Economy.compraDarkCoins(jugador, price)){
                        plugin.addVilCoins(jugador, 25000);
                        jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a25000 §3§lVil§e§lCoins§f a tu cuenta.");
                        Shop.payOwner("Bank", 12500);
                    }
                }
                else{

                    if(event.isLeftClick()){
                        if(lydark.Economy.compraLyCoins(jugador, price)){
                            plugin.addVilCoins(jugador, 25000);
                            jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a25000 §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", 12500);
                        }
                    }else{
                        if(lydark.Economy.compraDarkCoins(jugador, price)){
                            plugin.addVilCoins(jugador, 25000);
                            jugador.sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a25000 §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", 12500);
                        }
                    }

                }

            }
        }
    }
}
