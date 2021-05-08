package io.freeze_dolphin.ultimate_generators.objects.abstracts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.AsyncMachineProcessCompleteEvent;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public abstract class ModularContainer extends AContainer {

    protected ModularContainer(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public abstract boolean checkStructure(Block b);

    @Override
    protected void tick(Block b) {

        if (!checkStructure(b)) {
            return;
        }

        BlockMenu inv = BlockStorage.getInventory(b);

        if (isProcessing(b)) {

            if (takeCharge(b.getLocation())) {

                int timeleft = progress.get(b);

                if (timeleft > 0) {
                    ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                    progress.put(b, timeleft - 1);
                } else {

                    inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

                    for (ItemStack output : processing.get(b).getOutput()) {
                        inv.pushItem(output.clone(), getOutputSlots());
                    }

                    Bukkit.getPluginManager()
                            .callEvent(new AsyncMachineProcessCompleteEvent(b.getLocation(), this, getProcessing(b)));

                    progress.remove(b);
                    processing.remove(b);
                }
            }

        } else {
            MachineRecipe next = findNextRecipe(inv);

            if (next != null) {
                processing.put(b, next);
                progress.put(b, next.getTicks());
            }
        }
    }
}
