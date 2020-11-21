package io.github.freeze_dolphin.ultimate_generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.freeze_dolphin.ultimate_generators.lists.UGCategories;
import io.github.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.github.freeze_dolphin.ultimate_generators.objects.abstracts.BContainer;
import io.github.freeze_dolphin.ultimate_generators.objects.abstracts.BGenerator;
import io.github.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import io.github.freeze_dolphin.ultimate_generators.objects.machines.BiofuelRefinery;
import io.github.freeze_dolphin.ultimate_generators.objects.machines.OilRefinery;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;

public class UGImplementor {

	private static final ItemStack MOTOR = SlimefunItems.ELECTRIC_MOTOR;
	private static final ItemStack COIL = SlimefunItems.HEATING_COIL;
	private static final ItemStack BCIRCUIT = SlimefunItems.BASIC_CIRCUIT_BOARD;
	private static final ItemStack ACIRCUIT = SlimefunItems.ADVANCED_CIRCUIT_BOARD;

	public UGImplementor() {}

	public void implementIngredients() {

		(new SlimefunItem(UGCategories.TECH_MISC, UGItems.MODULAR_GENERATOR_REGULATOR, "MODULAR_GENERATOR_REGULATOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.BILLON_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COBALT_INGOT, 
				SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ENERGY_REGULATOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, 
				SlimefunItems.COBALT_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.BILLON_INGOT))).register(false);

	}

	public void implementMachines() {

		(new BContainer(UGCategories.MACHINES, UGItems.ENDERIUM_EXTRACTOR, "ENDERIUM_EXTRACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT, 
				SlimefunItems.HARDENED_GLASS, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.HARDENED_GLASS, 
				SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.RUNE_ENDER), false) {

			@Override
			public ItemStack getProgressBar() {
				return mat(Material.ENDER_PEARL);
			}

			@Override
			public void registerDefaultRecipes() {
				registerRecipe(320, new ItemStack[] {SlimefunItems.ENDER_LUMP_3, SlimefunItems.SILVER_DUST}, new ItemStack[] {UGItems.ENDERIUM_DUST});
				registerRecipe(1200, new ItemStack[] {SlimefunItems.ENDER_LUMP_3, SlimefunItems.SILVER_INGOT}, new ItemStack[] {UGItems.ENDERIUM_INGOT});
			}

			@Override
			public String getInventoryTitle() {
				return "&5末影之尘精炼机";
			}

			@Override
			public int getEnergyConsumption() {
				return 16;
			}

		}).registerChargeableBlock(false, 256);

	}

	public void implementSingleGenerators() {

		(new SlimefunItem(UGCategories.SINGLE_GENERATOR, UGItems.ENDLESS_GENERATOR, "ENDLESS_GENERATOR", RecipeType.NULL, Utils.buildRecipe(), true)).register(false, new EnergyTicker() {

			@Override
			public boolean explode(Location paramLocation) { return false; }

			@Override
			public double generateEnergy(Location l, SlimefunItem paramSlimefunItem, Config paramConfig) {
				return l.getBlock().getBlockPower() * 1024;
			}});

		(new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.NETHER_STAR_GENERATOR, "NETHER_STAR_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_ALLOY_INGOT, 
				SlimefunItems.PLASTIC_SHEET, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.PLASTIC_SHEET, 
				SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.BIG_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS), false) {

			@Override
			public ItemStack getProgressBar() {
				return new UniversalMaterial(Material.SKULL_ITEM, 1).toItemStack(1);
			}

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(30, mat(Material.NETHER_STAR)));
			}

			@Override
			public String getInventoryTitle() {
				return "&c下届之星发电机";
			}

			@Override
			public int getSpeed() {
				return 1;
			}

			@Override
			public int getEnergyProduction() {
				return 64;
			}
		}).registerChargeableBlock(false, 1024);

		(new OilRefinery(UGCategories.SINGLE_GENERATOR, UGItems.DIESEL_REFINERY, "DIESEL_REFINERY", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.HEATING_COIL, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HEATING_COIL, 
				SlimefunItems.HARDENED_GLASS, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HARDENED_GLASS, 
				SlimefunItems.HARDENED_GLASS, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.HARDENED_GLASS)) {

			@Override
			public String getInventoryTitle() {
				return "&c柴油精炼机";
			}

			@Override
			public int getSpeed() {
				return 1;
			}
		}).registerChargeableBlock(false, 256);

		(new BiofuelRefinery(UGCategories.SINGLE_GENERATOR, UGItems.BIOFUEL_REFINERY, "BIOFUEL_REFINERY", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.HEATING_COIL, SlimefunItems.PLASTIC_SHEET, SlimefunItems.HEATING_COIL, 
				SlimefunItems.HARDENED_GLASS, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HARDENED_GLASS, 
				SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.HEATING_COIL)) {

			@Override
			public String getInventoryTitle() {
				return "&2生物燃油精炼机";
			}

			@Override
			public int getSpeed() {
				return 1;
			}
		}).registerChargeableBlock(false, 256);

		(new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.DIESEL_GENERATOR, "DIESEL_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				null, SlimefunItems.DURALUMIN_INGOT, null, 
				SlimefunItems.DURALUMIN_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.DURALUMIN_INGOT, 
				SlimefunItems.HEATING_COIL, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.HEATING_COIL), false) {

			public ItemStack getProgressBar() {
				return mat(Material.FLINT_AND_STEEL);
			}

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(90, UGItems.DIESEL_BUCKET));
			}

			@Override
			public String getInventoryTitle() {
				return "&c柴油发电机";
			}

			@Override
			public int getSpeed() {
				return 1;
			}

			@Override
			public int getEnergyProduction() {
				return 18;
			}
		}).registerUnrechargeableBlock(true, 256);

		(new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.BIOFUEL_GENERATOR, "BIOFUEL_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.HEATING_COIL, SlimefunItems.BILLON_INGOT, SlimefunItems.HEATING_COIL, 
				SlimefunItems.BILLON_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BILLON_INGOT, 
				SlimefunItems.HEATING_COIL, SlimefunItems.BILLON_INGOT, SlimefunItems.HEATING_COIL), false) {

			public ItemStack getProgressBar() {
				return mat(Material.MAGMA_CREAM);
			}

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(120, UGItems.BIOFUEL_BUCKET));
			}

			@Override
			public String getInventoryTitle() {
				return "&c生物燃油发电机";
			}

			@Override
			public int getSpeed() {
				return 1;
			}

			@Override
			public int getEnergyProduction() {
				return 18;
			}
		}).registerUnrechargeableBlock(true, 256);

		(new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.DRAGON_BREATH_GENERATOR, "DRAGON_BREATH_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				COIL, SlimefunItems.HARDENED_GLASS, COIL, 
				SlimefunItems.PLASTIC_SHEET, SlimefunItems.RUNE_ENDER, SlimefunItems.PLASTIC_SHEET, 
				SlimefunItems.ENDER_LUMP_3, SlimefunItems.MEDIUM_CAPACITOR, ACIRCUIT), false) {

			@Override
			public ItemStack getProgressBar() {
				return mat(Material.END_CRYSTAL);
			}

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(30, UGItems.DRAGON_BREATH_GENERATOR));
			}

			@Override
			public String getInventoryTitle() {
				return "&5龙息发电机";
			}

			@Override
			public int getSpeed() {
				return 1;
			}

			@Override
			public int getEnergyProduction() {
				return 9;
			}
		}).registerChargeableBlock(false, 256);

		(new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.REACTION_GENERATOR, "REACTION_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.LEAD_INGOT, MOTOR, SlimefunItems.LEAD_INGOT, 
				COIL, SlimefunItems.HARDENED_GLASS, COIL, 
				BCIRCUIT, SlimefunItems.MEDIUM_CAPACITOR, BCIRCUIT), false) {

			@Override
			public ItemStack getProgressBar() {
				return mat(Material.FLINT_AND_STEEL);
			}

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(3, SlimefunItems.TINY_URANIUM));
				registerFuel(new MachineFuel(27, SlimefunItems.SMALL_URANIUM));
				registerFuel(new MachineFuel(108, SlimefunItems.URANIUM));
			}

			@Override
			public String getInventoryTitle() {
				return "&2反应发电机";
			}

			@Override
			public int getSpeed() {
				return 1;
			}

			@Override
			public int getEnergyProduction() {
				return 8;
			}
		}).registerChargeableBlock(false, 256);

	}


	public void implementModularGenerators() {

		(new SlimefunItem(UGCategories.MODULAR_GENERATOR, UGItems.ENDER_CRYSTAL_GENERATOR_BASE, "ENDER_CRYSTAL_GENERATOR_BASE", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				mat(Material.END_BRICKS), mat(Material.END_BRICKS), mat(Material.END_BRICKS), 
				mat(Material.END_BRICKS), UGItems.ENDERIUM_INGOT, mat(Material.END_BRICKS), 
				mat(Material.END_BRICKS), SlimefunItems.HARDENED_METAL_INGOT, mat(Material.END_BRICKS)))).register(false);
		
	}

	private ItemStack mat(Material m) {
		return new ItemStack(m);
	}

}
