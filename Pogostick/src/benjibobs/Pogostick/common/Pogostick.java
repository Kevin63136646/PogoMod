package benjibobs.Pogostick.common;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.SidedProxy;
import net.minecraftforge.common.Configuration;

@NetworkMod(clientSideRequired=true,serverSideRequired=true, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"Pogostick"}, packetHandler = PogostickClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"Pogostick"}, packetHandler = PogostickServerPacketHandler.class)) //For serverside packet handling

//MOD BASICS
@Mod(modid=Pogostick.modid,name="Pogostick",version="Beta")


public class Pogostick {
	
	public static CreativeTabs tabPogostick = new CreativeTabs("Pogostick") {
        public ItemStack getIconItemStack() {
                return new ItemStack(pogo, 1, 0);
        }
};
	
	public static final String modid = "bpogostick";
	public static Item pogo;
	public static Block tramp;
	public static Item pogoboots;
	public final Minecraft mc = FMLClientHandler.instance().getClient();
	
	 static EnumArmorMaterial armorPOGO = EnumHelper.addArmorMaterial("armorPOGO", 12, new int[] {1, 1, 1, 1}, 0);
	
	

	@Instance("Pogostick") //The instance, this is very important later on
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
	
	pogo = new ItemPogo(6987);
	pogo.setUnlocalizedName("pogostick");
    GameRegistry.registerItem(pogo, "pogostick");
    LanguageRegistry.addName(pogo, "Pogostick");
    
    tramp = (new BlockTramp(2876, "bouncer"));
    tramp.setUnlocalizedName("bouncer");
    tramp.setHardness(0.7F);
    tramp.setStepSound(Block.soundMetalFootstep);
    tramp.setResistance(0.7F);
    LanguageRegistry.addName(tramp, "Bouncer");
    GameRegistry.registerBlock(tramp, "Bouncer");
    
    pogoboots = new ItemPogoboots(6988, armorPOGO, 5, 3);
    pogoboots.setUnlocalizedName("pogoboots");
    LanguageRegistry.addName(pogoboots, "Pogo boots");
    GameRegistry.registerItem(pogoboots, "pogoboots");
    RenderingRegistry.addNewArmourRendererPrefix("armorPOGO");
    
    //Recipes
    
    ItemStack bouncerStack = new ItemStack(tramp);
    ItemStack pogoStack = new ItemStack(pogo);
    ItemStack woolStack = new ItemStack(Block.cloth);
    ItemStack stickStack = new ItemStack(Item.stick);
    ItemStack slimeStack = new ItemStack(Item.slimeBall);
    ItemStack emeraldStack = new ItemStack(Item.emerald);

    GameRegistry.addRecipe(new ItemStack(tramp), "xxx", "xyx", "xxx", 'x', woolStack, 'y', pogo);  
    GameRegistry.addRecipe(new ItemStack(pogo), " x ", " x ", " s ", 'x', stickStack, 's', slimeStack);
    GameRegistry.addRecipe(new ItemStack(pogoboots), "   ", "e e", "s s", 'e',emeraldStack,'s', slimeStack);
    
    MinecraftForge.EVENT_BUS.register(new PogostickEvents()); //PogostickEvents() is your event class
    
    LanguageRegistry.instance().addStringLocalization("itemGroup.Pogostick", "en_US", "Pogostick");
    LanguageRegistry.instance().addStringLocalization("itemGroup.Pogostick", "en_UK", "Pogostick");
    

	}

}
