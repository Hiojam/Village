package village;

import lydark.api.api.Lydark_API;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shop_gui.Exchange_Shop;

public class VilCoins_Command implements CommandExecutor {

    private final Village plugin;

    public VilCoins_Command(Village plugin) {
        this.plugin = plugin;
    }
    private final Lydark_API lydark = (Lydark_API) Bukkit.getServer().getPluginManager().getPlugin("Lydark_API");

    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        if(!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(lydark.Chat.prefix+" §cNo puedes ejecutar comandos desde la consola");
            return false;
        }else {
            Player jugador = (Player) sender;

            if(args.length > 0) {

                if (args[0].equalsIgnoreCase("exchange")) {

                    jugador.sendMessage(lydark.Chat.prefix + " §fAbriendo el menu de intercambio..");
                    Exchange_Shop inv = new Exchange_Shop(jugador);
                    jugador.openInventory(inv.getInventory());

                    jugador.playSound(jugador.getLocation(), Sound.NOTE_STICKS, 1, 1);
                    return true;

                } else if (args[0].equalsIgnoreCase("add")) {

                    if (jugador.getName().equals("RoxxionYT")) {

                        if (args.length >= 3) {

                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                            if (target != null && target.hasPlayedBefore()) {

                                try {

                                    double amount = Double.parseDouble(args[2]);

                                    plugin.addVilCoins(target, amount);
                                    if(target.isOnline()){
                                        target.getPlayer().sendMessage(lydark.Chat.wallet + " §fSe te han añadido §a" + amount + " §3§lVil§e§lCoins§f a tu cuenta.");
                                    }
                                    jugador.sendMessage(lydark.Chat.wallet + " §fLe has añadido §a" + amount + " §3§lVil§e§lCoins§f a el jugador §3" + target.getName());
                                    return true;

                                } catch (NumberFormatException numex) {

                                    jugador.sendMessage(lydark.Chat.wallet + " §cLa cantidad debe ser un numero.");
                                    return false;
                                }

                            } else {
                                jugador.sendMessage(lydark.Chat.prefix+" §cEl jugador no ha sido encontrado.");
                                return false;
                            }

                        } else {
                            jugador.sendMessage(lydark.Chat.prefix + " §cUtiliza §a/vilcoins (add|remove) (jugador) (cantidad)");
                            return false;
                        }

                    } else {
                        jugador.sendMessage(lydark.Chat.nopermission);
                        return false;
                    }

                } else if (args[0].equalsIgnoreCase("remove")) {

                    if (jugador.getName().equals("RoxxionYT")) {

                        if (args.length >= 3) {

                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                            if (target != null && target.hasPlayedBefore()) {

                                try {

                                    double amount = Double.parseDouble(args[2]);

                                    plugin.removeVilCoins(target, amount);
                                    if(target.isOnline()){
                                        target.getPlayer().sendMessage(lydark.Chat.wallet + " §fSe te han removido §a" + amount + " §3§lVil§e§lCoins§f de tu cuenta.");
                                    }
                                    jugador.sendMessage(lydark.Chat.wallet + " §fLe has removido §a" + amount + " §3§lVil§e§lCoins§f a el jugador §3" + target.getName());
                                    return true;

                                } catch (NumberFormatException numex) {

                                    jugador.sendMessage(lydark.Chat.wallet + " §cLa cantidad debe ser un numero.");
                                    return false;
                                }

                            } else {
                                jugador.sendMessage(lydark.Chat.prefix+" §cEl jugador no ha sido encontrado.");
                                return false;
                            }

                        } else {
                            jugador.sendMessage(lydark.Chat.wallet + " §cUtiliza §a/vilcoins (add|remove) (jugador) (cantidad)");
                            return false;
                        }

                    } else {
                        jugador.sendMessage(lydark.Chat.nopermission);
                        return false;
                    }

                }else if (args[0].equalsIgnoreCase("pay")) {

                    if (args.length >= 3) {

                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                        if (target != null && target.hasPlayedBefore()) {

                            try {

                                double amount = Double.parseDouble(args[2]);

                                if(amount > plugin.getVilCoins(jugador)){
                                    jugador.sendMessage(lydark.Chat.wallet+ " §cNo tienes dinero suficiente. Tu saldo es §a"+plugin.getVilCoins(jugador)+"§f.");
                                    return false;
                                }else{
                                    plugin.removeVilCoins(jugador, amount);
                                    plugin.addVilCoins(target, amount);
                                    if (target.isOnline()) {
                                        target.getPlayer().sendMessage(lydark.Chat.wallet + " §fEl jugador §3"+jugador.getName()+" §fte ha pagado §a"+amount+" §3§lVil§e§lCoins§f.");
                                    }
                                    jugador.sendMessage(lydark.Chat.wallet+ " §fLe has pagado exitosamente §a"+amount+" §fa el jugador §3"+target.getName()+"§f.");
                                    return true;
                                }

                            } catch (NumberFormatException numex) {

                                jugador.sendMessage(lydark.Chat.wallet + " §cLa cantidad debe ser un numero.");
                                return false;
                            }

                        } else {
                            jugador.sendMessage(lydark.Chat.prefix + " §cEl jugador no ha sido encontrado.");
                            return false;
                        }

                    } else {
                        jugador.sendMessage(lydark.Chat.wallet + " §cUtiliza §a/vilcoins pay (jugador) (cantidad)");
                        return false;
                    }

                } else {
                    if (jugador.hasPermission("lydark.admin")) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                        if(target != null && target.hasPlayedBefore()){
                            Document data2 = lydark.MongoDB.findPlayer(target);

                            if(data2 != null && data2.containsKey("vilcoins")){
                                jugador.sendMessage(lydark.Chat.wallet + " §3§lVil§e§lCoins§f de §3" + target.getName() + "§f: §a" + plugin.getVilCoins(target));
                            }
                            return true;
                        }else{
                            jugador.sendMessage(lydark.Chat.prefix + " §cEl jugador no ha sido encontrado.");
                            return false;
                        }
                    }else {
                        jugador.sendMessage(lydark.Chat.nopermission);
                    }
                    return false;
                }
            }
            else{
                jugador.sendMessage(lydark.Chat.wallet+" §3§lVil§e§lCoins§f: §a"+plugin.getVilCoins(jugador));
                return true;
            }
        }
    }

}
