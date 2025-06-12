package me.arcx.mayor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.*;

import java.util.List;

public class MayorGUI {

    private final ElectionManager manager;

    public MayorGUI(ElectionManager manager) {
        this.manager = manager;
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "§aVote for Mayor");
        List<Mayor> mayors = manager.getMayors();

        for (int i = 0; i < mayors.size(); i++) {
            Mayor mayor = mayors.get(i);
            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
            ItemMeta meta = skull.getItemMeta();
            meta.setDisplayName("§6" + mayor.name);
            meta.setLore(mayor.perks.stream().map(p -> "§7" + p).toList());
            skull.setItemMeta(meta);
            inv.setItem(11 + i, skull);
        }

        player.openInventory(inv);
    }

    public void onClick(Player player, InventoryClickEvent e) {
        if (!e.getView().getTitle().equals("§aVote for Mayor")) return;
        e.setCancelled(true);

        if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) return;

        String name = e.getCurrentItem().getItemMeta().getDisplayName().replace("§6", "");
        Mayor selected = manager.getMayors().stream()
            .filter(m -> m.name.equals(name)).findFirst().orElse(null);

        if (selected != null) {
            manager.vote(player.getName(), selected);
            player.sendMessage("§aYou voted for §6" + selected.name);
            player.closeInventory();
        }
    }
}
