package io.github.freeze_dolphin.ultimate_generators.objects.machines.ender_crystal_generator;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.inventory.ItemStack;

import io.github.freeze_dolphin.ultimate_generators.objects.entities.StableEnderCrystal;

public abstract class EnderCrystalStabilizer extends SlimefunItem {

	private final String ID;

	public EnderCrystalStabilizer(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
		ID = id;	
	}

	public abstract int getEnergyConsumption();
	
	public String getMachineIdentifier() { return ID; }

	public void register(boolean slimefun) {
		addItemHandler(new ItemHandler[]{new BlockTicker() {
			public void tick(Block b, SlimefunItem sf, Config data) {
				EnderCrystalStabilizer.this.tick(b);
			}

			public void uniqueTick() {}

			public boolean isSynchronized() {
				return false;
			}
		}});
		super.register(slimefun);
	}
	
	private static boolean insertedCrystalAlready(Block b) {
		return (BlockStorage.hasBlockInfo(b.getLocation()) && BlockStorage.getLocationInfo(b.getLocation(), "crystal") != null && BlockStorage.getLocationInfo(b.getLocation(), "crystal").equals("true"));
	}
	
	protected void tick(Block b) {
		Location l = b.getLocation();
		
		if (insertedCrystalAlready(b)) {
			StableEnderCrystal.update(l, "");
		} else {
			StableEnderCrystal.remove(l);
		}
	}
	
}