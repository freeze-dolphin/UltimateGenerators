package io.freeze_dolphin.ultimate_generators.objects.machines.ender_crystal_generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_8.ParticleEffect;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.energy.EnergyTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

import org.apache.commons.lang3.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.ultimate_generators.Loader;
import io.freeze_dolphin.ultimate_generators.Utils;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;

public abstract class EnderCrystalGenerator extends SlimefunItem {

	public static Map<Location, MachineFuel> processing = new HashMap<Location, MachineFuel>();
	public static Map<Location, Integer> progress = new HashMap<Location, Integer>();

	private Set<MachineFuel> recipes = new HashSet<MachineFuel>();

	private static final int[] border = 
		{
				0,                               8, 
				9,                              17, 
				18, 19, 20, 21,     23, 24, 25, 26, 
				27,                             35, 
				36
		};
	private static final int[] border_in = 
		{
				1,                      7, 
				10, 11, 12, 13, 14, 15, 16
		};
	private static final int[] border_out = 
		{
				28, 29, 30, 31, 32, 33, 34, 
				37,                     43
		};

	public int[] getInputSlots() { 
		return new int[] { 2,  3,  4,  5,  6 }; 
	}

	public int[] getOutputSlots() { 
		return new int[] { 38, 39, 40, 41, 42 }; 
	}

	protected static final int indicator = 22;
	protected static final int structureInfo = 44;

	private final String ID;

	public EnderCrystalGenerator(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);

		ID = id;

