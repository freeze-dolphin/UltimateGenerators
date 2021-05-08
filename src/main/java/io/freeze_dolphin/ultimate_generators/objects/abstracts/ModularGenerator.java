package io.freeze_dolphin.ultimate_generators.objects.abstracts;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.AsyncGeneratorProcessCompleteEvent;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public abstract class ModularGenerator extends AGenerator {

    public ModularGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    private boolean isBucket(ItemStack item) {
        if (item == null) {
            return false;
        }

        ItemStackWrapper wrapper = new ItemStackWrapper(item);
        return item.getType() == Material.LAVA_BUCKET
                || SlimefunUtils.isItemSimilar(wrapper, SlimefunItems.FUEL_BUCKET, true)
                || SlimefunUtils.isItemSimilar(wrapper, SlimefunItems.OIL_BUCKET, true);
    }

    private MachineFuel findRecipe(BlockMenu menu, Map<Integer, Integer> found) {
        for (MachineFuel fuel : fuelTypes) {
            for (int slot : getInputSlots()) {
                if (fuel.test(menu.getItemInSlot(slot))) {
                    found.put(slot, fuel.getInput().getAmount());
                    return fuel;
                }
            }
        }

        return null;
    }

    @Override
    public int getGeneratedOutput(Location l, Config data) {
        BlockMenu inv = BlockStorage.getInventory(l);

        if (isProcessing(l)) {
            int timeleft = progress.get(l);

            if (timeleft > 0) {
                ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(l).getTicks(), getProgressBar());

                if (isChargeable()) {
                    int charge = getCharge(l, data);

                    if (getCapacity() - charge >= getEnergyProduction()) {
                        progress.put(l, timeleft - 1);
                        return getEnergyProduction();
                    }

                    return 0;
                } else {
                    progress.put(l, timeleft - 1);
                    return getEnergyProduction();
                }
            } else {
                ItemStack fuel = processing.get(l).getInput();

                if (isBucket(fuel)) {
                    inv.pushItem(new ItemStack(Material.BUCKET), getOutputSlots());
                }

                inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

                Bukkit.getPluginManager().callEvent(new AsyncGeneratorProcessCompleteEvent(l, this, getProcessing(l)));

                progress.remove(l);
                processing.remove(l);
                return 0;
            }
        } else {
            Map<Integer, Integer> found = new HashMap<>();
            MachineFuel fuel = findRecipe(inv, found);

            if (fuel != null) {
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    inv.consumeItem(entry.getKey(), entry.getValue());
                }

                processing.put(l, fuel);
                progress.put(l, fuel.getTicks());
            }

            return 0;
        }
    }

}
