package util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import village.Village;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class Item {

    private static Village plugin;

    public Item(Village plugin){
        Item.plugin = plugin;
    }

    public static ItemStack itemGenerator(String type, String item, List<String> lore){

        FileConfiguration config = plugin.getConfig();
        String name = ChatColor.translateAlternateColorCodes('&', config.getString("Items."+type+"."+item+".name"));
        String head = config.getString("Items."+type+"."+item+".head");

        ItemStack itemgenerator = new ItemStack(Material.SKULL_ITEM,1,(byte) SkullType.PLAYER.ordinal());
        SkullMeta itemgeneratormeta = (SkullMeta) itemgenerator.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", head));
        Field field;
        try {
            field = itemgeneratormeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(itemgeneratormeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException x) {
            x.printStackTrace();
        }
        itemgeneratormeta.setDisplayName(name);
        if(lore != null){
            itemgeneratormeta.setLore(lore);
        }

        itemgenerator.setItemMeta(itemgeneratormeta);

        return itemgenerator;
    }

    public static ItemStack itemGenerator(String type, String item){

        FileConfiguration config = plugin.getConfig();
        String name = ChatColor.translateAlternateColorCodes('&', config.getString("Items."+type+"."+item+".name"));
        String head = config.getString("Items."+type+"."+item+".head");

        ItemStack itemgenerator = new ItemStack(Material.SKULL_ITEM,1,(byte) SkullType.PLAYER.ordinal());
        SkullMeta itemgeneratormeta = (SkullMeta) itemgenerator.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", head));
        Field field;
        try {
            field = itemgeneratormeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(itemgeneratormeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException x) {
            x.printStackTrace();
        }
        itemgeneratormeta.setDisplayName(name);

        itemgenerator.setItemMeta(itemgeneratormeta);

        return itemgenerator;
    }
}