		new BlockMenuPreset(id, getInventoryTitle()) {

			@Override
			public void init() {
				constructMenu(this);
			}

			@Override
			public void newInstance(BlockMenu menu, Block b) {
			}

			@Override
			public boolean canOpen(Block b, Player p) {
				return Utils.reflectCanOpenMethod(b, p);
			}

			@Override
			public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
				if (flow.equals(ItemTransportFlow.INSERT)) return getInputSlots();
				else return getOutputSlots();
			}
		};

		registerBlockHandler(id, new SlimefunBlockHandler() {

			@Override
			public void onPlace(Player p, Block b, SlimefunItem item) {

			}

			@Override
			public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
				BlockMenu inv = BlockStorage.getInventory(b);
				if (inv != null) {
					for (int slot: getInputSlots()) {
						if (inv.getItemInSlot(slot) != null) {
							b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
							inv.replaceExistingItem(slot, null);
						}
					}
					for (int slot: getOutputSlots()) {
						if (inv.getItemInSlot(slot) != null) {
							b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
							inv.replaceExistingItem(slot, null);
						}
					}
				}

				for (Entity ety : b.getWorld().getNearbyEntities(b.getLocation(), 4D, 4D, 4D)) {
					if (ety.getType().equals(EntityType.ENDER_CRYSTAL)) {
						EnderCrystal ec = (EnderCrystal) ety;
						try {
							if (!ec.isShowingBottom() && ec.getCustomName().equals(ChatColor.translateAlternateColorCodes('&', "&d&l强化末影水晶&r"))) {
								if (ec.getBeamTarget().getBlock().getLocation().distance(Utils.locModify(b.getLocation(), 0.5F, -1.5F, 0.5F).getBlock().getLocation()) == 0D) ec.setBeamTarget(null);
								continue;
							}
						} catch (Exception ex) {
							ec.setBeamTarget(null);
						}

					}
				}

				progress.remove(b.getLocation());
				processing.remove(b.getLocation());
				return true;
			}
		});
		registerDefaultRecipes();
	}

	private void constructMenu(BlockMenuPreset preset) {
		for (int i: border) {
			preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " "), new MenuClickHandler() {

				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}

			});
		}
		for (int i: border_in) {
			preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 9), " "), new MenuClickHandler() {

				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}

			});
		}
		for (int i: border_out) {
			preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 1), " "), new MenuClickHandler() {

				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}

			});
		}

		for (int i: getOutputSlots()) {
			preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

				@Override
				public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
					return false;
				}

				@Override
				public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
					return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
				}
			});
		}

		preset.addItem(indicator, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " "), new MenuClickHandler() {

			@Override
			public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
				return false;
			}

		});

		preset.addItem(structureInfo, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), "&f机器信息", "&3结构完整性: &4&l✘&r", "&3已连接水晶: &e0", "&3机器状态: &e无"), new MenuClickHandler() {

			@Override
			public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
				return false;
			}

		});
	}

	private void registerDefaultRecipes() {
		registerFuel(new MachineFuel(3600, UGItems.RUNE_COMPLEX_ENDER, SlimefunItems.STONE_CHUNK));
	}

	private String getInventoryTitle() {
		return "&d末影水晶发电机";
	}

	private ItemStack getProgressBar() {
		return new ItemStack(Material.END_CRYSTAL);
	}

	public String getMachineIdentifier() { return ID; }

	public MachineFuel getProcessing(Location l) {
		return processing.get(l);
	}

	public boolean isProcessing(Location l) {
		return progress.containsKey(l);
	}

	private void registerFuel(MachineFuel fuel) {
		this.recipes.add(fuel);
	}

	private boolean checkStructure(Block b) {
		Location l = b.getLocation();

		List<Location> ll = new ArrayList<>();

		ll.add(Utils.locModify(l, -1, -1, -1));
		ll.add(Utils.locModify(l, -1, -1, 0));
		ll.add(Utils.locModify(l, -1, -1, 1));
		ll.add(Utils.locModify(l, 0, -1, -1));
		ll.add(Utils.locModify(l, 0, -1, 1));
		ll.add(Utils.locModify(l, 1, -1, -1));
		ll.add(Utils.locModify(l, 1, -1, 0));
		ll.add(Utils.locModify(l, 1, -1, 1));

		Location lll = Utils.locModify(l, 0, -1, 0);
		if (BlockStorage.checkID(lll) == null || !BlockStorage.checkID(lll).equals("ENDER_CRYSTAL_GENERATOR_STABILIZER") || lll.getBlock().isBlockPowered()) return false;

		for (Location lc : ll) {
			if (BlockStorage.checkID(lc) == null || !BlockStorage.checkID(lc).equals("ENDER_CRYSTAL_GENERATOR_BASE")) {
				return false;
			}
		}

		return true;

	}

	@Override
	public void register(boolean slimefun) {
		addItemHandler(new EnergyTicker() {

			@Override
			public double generateEnergy(Location l, SlimefunItem sf, Config data) {
				Location genL = Utils.locModify(l.getBlock().getLocation(), 0.5F, -1.5F, 0.5F);
				Location partL = Utils.locModify(genL, 0F, 2F, 0F);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Loader.getImplement(), new Runnable() {

					@Override
					public void run() {

						List<EnderCrystal> ecc = new ArrayList<>();

						for (Entity ety : l.getWorld().getNearbyEntities(l, 4D, 4D, 4D)) {
							if (ety.getType().equals(EntityType.ENDER_CRYSTAL)) {
								EnderCrystal ec = (EnderCrystal) ety;								
								try {
									if (!ec.isShowingBottom() && ec.getCustomName().equals(ChatColor.translateAlternateColorCodes('&', "&d&l强化末影水晶&r"))) {
										if (ec.getBeamTarget() == null) ec.setBeamTarget(genL);
										if (ec.getBeamTarget().getBlock().getLocation().distance(genL.getBlock().getLocation()) == 0D) ecc.add(ec);
										continue;
									}
								} catch (Exception ex) {
									ec.setBeamTarget(null);
								}

							}
						}
						setConnectedCrystalNum(l.getBlock(), ecc.size());
					}
				});

				if (l.getBlock().isBlockPowered()) { return 0D; }

				if (!checkStructure(l.getBlock())) {
					Utils.asyncDelay(new Runnable() {

						@Override
						public void run() {
							BlockStorage.getInventory(l).replaceExistingItem(structureInfo, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), "&f机器信息", "&3结构完整性: &4&l✘&r", "&3已连接水晶: &e" + getConnectedCrystalNum(l.getBlock()), "&3机器状态: &a正常"));
						}
					});
					return 0D;
				}

				if (isProcessing(l)) {

					Utils.asyncDelay(new Runnable() {

						@Override
						public void run() {
							
							try {
								ParticleEffect.END_ROD.display(partL, 0F, 0F, 0F, 0.1F, RandomUtils.nextInt(1, getConnectedCrystalNum(l.getBlock())));
							} catch (Exception e) {}
							
							if (getConnectedCrystalNum(l.getBlock()) > getWarningCrystalNum()) {
								int warn = getConnectedCrystalNum(l.getBlock()) - getWarningCrystalNum();
								if (RandomUtils.nextInt(0, 100000) < warn) {
									BlockStorage.clearBlockInfo(l);
									l.getBlock().setType(Material.AIR);
									l.getWorld().createExplosion(l, RandomUtils.nextFloat(4F, 12F), RandomUtils.nextBoolean());
									return;
								} else {
									BlockStorage.getInventory(l).replaceExistingItem(structureInfo, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), "&f机器信息", "&3结构完整性: &2&l✔&r", "&3已连接水晶: &e" + getConnectedCrystalNum(l.getBlock()), "&3机器状态: &c超负荷运载 &e(堆芯溶解几率 - " + (warn / 100000F) + ")"));
									try {
										ParticleEffect.VILLAGER_ANGRY.display(partL, 0F, 0F, 0F, 0.1F, RandomUtils.nextInt(1, 1 + warn));
									} catch (Exception e) {}
								}
							} else {
								BlockStorage.getInventory(l).replaceExistingItem(structureInfo, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), "&f机器信息", "&3结构完整性: &2&l✔&r", "&3已连接水晶: &e" + getConnectedCrystalNum(l.getBlock()), "&3机器状态: &a正常"));
								try {
									ParticleEffect.DRAGON_BREATH.display(partL, 0F, 0F, 0F, 0.3F, RandomUtils.nextInt(1, 3));
								} catch (Exception e) {}
							}
						}
					});

					int timeleft = progress.get(l);
					if (timeleft > 0) {
						ItemStack item = getProgressBar().clone();
						item.setDurability(MachineHelper.getDurability(item, timeleft, processing.get(l).getTicks()));
						ItemMeta im = item.getItemMeta();
						im.setDisplayName(" ");
						List<String> lore = new ArrayList<String>();
						lore.add(MachineHelper.getProgress(timeleft, processing.get(l).getTicks()));
						lore.add("");
						lore.add(MachineHelper.getTimeLeft(timeleft / 2));
						im.setLore(lore);
						item.setItemMeta(im);

						BlockStorage.getInventory(l).replaceExistingItem(indicator, item);

						if (ChargableBlock.isChargable(l)) {
							if (ChargableBlock.getMaxCharge(l) - ChargableBlock.getCharge(l) >= getEnergyProductionPerCrystal() * getConnectedCrystalNum(l.getBlock())) {
								ChargableBlock.addCharge(l, getEnergyProductionPerCrystal() * getConnectedCrystalNum(l.getBlock()));
								progress.put(l, timeleft - 1);
								return ChargableBlock.getCharge(l);
							}
							return 0;
						}
						else {
							progress.put(l, timeleft - 1);
							return getEnergyProductionPerCrystal() * getConnectedCrystalNum(l.getBlock());
						}
					}
					else {
						ItemStack fuel = processing.get(l).getInput();
						if (SlimefunManager.isItemSimiliar(fuel, new ItemStack(Material.LAVA_BUCKET), true)) {
							pushItems(l, new ItemStack[] {new ItemStack(Material.BUCKET)});
						}
						else if (SlimefunManager.isItemSimiliar(fuel, SlimefunItems.BUCKET_OF_FUEL, true)) {
							pushItems(l, new ItemStack[] {new ItemStack(Material.BUCKET)});
						}
						else if (SlimefunManager.isItemSimiliar(fuel, SlimefunItems.BUCKET_OF_OIL, true)) {
							pushItems(l, new ItemStack[] {new ItemStack(Material.BUCKET)});
						}
						else if (SlimefunManager.isItemSimiliar(fuel, UGItems.BIOFUEL_BUCKET, true)) {
							pushItems(l, new ItemStack[] {new ItemStack(Material.BUCKET)});
						}
						else if (SlimefunManager.isItemSimiliar(fuel, UGItems.BIOMASS_BUCKET, true)) {
							pushItems(l, new ItemStack[] {new ItemStack(Material.BUCKET)});
						}
						else if (SlimefunManager.isItemSimiliar(fuel, UGItems.DIESEL_BUCKET, true)) {
							pushItems(l, new ItemStack[] {new ItemStack(Material.BUCKET)});
						}
						BlockStorage.getInventory(l).replaceExistingItem(indicator, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " "));

						progress.remove(l);
						processing.remove(l);
						return 0;
					}
				}
				else {

					BlockStorage.getInventory(l).replaceExistingItem(structureInfo, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), "&f机器信息", "&3结构完整性: &2&l✔&r", "&3已连接水晶: &e" + getConnectedCrystalNum(l.getBlock()), "&3机器状态: &a正常"));

					MachineFuel r = null;
					Map<Integer, Integer> found = new HashMap<Integer, Integer>();
					outer:
						for (MachineFuel recipe: recipes) {
							for (int slot: getInputSlots()) {
								if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(l).getItemInSlot(slot), recipe.getInput(), true)) {
									found.put(slot, recipe.getInput().getAmount());
									r = recipe;
									break outer;
								}
							}
						}

					if (r != null) {
						for (Map.Entry<Integer, Integer> entry: found.entrySet()) {
							BlockStorage.getInventory(l).replaceExistingItem(entry.getKey(), InvUtils.decreaseItem(BlockStorage.getInventory(l).getItemInSlot(entry.getKey()), entry.getValue()));
						}
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

	public abstract int getEnergyProductionPerCrystal();
	public abstract int getWarningCrystalNum();

	public Set<MachineFuel> getFuelTypes() {
		return this.recipes;
	}

	private Inventory inject(Location l) {
		int size = BlockStorage.getInventory(l).toInventory().getSize();
		Inventory inv = Bukkit.createInventory(null, size);
		for (int i = 0; i < size; i++) {
			inv.setItem(i, new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
		}
		for (int slot: getOutputSlots()) {
			inv.setItem(slot, BlockStorage.getInventory(l).getItemInSlot(slot));
		}
		return inv;
	}

	protected void pushItems(Location l, ItemStack[] items) {
		Inventory inv = inject(l);
		inv.addItem(items);

		for (int slot: getOutputSlots()) {
			BlockStorage.getInventory(l).replaceExistingItem(slot, inv.getItem(slot));
		}
	}

	private static void setConnectedCrystalNum(Block b, int num) {
		BlockStorage.addBlockInfo(b, "crystal-number", String.valueOf(num));
	}

	@SuppressWarnings("deprecation")
	private static int getConnectedCrystalNum(Block b) {
		if (!BlockStorage.hasBlockInfo(b)) return 0;
		if (BlockStorage.getBlockInfo(b, "crystal-number") == null) return 0;
		if (BlockStorage.getBlockInfo(b, "crystal-number").equals("")) return 0;
		return Integer.parseInt(BlockStorage.getBlockInfo(b, "crystal-number"));
	}

}
