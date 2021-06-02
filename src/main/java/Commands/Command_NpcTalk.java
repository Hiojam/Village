package Commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.lydark.api.chat.ChatUtil;
import org.lydark.api.commands.Command;
import org.lydark.api.commands.CommandContext;
import village.Village;

import java.util.List;
import java.util.Random;

public class Command_NpcTalk {

    @Command(name = "npctalk", permission = "lydark.admin")
    public boolean npcTalkCommand(CommandContext context){

        if(!(context.getSender() instanceof Player)){
            context.getSender().sendMessage(ChatUtil.prefix()+" §cNo puedes ejecutar comandos desde la consola");
            return true;
        }

        if(context.getArgs().length == 0){
            return false;
        }

        Player p = (Player) context.getSender();

        if(context.getArg(0).equalsIgnoreCase("padre-martin")){

            Random r = new Random();
            FileConfiguration config = Village.getInstance().getConfig();

            List<String> mensajes = config.getStringList("NPC.Padre-Martin");
            int a = mensajes.size();
            int numero = r.nextInt(a);
            String mensaje = mensajes.get(numero);

            if(mensaje.equals("NO CAMBIAR ESTE TEXTO #1")){
                assert Village.getInstance().getServer().getWorld("Estructuras") != null;

                if(Village.getInstance().getServer().getWorld("Estructuras").getTime() > 12500 && Village.getInstance().getServer().getWorld("Estructuras").getTime()< 23100){
                    p.sendMessage(" §7[§ePadre Martin III§7] §aViva Satanas!");
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 1);
                }else{
                    p.sendMessage(" §7[§ePadre Martin III§7] §aViva Jesucristo!");
                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1);
                }
            }else{
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje));
                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1);
            }

            return true;
        }

        return false;
    }
}
