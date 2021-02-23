package io.freeze_dolphin.ultimate_generators.objects.machines.rainbow_generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_8.ParticleEffect;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;

import org.apache.commons.lang3.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.ultimate_generators.Loader;
import io.freeze_dolphin.ultimate_generators.Utils;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.objects.abstracts.ModularGenerator;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import java.awt.Color;

public abstract class RainbowGenerator extends ModularGenerator {

    public RainbowGenerator(Category category, ItemStack item, String id, RecipeType recipeType,
            ItemStack[] recipe, boolean sw) {
        super(category, item, id, recipeType, recipe, sw);
    }

    @Override
    public boolean checkStructure(Block b) {
        List<Location> s = new ArrayList<>();

        Location l = b.getLocation();

        s.add(Utils.locModify(l, -2, -2, -2));
        s.add(Utils.locModify(l, -2, -2, -1));
        s.add(Utils.locModify(l, -2, -2, 0));
        s.add(Utils.locModify(l, -2, -2, 1));
        s.add(Utils.locModify(l, -2, -2, 2));
        s.add(Utils.locModify(l, -1, -2, -2));
        s.add(Utils.locModify(l, -1, -2, -1));
        s.add(Utils.locModify(l, -1, -2, 0));
        s.add(Utils.locModify(l, -1, -2, 1));
        s.add(Utils.locModify(l, -1, -2, 2));
        s.add(Utils.locModify(l, 0, -2, -2));
        s.add(Utils.locModify(l, 0, -2, -1));
        s.add(Utils.locModify(l, 0, -2, 0));
        s.add(Utils.locModify(l, 0, -2, 1));
        s.add(Utils.locModify(l, 0, -2, 2));
        s.add(Utils.locModify(l, 1, -2, -2));
        s.add(Utils.locModify(l, 1, -2, -1));
        s.add(Utils.locModify(l, 1, -2, 0));
        s.add(Utils.locModify(l, 1, -2, 1));
        s.add(Utils.locModify(l, 1, -2, 2));
        s.add(Utils.locModify(l, 2, -2, -2));
        s.add(Utils.locModify(l, 2, -2, -1));
        s.add(Utils.locModify(l, 2, -2, 0));
        s.add(Utils.locModify(l, 2, -2, 1));
        s.add(Utils.locModify(l, 2, -2, 2));

        s.add(Utils.locModify(l, -2, -1, -2));
        s.add(Utils.locModify(l, -2, -1, -1));
        s.add(Utils.locModify(l, -2, -1, 0));
        s.add(Utils.locModify(l, -2, -1, 1));
        s.add(Utils.locModify(l, -2, -1, 2));
        s.add(Utils.locModify(l, -1, -1, -2));
        s.add(Utils.locModify(l, -1, -1, -1));
        s.add(Utils.locModify(l, -1, -1, 0));
        s.add(Utils.locModify(l, -1, -1, 1));
        s.add(Utils.locModify(l, -1, -1, 2));
        s.add(Utils.locModify(l, 0, -1, -2));
        s.add(Utils.locModify(l, 0, -1, -1));
        s.add(Utils.locModify(l, 0, -1, 0));
        s.add(Utils.locModify(l, 0, -1, 1));
        s.add(Utils.locModify(l, 0, -1, 2));
        s.add(Utils.locModify(l, 1, -1, -2));
        s.add(Utils.locModify(l, 1, -1, -1));
        s.add(Utils.locModify(l, 1, -1, 0));
        s.add(Utils.locModify(l, 1, -1, 1));
        s.add(Utils.locModify(l, 1, -1, 2));
        s.add(Utils.locModify(l, 2, -1, -2));
        s.add(Utils.locModify(l, 2, -1, -1));
        s.add(Utils.locModify(l, 2, -1, 0));
        s.add(Utils.locModify(l, 2, -1, 1));
        s.add(Utils.locModify(l, 2, -1, 2));

        s.add(Utils.locModify(l, -2, 0, -2));
        s.add(Utils.locModify(l, -2, 0, -1));
        s.add(Utils.locModify(l, -2, 0, 0));
        s.add(Utils.locModify(l, -2, 0, 1));
        s.add(Utils.locModify(l, -2, 0, 2));
        s.add(Utils.locModify(l, -1, 0, -2));
        s.add(Utils.locModify(l, -1, 0, -1));
        s.add(Utils.locModify(l, -1, 0, 0));
        s.add(Utils.locModify(l, -1, 0, 1));
        s.add(Utils.locModify(l, -1, 0, 2));
        s.add(Utils.locModify(l, 0, 0, -2));
        s.add(Utils.locModify(l, 0, 0, -1));
        // s.add(Utils.locModify(l, 0, 0, 0));
        s.add(Utils.locModify(l, 0, 0, 1));
        s.add(Utils.locModify(l, 0, 0, 2));
        s.add(Utils.locModify(l, 1, 0, -2));
        s.add(Utils.locModify(l, 1, 0, -1));
        s.add(Utils.locModify(l, 1, 0, 0));
        s.add(Utils.locModify(l, 1, 0, 1));
        s.add(Utils.locModify(l, 1, 0, 2));
        s.add(Utils.locModify(l, 2, 0, -2));
        s.add(Utils.locModify(l, 2, 0, -1));
        s.add(Utils.locModify(l, 2, 0, 0));
        s.add(Utils.locModify(l, 2, 0, 1));
        s.add(Utils.locModify(l, 2, 0, 2));

        s.add(Utils.locModify(l, -2, 1, -2));
        s.add(Utils.locModify(l, -2, 1, -1));
        s.add(Utils.locModify(l, -2, 1, 0));
        s.add(Utils.locModify(l, -2, 1, 1));
        s.add(Utils.locModify(l, -2, 1, 2));
        s.add(Utils.locModify(l, -1, 1, -2));
        s.add(Utils.locModify(l, -1, 1, -1));
        s.add(Utils.locModify(l, -1, 1, 0));
        s.add(Utils.locModify(l, -1, 1, 1));
        s.add(Utils.locModify(l, -1, 1, 2));
        s.add(Utils.locModify(l, 0, 1, -2));
        s.add(Utils.locModify(l, 0, 1, -1));
        s.add(Utils.locModify(l, 0, 1, 0));
        s.add(Utils.locModify(l, 0, 1, 1));
        s.add(Utils.locModify(l, 0, 1, 2));
        s.add(Utils.locModify(l, 1, 1, -2));
        s.add(Utils.locModify(l, 1, 1, -1));
        s.add(Utils.locModify(l, 1, 1, 0));
        s.add(Utils.locModify(l, 1, 1, 1));
        s.add(Utils.locModify(l, 1, 1, 2));
        s.add(Utils.locModify(l, 2, 1, -2));
        s.add(Utils.locModify(l, 2, 1, -1));
        s.add(Utils.locModify(l, 2, 1, 0));
        s.add(Utils.locModify(l, 2, 1, 1));
        s.add(Utils.locModify(l, 2, 1, 2));

        s.add(Utils.locModify(l, -2, 2, -2));
        s.add(Utils.locModify(l, -2, 2, -1));
        s.add(Utils.locModify(l, -2, 2, 0));
        s.add(Utils.locModify(l, -2, 2, 1));
        s.add(Utils.locModify(l, -2, 2, 2));
        s.add(Utils.locModify(l, -1, 2, -2));
        s.add(Utils.locModify(l, -1, 2, -1));
        s.add(Utils.locModify(l, -1, 2, 0));
        s.add(Utils.locModify(l, -1, 2, 1));
        s.add(Utils.locModify(l, -1, 2, 2));
        s.add(Utils.locModify(l, 0, 2, -2));
        s.add(Utils.locModify(l, 0, 2, -1));
        s.add(Utils.locModify(l, 0, 2, 0));
        s.add(Utils.locModify(l, 0, 2, 1));
        s.add(Utils.locModify(l, 0, 2, 2));
        s.add(Utils.locModify(l, 1, 2, -2));
        s.add(Utils.locModify(l, 1, 2, -1));
        s.add(Utils.locModify(l, 1, 2, 0));
        s.add(Utils.locModify(l, 1, 2, 1));
        s.add(Utils.locModify(l, 1, 2, 2));
        s.add(Utils.locModify(l, 2, 2, -2));
        s.add(Utils.locModify(l, 2, 2, -1));
        s.add(Utils.locModify(l, 2, 2, 0));
        s.add(Utils.locModify(l, 2, 2, 1));
        s.add(Utils.locModify(l, 2, 2, 2));

        return s.stream().noneMatch(ll -> (ll.getBlock().getType().equals(Material.AIR) || !BlockStorage.check(ll, "REINFORCED_RAINBOW_GLASS")));

    }

