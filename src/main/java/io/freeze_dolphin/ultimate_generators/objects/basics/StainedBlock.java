package io.freeze_dolphin.ultimate_generators.objects.basics;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StainedBlock extends ItemStack {

    public enum BlockColor {

        WHITE(0), ORANGE(1), MAGENTA(2), LIGHT_BLUE(3), YELLOW(4), LIME(5), PINK(6), GRAY(7), LIGHT_GRAY(8), CYAN(9),
        PURPLE(10), BLUE(11), BROWN(12), GREEN(13), RED(14), BLACK(15);

        private final short damage;

        private BlockColor(int damage) {
            this.damage = (short) damage;
        }

        public short getDamage() {
            return damage;
        }

    }

    public enum StainedBlockType {
        HARDENED_CLAY, GLASS, GLASS_PANE
    }

    public StainedBlock(BlockColor color, StainedBlockType type, String displayName, String... lore) {
        super();
        Material m;
        switch (type) {
            case HARDENED_CLAY:
                m = Material.STAINED_CLAY;
                break;
            case GLASS:
                m = Material.STAINED_GLASS;
                break;
            case GLASS_PANE:
                m = Material.STAINED_GLASS_PANE;
                break;
            default:
                throw new IllegalArgumentException("A StainedBlock's type must be HARDENED_CLAY, GLASS or GLASS_PANE");
        }

        this.setType(m);
        this.setDurability(color.getDamage());
        ItemMeta im = this.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName + "&r"));
        List<String> ls = new LinkedList<>();
        for (String s : lore) {
            ls.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        im.setLore(ls);

    }

}
