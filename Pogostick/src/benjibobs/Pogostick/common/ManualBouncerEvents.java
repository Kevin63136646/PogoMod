package benjibobs.Pogostick.common;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.server.FMLServerHandler;

public class ManualBouncerEvents {

	MinecraftServer mc = FMLServerHandler.instance().getServer();
	Minecraft mc1 = FMLClientHandler.instance().getClient();
	
	public static boolean canceldmg = false;
	
	@SubscribeEvent
	public void fallCancel(LivingJumpEvent event){
		
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = mc1.thePlayer;
			if(mc1.theWorld.getBlock((int)player.lastTickPosX, (int)player.lastTickPosY - 1, (int)player.lastTickPosZ) == Pogostick.mantramp){
				canceldmg = true;
				player.motionY = 1.1;
			}
		}
		
	}
	
	@SubscribeEvent
	public void dmgCancel(LivingHurtEvent event){
		if(event.entity instanceof EntityPlayer && event.source == DamageSource.fall){
			if(canceldmg == true){
				canceldmg = false;
				event.setCanceled(true);
				event.ammount = 0.0F;
			}
		}
	}
	
}
