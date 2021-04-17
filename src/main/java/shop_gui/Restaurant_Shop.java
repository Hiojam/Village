package shop_gui;

import org.bukkit.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.Item;

import java.util.ArrayList;
import java.util.List;

public class Restaurant_Shop implements InventoryHolder {

    private final Inventory inv;

    public Restaurant_Shop(){
        inv = Bukkit.createInventory(this, 45, "§5§lRestaurant Shop");
        createInventory();
    }

    @Override
    public Inventory getInventory(){
        return inv;
    }


    public void createInventory() {

        //HAMBURGUESA
        List<String> hamburguesalore = new ArrayList<>();
        hamburguesalore.add("§b§m-------------------------------");
        hamburguesalore.add("§f¡Compra una hamburguesa!");
        hamburguesalore.add("§fPrecio: §a$20");
        hamburguesalore.add("§b§m-------------------------------");

        ItemStack hamburguesa = Item.itemGenerator("Restaurant", "Hamburguer", hamburguesalore);
        inv.setItem(11, hamburguesa);

        //FRIES
        List<String> frieslore = new ArrayList<>();
        frieslore.add("§b§m-------------------------------");
        frieslore.add("§f¡Compra unas papas fritas!");
        frieslore.add("§fPrecio: §a$15");
        frieslore.add("§b§m-------------------------------");

        ItemStack fries = Item.itemGenerator("Restaurant", "Fries", frieslore);
        inv.setItem(13, fries);

        //PIZZA
        List<String> pizzalore = new ArrayList<>();
        pizzalore.add("§b§m-------------------------------");
        pizzalore.add("§f¡Compra una pizza!");
        pizzalore.add("§fPrecio: §a$30");
        pizzalore.add("§b§m-------------------------------");

        ItemStack pizza = Item.itemGenerator("Restaurant", "Pizza", pizzalore);
        inv.setItem(15, pizza);

        //DONUT
        List<String> donutlore = new ArrayList<>();
        donutlore.add("§b§m-------------------------------");
        donutlore.add("§f¡Compra una donut!");
        donutlore.add("§fPrecio: §a$15");
        donutlore.add("§b§m-------------------------------");

        ItemStack donut = Item.itemGenerator("Restaurant", "Donut", donutlore);
        inv.setItem(29, donut);

        //MUFFIN
        List<String> muffinlore = new ArrayList<>();
        muffinlore.add("§b§m-------------------------------");
        muffinlore.add("§f¡Compra un muffin!");
        muffinlore.add("§fPrecio: §a$15");
        muffinlore.add("§b§m-------------------------------");

        ItemStack muffin = Item.itemGenerator("Restaurant", "Muffin", muffinlore);
        inv.setItem(31, muffin);

        //DRINK
        List<String> drinklore = new ArrayList<>();
        drinklore.add("§b§m-------------------------------");
        drinklore.add("§f¡Compra una bebida!");
        drinklore.add("§fPrecio: §a$12");
        drinklore.add("§b§m-------------------------------");

        ItemStack drink = Item.itemGenerator("Restaurant", "Drink", drinklore);
        inv.setItem(33, drink);

        //DECORACION
        ItemStack decoracion = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 15);
        ItemMeta decoracionmeta = decoracion.getItemMeta();
        decoracionmeta.setDisplayName(" ");
        decoracion.setItemMeta(decoracionmeta);

        //DECORACION CRISTALES
        for(int i = 0; i<10; i++){
            inv.setItem(i, decoracion);
        }
        for(int i = 36; i<45; i++){
            inv.setItem(i, decoracion);
        }
        inv.setItem(17, decoracion);
        inv.setItem(18, decoracion);
        inv.setItem(26, decoracion);
        inv.setItem(27, decoracion);
        inv.setItem(35, decoracion);
    }


}
