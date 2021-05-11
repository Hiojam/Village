package village;

import lydark.api.Lydark_API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class NpcTalk_Command implements CommandExecutor {

    private final Village plugin;
    private final Lydark_API lydark = (Lydark_API) Bukkit.getServer().getPluginManager().getPlugin("Lydark_API");

    public NpcTalk_Command(Village plugin) {
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
                    if(args[0].equalsIgnoreCase("padre-martin")){

                        Random r = new Random();
                        FileConfiguration config = plugin.getConfig();

                        List<String> mensajes = config.getStringList("NPC.Padre-Martin");
                        int a = mensajes.size();
                        int numero = r.nextInt(a);
                        String mensaje = mensajes.get(numero);

                        if(mensaje.equals("NO CAMBIAR ESTE TEXTO #1")){
                            assert plugin.getServer().getWorld("Estructuras") != null;
                            if(plugin.getServer().getWorld("Estructuras").getTime() > 12500 && plugin.getServer().getWorld("Estructuras").getTime()< 23100){
                                jugador.sendMessage(" §7[§ePadre Martin III§7] §aViva Satanas!");
                                jugador.playSound(jugador.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 1);
                            }else{
                                jugador.sendMessage(" §7[§ePadre Martin III§7] §aViva Jesucristo!");
                                jugador.playSound(jugador.getLocation(), Sound.NOTE_PIANO, 1, 1);
                            }
                        }else{
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje));
                            jugador.playSound(jugador.getLocation(), Sound.NOTE_PIANO, 1, 1);
                        }

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
}
