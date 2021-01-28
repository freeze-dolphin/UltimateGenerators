package io.freeze_dolphin.ultimate_generators.objects.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.mrCookieSlime.Slimefun.AncientAltar.AltarRecipe;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class DebugSlimefunItem extends SlimefunItem {

	public DebugSlimefunItem(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

	@Override
	public void load() {
		try {
			if (!isHidden()) getCategory().add(this);
			ItemStack output = getItem().clone();
			if (getRecipeOutput() != null) output = getRecipeOutput().clone();

			if (getRecipeType().toItem().isSimilar(RecipeType.MOB_DROP.toItem())) {
				try {
					EntityType entity = EntityType.valueOf(ChatColor.stripColor(getRecipe()[4].getItemMeta().getDisplayName()).toUpperCase().replace(" ", "_"));
					List<ItemStack> dropping = new ArrayList<ItemStack>();
					if (SlimefunManager.drops.containsKey(entity)) dropping = SlimefunManager.drops.get(entity);
					dropping.add(output);
					SlimefunManager.drops.put(entity, dropping);
				} catch(Exception x) {
					x.printStackTrace();
				}
			}
			else if (getRecipeType().toItem().isSimilar(RecipeType.ANCIENT_ALTAR.toItem())) {
				new AltarRecipe(Arrays.asList(getRecipe()), output);
			}
			else if (getRecipeType().getMachine() != null && getByID(getRecipeType().getMachine().getID()) instanceof SlimefunMachine) {
				((SlimefunMachine) getByID(getRecipeType().getMachine().getID())).addRecipe(getRecipe(), output);
			}
			install();
		} catch(Exception x) {
			x.printStackTrace();
			System.err.println("[Slimefun] An item Initialization failed");
		}
	}

}
