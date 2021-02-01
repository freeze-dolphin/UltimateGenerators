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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.ultimate_generators.Utils;
import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;

public class EnderCrystalGenerator extends SlimefunItem {

	public static Map<Location, MachineFuel> processing = new HashMap<Location, MachineFuel>();
	public static Map<Location, Integer> progress = new HashMap<Location, Integer>();

	private Set<MachineFuel> recipes = new HashSet<MachineFuel>();

	private static final int[] border = 
		{
				0,                             8, 
				9,                             17, 
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
	private final boolean displayMachineInfo;

	public EnderCrystalGenerator(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, boolean displayMachineInfo) {
		super(category, item, id, recipeType, recipe);

		ID = id;
		this.displayMachineInfo = displayMachineInfo;

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
		
		preset.addItem(machineInfo, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " "), new MenuClickHandler() {

			@Override
			public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
				return false;
			}

		});
	}

	private void registerDefaultRecipes() {

	}

	private String getInventoryTitle() {
		return "&d末影水晶发电机";
	}
	
	private int getSpeed() {
		return 1;
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

	private boolean checkStructure(Block b) {
		Location l = b.getLocation();

		List<Location> ll = new ArrayList<>();

		ll.add(Utils.locModify(l, -1, -1, -1));
		ll.add(Utils.locModify(l, -1, -1, 0));
		ll.add(Utils.locModify(l, -1, -1, 1));
		ll.add(Utils.locModify(l, 0, -1, -1));
		ll.add(Utils.locModify(l, 0, -1, 0));
		ll.add(Utils.locModify(l, 0, -1, 1));
		ll.add(Utils.locModify(l, 1, -1, -1));
		ll.add(Utils.locModify(l, 1, -1, 0));
		ll.add(Utils.locModify(l, 1, -1, 1));

		for (Location lc : ll) {
			if (!BlockStorage.checkID(lc).equals("ENDER_CRYSTAL_GENERATOR_BASE")) {
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

				if (l.getBlock().isBlockPowered()) { return 0D; }

				if (!checkStructure(l.getBlock())) {
					BlockStorage.getInventory(l).replaceExistingItem(structureInfo, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 14), "&c结构不完整"));
					return 0D;
				}
				
				BlockStorage.getInventory(l).replaceExistingItem(structureInfo, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 14), "&c结构不完整"));
				
				if (isProcessing(l)) {
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
							if (ChargableBlock.getMaxCharge(l) - ChargableBlock.getCharge(l) >= getEnergyProduction()) {
								ChargableBlock.addCharge(l, getEnergyProduction());
								progress.put(l, timeleft - 1);
								return ChargableBlock.getCharge(l);
							}
							return 0;
						}
						else {
							progress.put(l, timeleft - 1);
							return getEnergyProduction();
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
}
