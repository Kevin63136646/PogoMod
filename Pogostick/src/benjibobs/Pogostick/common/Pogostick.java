package benjibobs.Pogostick.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.server.FMLServerHandler;

//MOD BASICS
@Mod(modid=Pogostick.modid,name="Pogostick",version="Public Release")


public class Pogostick {
	
	public static CreativeTabs tabPogostick = new CreativeTabs("Pogostick") {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			
			return fpogo;
		}
       
};
	
	public static final String modid = "bpogostick";
	
	public static boolean newyear = true;
	
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
	public static Block mantramp;
	
	public final MinecraftServer mcs = FMLServerHandler.instance().getServer();
	
	static ArmorMaterial armorPOGO = EnumHelper.addArmorMaterial("armorPOGO", 35, new int[] {1, 1, 1, 1}, 0);
	

	@Instance("bpogostick") //The instance, this is very important later on
	public static Pogostick instance = new Pogostick();

	@SidedProxy(clientSide = "benjibobs.Pogostick.client.PogostickClientProxy", serverSide = "benjibobs.Pogostick.common.PogostickCommonProxy") //Tells Forge the location of your proxies
	public static PogostickCommonProxy proxy;

	@EventHandler
	public void InitPogostick(FMLInitializationEvent event){ //Your main initialization method

	//MULTIPLAYER ABILITY
	NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy); //Registers the class that deals with GUI data
	
	bpogo = new ItemPogo();
	bpogo.setUnlocalizedName("bpogostick");
    GameRegistry.registerItem(bpogo, "pogo_bpogostick");
    //LanguageRegistry.addName(bpogo, "Pogostick Template");
    
    wpogo = new ItemWoodPogo();
    wpogo.setUnlocalizedName("woodpogo");
    GameRegistry.registerItem(wpogo, "pogo_woodpogo");
    //LanguageRegistry.addName(wpogo, "Wooden Pogostick");
    
    spogo = new ItemStonePogo();
    spogo.setUnlocalizedName("stonepogo");
    GameRegistry.registerItem(spogo, "pogo_stonepogo");
    //LanguageRegistry.addName(spogo, "Stone Pogostick");
    
    ipogo = new ItemIronPogo();
    ipogo.setUnlocalizedName("ironpogo");
    GameRegistry.registerItem(ipogo, "pogo_ironpogo");
    //LanguageRegistry.addName(ipogo, "Iron Pogostick");
    
    gpogo = new ItemGoldPogo();
    gpogo.setUnlocalizedName("goldpogo");
    GameRegistry.registerItem(gpogo, "pogo_goldpogo");
    //LanguageRegistry.addName(gpogo, "Gold Pogostick");
    
    dpogo = new ItemDiamondPogo();
    dpogo.setUnlocalizedName("diamondpogo");
    GameRegistry.registerItem(dpogo, "pogo_diamondpogo");
    //LanguageRegistry.addName(dpogo, "Diamond Pogostick");
    
    tntpogo = new ItemTNTPogo();
    tntpogo.setUnlocalizedName("tntpogo");
    GameRegistry.registerItem(tntpogo, "pogo_tntpogo");
    //LanguageRegistry.addName(tntpogo, "Explosive Pogostick");
    
    dmgpogo = new ItemHarmPogo();
    dmgpogo.setUnlocalizedName("dmgpogo");
    GameRegistry.registerItem(dmgpogo, "pogo_dmgpogo");
    //LanguageRegistry.addName(dmgpogo, "Pogostick of Harm");
    
    kpogo = new ItemKBPogo();
    kpogo.setUnlocalizedName("kpogo");
    GameRegistry.registerItem(kpogo, "pogo_kpogo");
    //LanguageRegistry.addName(kpogo, "Pogostick of Knockback");
    
    fpogo = new ItemFirePogo();
    fpogo.setUnlocalizedName("firepogo");
    GameRegistry.registerItem(fpogo, "pogo_firepogo");
    //LanguageRegistry.addName(fpogo, "Flaming Pogostick");
    
    tramp = new BlockTramp("semibouncer");
    tramp.setBlockName("semibouncer");
    tramp.setHardness(0.7F);
    tramp.setStepSound(Block.soundTypeCloth);
    tramp.setResistance(0.7F);
    //LanguageRegistry.addName(tramp, "Semi-auto Bouncer");
    GameRegistry.registerBlock(tramp, "pogo_Semi-auto Bouncer");
    
    mantramp = new BlockManTramp( "manbouncer");
    mantramp.setBlockName("manbouncer");
    mantramp.setHardness(0.7F);
    mantramp.setStepSound(Block.soundTypeCloth);
    mantramp.setResistance(0.7F);
    //LanguageRegistry.addName(mantramp, "Manual Bouncer");
    GameRegistry.registerBlock(mantramp, "pogo_Manual Bouncer");
    
    pogoboots = new ItemPogoboots(armorPOGO, 5, 3);
    pogoboots.setUnlocalizedName("pogoboots");
    //LanguageRegistry.addName(pogoboots, "Pogo-boots");
    GameRegistry.registerItem(pogoboots, "pogo_pogoboots");
    
    
    //Recipes
    
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

    GameRegistry.addRecipe(new ItemStack(mantramp), "xxx", "xyx", "xxx", 'x', woolStack, 'y', pogoStack);  
    GameRegistry.addRecipe(new ItemStack(bpogo), " x ", " x ", " s ", 'x', stickStack, 's', slimeStack);
    GameRegistry.addRecipe(new ItemStack(pogoboots), "   ", "e e", "s s", 'e',emeraldStack,'s', slimeStack);
    GameRegistry.addRecipe(new ItemStack(wpogo), "   ", " p ", " w ", 'w', woodStack , 'p', pogoStack);
    GameRegistry.addRecipe(new ItemStack(spogo), "   ", " p ", " s ", 'p', pogoStack, 's', stoneStack);
    GameRegistry.addRecipe(new ItemStack(ipogo), "   ", " p ", " i ", 'p', pogoStack, 'i', ironIngotStack);
    GameRegistry.addRecipe(new ItemStack(gpogo), "   ", " p ", " g ", 'p', pogoStack, 'g', goldIngotStack);
    GameRegistry.addRecipe(new ItemStack(dpogo), "   ", " p ", " d ", 'd', diaStack, 'p', pogoStack);
    GameRegistry.addRecipe(new ItemStack(tntpogo), "   ", " p ", " t ", 'p', dpStack, 't', tntStack);
    GameRegistry.addRecipe(new ItemStack(dmgpogo), "   ", " p ", " h ", 'p', pogoStack, 'h', potStack);
    GameRegistry.addRecipe(new ItemStack(pogoboots), "e e", "s s", "   ", 'e',emeraldStack,'s', slimeStack);
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

    //LanguageRegistry.instance().addStringLocalization("ItemsGroup.Pogostick", "en_US", "Pogostick");
    //LanguageRegistry.instance().addStringLocalization("ItemsGroup.Pogostick", "en_UK", "Pogostick");
    

	}
	
	@EventHandler
	@SideOnly(Side.CLIENT)
	public void Render(FMLInitializationEvent event){
		RenderingRegistry.addNewArmourRendererPrefix("armorPOGO");
		MinecraftForge.EVENT_BUS.register(new ManualBouncerEvents());
		MinecraftForge.EVENT_BUS.register(new PogostickEvents());//PogostickEvents() is your event class
	}

}
