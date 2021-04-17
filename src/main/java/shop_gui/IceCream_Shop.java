package shop_gui;

import org.bukkit.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.Item;

import java.util.ArrayList;
import java.util.List;

public class IceCream_Shop implements InventoryHolder {

    private final Inventory inv;

    public IceCream_Shop(){
        inv = Bukkit.createInventory(this, 27, "§5§lIceCream Shop");
        createInventory();
    }

    @Override
    public Inventory getInventory(){
        return inv;
    }


    public void createInventory() {

        //CHOCOLATE
        List<String> chocolatelore = new ArrayList<>();
        chocolatelore.add("§b§m-------------------------------");
        chocolatelore.add("§f¡Compra un helado de chocolate!");
        chocolatelore.add("§fPrecio: §a$10");
        chocolatelore.add("§b§m-------------------------------");

        ItemStack chocolate = Item.itemGenerator("IceCream", "Chocolate", chocolatelore);
        inv.setItem(11, chocolate);

        //VAINILLA
        List<String> vainillalore = new ArrayList<>();
        vainillalore.add("§b§m-------------------------------");
        vainillalore.add("§f¡Compra un helado de vainilla!");
        vainillalore.add("§fPrecio: §a$10");
        vainillalore.add("§b§m-------------------------------");

        ItemStack vainilla = Item.itemGenerator("IceCream", "Vainilla", vainillalore);
        inv.setItem(13, vainilla);

        //STRAWBERRY
        List<String> strawberrylore = new ArrayList<>();
        strawberrylore.add("§b§m-------------------------------");
        strawberrylore.add("§f¡Compra un helado de fresa!");
        strawberrylore.add("§fPrecio: §a$10");
        strawberrylore.add("§b§m-------------------------------");

        ItemStack strawberry = Item.itemGenerator("IceCream", "Strawberry", strawberrylore);
        inv.setItem(15, strawberry);

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


}
