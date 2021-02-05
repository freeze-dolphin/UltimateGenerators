package io.freeze_dolphin.ultimate_generators.objects.machines.ender_crystal_generator;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;

import org.apache.commons.lang3.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.ultimate_generators.Loader;
import io.freeze_dolphin.ultimate_generators.Utils;

public class EnderCrystalStabilizer extends SlimefunItem {

	public EnderCrystalStabilizer(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	@Override
	public void register(boolean slimefun) {
		addItemHandler(new BlockTicker() {

			@Override
			public void tick(Block b, SlimefunItem sf, Config data) {
				EnderCrystalStabilizer.this.tick(b);
			}

			@Override
			public void uniqueTick() {
			}

			@Override
			public boolean isSynchronized() {
				return false;
			}
		});

		super.register(slimefun);
	}

	private int getEnergyConsumption() {
		return 9;
	}

	protected void tick(Block b) {

		if (b.isBlockPowered()) return;

		if (ChargableBlock.getCharge(b) < getEnergyConsumption()) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Loader.getImplement(), new Runnable() {

				@Override
				public void run() {
					BlockStorage.clearBlockInfo(b);
					b.setType(Material.AIR);
					b.getWorld().createExplosion(Utils.locModify(b.getLocation(), 0.5F, 0.5F, 0.5F), 0F, false);
					if (RandomUtils.nextBoolean()) b.setType(Material.LAVA);
					if (RandomUtils.nextBoolean()) b.getWorld().createExplosion(Utils.locModify(b.getLocation(), 0.5F, 0.5F, 0.5F), RandomUtils.nextFloat(1F, 8F), RandomUtils.nextBoolean());
				}
			});
		} else {
			ChargableBlock.addCharge(b, -getEnergyConsumption());
		}

	}

}