    @Override
    public void register(boolean slimefun) {
        addItemHandler(new EnergyTicker() {

            @Override
            public double generateEnergy(Location l, SlimefunItem sf, Config data) {

                if (l.getBlock().getBlockPower() > 1) {
                    return 0D;
                }

                if (!checkStructure(l.getBlock())) {
                    return 0D;
                }

                if (isProcessing(l)) {

                    Utils.asyncDelay(() -> {
                        try {
                            for (int i = 0; i < Integer.parseInt(Loader.getProperties().getProperty("rainbow-generator-max-particle-number", "6")); i++) {
                                ParticleEffect.REDSTONE.displayColoredParticle(
                                        Utils.locModify(
                                                l,
                                                RandomUtils.nextInt(-2, 2),
                                                RandomUtils.nextInt(-2, 2),
                                                RandomUtils.nextInt(-2, 2)
                                        ),
                                        Color.getHSBColor(
                                                RandomUtils.nextFloat(0F, 255F),
                                                RandomUtils.nextFloat(0F, 255F),
                                                RandomUtils.nextFloat(0F, 255F)
                                        )
                                );
                            }
                        } catch (Exception ex) {
                        }
                    });

                    int timeleft = progress.get(l);
                    if (timeleft > 0) {
                        ItemStack item = getProgressBar().clone();
                        item.setDurability(MachineHelper.getDurability(item, timeleft, processing.get(l).getTicks()));
                        ItemMeta im = item.getItemMeta();
                        im.setDisplayName(" ");
                        List<String> lore = new ArrayList<>();
                        lore.add(MachineHelper.getProgress(timeleft, processing.get(l).getTicks()));
                        lore.add("");
                        lore.add(MachineHelper.getTimeLeft(timeleft / 2));
                        im.setLore(lore);
                        item.setItemMeta(im);

                        BlockStorage.getInventory(l).replaceExistingItem(INDICATOR, item);

                        if (ChargableBlock.isChargable(l)) {
                            if (ChargableBlock.getMaxCharge(l) - ChargableBlock.getCharge(l) >= getEnergyProduction()) {
                                ChargableBlock.addCharge(l, getEnergyProduction());
                                progress.put(l, timeleft - 1);
                                return ChargableBlock.getCharge(l);
                            }
                            return 0;
                        } else {
                            progress.put(l, timeleft - 1);
                            return l.getWorld().isThundering() ? getEnergyProduction() * 1.5 : getEnergyProduction();
                        }
                    } else {
                        ItemStack fuel = processing.get(l).getInput();
                        if (SlimefunManager.isItemSimiliar(fuel, new ItemStack(Material.LAVA_BUCKET), true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, SlimefunItems.BUCKET_OF_FUEL, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, SlimefunItems.BUCKET_OF_OIL, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, UGItems.BIOFUEL_BUCKET, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, UGItems.BIOMASS_BUCKET, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        } else if (SlimefunManager.isItemSimiliar(fuel, UGItems.DIESEL_BUCKET, true)) {
                            pushItems(l, new ItemStack[]{new ItemStack(Material.BUCKET)});
                        }
                        BlockStorage.getInventory(l).replaceExistingItem(INDICATOR, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " "));

                        progress.remove(l);
                        processing.remove(l);
                        return 0;
                    }
                } else {
                    MachineFuel r = null;
                    Map<Integer, Integer> found = new HashMap<>();
                    outer:
                    for (MachineFuel recipe : recipes) {
                        for (int slot : getInputSlots()) {
                            if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(l).getItemInSlot(slot), recipe.getInput(), true)) {
                                found.put(slot, recipe.getInput().getAmount());
                                r = recipe;
                                break outer;
                            }
                        }
                    }

                    if (r != null) {
                        found.entrySet().forEach(entry -> {
                            BlockStorage.getInventory(l).replaceExistingItem(entry.getKey(), InvUtils.decreaseItem(BlockStorage.getInventory(l).getItemInSlot(entry.getKey()), entry.getValue()));
                        });
                        processing.put(l, r);
                        progress.put(l, r.getTicks());
                    }
                    return 0;
                }
            }

            @Override
            public boolean explode(Location l) {
                return false;
            }
        });

        super.register(slimefun);
    }
    
}
