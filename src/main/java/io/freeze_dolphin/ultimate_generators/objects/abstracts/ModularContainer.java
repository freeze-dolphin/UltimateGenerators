package io.freeze_dolphin.ultimate_generators.objects.abstracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.ultimate_generators.Utils;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;

public abstract class ModularContainer extends SlimefunItem {

	public static Map<Block, MachineRecipe> processing = new HashMap<>();
	public static Map<Block, Integer> progress = new HashMap<>();
	protected List<MachineRecipe> recipes = new ArrayList<>();

	private static final int[] border = 
		{
				0, 
				9,                             17, 
				18, 19, 20, 21,     23, 24, 25, 26, 
				27,                             35, 
				36,                             44
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
	protected static final int machineInfo = 8;

	private final String ID;
	private final boolean displayMachineInfo;

	public ModularContainer(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, boolean displayMachineInfo) {
		super(category, item, id, recipeType, recipe);

		ID = id;
		this.displayMachineInfo = displayMachineInfo;

		new BlockMenuPreset(id, getInventoryTitle()) {
			public void init() {
				constructMenu(this);
			}

			public void newInstance(BlockMenu menu, Block b) {
			}

			public boolean canOpen(Block b, Player p) {
				return Utils.reflectCanOpenMethod(b, p);
			}

			public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
				if (flow.equals(ItemTransportFlow.INSERT)) {
					return getInputSlots();
				}
				return getOutputSlots();
			}
		};
		registerBlockHandler(id, new SlimefunBlockHandler() {
			public void onPlace(Player p, Block b, SlimefunItem item) {
			}

			public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
				BlockMenu inv = BlockStorage.getInventory(b);
				if (inv != null) {
					for (int slot : getInputSlots()) {
						if (inv.getItemInSlot(slot) != null) {
							b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
							inv.replaceExistingItem(slot, null);
						}
					}
					for (int slot : getOutputSlots()) {
						if (inv.getItemInSlot(slot) != null) {
							b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
							inv.replaceExistingItem(slot, null);
						}
					}
				}
				ModularContainer.progress.remove(b);
				ModularContainer.processing.remove(b);
				return true;
			}
		});
		registerDefaultRecipes();
	}

	protected void constructMenu(BlockMenuPreset preset) {
		for (int i : border) {
			preset.addItem(i,
					new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " ", new String[0]),
					new ChestMenu.MenuClickHandler() {
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}
		for (int i : border_in) {
			preset.addItem(i,
					new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 9), " ", new String[0]),
					new ChestMenu.MenuClickHandler() {
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}
		for (int i : border_out) {
			preset.addItem(i,
					new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 1), " ", new String[0]),
					new ChestMenu.MenuClickHandler() {
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}
		preset.addItem(indicator, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " ", new String[0]), new MenuClickHandler() {
			public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
				return false;
			}
		});
		for (int i : getOutputSlots()) {
			preset.addMenuClickHandler(i, new ChestMenu.AdvancedMenuClickHandler() {
				public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
					return false;
				}

				public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor,
						ClickAction action) {
					return (cursor == null) || (cursor.getType() == null) || (cursor.getType() == Material.AIR);
				}
			});
		}

		if (displayMachineInfo) {
			preset.addItem(machineInfo, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), "&f机器信息", "&7 - &3耗电量: &e" + getEnergyConsumption() * 2 + " J/s", "&7 - &3工作速度: &e" + (getSpeed() == 1 ? "&f默认" : getSpeed())), new MenuClickHandler() {

				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}

			});
		} else {
			preset.addItem(machineInfo, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " "), new MenuClickHandler() {

				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}

			});
		}
	}


	public abstract ItemStack getProgressBar();
	public abstract void registerDefaultRecipes();
	public abstract String getInventoryTitle();
	public abstract int getEnergyConsumption();

	public int getSpeed() {
		return 1;
	}

	public String getMachineIdentifier() { return ID; }

	public MachineRecipe getProcessing(Block b) {
		return (MachineRecipe) processing.get(b);
	}

	public boolean isProcessing(Block b) {
		return getProcessing(b) != null;
	}

	public void registerRecipe(MachineRecipe recipe) {
		recipe.setTicks(recipe.getTicks() / getSpeed());
		recipes.add(recipe);
	}

	public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
		registerRecipe(new MachineRecipe(seconds, input, output));
	}

	private Inventory inject(Block b) {
		int size = BlockStorage.getInventory(b).toInventory().getSize();
		Inventory inv = Bukkit.createInventory(null, size);
		for (int i = 0; i < size; i++) {
			inv.setItem(i, new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
		}
		for (int slot : getOutputSlots()) {
			inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
		}
		return inv;
	}

	protected boolean fits(Block b, ItemStack[] items) {
		return inject(b).addItem(items).isEmpty();
	}

	protected void pushItems(Block b, ItemStack[] items) {
		Inventory inv = inject(b);
		inv.addItem(items);
		for (int slot : getOutputSlots()) {
			BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot));
		}
	}

	public void register(boolean slimefun) {
		addItemHandler(new ItemHandler[]{new BlockTicker() {
			public void tick(Block b, SlimefunItem sf, Config data) {
				ModularContainer.this.tick(b);
			}

			public void uniqueTick() {
			}

			public boolean isSynchronized() {
				return false;
			}
		}});
		super.register(slimefun);
	}

	public abstract boolean checkStructure(Block b);

	protected void tick(Block b) {

		if (b.getBlockPower() > 1) { return; }

		if (!checkStructure(b)) { return; }

		ItemMeta im;
		if (isProcessing(b)) {
			int timeleft = ((Integer) progress.get(b)).intValue();
			if (timeleft > 0) {
				ItemStack item = getProgressBar().clone();
				item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe) processing.get(b)).getTicks()));
				im = item.getItemMeta();
				im.setDisplayName(" ");
				List<String> lore = new ArrayList<>();
				lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe) processing.get(b)).getTicks()));
				lore.add("");
				lore.add(MachineHelper.getTimeLeft(timeleft / 2));
				im.setLore(lore);
				item.setItemMeta(im);

				BlockStorage.getInventory(b).replaceExistingItem(indicator, item);
				if (ChargableBlock.isChargable(b)) {
					if (ChargableBlock.getCharge(b) < getEnergyConsumption()) {
						return;
					}
					ChargableBlock.addCharge(b, -getEnergyConsumption());
					progress.put(b, Integer.valueOf(timeleft - 1));
				} else {
					progress.put(b, Integer.valueOf(timeleft - 1));
				}
			} else {
				BlockStorage.getInventory(b).replaceExistingItem(indicator,
						new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " ", new String[0]));
				pushItems(b, (ItemStack[]) ((MachineRecipe) processing.get(b)).getOutput().clone());

				progress.remove(b);
				processing.remove(b);
			}
		} else {
			MachineRecipe r = null;
			Map<Integer, Integer> found = new HashMap<>();
			for (MachineRecipe recipe : recipes) {
				for (ItemStack input : recipe.getInput()) {
					for (int slot : getInputSlots()) {
						if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), input,
								true)) {
							found.put(Integer.valueOf(slot), Integer.valueOf(input.getAmount()));
							break;
						}
					}
				}
				if (found.size() == recipe.getInput().length) {
					r = recipe;
					break;
				}
				found.clear();
			}
			if (r != null) {
				if (!fits(b, r.getOutput())) {
					return;
				}
				for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
					BlockStorage.getInventory(b).replaceExistingItem(((Integer) entry.getKey()).intValue(),
							InvUtils.decreaseItem(
									BlockStorage.getInventory(b).getItemInSlot(((Integer) entry.getKey()).intValue()),
									((Integer) entry.getValue()).intValue()));
				}
				processing.put(b, r);
				progress.put(b, Integer.valueOf(r.getTicks()));
			}
		}
	}
}
