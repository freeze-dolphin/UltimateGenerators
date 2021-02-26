package io.freeze_dolphin.ultimate_generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.ultimate_generators.lists.UGCategories;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.lists.UGRecipeType;
import io.freeze_dolphin.ultimate_generators.objects.abstracts.BContainer;
import io.freeze_dolphin.ultimate_generators.objects.abstracts.BGenerator;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import io.freeze_dolphin.ultimate_generators.objects.machines.BiofuelRefinery;
import io.freeze_dolphin.ultimate_generators.objects.machines.MagnesiumGenerator;
import io.freeze_dolphin.ultimate_generators.objects.machines.OilRefinery;
import io.freeze_dolphin.ultimate_generators.objects.machines.ender_crystal_generator.EnderCrystalGenerator;
import io.freeze_dolphin.ultimate_generators.objects.machines.ender_crystal_generator.EnderCrystalStabilizer;
import io.freeze_dolphin.ultimate_generators.objects.machines.rainbow_generator.RainbowGenerator;
import io.freeze_dolphin.ultimate_generators.objects.tasks.ReinforcedRainbowTicker;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.SlimefunStartup;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AReactor;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;

class UGImplementor {

    private static final ItemStack MOTOR = SlimefunItems.ELECTRIC_MOTOR;
    private static final ItemStack COIL = SlimefunItems.HEATING_COIL;
    private static final ItemStack BCIRCUIT = SlimefunItems.BASIC_CIRCUIT_BOARD;
    private static final ItemStack ACIRCUIT = SlimefunItems.ADVANCED_CIRCUIT_BOARD;
    private static final ItemStack HGLASS = SlimefunItems.HARDENED_GLASS;

    private static final ItemStack ALUI = SlimefunItems.ALUMINUM_INGOT;
    private static final ItemStack COPPI = SlimefunItems.COPPER_INGOT;

    private final Loader plug;

    public UGImplementor(Loader plug) {
        this.plug = plug;
    }

