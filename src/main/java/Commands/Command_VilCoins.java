package Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.lydark.api.Api;
import org.lydark.api.chat.ChatUtil;
import org.lydark.api.commands.Command;
import org.lydark.api.commands.CommandContext;
import org.lydark.api.data.players.IStats;
import org.lydark.api.data.players.LydarkPlayer;
import shop_gui.Exchange_Shop;

public class Command_VilCoins {

    @Command(name = "vilcoins", permission = "lydark.admin")
    public boolean vilCoinsCommand(CommandContext context){

        if(context.getArgs().length == 0){
            if(!(context.getSender() instanceof Player)){
                return false;
            }
            LydarkPlayer lyplayer = Api.getInstance().getPlayers().getPlayer(context.getSender().getName());
            context.getSender().sendMessage(ChatUtil.wallet()+" §3§lVil§e§lCoins§f: §a"+lyplayer.getStats("village").getCoins());
            return true;
        }

        if (context.getArg(0).equalsIgnoreCase("exchange")) {
            if(!(context.getSender() instanceof Player)){
                return false;
            }
            Player p = (Player) context.getSender();
            p.sendMessage(ChatUtil.prefix() + " §fAbriendo el menu de intercambio..");
            Exchange_Shop inv = new Exchange_Shop(p);
            p.openInventory(inv.getInventory());

            p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1, 1);
            return true;

        } else if (context.getArg(0).equalsIgnoreCase("add")) {

            if (context.getArgs().length >= 3) {

                OfflinePlayer target = Bukkit.getOfflinePlayer(context.getArg(1));

                if (target != null && target.hasPlayedBefore()) {

                    try {

                        double amount = Double.parseDouble(context.getArg(2));
                        LydarkPlayer lytarget = Api.getInstance().getPlayers().getOrCreatePlayer(target.getName());

                        IStats targetStats = Api.getInstance().getGameModes().get("village").getStatsFor(lytarget);
                        targetStats.setCoins(targetStats.getCoins()+amount);

                        if(target.isOnline()){
                            target.getPlayer().sendMessage(ChatUtil.wallet() + " §fSe te han añadido §a" + amount + " §3§lVil§e§lCoins§f a tu cuenta.");
                        }else{
                            Api.getInstance().getPlayers().unloadPlayer(lytarget.getName());
                        }
                        context.getSender().sendMessage(ChatUtil.wallet() + " §fLe has añadido §a" + amount + " §3§lVil§e§lCoins§f a el jugador §3" + target.getName());
                        return true;

                    } catch (NumberFormatException numex) {

                        context.getSender().sendMessage(ChatUtil.prefix() + " §cLa cantidad debe ser un numero.");
                        return true;
                    }

                } else {
                    context.getSender().sendMessage(ChatUtil.prefix()+" §cEl jugador no ha sido encontrado.");
                    return true;
                }

            } else {
                context.getSender().sendMessage(ChatUtil.prefix() + " §cUtiliza §a/vilcoins (add|remove) (jugador) (cantidad)");
                return true;
            }

        } else if (context.getArg(0).equalsIgnoreCase("remove")) {

            if (context.getArgs().length >= 3) {

                OfflinePlayer target = Bukkit.getOfflinePlayer(context.getArg(1));

                if (target != null && target.hasPlayedBefore()) {

                    try {

                        double amount = Double.parseDouble(context.getArg(2));
                        LydarkPlayer lytarget = Api.getInstance().getPlayers().getOrCreatePlayer(target.getName());

                        IStats targetStats = Api.getInstance().getGameModes().get("village").getStatsFor(lytarget);
                        targetStats.setCoins(targetStats.getCoins()-amount);

                        if(target.isOnline()){
                            target.getPlayer().sendMessage(ChatUtil.wallet() + " §fSe te han removido §a" + amount + " §3§lVil§e§lCoins§f de tu cuenta.");
                        }else{
                            Api.getInstance().getPlayers().unloadPlayer(lytarget.getName());
                        }
                        context.getSender().sendMessage(ChatUtil.wallet() + " §fLe has removido §a" + amount + " §3§lVil§e§lCoins§f a el jugador §3" + target.getName());
                        return true;

                    } catch (NumberFormatException numex) {

                        context.getSender().sendMessage(ChatUtil.wallet() + " §cLa cantidad debe ser un numero.");
                        return true;
                    }

                } else {
                    context.getSender().sendMessage(ChatUtil.wallet()+" §cEl jugador no ha sido encontrado.");
                    return true;
                }

            } else {
                context.getSender().sendMessage(ChatUtil.wallet() + " §cUtiliza §a/vilcoins (add|remove) (jugador) (cantidad)");
                return true;
            }

        }else if (context.getArg(0).equalsIgnoreCase("pay")) {

            if(!(context.getSender() instanceof Player)){
                return false;
            }
            Player p = (Player) context.getSender();

            if (context.getArgs().length >= 3) {

                OfflinePlayer target = Bukkit.getOfflinePlayer(context.getArg(1));

                if (target != null && target.hasPlayedBefore()) {

                    try {

                        double amount = Double.parseDouble(context.getArg(2));
                        LydarkPlayer lyplayer = Api.getInstance().getPlayers().getPlayer(p.getName());
                        LydarkPlayer lytarget = Api.getInstance().getPlayers().getPlayer(target.getName());

                        IStats playerStats = Api.getInstance().getGameModes().get("village").getStatsFor(lyplayer);
                        IStats targetStats = Api.getInstance().getGameModes().get("village").getStatsFor(lytarget);

                        if(amount > targetStats.getCoins()){
                            p.sendMessage(ChatUtil.wallet()+ " §cNo tienes dinero suficiente. Tu saldo es §a"+targetStats.getCoins()+"§f.");
                        }else{

                            playerStats.setCoins(playerStats.getCoins()-amount);
                            targetStats.setCoins(playerStats.getCoins()+amount);

                            if (target.isOnline()) {
                                target.getPlayer().sendMessage(ChatUtil.wallet() + " §fEl jugador §3"+p.getName()+" §fte ha pagado §a"+amount+" §3§lVil§e§lCoins§f.");
                            }else{
                                Api.getInstance().getPlayers().unloadPlayer(lytarget.getName());
                            }
                            p.sendMessage(ChatUtil.wallet()+ " §fLe has pagado exitosamente §a"+amount+" §fa el jugador §3"+target.getName()+"§f.");
                        }
                        return true;

                    } catch (NumberFormatException numex) {

                        p.sendMessage(ChatUtil.wallet() + " §cLa cantidad debe ser un numero.");
                        return false;
                    }

                } else {
                    p.sendMessage(ChatUtil.prefix() + " §cEl jugador no ha sido encontrado.");
                    return false;
                }

            } else {
                p.sendMessage(ChatUtil.wallet() + " §cUtiliza §a/vilcoins pay (jugador) (cantidad)");
                return false;
            }

        } else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(context.getArg(0));

            if(target != null && target.hasPlayedBefore()){

                LydarkPlayer lytarget = Api.getInstance().getPlayers().getOrCreatePlayer(target.getName());
                IStats targetStats = Api.getInstance().getGameModes().get("village").getStatsFor(lytarget);

                context.getSender().sendMessage(ChatUtil.wallet() + " §3§lVil§e§lCoins§f de §3" + target.getName() + "§f: §a" + targetStats.getCoins());

                if(!(target.isOnline())){
                    Api.getInstance().getPlayers().unloadPlayer(target.getName());
                }

            }else{
                context.getSender().sendMessage(ChatUtil.prefix() + " §cEl jugador no ha sido encontrado.");
            }
            return true;
        }

    }
}
