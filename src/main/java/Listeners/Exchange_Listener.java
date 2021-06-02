package Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.lydark.api.Api;
import org.lydark.api.chat.ChatUtil;
import org.lydark.api.data.players.IStats;
import org.lydark.api.data.players.LydarkPlayer;
import shop_gui.Exchange_Shop;
import util.Shop;

public class Exchange_Listener implements Listener {

    @EventHandler
    public void IceCream_Shop(InventoryClickEvent event){
        if(event.getClickedInventory() == null){ return; }

        if(event.getView().getTopInventory().getHolder() instanceof Exchange_Shop){
            event.setCancelled(true);
            if(event.getClickedInventory().getType() == InventoryType.PLAYER){ return; }
            if(event.getCurrentItem() == null) { return; }
            if(event.getCurrentItem().getType() == Material.AIR) { return; }

            Player p = (Player) event.getWhoClicked();
            LydarkPlayer lyplayer = Api.getInstance().getPlayers().getPlayer(p.getName());

            if(event.getSlot() == 11) {
                p.closeInventory();
                double price = 50;
                double moneyamount = 1000;

                if(!buyCoins(lyplayer, p, price, moneyamount)){
                    if(event.isLeftClick()){
                        if(lyplayer.getLyCoins() >= price){
                            lyplayer.setLyCoins(lyplayer.getLyCoins()-price);
                            IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                            playerStats.setCoins(playerStats.getCoins()+moneyamount);
                            p.sendMessage(ChatUtil.wallet()+ "§fSe te han añadido §a"+moneyamount+" §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", moneyamount/2);
                            return;
                        }
                    }else{
                        if(lyplayer.getDarkCoins() >= price){
                            lyplayer.setDarkCoins(lyplayer.getDarkCoins()-price);
                            IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                            playerStats.setCoins(playerStats.getCoins()+moneyamount);
                            p.sendMessage(ChatUtil.wallet()+ "§fSe te han añadido §a"+moneyamount+" §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", moneyamount/2);
                            return;
                        }
                    }
                    p.sendMessage(ChatUtil.wallet()+ "§cNo tienes dinero suficiente.");
                }else{
                    buyCoins(lyplayer, p, price, moneyamount);
                }
            }

            else if(event.getSlot() == 13){

                p.closeInventory();
                double price = 250;
                double moneyamount = 5000;

                if(!buyCoins(lyplayer, p, price, moneyamount)){
                    if(event.isLeftClick()){
                        if(lyplayer.getLyCoins() >= price){
                            lyplayer.setLyCoins(lyplayer.getLyCoins()-price);
                            IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                            playerStats.setCoins(playerStats.getCoins()+moneyamount);
                            p.sendMessage(ChatUtil.wallet()+ "§fSe te han añadido §a"+moneyamount+" §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", moneyamount/2);
                            return;
                        }
                    }else{
                        if(lyplayer.getDarkCoins() >= price){
                            lyplayer.setDarkCoins(lyplayer.getDarkCoins()-price);
                            IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                            playerStats.setCoins(playerStats.getCoins()+moneyamount);
                            p.sendMessage(ChatUtil.wallet()+ "§fSe te han añadido §a"+moneyamount+" §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", moneyamount/2);
                            return;
                        }
                    }
                    p.sendMessage(ChatUtil.wallet()+ "§cNo tienes dinero suficiente.");
                }else{
                    buyCoins(lyplayer, p, price, moneyamount);
                }

            }

            else if(event.getSlot() == 15){

                p.closeInventory();
                double price = 1250;
                double moneyamount = 25000;

                if(!buyCoins(lyplayer, p, price, moneyamount)){
                    if(event.isLeftClick()){
                        if(lyplayer.getLyCoins() >= price){
                            lyplayer.setLyCoins(lyplayer.getLyCoins()-price);
                            IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                            playerStats.setCoins(playerStats.getCoins()+moneyamount);
                            p.sendMessage(ChatUtil.wallet()+ "§fSe te han añadido §a"+moneyamount+" §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", moneyamount/2);
                            return;
                        }
                    }else{
                        if(lyplayer.getDarkCoins() >= price){
                            lyplayer.setDarkCoins(lyplayer.getDarkCoins()-price);
                            IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                            playerStats.setCoins(playerStats.getCoins()+moneyamount);
                            p.sendMessage(ChatUtil.wallet()+ "§fSe te han añadido §a"+moneyamount+" §3§lVil§e§lCoins§f a tu cuenta.");
                            Shop.payOwner("Bank", moneyamount/2);
                            return;
                        }
                    }
                    p.sendMessage(ChatUtil.wallet()+ "§cNo tienes dinero suficiente.");
                }else{
                    buyCoins(lyplayer, p, price, moneyamount);
                }

            }
        }
    }


    private boolean buyCoins(LydarkPlayer lyplayer, Player p, double price, double moneyamount){

        if(lyplayer.getOption("useLyCoins") && !lyplayer.getOption("useDarkCoins")){
            if(lyplayer.getLyCoins() >= price){
                lyplayer.setLyCoins(lyplayer.getLyCoins()-price);
                IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                playerStats.setCoins(playerStats.getCoins()+moneyamount);
                p.sendMessage(ChatUtil.wallet()+ "§fSe te han añadido §a"+moneyamount+" §3§lVil§e§lCoins§f a tu cuenta.");
                Shop.payOwner("Bank", moneyamount/2);
                return true;
            }
            p.sendMessage(ChatUtil.wallet()+ "§cNo tienes dinero suficiente.");
            return true;
        }
        else if(lyplayer.getOption("useDarkCoins") && !lyplayer.getOption("useLyCoins")){
            if(lyplayer.getDarkCoins() >= price){
                lyplayer.setDarkCoins(lyplayer.getDarkCoins()-price);
                IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                playerStats.setCoins(playerStats.getCoins()+moneyamount);
                p.sendMessage(ChatUtil.wallet()+ "§fSe te han añadido §a"+moneyamount+" §3§lVil§e§lCoins§f a tu cuenta.");
                Shop.payOwner("Bank", moneyamount/2);
                return true;
            }
            p.sendMessage(ChatUtil.wallet()+ "§cNo tienes dinero suficiente.");
            return true;
        }
        else{
            return false;
        }
    }
}
