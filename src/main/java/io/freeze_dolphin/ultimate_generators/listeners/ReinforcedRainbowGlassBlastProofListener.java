package io.freeze_dolphin.ultimate_generators.listeners;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Iterator;

@Deprecated
public class ReinforcedRainbowGlassBlastProofListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent e) {
        Iterator<Block> blocks = e.blockList().iterator();
        while (blocks.hasNext()) {
            Block block = blocks.next();
            SlimefunItem item = BlockStorage.check(block);
            if (item != null) {
                blocks.remove();
                if (!item.getID().equalsIgnoreCase("REINFORCED_RAINBOW_GLASS")) {
                    boolean success = true;
                    if (SlimefunItem.blockhandler.containsKey(item.getID())) {
                        success = SlimefunItem.blockhandler.get(item.getID()).onBreak(null, block, item, UnregisterReason.EXPLODE);
                    }
                    if (success) {
                        BlockStorage.clearBlockInfo(block);
                        block.setType(Material.AIR);
                    }
                }
            }
        }

    }

}
