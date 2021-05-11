package shop_gui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lydark.api.Lydark_API;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Exchange_Shop implements InventoryHolder {

    private final Inventory inv;
    private final Lydark_API lydark = (Lydark_API) Bukkit.getServer().getPluginManager().getPlugin("Lydark_API");

    public Exchange_Shop(Player jugador){
        inv = Bukkit.createInventory(this, 27, "§5§lExchange Shop");
        createInventory(jugador);
    }

    @Override
    public Inventory getInventory(){
        return inv;
    }


    public void createInventory(Player jugador) {

        Document data = lydark.mongo.findPlayer(jugador);
        //20 VILCOINS = 1 LYCOIN/DARKCOIN

        //1000 VilCoins
        List<String> onelycoinslore = new ArrayList<>();
        onelycoinslore.add("§b§m-------------------------------");
        onelycoinslore.add("§fIntercambia §a50 §d§lLy§e§lCoins§f/§5§lDark§e§lCoins");
        onelycoinslore.add("§fpor 1000 §3§lVil§e§lCoins§f.");
        if(data != null && data.getString("coins").equals("bothcoins")){
            onelycoinslore.add("");
            onelycoinslore.add("§fClick izquierdo: §d§lLy§e§lCoins");
            onelycoinslore.add("§fClick derecho: §5§lDark§e§lCoins");
        }
        onelycoinslore.add("§b§m-------------------------------");
        ItemStack onelycoins = createItem("§f1000 §3§lVil§e§lCoins", onelycoinslore, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTAyOGRhNjI4OWQzN2U5NDMyNjY2YjhmMmQwOGQ4MjQ4NTJlNzUyMTFkYWI3Zjg3MjIzZTg5ODQ1YjQyZDllMiJ9fX0=");
        inv.setItem(11, onelycoins);

        //5000 VilCoins
        List<String> twolycoinslore = new ArrayList<>();
        twolycoinslore.add("§b§m-------------------------------");
        twolycoinslore.add("§fIntercambia §a250 §d§lLy§e§lCoins§f/§5§lDark§e§lCoins");
        twolycoinslore.add("§fpor 5000 §3§lVil§e§lCoins§f.");
        if(data != null && data.getString("coins").equals("bothcoins")){
            twolycoinslore.add("");
            twolycoinslore.add("§fClick izquierdo: §d§lLy§e§lCoins");
            twolycoinslore.add("§fClick derecho: §5§lDark§e§lCoins");
        }
        twolycoinslore.add("§b§m-------------------------------");
        ItemStack twolycoins = createItem("§f5000 §3§lVil§e§lCoins", twolycoinslore, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmNhYWQ4NmM3MDhlYjI3NzczYTY0ZjkzNDc5ZTM5ZjA0NDJhNWNlMDg2YjYzMjk2YzdiN2QxY2Y1MTE2MDk1NiJ9fX0=");
        inv.setItem(13, twolycoins);

        //25000
        List<String> threelycoinslore = new ArrayList<>();
        threelycoinslore.add("§b§m-------------------------------");
        threelycoinslore.add("§fIntercambia §a1250 §d§lLy§e§lCoins§f/§5§lDark§e§lCoins");
        threelycoinslore.add("§fpor 25000 §3§lVil§e§lCoins§f.");
        if(data != null && data.getString("coins").equals("bothcoins")){
            threelycoinslore.add("");
            threelycoinslore.add("§fClick izquierdo: §d§lLy§e§lCoins");
            threelycoinslore.add("§fClick derecho: §5§lDark§e§lCoins");
        }
        threelycoinslore.add("§b§m-------------------------------");
        ItemStack threelycoins = createItem("§f25000 §3§lVil§e§lCoins", threelycoinslore, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWRkODY3M2Y4NDJlNTBmMTMxYjM3N2M4NGI2ZmU1MWMyMTkxMDZkZjNmYmMwOTFhN2JjOTI2OWI5MWM2NjA1MiJ9fX0=");
        inv.setItem(15, threelycoins);


        //DECORACION
        ItemStack decoracion = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 15);
        ItemMeta decoracionmeta = decoracion.getItemMeta();
        decoracionmeta.setDisplayName(" ");
        decoracion.setItemMeta(decoracionmeta);

        //DECORACION CRISTALES
        for(int i = 0; i<10; i++){
            inv.setItem(i, decoracion);
        }
        for(int i = 17; i<27; i++){
            inv.setItem(i, decoracion);
        }
    }

    private ItemStack createItem(String name, List<String> lore, String head){
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(byte) SkullType.PLAYER.ordinal());
        SkullMeta itemmeta = (SkullMeta) item.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", head));
        Field field;
        try {
            field = itemmeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(itemmeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException x) {
            x.printStackTrace();
        }
        itemmeta.setDisplayName(name);
        if(lore != null){
            itemmeta.setLore(lore);
        }

        item.setItemMeta(itemmeta);
        return item;
    }

}
