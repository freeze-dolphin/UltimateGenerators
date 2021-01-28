package io.freeze_dolphin.ultimate_generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.ultimate_generators.lists.UGCategories;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.objects.abstracts.BGenerator;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import io.freeze_dolphin.ultimate_generators.objects.machines.BiofuelRefinery;
import io.freeze_dolphin.ultimate_generators.objects.machines.OilRefinery;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.SlimefunStartup;
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
	private static final ItemStack HGLASS = SlimefunItems.HARDENED_GLASS;

	private static final ItemStack ALUI = SlimefunItems.ALUMINUM_INGOT;
	private static final ItemStack COPPI = SlimefunItems.COPPER_INGOT;

	public UGImplementor() {}

	public void implementIngredients() {

		/*
		(new SlimefunItem(UGCategories.TECH_MISC, UGItems.MODULAR_GENERATOR_REGULATOR, "MODULAR_GENERATOR_REGULATOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.BILLON_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COBALT_INGOT, 
				SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ENERGY_REGULATOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, 
				SlimefunItems.COBALT_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.BILLON_INGOT))).register(false);
		 */

		(new SlimefunItem(UGCategories.TECH_MISC, UGItems.SOLID_STORAGE_EXPANSION, "SOLID_STORAGE_EXPANSION", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				null, SlimefunItems.CARGO_OUTPUT, null, 
				ALUI, SlimefunItems.ANDROID_INTERFACE_ITEMS, ALUI, 
				ALUI, mat(Material.GLASS), ALUI
				))).register(false);

		(new SlimefunItem(UGCategories.TECH_MISC, UGItems.LIQUID_STORAGE_EXPANSION, "LIQUID_STORAGE_EXPANSION", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				null, SlimefunItems.CARGO_OUTPUT, null, 
				ALUI, SlimefunItems.ANDROID_INTERFACE_FUEL, ALUI, 
				ALUI, mat(Material.GLASS), ALUI
				))).register(false);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ELECTRICITY_STORAGE_UNIT, "ELECTRICITY_STORAGE_UNIT", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				HGLASS, SlimefunItems.REDSTONE_ALLOY, HGLASS, 
				ALUI, SlimefunItems.BATTERY, ALUI, 
				HGLASS, SlimefunItems.REDSTONE_ALLOY, HGLASS
				))).register(false);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ADVANCED_BATTERY, "ADVANCED_BATTERY", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				SlimefunItems.BATTERY, BCIRCUIT, SlimefunItems.BATTERY, 
				COPPI, COPPI, COPPI, 
				SlimefunItems.BATTERY, BCIRCUIT, SlimefunItems.BATTERY
				))).register(false);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ALPHA_BATTERY, "ALPHA_BATTERY", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				UGItems.ADVANCED_BATTERY, ACIRCUIT, UGItems.ADVANCED_BATTERY, 
				SlimefunItems.REDSTONE_ALLOY, COPPI, SlimefunItems.REDSTONE_ALLOY, 
				UGItems.ADVANCED_BATTERY, ACIRCUIT, UGItems.ADVANCED_BATTERY
				))).register(false);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.BETA_BATTERY, "BETA_BATTERY", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				UGItems.ALPHA_BATTERY, ACIRCUIT, UGItems.ALPHA_BATTERY, 
				SlimefunItems.REDSTONE_ALLOY, COPPI, SlimefunItems.REDSTONE_ALLOY, 
				UGItems.ALPHA_BATTERY, ACIRCUIT, UGItems.ALPHA_BATTERY
				))).register(false);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.GAMMA_BATTERY, "GAMMA_BATTERY", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				UGItems.BETA_BATTERY, ACIRCUIT, UGItems.BETA_BATTERY, 
				SlimefunItems.REDSTONE_ALLOY, COPPI, SlimefunItems.REDSTONE_ALLOY, 
				UGItems.BETA_BATTERY, ACIRCUIT, UGItems.BETA_BATTERY
				))).register(false);

	}

	public void implementMachines() {

		/*
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
		 */

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.BASIC_ELECTRICITY_STORAGE, "BASIC_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				SlimefunItems.BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.BATTERY, 
				SlimefunItems.REDSTONE_ALLOY, ALUI, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.BATTERY
		})).registerDistibutingCapacitor(true, 640);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ADVANCED_ELECTRICITY_STORAGE, "ADVANCED_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				UGItems.ADVANCED_BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.REDSTONE_ALLOY, UGItems.BASIC_ELECTRICITY_STORAGE, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, UGItems.ADVANCED_BATTERY
		})).registerDistibutingCapacitor(true, 2560);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ALPHA_ELECTRICITY_STORAGE, "ALPHA_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				UGItems.ALPHA_BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.REDSTONE_ALLOY, UGItems.ADVANCED_ELECTRICITY_STORAGE, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, UGItems.ALPHA_BATTERY
		})).registerDistibutingCapacitor(true, 5120);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.BETA_ELECTRICITY_STORAGE, "BETA_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				UGItems.BETA_BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.REDSTONE_ALLOY, UGItems.ALPHA_ELECTRICITY_STORAGE, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, UGItems.BETA_BATTERY
		})).registerDistibutingCapacitor(true, 40960);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE, "GAMMA_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				UGItems.GAMMA_BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.REDSTONE_ALLOY, UGItems.BETA_ELECTRICITY_STORAGE, SlimefunItems.REDSTONE_ALLOY, 
				SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, UGItems.GAMMA_BATTERY
		})).registerDistibutingCapacitor(true, 327680);

		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.LAMBDA_ELECTRICITY_STORAGE, "LAMBDA_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				UGItems.GAMMA_ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE, 
				UGItems.GAMMA_ELECTRICITY_STORAGE, SlimefunItems.REDSTONE_ALLOY, UGItems.GAMMA_ELECTRICITY_STORAGE, 
				UGItems.GAMMA_ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE
		})).registerDistibutingCapacitor(true, 2621440);

		SlimefunStartup.getItemCfg().setValue("KAPA_ELECTRICITY_STORAGE.hide-in-guide", true);
		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.KAPA_ELECTRICITY_STORAGE, "KAPA_ELECTRICITY_STORAGE", RecipeType.NULL, new ItemStack[] {})).registerDistibutingCapacitor(true, 20971520);

		SlimefunStartup.getItemCfg().setValue("PHI_ELECTRICITY_STORAGE.hide-in-guide", true);
		(new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.PHI_ELECTRICITY_STORAGE, "PHI_ELECTRICITY_STORAGE", RecipeType.NULL, new ItemStack[] {})).registerDistibutingCapacitor(true, 167772160);

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
				SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.BIG_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS), Loader.getDisplaySw()) {

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
				SlimefunItems.HEATING_COIL, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.HEATING_COIL), Loader.getDisplaySw()) {

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
				SlimefunItems.HEATING_COIL, SlimefunItems.BILLON_INGOT, SlimefunItems.HEATING_COIL), Loader.getDisplaySw()) {

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
				SlimefunItems.ENDER_LUMP_3, SlimefunItems.MEDIUM_CAPACITOR, ACIRCUIT), Loader.getDisplaySw()) {

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
				BCIRCUIT, SlimefunItems.MEDIUM_CAPACITOR, BCIRCUIT), Loader.getDisplaySw()) {

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

		new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.COAL_GENERATOR, "ENLARGED_COAL_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				null, UGItems.SOLID_STORAGE_EXPANSION, null, 
				ALUI, SlimefunItems.COAL_GENERATOR, ALUI, 
				null, UGItems.SOLID_STORAGE_EXPANSION, null
		}, Loader.getDisplaySw()) {

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(8, new UniversalMaterial(Material.COAL, 0).toItemStack(1)));
				registerFuel(new MachineFuel(8, new UniversalMaterial(Material.COAL, 1).toItemStack(1)));
				registerFuel(new MachineFuel(80, new ItemStack(Material.COAL_BLOCK)));
				registerFuel(new MachineFuel(12, new ItemStack(Material.BLAZE_ROD)));

				// Logs
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LOG, 0).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LOG, 1).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LOG, 2).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LOG, 3).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LOG_2, 0).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LOG_2, 1).toItemStack(1)));

				// Wooden Planks
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.WOOD, 0).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.WOOD, 1).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.WOOD, 2).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.WOOD, 3).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.WOOD, 4).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.WOOD, 5).toItemStack(1)));
			}

			@Override
			public ItemStack getProgressBar() {
				return new ItemStack(Material.FLINT_AND_STEEL);
			}

			@Override
			public String getInventoryTitle() {
				return "&c煤炭发电机";
			}

			@Override
			public int getEnergyProduction() {
				return 8;
			}

			@Override
			public int getSpeed() {
				return 1;
			}

		}.registerUnrechargeableBlock(true, 64);

		new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.BIO_REACTOR, "ENLARGED_BIO_REACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				null, UGItems.SOLID_STORAGE_EXPANSION, null, 
				ALUI, SlimefunItems.BIO_REACTOR, ALUI, 
				null, UGItems.SOLID_STORAGE_EXPANSION, null
		}, Loader.getDisplaySw()) {

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(2, new ItemStack(Material.ROTTEN_FLESH)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.SPIDER_EYE)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.BONE)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.APPLE)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.MELON)));
				registerFuel(new MachineFuel(27, new ItemStack(Material.MELON_BLOCK)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.PUMPKIN)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.PUMPKIN_SEEDS)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.MELON_SEEDS)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.WHEAT)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.SEEDS)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.CARROT_ITEM)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.POTATO_ITEM)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.SUGAR_CANE)));
				registerFuel(new MachineFuel(3, new ItemStack(Material.NETHER_STALK)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.YELLOW_FLOWER)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.RED_ROSE)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.RED_MUSHROOM)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.BROWN_MUSHROOM)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.VINE)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.CACTUS)));
				registerFuel(new MachineFuel(2, new ItemStack(Material.WATER_LILY)));

				// Leaves
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LEAVES, 0).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LEAVES, 1).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LEAVES, 2).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LEAVES, 3).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LEAVES_2, 0).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.LEAVES_2, 1).toItemStack(1)));

				// Saplings
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.SAPLING, 0).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.SAPLING, 1).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.SAPLING, 2).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.SAPLING, 3).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.SAPLING, 4).toItemStack(1)));
				registerFuel(new MachineFuel(1, new UniversalMaterial(Material.SAPLING, 5).toItemStack(1)));
			}

			@Override
			public ItemStack getProgressBar() {
				return new ItemStack(Material.GOLD_HOE);
			}

			@Override
			public String getInventoryTitle() {
				return "&2生化反应器";
			}

			@Override
			public int getEnergyProduction() {
				return 4;
			}

			@Override
			public int getSpeed() {
				return 1;
			}

		}.registerUnrechargeableBlock(true, 128);

		new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.LAVA_GENERATOR, "ENLARGED_LAVA_GENERATOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				null, UGItems.LIQUID_STORAGE_EXPANSION, null, 
				ALUI, SlimefunItems.LAVA_GENERATOR, ALUI, 
				null, UGItems.LIQUID_STORAGE_EXPANSION, null
		}, Loader.getDisplaySw()) {

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(40, new ItemStack(Material.LAVA_BUCKET)));
			}

			@Override
			public ItemStack getProgressBar() {
				return new ItemStack(Material.FLINT_AND_STEEL);
			}

			@Override
			public String getInventoryTitle() {
				return "&4岩浆发电机";
			}

			@Override
			public int getEnergyProduction() {
				return 10;
			}

			@Override
			public int getSpeed() {
				return 1;
			}

		}.registerUnrechargeableBlock(true, 512);

		new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.COMBUSTION_REACTOR, "ENLARGED_COMBUSTION_REACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				null, UGItems.LIQUID_STORAGE_EXPANSION, null, 
				ALUI, SlimefunItems.COMBUSTION_REACTOR, ALUI, 
				null, UGItems.LIQUID_STORAGE_EXPANSION, null
		}, Loader.getDisplaySw()) {

			@Override
			public void registerDefaultRecipes() {
				registerFuel(new MachineFuel(30, SlimefunItems.BUCKET_OF_OIL));
				registerFuel(new MachineFuel(90, SlimefunItems.BUCKET_OF_FUEL));
			}

			@Override
			public ItemStack getProgressBar() {
				return new ItemStack(Material.FLINT_AND_STEEL);
			}

			@Override
			public String getInventoryTitle() {
				return "&c燃烧反应器";
			}

			@Override
			public int getEnergyProduction() {
				return 12;
			}

			@Override
			public int getSpeed() {
				return 1;
			}

		}.registerUnrechargeableBlock(true, 256);

	}


	public void implementModularGenerators() {

		/*
		(new SlimefunItem(UGCategories.MODULAR_GENERATOR, UGItems.ENDER_CRYSTAL_GENERATOR_BASE, "ENDER_CRYSTAL_GENERATOR_BASE", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
				mat(Material.END_BRICKS), mat(Material.END_BRICKS), mat(Material.END_BRICKS), 
				mat(Material.END_BRICKS), UGItems.ENDERIUM_INGOT, mat(Material.END_BRICKS), 
				mat(Material.END_BRICKS), SlimefunItems.HARDENED_METAL_INGOT, mat(Material.END_BRICKS)))).register(false);
		 */

	}

	private ItemStack mat(Material m) {
		return new ItemStack(m);
	}

}
