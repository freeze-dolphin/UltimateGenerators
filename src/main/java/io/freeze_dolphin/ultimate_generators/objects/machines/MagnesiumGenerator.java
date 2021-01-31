package io.freeze_dolphin.ultimate_generators.objects.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.objects.abstracts.BGenerator;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;

public class MagnesiumGenerator extends BGenerator {

	public MagnesiumGenerator(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, boolean indicator) {
		super(category, item, id, recipeType, recipe, indicator);
	}

	@Override
	public ItemStack getProgressBar() {
		return new ItemStack(Material.FLINT_AND_STEEL);
	}

	@Override
	public void registerDefaultRecipes() {
		registerFuel(new MachineFuel(20, UGItems.MAGNESIUM_SALT));
	}

	@Override
	public String getInventoryTitle() {
		return "&f镁发电机";
	}

	@Override
	public int getEnergyProduction() {
		return 18;
	}

	@Override
	public int getSpeed() {
		return 1;
	}

}