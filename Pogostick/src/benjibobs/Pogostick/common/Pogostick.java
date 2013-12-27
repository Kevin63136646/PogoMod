package benjibobs.Pogostick.common;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import benjibobs.Pogostick.common.handlers.PogostickClientPacketHandler;
import benjibobs.Pogostick.common.handlers.PogostickServerPacketHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraftforge.common.Configuration;

@NetworkMod(clientSideRequired=true,serverSideRequired=true, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"Pogostick"}, packetHandler = PogostickClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"Pogostick"}, packetHandler = PogostickServerPacketHandler.class)) //For serverside packet handling

//MOD BASICS
@Mod(modid=Pogostick.modid,name="Pogostick",version="Public Release")


public class Pogostick {
	
	public static CreativeTabs tabPogostick = new CreativeTabs("Pogostick") {
        public ItemStack getIconItemStack() {
                return new ItemStack(fpogo, 1, 0);
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
	
	public final MinecraftServer mcs = FMLServerHandler.instance().getServer();
	
	 static EnumArmorMaterial armorPOGO = EnumHelper.addArmorMaterial("armorPOGO", 35, new int[] {1, 1, 1, 1}, 0);
	

	@Instance("bpogostick") //The instance, this is very important later on
	public static Pogostick instance = new Pogostick();

	@SidedProxy(clientSide = "benjibobs.Pogostick.client.PogostickClientProxy", serverSide = "benjibobs.Pogostick.common.PogostickCommonProxy") //Tells Forge the location of your proxies
	public static PogostickCommonProxy proxy;

	@PreInit
	public void PreInit(FMLPreInitializationEvent e){	
		
		
	}

	@Init
	public void InitPogostick(FMLInitializationEvent event){ //Your main initialization method

	//MULTIPLAYER ABILITY
	NetworkRegistry.instance().registerGuiHandler(this, proxy); //Registers the class that deals with GUI data
	
	bpogo = new ItemPogo(6988);
	bpogo.setUnlocalizedName("bpogostick");
    GameRegistry.registerItem(bpogo, "bpogostick");
    LanguageRegistry.addName(bpogo, "Pogostick Template");
    
    wpogo = new ItemWoodPogo(6989);
    wpogo.setUnlocalizedName("woodpogo");
    GameRegistry.registerItem(wpogo, "woodpogo");
    LanguageRegistry.addName(wpogo, "Wooden Pogostick");
    
    spogo = new ItemStonePogo(6990);
    spogo.setUnlocalizedName("stonepogo");
    GameRegistry.registerItem(spogo, "stonepogo");
    LanguageRegistry.addName(spogo, "Stone Pogostick");
    
    ipogo = new ItemIronPogo(6991);
    ipogo.setUnlocalizedName("ironpogo");
    GameRegistry.registerItem(ipogo, "ironpogo");
    LanguageRegistry.addName(ipogo, "Iron Pogostick");
    
    gpogo = new ItemGoldPogo(6992);
    gpogo.setUnlocalizedName("goldpogo");
    GameRegistry.registerItem(gpogo, "goldpogo");
    LanguageRegistry.addName(gpogo, "Gold Pogostick");
    
    dpogo = new ItemDiamondPogo(6993);
    dpogo.setUnlocalizedName("diamondpogo");
    GameRegistry.registerItem(dpogo, "diamondpogo");
    LanguageRegistry.addName(dpogo, "Diamond Pogostick");
    
    tntpogo = new ItemTNTPogo(6994);
    tntpogo.setUnlocalizedName("tntpogo");
    GameRegistry.registerItem(tntpogo, "tntpogo");
    LanguageRegistry.addName(tntpogo, "Explosive Pogostick");
    
    dmgpogo = new ItemHarmPogo(6995);
    dmgpogo.setUnlocalizedName("dmgpogo");
    GameRegistry.registerItem(dmgpogo, "dmgpogo");
    LanguageRegistry.addName(dmgpogo, "Pogostick of Harm");
    
    kpogo = new ItemKBPogo(6996);
    kpogo.setUnlocalizedName("kpogo");
    GameRegistry.registerItem(kpogo, "kpogo");
    LanguageRegistry.addName(kpogo, "Pogostick of Knockback");
    
    fpogo = new ItemFirePogo(6997);
    fpogo.setUnlocalizedName("firepogo");
    GameRegistry.registerItem(fpogo, "firepogo");
    LanguageRegistry.addName(fpogo, "Flaming Pogostick");
    
    tramp = (new BlockTramp(2876, "bouncer"));
    tramp.setUnlocalizedName("bouncer");
    tramp.setHardness(0.7F);
    tramp.setStepSound(Block.soundMetalFootstep);
    tramp.setResistance(0.7F);
    LanguageRegistry.addName(tramp, "Bouncer");
    GameRegistry.registerBlock(tramp, "Bouncer");
    
    pogoboots = new ItemPogoboots(6987, armorPOGO, 5, 3);
    pogoboots.setUnlocalizedName("pogoboots");
    LanguageRegistry.addName(pogoboots, "Pogo-boots");
    GameRegistry.registerItem(pogoboots, "pogoboots");
    
    
    //Recipes
    
    ItemStack bouncerStack = new ItemStack(tramp);
    ItemStack pogoStack = new ItemStack(bpogo);
    ItemStack woolStack = new ItemStack(Block.cloth);
    ItemStack stickStack = new ItemStack(Item.stick);
    ItemStack slimeStack = new ItemStack(Item.slimeBall);
    ItemStack emeraldStack = new ItemStack(Item.emerald);
    ItemStack woodStack = new ItemStack(Block.planks);
    ItemStack stoneStack = new ItemStack(Block.cobblestone);
    ItemStack ironIngotStack = new ItemStack(Item.ingotIron);
    ItemStack goldIngotStack = new ItemStack(Item.ingotGold);
    ItemStack diaStack = new ItemStack(Item.diamond);
    ItemStack tntStack = new ItemStack(Block.tnt);
    ItemStack dpStack = new ItemStack(dpogo);
    ItemStack potStack = new ItemStack(Item.swordDiamond);
    ItemStack silkStack = new ItemStack(Item.silk);
    ItemStack blazeStack = new ItemStack(Item.blazePowder);

    GameRegistry.addRecipe(new ItemStack(tramp), "xxx", "xyx", "xxx", 'x', woolStack, 'y', pogoStack);  
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
    
    LanguageRegistry.instance().addStringLocalization("itemGroup.Pogostick", "en_US", "Pogostick");
    LanguageRegistry.instance().addStringLocalization("itemGroup.Pogostick", "en_UK", "Pogostick");
    

	}
	
	@Init
	@SideOnly(Side.CLIENT)
	public void Render(FMLInitializationEvent event){
		RenderingRegistry.addNewArmourRendererPrefix("armorPOGO");
		MinecraftForge.EVENT_BUS.register(new PogostickEvents()); //PogostickEvents() is your event class
	}

}
