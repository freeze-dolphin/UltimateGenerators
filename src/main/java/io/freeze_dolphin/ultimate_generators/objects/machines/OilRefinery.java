package io.freeze_dolphin.ultimate_generators.objects.machines;

import java.util.ArrayList;
import java.util.List;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.ultimate_generators.Loader;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.objects.abstracts.BContainer;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;

public abstract class OilRefinery extends BContainer {

	public OilRefinery(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, name, recipeType, recipe, Loader.getDisplaySw());
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() {
		registerRecipe(40, new ItemStack[] {SlimefunItems.BUCKET_OF_OIL}, new ItemStack[] {UGItems.DIESEL_BUCKET});
	}

	@Override
	public String getMachineIdentifier() {
		return "DIESEL_REFINERY";
	}

	@Override
	public String getInventoryTitle() {
		return "&c柴油精炼器";
	}

	@Override
	public int getEnergyConsumption() {
		return 16;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

	protected void tick(Block b) {

		if (b.getBlockPower() > 1) { return; }

		if (isProcessing(b)) {
			int timeleft = progress.get(b);
			if (timeleft > 0) {
				ItemStack item = getProgressBar().clone();
				item.setDurability(MachineHelper.getDurability(item, timeleft, processing.get(b).getTicks()));
				ItemMeta im = item.getItemMeta();
				im.setDisplayName(" ");
				List<String> lore = new ArrayList<String>();
				lore.add(MachineHelper.getProgress(timeleft, processing.get(b).getTicks()));
				lore.add("");
				lore.add(MachineHelper.getTimeLeft(timeleft / 2));
				im.setLore(lore);
				item.setItemMeta(im);

				BlockStorage.getInventory(b).replaceExistingItem(22, item);

				if (ChargableBlock.isChargable(b)) {
					if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
					ChargableBlock.addCharge(b, -getEnergyConsumption());
					progress.put(b, timeleft - 1);
				}
				else progress.put(b, timeleft - 1);
			}
			else {				
				pushItems(b, processing.get(b).getOutput());
				BlockStorage.getInventory(b).replaceExistingItem(22, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, (byte) 15), " "));
				progress.remove(b);
				processing.remove(b);
			}
		}
		else {
			for (int slot: getInputSlots()) {
				if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), SlimefunItems.BUCKET_OF_OIL, true)) {
					MachineRecipe r = new MachineRecipe(40, new ItemStack[0], new ItemStack[] { UGItems.DIESEL_BUCKET });
					if (!fits(b, r.getOutput())) return;
					BlockStorage.getInventory(b).replaceExistingItem(slot, InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(slot), 1));
					processing.put(b, r);
					progress.put(b, r.getTicks());
					break;
				}
			}
		}
	}

}