    public void implementIngredients() {

        (new SlimefunItem(UGCategories.TECH_MISC, UGItems.MODULAR_GENERATOR_REGULATOR, "MODULAR_GENERATOR_REGULATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(SlimefunItems.BILLON_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                        SlimefunItems.COBALT_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                        SlimefunItems.ENERGY_REGULATOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                        SlimefunItems.COBALT_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.BILLON_INGOT)))
                .register(false);

        (new SlimefunItem(UGCategories.TECH_MISC, UGItems.SOLID_STORAGE_EXPANSION, "SOLID_STORAGE_EXPANSION",
                RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(null, SlimefunItems.CARGO_OUTPUT, null, ALUI,
                        SlimefunItems.ANDROID_INTERFACE_ITEMS, ALUI, ALUI, mat(Material.GLASS), ALUI))).register(false);

        (new SlimefunItem(UGCategories.TECH_MISC, UGItems.LIQUID_STORAGE_EXPANSION, "LIQUID_STORAGE_EXPANSION",
                RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(null, SlimefunItems.CARGO_OUTPUT, null, ALUI,
                        SlimefunItems.ANDROID_INTERFACE_FUEL, ALUI, ALUI, mat(Material.GLASS), ALUI))).register(false);

        (new SlimefunItem(
                UGCategories.ELECTRICITY_STORAGE, UGItems.ELECTRICITY_STORAGE_UNIT, "ELECTRICITY_STORAGE_UNIT",
                RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(HGLASS, SlimefunItems.REDSTONE_ALLOY, HGLASS,
                        ALUI, SlimefunItems.BATTERY, ALUI, HGLASS, SlimefunItems.REDSTONE_ALLOY, HGLASS)))
                .register(false);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ADVANCED_BATTERY, "ADVANCED_BATTERY",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(SlimefunItems.BATTERY, BCIRCUIT, SlimefunItems.BATTERY, COPPI, COPPI, COPPI,
                        SlimefunItems.BATTERY, BCIRCUIT, SlimefunItems.BATTERY))).register(false);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ALPHA_BATTERY, "ALPHA_BATTERY",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(UGItems.ADVANCED_BATTERY, ACIRCUIT, UGItems.ADVANCED_BATTERY, COPPI,
                        UGItems.ELECTRICITY_STORAGE_UNIT, COPPI, UGItems.ADVANCED_BATTERY, ACIRCUIT,
                        UGItems.ADVANCED_BATTERY))).register(false);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.BETA_BATTERY, "BETA_BATTERY",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(UGItems.ALPHA_BATTERY, ACIRCUIT, UGItems.ALPHA_BATTERY, COPPI,
                        UGItems.ELECTRICITY_STORAGE_UNIT, COPPI, UGItems.ALPHA_BATTERY, ACIRCUIT,
                        UGItems.ALPHA_BATTERY))).register(false);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.GAMMA_BATTERY, "GAMMA_BATTERY",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(UGItems.BETA_BATTERY, ACIRCUIT, UGItems.BETA_BATTERY, COPPI,
                        UGItems.ELECTRICITY_STORAGE_UNIT, COPPI, UGItems.BETA_BATTERY, ACIRCUIT, UGItems.BETA_BATTERY)))
                .register(false);

        (new SlimefunItem(Categories.RESOURCES, UGItems.DIESEL_BUCKET, "DIESEL_BUCKET",
                new RecipeType(
                        new CustomItem(new UniversalMaterial(Material.PISTON_BASE), "&c柴油精炼器&r", "&a在柴油精炼器里将石油精炼为柴油")),
                Utils.buildRecipe())).register(false);
        (new SlimefunItem(Categories.RESOURCES, UGItems.BIOMASS_BUCKET, "BIOMASS_BUCKET",
                new RecipeType(new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 5), "&a生物质萃取机&r",
                        "&a在生物质萃取机里提取一些作物中的生物质")),
                Utils.buildRecipe())).register(false);
        (new SlimefunItem(Categories.RESOURCES, UGItems.BIOFUEL_BUCKET, "BIOFUEL_BUCKET", new RecipeType(
                new CustomItem(new UniversalMaterial(Material.PISTON_BASE), "&2生物燃油精炼器&r", "&a在生物燃油精炼器里将生物质精炼为生物燃油")),
                Utils.buildRecipe())).register(false);

        (new SlimefunItem(Categories.MISC, UGItems.MAGNESIUM_SALT, "MAGNESIUM_SALT", RecipeType.PRESSURE_CHAMBER,
                Utils.buildRecipe(SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST, Boolean.parseBoolean(DefaultConfig.getConfig("magnesium-salt-require-zinc-dust")) ? SlimefunItems.ZINC_DUST : null)))
                .register(false);

        (new SlimefunItem(Categories.LUMPS_AND_MAGIC, UGItems.ENDER_LUMP_4, "ENDER_LUMP_4", RecipeType.ANCIENT_ALTAR,
                new ItemStack[]{SlimefunItems.ENDER_LUMP_3, SlimefunItems.ENDER_LUMP_3, SlimefunItems.ENDER_LUMP_3,
                    SlimefunItems.ENDER_LUMP_3, SlimefunItems.ENDER_LUMP_3, SlimefunItems.ENDER_LUMP_3,
                    SlimefunItems.ENDER_LUMP_3, SlimefunItems.ENDER_LUMP_3, SlimefunItems.ENDER_LUMP_3}))
                .register(false);

        (new SlimefunItem(Categories.LUMPS_AND_MAGIC, UGItems.RUNE_COMPLEX_ENDER, "RUNE_COMPLEX_ENDER",
                RecipeType.ANCIENT_ALTAR,
                new ItemStack[]{SlimefunItems.RUNE_ENDER, SlimefunItems.GOLD_24K, SlimefunItems.RUNE_ENDER,
                    UGItems.ENDER_LUMP_4, SlimefunItems.STONE_CHUNK, UGItems.ENDER_LUMP_4, SlimefunItems.RUNE_ENDER,
                    SlimefunItems.GOLD_24K, SlimefunItems.RUNE_ENDER})).register(false);

        (new SlimefunItem(Categories.MISC, UGItems.ENDER_CRYSTAL_ENHANCER, "ENERGY_ACUMULATED_ENDER_CRYSTAL",
                RecipeType.ANCIENT_ALTAR,
                new ItemStack[]{mat(Material.GLASS), mat(Material.GLASS), mat(Material.GLASS), mat(Material.GLASS),
                    UGItems.ENDER_LUMP_4, mat(Material.GLASS), mat(Material.GLASS), mat(Material.GLASS),
                    mat(Material.GLASS)})).register(false);
        Slimefun.addWikiPage("ENERGY_ACUMULATED_ENDER_CRYSTAL",
                "https://gitee.com/freeze-dolphin/UltimateGenerators-wiki/blob/master/Generator-(Ender-Crystal-Generator).md");

        (new SlimefunItem(Categories.RESOURCES, UGItems.HEAVY_WATER_BUCKET, "HEAVY_WATER_BUCKET", new RecipeType(
                new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 7), "&7重水提炼机&r", "&a在重水提炼机里提取水中的重水")),
                Utils.buildRecipe())).register(false);

        (new SlimefunItem(Categories.MISC, UGItems.NEUTRON_MODERATOR, "NEUTRON_MODERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{mat(Material.GLASS), UGItems.HEAVY_WATER_BUCKET, mat(Material.GLASS),
                    UGItems.HEAVY_WATER_BUCKET, SlimefunItems.CARBON_CHUNK, UGItems.HEAVY_WATER_BUCKET,
                    mat(Material.GLASS), UGItems.HEAVY_WATER_BUCKET, mat(Material.GLASS)})).register(false);

        (new SlimefunItem(Categories.TECH_MISC, UGItems.THERMAL_NEUTRON_REACTOR_COOLANT_CELL,
                "THERMAL_NEUTRON_REACTOR_COOLANT_CELL", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{null, null, UGItems.HEAVY_WATER_BUCKET, null, SlimefunItems.REACTOR_COOLANT_CELL,
                    null, UGItems.HEAVY_WATER_BUCKET, null, null})).register(false);

        (new SlimefunItem(Categories.RESOURCES, UGItems.RAINBOW_ALLOY, "RAINBOW_ALLOY", RecipeType.SMELTERY,
                Utils.buildRecipe(SlimefunItems.REDSTONE_ALLOY, SlimefunItems.CORINTHIAN_BRONZE_INGOT,
                        SlimefunItems.COBALT_INGOT, SlimefunItems.MAGIC_LUMP_3, SlimefunItems.RUNE_RAINBOW)))
                .register(false);

        SlimefunStartup.getItemCfg().setValue("REINFORCED_RAINBOW_GLASS.allow-disenchanting", false);
        BlockTicker fast = new ReinforcedRainbowTicker.Fast();
        (new SlimefunItem(Categories.MAGIC, UGItems.REINFORCED_RAINBOW_GLASS, "REINFORCED_RAINBOW_GLASS",
                RecipeType.ANCIENT_ALTAR,
                new ItemStack[]{SlimefunItems.RAINBOW_GLASS, SlimefunItems.RAINBOW_GLASS, SlimefunItems.RAINBOW_GLASS,
                    SlimefunItems.REINFORCED_ALLOY_INGOT, UGItems.RAINBOW_ALLOY,
                    SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.RAINBOW_GLASS, SlimefunItems.RAINBOW_GLASS,
                    SlimefunItems.RAINBOW_GLASS}, new CustomItem(UGItems.REINFORCED_RAINBOW_GLASS, Integer.parseInt(DefaultConfig.getConfig("reinforced-rainbow-glass-crafting-number-on-once"))))).register(false,
                new ItemHandler[]{Boolean.parseBoolean(DefaultConfig.getConfig("reinforced-rainbow-glass-twinkling-randomly")) ? new ReinforcedRainbowTicker.Fancy() : fast});

        Slimefun.addWikiPage("REINFORCED_RAINBOW_GLASS", "https://gitee.com/freeze-dolphin/UltimateGenerators-wiki/blob/master/Generator-(Rainbow-Reactor).md");

    }

    public void implementMachines() {

        (new OilRefinery(UGCategories.MACHINES, UGItems.DIESEL_REFINERY, "DIESEL_REFINERY",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(SlimefunItems.HEATING_COIL, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HEATING_COIL,
                        SlimefunItems.HARDENED_GLASS, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HARDENED_GLASS,
                        SlimefunItems.HARDENED_GLASS, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.HARDENED_GLASS)) {
        }).registerChargeableBlock(false, 256);

        (new BiofuelRefinery(UGCategories.MACHINES, UGItems.BIOFUEL_REFINERY, "BIOFUEL_REFINERY",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(SlimefunItems.HEATING_COIL, SlimefunItems.PLASTIC_SHEET, SlimefunItems.HEATING_COIL,
                        SlimefunItems.HARDENED_GLASS, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HARDENED_GLASS,
                        SlimefunItems.HEATING_COIL, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.HEATING_COIL)) {
        }).registerChargeableBlock(false, 256);

        (new BContainer(UGCategories.MACHINES, UGItems.BIOMASS_EXTRACTION_MACHINE, "BIOMASS_EXTRACTION_MACHINE",
                RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(ALUI, mat(Material.PISTON_BASE), ALUI, HGLASS,
                        COIL, HGLASS, COIL, mat(Material.HOPPER), COIL),
                Loader.getDisplaySw()) {

            @Override
            public ItemStack getProgressBar() {
                return mat(Material.SLIME_BALL);
            }

            @Override
            public void registerDefaultRecipes() {
                registerRecipe(4, new ItemStack[]{new ItemStack(Material.SLIME_BALL, 2), mat(Material.BUCKET)},
                        new ItemStack[]{UGItems.BIOMASS_BUCKET});
                registerRecipe(4,
                        new ItemStack[]{new ItemStack(Material.NETHER_WART_BLOCK, 2), mat(Material.BUCKET)},
                        new ItemStack[]{UGItems.BIOMASS_BUCKET});

                registerRecipe(8, new ItemStack[]{new ItemStack(Material.LEAVES, 32), mat(Material.BUCKET)},
                        new ItemStack[]{UGItems.BIOMASS_BUCKET});
                registerRecipe(8, new ItemStack[]{new ItemStack(Material.LEAVES_2, 32), mat(Material.BUCKET)},
                        new ItemStack[]{UGItems.BIOMASS_BUCKET});
                registerRecipe(8, new ItemStack[]{new ItemStack(Material.SEEDS, 16), mat(Material.BUCKET)},
                        new ItemStack[]{UGItems.BIOMASS_BUCKET});
                registerRecipe(8, new ItemStack[]{new ItemStack(Material.BEETROOT_SEEDS, 16), mat(Material.BUCKET)},
                        new ItemStack[]{UGItems.BIOMASS_BUCKET});
                registerRecipe(8, new ItemStack[]{new ItemStack(Material.MELON_SEEDS, 16), mat(Material.BUCKET)},
                        new ItemStack[]{UGItems.BIOMASS_BUCKET});
                registerRecipe(8, new ItemStack[]{new ItemStack(Material.PUMPKIN_SEEDS, 16), mat(Material.BUCKET)},
                        new ItemStack[]{UGItems.BIOMASS_BUCKET});
            }

            @Override
            public String getInventoryTitle() {
                return "&a生物质萃取机";
            }

            @Override
            public int getEnergyConsumption() {
                return 3;
            }
        }).registerChargeableBlock(false, 128);

        Slimefun.addWikiPage("BIOMASS_EXTRACTION_MACHINE",
                "https://gitee.com/freeze-dolphin/UltimateGenerators-wiki/blob/master/Machine-(Biomass-Extraction-Machine).md");

        /*
		 * (new BContainer(UGCategories.MACHINES, UGItems.ENDERIUM_EXTRACTOR,
		 * "ENDERIUM_EXTRACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
		 * SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_METAL_INGOT,
		 * SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.HARDENED_GLASS,
		 * SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.HARDENED_GLASS,
		 * SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.MEDIUM_CAPACITOR,
		 * SlimefunItems.RUNE_ENDER), false) {
		 * 
		 * @Override public ItemStack getProgressBar() { return
		 * mat(Material.ENDER_PEARL); }
		 * 
		 * @Override public void registerDefaultRecipes() { registerRecipe(320, new
		 * ItemStack[] {SlimefunItems.ENDER_LUMP_3, SlimefunItems.SILVER_DUST}, new
		 * ItemStack[] {UGItems.ENDERIUM_DUST}); registerRecipe(1200, new ItemStack[]
		 * {SlimefunItems.ENDER_LUMP_3, SlimefunItems.SILVER_INGOT}, new ItemStack[]
		 * {UGItems.ENDERIUM_INGOT}); }
		 * 
		 * @Override public String getInventoryTitle() { return "&5末影之尘精炼机"; }
		 * 
		 * @Override public int getEnergyConsumption() { return 16; }
		 * 
		 * }).registerChargeableBlock(false, 256);
         */
        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.GLASS_ELECTRICITY_TRANSMITTER, "GLASS_ELECTRICITY_TRANSMITTER", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            null, ALUI, null, 
            ALUI, UGItems.ELECTRICITY_STORAGE_UNIT, ALUI,
            null, ALUI, null
        }, new CustomItem(UGItems.REINFORCED_RAINBOW_GLASS, Integer.parseInt(DefaultConfig.getConfig("glass-electricity-transmitter-crafting-number-on-once"))))).registerDistibutingCapacitor(false, 16);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.BASIC_ELECTRICITY_STORAGE,
                "BASIC_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.BATTERY,
                    SlimefunItems.REDSTONE_ALLOY, ALUI, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.BATTERY,
                    SlimefunItems.REDSTONE_ALLOY, SlimefunItems.BATTERY})).registerDistibutingCapacitor(false, 640);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ADVANCED_ELECTRICITY_STORAGE,
                "ADVANCED_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{UGItems.ADVANCED_BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.REDSTONE_ALLOY, UGItems.BASIC_ELECTRICITY_STORAGE, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, UGItems.ADVANCED_BATTERY}))
                .registerDistibutingCapacitor(false, 2560);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.ALPHA_ELECTRICITY_STORAGE,
                "ALPHA_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{UGItems.ALPHA_BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.REDSTONE_ALLOY, UGItems.ADVANCED_ELECTRICITY_STORAGE,
                    SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY,
                    UGItems.ALPHA_BATTERY})).registerDistibutingCapacitor(false, 5120);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.BETA_ELECTRICITY_STORAGE,
                "BETA_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{UGItems.BETA_BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.REDSTONE_ALLOY, UGItems.ALPHA_ELECTRICITY_STORAGE, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, UGItems.BETA_BATTERY}))
                .registerDistibutingCapacitor(false, 40960);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE,
                "GAMMA_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{UGItems.GAMMA_BATTERY, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.REDSTONE_ALLOY, UGItems.BETA_ELECTRICITY_STORAGE, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.REDSTONE_ALLOY, SlimefunItems.REDSTONE_ALLOY, UGItems.GAMMA_BATTERY}))
                .registerDistibutingCapacitor(false, 327680);

        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.LAMBDA_ELECTRICITY_STORAGE,
                "LAMBDA_ELECTRICITY_STORAGE", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{UGItems.GAMMA_ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE,
                    UGItems.GAMMA_ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE,
                    SlimefunItems.REDSTONE_ALLOY, UGItems.GAMMA_ELECTRICITY_STORAGE,
                    UGItems.GAMMA_ELECTRICITY_STORAGE, UGItems.GAMMA_ELECTRICITY_STORAGE,
                    UGItems.GAMMA_ELECTRICITY_STORAGE})).registerDistibutingCapacitor(false, 2621440);

        Slimefun.addWikiPage("LAMBDA_ELECTRICITY_STORAGE",
                "https://gitee.com/freeze-dolphin/UltimateGenerators-wiki/blob/master/Easter-(Electricity-Storage).md");

        SlimefunStartup.getItemCfg().setValue("KAPA_ELECTRICITY_STORAGE.hide-in-guide", true);
        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.KAPA_ELECTRICITY_STORAGE,
                "KAPA_ELECTRICITY_STORAGE", UGRecipeType.NULL, new ItemStack[]{})).registerDistibutingCapacitor(false,
                20971520);

        SlimefunStartup.getItemCfg().setValue("PHI_ELECTRICITY_STORAGE.hide-in-guide", true);
        (new SlimefunItem(UGCategories.ELECTRICITY_STORAGE, UGItems.PHI_ELECTRICITY_STORAGE, "PHI_ELECTRICITY_STORAGE",
                UGRecipeType.NULL, new ItemStack[]{})).registerDistibutingCapacitor(false, 167772160);

        (new BContainer(UGCategories.MACHINES, UGItems.HEAVY_WATER_REFINING_MACHINE, "HEAVY_WATER_REFINING_MACHINE",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{HGLASS, null, HGLASS, COIL, SlimefunItems.ELECTRIC_DUST_WASHER_2, COIL, BCIRCUIT,
                    SlimefunItems.MEDIUM_CAPACITOR, BCIRCUIT},
                Loader.getDisplaySw()) {

            @Override
            public void registerDefaultRecipes() {
                registerRecipe(120, new ItemStack[]{mat(Material.WATER_BUCKET)},
                        new ItemStack[]{UGItems.HEAVY_WATER_BUCKET});
            }

            @Override
            public ItemStack getProgressBar() {
                return mat(Material.WATER_BUCKET);
            }

            @Override
            public String getInventoryTitle() {
                return "&7重水提炼机";
            }

            @Override
            public int getEnergyConsumption() {
                return 9;
            }
        }).registerChargeableBlock(false, 256);

    }

    public void implementSingleGenerators() {

        (new SlimefunItem(UGCategories.SINGLE_GENERATOR, UGItems.ENDLESS_GENERATOR, "ENDLESS_GENERATOR",
                UGRecipeType.NULL, Utils.buildRecipe(), true)).register(false, new EnergyTicker() {

            @Override
            public boolean explode(Location paramLocation) {
                return false;
            }

            @Override
            public double generateEnergy(Location l, SlimefunItem paramSlimefunItem, Config paramConfig) {
                return l.getBlock().getBlockPower() * 4096;
            }
        });

        (new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.NETHER_STAR_GENERATOR, "NETHER_STAR_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_PLATE,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.PLASTIC_SHEET,
                        SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.PLASTIC_SHEET, SlimefunItems.WITHER_PROOF_GLASS,
                        SlimefunItems.BIG_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS),
                Loader.getDisplaySw()) {

            @Override
            public ItemStack getProgressBar() {
                return new UniversalMaterial(Material.SKULL_ITEM, 1).toItemStack(1);
            }

            @Override
            public void registerDefaultRecipes() {
                registerFuel(new MachineFuel(120, mat(Material.NETHER_STAR)));
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

        (new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.DIESEL_GENERATOR, "DIESEL_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(null, SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT,
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.HEATING_COIL,
                        SlimefunItems.DURALUMIN_INGOT, SlimefunItems.HEATING_COIL),
                Loader.getDisplaySw()) {

            @Override
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
        }).registerUnrechargeableBlock(false, 256);

        (new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.BIOFUEL_GENERATOR, "BIOFUEL_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(SlimefunItems.HEATING_COIL, SlimefunItems.BILLON_INGOT, SlimefunItems.HEATING_COIL,
                        SlimefunItems.BILLON_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BILLON_INGOT,
                        SlimefunItems.HEATING_COIL, SlimefunItems.BILLON_INGOT, SlimefunItems.HEATING_COIL),
                Loader.getDisplaySw()) {

            @Override
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
        }).registerUnrechargeableBlock(false, 256);

        (new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.DRAGON_BREATH_GENERATOR, "DRAGON_BREATH_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(COIL, SlimefunItems.HARDENED_GLASS, COIL, SlimefunItems.PLASTIC_SHEET,
                        SlimefunItems.RUNE_ENDER, SlimefunItems.PLASTIC_SHEET, SlimefunItems.ENDER_LUMP_3,
                        SlimefunItems.MEDIUM_CAPACITOR, ACIRCUIT),
                Loader.getDisplaySw()) {

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

        (new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.REACTION_GENERATOR, "REACTION_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(SlimefunItems.LEAD_INGOT, MOTOR, SlimefunItems.LEAD_INGOT, COIL,
                        SlimefunItems.HARDENED_GLASS, COIL, BCIRCUIT, SlimefunItems.MEDIUM_CAPACITOR, BCIRCUIT),
                Loader.getDisplaySw()) {

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

        new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.COAL_GENERATOR, "ENLARGED_COAL_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, UGItems.SOLID_STORAGE_EXPANSION, null, ALUI,
                    SlimefunItems.COAL_GENERATOR, ALUI, null, UGItems.SOLID_STORAGE_EXPANSION, null},
                Loader.getDisplaySw()) {

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

        }.registerUnrechargeableBlock(false, 64);

        new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.BIO_REACTOR, "ENLARGED_BIO_REACTOR",
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, UGItems.SOLID_STORAGE_EXPANSION, null, ALUI,
                    SlimefunItems.BIO_REACTOR, ALUI, null, UGItems.SOLID_STORAGE_EXPANSION, null},
                Loader.getDisplaySw()) {

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

        }.registerUnrechargeableBlock(false, 128);

        new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.LAVA_GENERATOR, "ENLARGED_LAVA_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, UGItems.LIQUID_STORAGE_EXPANSION, null,
                    ALUI, SlimefunItems.LAVA_GENERATOR, ALUI, null, UGItems.LIQUID_STORAGE_EXPANSION, null},
                Loader.getDisplaySw()) {

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

        }.registerUnrechargeableBlock(false, 512);

        new BGenerator(UGCategories.SINGLE_GENERATOR, UGItems.COMBUSTION_REACTOR, "ENLARGED_COMBUSTION_REACTOR",
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, UGItems.LIQUID_STORAGE_EXPANSION, null,
                    ALUI, SlimefunItems.COMBUSTION_REACTOR, ALUI, null, UGItems.LIQUID_STORAGE_EXPANSION, null},
                Loader.getDisplaySw()) {

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

        }.registerUnrechargeableBlock(false, 256);

        (new MagnesiumGenerator(UGCategories.SINGLE_GENERATOR, UGItems.MAGNESIUM_GENERATOR, "MAGNESIUM_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                Utils.buildRecipe(null, SlimefunItems.ELECTRIC_MOTOR, null, SlimefunItems.COMPRESSED_CARBON,
                        new ItemStack(Material.WATER_BUCKET), SlimefunItems.COMPRESSED_CARBON,
                        SlimefunItems.DURALUMIN_INGOT, SlimefunItems.DURALUMIN_INGOT, SlimefunItems.DURALUMIN_INGOT),
                Loader.getDisplaySw())).registerChargeableBlock(false, 128);

        (new SlimefunItem(UGCategories.SINGLE_GENERATOR, UGItems.QUANTUM_SOLAR_GENERATOR, "QUANTUM_SOLAR_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.SOLAR_GENERATOR_4,
                    SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.SOLAR_GENERATOR_4,
                    SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SOLAR_GENERATOR_4,
                    SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.SOLAR_GENERATOR_4,
                    SlimefunItems.SOLAR_GENERATOR_4})).registerChargeableBlock(false, 65536,
                new ItemHandler[]{new EnergyTicker() {

                @Override
                public double generateEnergy(Location l, SlimefunItem item, Config data) {
                    if ((!l.getWorld().isChunkLoaded(l.getBlockX() >> 4, l.getBlockZ() >> 4))
                            || (l.getBlock().getLightFromSky() != 15)) {
                        return 0.0D;
                    }
                    if ((l.getWorld().getTime() < 12300L) || (l.getWorld().getTime() > 23850L)) {
                        return 1024.0D;
                    }
                    return 512.0D;
                }

                @Override
                public boolean explode(Location l) {
                    return false;
                }
            }});

    }

    public void implementModularGenerators() {

        (new EnderCrystalGenerator(UGCategories.MODULAR_GENERATOR, UGItems.ENDER_CRYSTAL_GENERATOR,
                "ENDER_CRYSTAL_GENERATOR", RecipeType.ANCIENT_ALTAR,
                new ItemStack[]{SlimefunItems.RUNE_ENDER, SlimefunItems.HOLOGRAM_PROJECTOR, SlimefunItems.RUNE_ENDER,
                    SlimefunItems.FREEZER_2, SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                    UGItems.MODULAR_GENERATOR_REGULATOR, SlimefunItems.RUNE_ENDER, SlimefunItems.NUCLEAR_REACTOR,
                    SlimefunItems.RUNE_ENDER}) {

            @Override
            public int getEnergyProductionPerCrystal() {
                return (Integer.parseInt(String.valueOf(Loader.getProperties().get("generation-per-crystal"))));
            }

            @Override
            public int getWarningCrystalNum() {
                return (Integer.parseInt(String.valueOf(Loader.getProperties().get("warning-crystal-number"))));
            }
        }).registerChargeableBlock(false, 14336);

        Slimefun.addWikiPage("ENDER_CRYSTAL_GENERATOR",
                "https://gitee.com/freeze-dolphin/UltimateGenerators-wiki/blob/master/Generator-(Ender-Crystal-Generator).md");

        (new SlimefunItem(UGCategories.MODULAR_GENERATOR, UGItems.ENDER_CRYSTAL_GENERATOR_BASE,
                "ENDER_CRYSTAL_GENERATOR_BASE", RecipeType.ANCIENT_ALTAR,
                new ItemStack[]{SlimefunItems.ENDER_LUMP_2, mat(Material.END_BRICKS), SlimefunItems.ENDER_LUMP_2,
                    mat(Material.END_BRICKS), SlimefunItems.ANCIENT_PEDESTAL, mat(Material.END_BRICKS),
                    SlimefunItems.ENDER_LUMP_2, mat(Material.END_BRICKS), SlimefunItems.ENDER_LUMP_2}))
                .register(false);

        Slimefun.addWikiPage("ENDER_CRYSTAL_GENERATOR_BASE",
                "https://gitee.com/freeze-dolphin/UltimateGenerators-wiki/blob/master/Generator-(Ender-Crystal-Generator).md");

        (new EnderCrystalStabilizer(UGCategories.MODULAR_GENERATOR, UGItems.ENDER_CRYSTAL_GENERATOR_STABILIZER,
                "ENDER_CRYSTAL_GENERATOR_STABILIZER", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 2), 1), null,
                    new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 2), 1), UGItems.ENDER_LUMP_4,
                    SlimefunItems.ELECTRIC_MOTOR, UGItems.ENDER_LUMP_4, SlimefunItems.RAINBOW_GLASS,
                    UGItems.ENDER_LUMP_4, SlimefunItems.RAINBOW_GLASS})).registerChargeableBlock(false, 256);

        Slimefun.addWikiPage("ENDER_CRYSTAL_GENERATOR_STABILIZER",
                "https://gitee.com/freeze-dolphin/UltimateGenerators-wiki/blob/master/Generator-(Ender-Crystal-Generator).md");

        /*
		 * (new SlimefunItem(UGCategories.MODULAR_GENERATOR,
		 * UGItems.ENDER_CRYSTAL_GENERATOR_BASE, "ENDER_CRYSTAL_GENERATOR_BASE",
		 * RecipeType.ENHANCED_CRAFTING_TABLE, Utils.buildRecipe(
		 * mat(Material.END_BRICKS), mat(Material.END_BRICKS), mat(Material.END_BRICKS),
		 * mat(Material.END_BRICKS), UGItems.ENDERIUM_INGOT, mat(Material.END_BRICKS),
		 * mat(Material.END_BRICKS), SlimefunItems.HARDENED_METAL_INGOT,
		 * mat(Material.END_BRICKS)))).register(false);
         */
        (new AReactor(UGCategories.MODULAR_GENERATOR, UGItems.THERMAL_NEUTRON_REACTOR, "THERMAL_NEUTRON_REACTOR",
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.CARBONADO_EDGED_CAPACITOR,
                    SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_PLATE, UGItems.NEUTRON_MODERATOR,
                    SlimefunItems.REINFORCED_PLATE, UGItems.NEUTRON_MODERATOR, SlimefunItems.REINFORCED_PLATE,
                    UGItems.NEUTRON_MODERATOR}) {

            @Override
            public void registerDefaultRecipes() {
                registerFuel(new MachineFuel(1200, SlimefunItems.URANIUM, SlimefunItems.NEPTUNIUM));
            }

            @Override
            public ItemStack getProgressBar() {
                return mat(Material.FLINT_AND_STEEL);
            }

            @Override
            public String getInventoryTitle() {
                return "&c热中子核能反应器";
            }

            @Override
            public int getEnergyProduction() {
                return 448;
            }

            @Override
            public ItemStack getCoolant() {
                return UGItems.THERMAL_NEUTRON_REACTOR_COOLANT_CELL;
            }

            @Override
            public void extraTick(Location l) {
            }
        }).registerChargeableBlock(false, 16384);

        (new RainbowGenerator(UGCategories.MODULAR_GENERATOR, UGItems.RAINBOW_REACTOR, "RAINBOW_REACTOR", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            UGItems.RAINBOW_ALLOY, UGItems.REINFORCED_RAINBOW_GLASS, UGItems.RAINBOW_ALLOY,
            SlimefunItems.SOLAR_GENERATOR, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.SOLAR_GENERATOR,
            UGItems.RAINBOW_ALLOY, UGItems.RAINBOW_ALLOY, UGItems.RAINBOW_ALLOY
        }) {

            @Override
            public void registerDefaultRecipes() {
                registerFuel(new MachineFuel(600, UGItems.RAINBOW_ALLOY, SlimefunItems.MAGNESIUM_DUST));
            }

            @Override
            public String getInventoryTitle() {
                return "&d彩虹反应器";
            }

            @Override
            public int getEnergyProduction() {
                return 768;
            }

            @Override
            public ItemStack getProgressBar() {
                return UGItems.REINFORCED_RAINBOW_GLASS;
            }

            @Override
            public int getSpeed() {
                return 1;
            }
        }).registerChargeableBlock(false, 32768);

        Slimefun.addWikiPage("RAINBOW_REACTOR", "https://gitee.com/freeze-dolphin/UltimateGenerators-wiki/blob/master/Generator-(Rainbow-Reactor).md");

    }

    private ItemStack mat(Material m) {
        return new ItemStack(m);
    }

}
