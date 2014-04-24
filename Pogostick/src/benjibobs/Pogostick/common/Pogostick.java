package benjibobs.Pogostick.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=Pogostick.modid,name="Pogostick",version="1.5")


public class Pogostick {
	
	public static CreativeTabs tabPogostick = new CreativeTabs("Pogostick") {

		@Override
		public Item getTabIconItem() {
			
			return fpogo;
			
		}
       
};
	
	public static final String modid = "bpogostick";
	
	public static Item bpogo;
	public static Item wpogo;
	public static Item pogoboots;
	public static Item spogo;
	public static Item ipogo;
	public static Item gpogo;
	public static Item dpogo;
	public static Item tntpogo;
	public static Item dmgpogo;
	public static Item kpogo;
	public static Item fpogo;
	
	public static boolean ground;
	
	public static Block tramp;
	
	static ArmorMaterial armorPOGO = EnumHelper.addArmorMaterial("armorPOGO", 35, new int[] {1, 1, 1, 1}, 0);

	@Instance("bpogostick")
	public static Pogostick instance = new Pogostick();

	@EventHandler
	public void InitPogostick(FMLPreInitializationEvent event){ 
	
	bpogo = new ItemPogo();
	bpogo.setUnlocalizedName("bpogostick");
    GameRegistry.registerItem(bpogo, "pogo_bpogostick");
    
    wpogo = new ItemWoodPogo();
    wpogo.setUnlocalizedName("woodpogo");
    GameRegistry.registerItem(wpogo, "pogo_woodpogo");
    
    spogo = new ItemStonePogo();
    spogo.setUnlocalizedName("stonepogo");
    GameRegistry.registerItem(spogo, "pogo_stonepogo");
    
    ipogo = new ItemIronPogo();
    ipogo.setUnlocalizedName("ironpogo");
    GameRegistry.registerItem(ipogo, "pogo_ironpogo");
    
    gpogo = new ItemGoldPogo();
    gpogo.setUnlocalizedName("goldpogo");
    GameRegistry.registerItem(gpogo, "pogo_goldpogo");
    
    dpogo = new ItemDiamondPogo();
    dpogo.setUnlocalizedName("diamondpogo");
    GameRegistry.registerItem(dpogo, "pogo_diamondpogo");
    
    tntpogo = new ItemTNTPogo();
    tntpogo.setUnlocalizedName("tntpogo");
    GameRegistry.registerItem(tntpogo, "pogo_tntpogo");
    
    dmgpogo = new ItemHarmPogo();
    dmgpogo.setUnlocalizedName("dmgpogo");
    GameRegistry.registerItem(dmgpogo, "pogo_dmgpogo");
    
    kpogo = new ItemKBPogo();
    kpogo.setUnlocalizedName("kpogo");
    GameRegistry.registerItem(kpogo, "pogo_kpogo");
    
    fpogo = new ItemFirePogo();
    fpogo.setUnlocalizedName("firepogo");
    GameRegistry.registerItem(fpogo, "pogo_firepogo");
    
    tramp = new BlockTramp("bouncer");
    tramp.setBlockName("bouncer");
    tramp.setHardness(0.7F);
    tramp.setStepSound(Block.soundTypeCloth);
    tramp.setResistance(0.7F);
    GameRegistry.registerBlock(tramp, "pogo_Bouncer");
    
    pogoboots = new ItemPogoboots(armorPOGO, 5, 3);
    pogoboots.setUnlocalizedName("pogoboots");
    GameRegistry.registerItem(pogoboots, "pogo_pogoboots");
    
    
    /** Recipes */
    
    ItemStack bouncerStack = new ItemStack(tramp);
    ItemStack pogoStack = new ItemStack(bpogo);
    ItemStack woolStack = new ItemStack(Blocks.wool);
    ItemStack stickStack = new ItemStack(Items.stick);
    ItemStack slimeStack = new ItemStack(Items.slime_ball);
    ItemStack emeraldStack = new ItemStack(Items.emerald);
    ItemStack woodStack = new ItemStack(Blocks.planks);
    ItemStack stoneStack = new ItemStack(Blocks.cobblestone);
    ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
    ItemStack goldIngotStack = new ItemStack(Items.gold_ingot);
    ItemStack diaStack = new ItemStack(Items.diamond);
    ItemStack tntStack = new ItemStack(Blocks.tnt);
    ItemStack dpStack = new ItemStack(dpogo);
    ItemStack potStack = new ItemStack(Items.diamond_sword);
    ItemStack silkStack = new ItemStack(Items.string);
    ItemStack blazeStack = new ItemStack(Items.blaze_powder);
    ItemStack fireworkStack = new ItemStack(Items.fireworks);

    GameRegistry.addRecipe(new ItemStack(tramp), "xxx", "xyx", "xxx", 'x', woolStack, 'y', pogoStack);  
    GameRegistry.addRecipe(new ItemStack(bpogo), " x ", " x ", " s ", 'x', stickStack, 's', slimeStack);
    GameRegistry.addRecipe(new ItemStack(pogoboots), "   ", "e e", "s s", 'e',emeraldStack,'s', slimeStack);
    GameRegistry.addRecipe(new ItemStack(pogoboots), "e e", "s s", "   ", 'e',emeraldStack,'s', slimeStack);
    GameRegistry.addRecipe(new ItemStack(wpogo), "   ", " p ", " w ", 'w', woodStack , 'p', pogoStack);
    GameRegistry.addRecipe(new ItemStack(spogo), "   ", " p ", " s ", 'p', pogoStack, 's', stoneStack);
    GameRegistry.addRecipe(new ItemStack(ipogo), "   ", " p ", " i ", 'p', pogoStack, 'i', ironIngotStack);
    GameRegistry.addRecipe(new ItemStack(gpogo), "   ", " p ", " g ", 'p', pogoStack, 'g', goldIngotStack);
    GameRegistry.addRecipe(new ItemStack(dpogo), "   ", " p ", " d ", 'd', diaStack, 'p', pogoStack);
    GameRegistry.addRecipe(new ItemStack(tntpogo), "   ", " p ", " t ", 'p', dpStack, 't', tntStack);
    GameRegistry.addRecipe(new ItemStack(dmgpogo), "   ", " p ", " h ", 'p', pogoStack, 'h', potStack);
    GameRegistry.addRecipe(new ItemStack(wpogo), " p ", " w ", "   ", 'w', woodStack , 'p', pogoStack);
    GameRegistry.addRecipe(new ItemStack(spogo), " p ", " s ", "   ", 'p', pogoStack, 's', stoneStack);
    GameRegistry.addRecipe(new ItemStack(ipogo), " p ", " i ", "   ", 'p', pogoStack, 'i', ironIngotStack);
    GameRegistry.addRecipe(new ItemStack(gpogo), " p ", " g ", "   ", 'p', pogoStack, 'g', goldIngotStack);
    GameRegistry.addRecipe(new ItemStack(dpogo), " p ", " d ", "   ", 'd', diaStack, 'p', pogoStack);
    GameRegistry.addRecipe(new ItemStack(tntpogo), " p ", " t ", "   ", 'p', dpStack, 't', tntStack);
    GameRegistry.addRecipe(new ItemStack(dmgpogo), " p ", " h ", "   ", 'p', pogoStack, 'h', potStack);
    GameRegistry.addRecipe(new ItemStack(kpogo), "   ", " p ", " k ", 'p', pogoStack, 'k', bouncerStack);
    GameRegistry.addRecipe(new ItemStack(kpogo), " p ", " k ", "   ", 'p', pogoStack, 'k', bouncerStack);
    GameRegistry.addRecipe(new ItemStack(fpogo), "   ", " p ", " b ", 'p', pogoStack, 'b', blazeStack);
    GameRegistry.addRecipe(new ItemStack(fpogo), " p ", " b ", "   ", 'p', pogoStack, 'b', blazeStack);
    
	MinecraftForge.EVENT_BUS.register(new PogostickEvents());
    
	}
	
	@EventHandler
	@SideOnly(Side.CLIENT)
	public void Render(FMLPreInitializationEvent event){
		RenderingRegistry.addNewArmourRendererPrefix("armorPOGO");
	}

}
