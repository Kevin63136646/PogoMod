package benjibobs.Pogostick.common;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.server.FMLServerHandler;

public class ManualBouncerEvents {

	MinecraftServer mc = FMLServerHandler.instance().getServer();
	Minecraft mc1 = FMLClientHandler.instance().getClient();
	
	public static boolean canceldmg = false;
	
	@ForgeSubscribe
	public void fallCancel(LivingJumpEvent event){
		
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = mc1.thePlayer;
			if(mc1.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 1, (int)player.lastTickPosZ) == Pogostick.mantramp.blockID){
				canceldmg = true;
				player.motionY = 1.1;
			}
		}
		
	}
	
	@ForgeSubscribe
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
