package benjibobs.Pogostick.common;

import javax.jws.Oneway;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PogostickEvents {

	Minecraft mc = FMLClientHandler.instance().getClient();

	@ForgeSubscribe
	public void cancelFallDmg(LivingHurtEvent event) {
		
		if(event.source == DamageSource.fall && event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if(player.inventory.getCurrentItem() != null){
			if(player.inventory.getCurrentItem().itemID == 7243){
				event.setCanceled(true);
				event.ammount = 0.0F;
				if(player.isSneaking() == false){
					player.motionY = 1;
				}
			}
			}
			
		}

	}
	
	@ForgeSubscribe
	public void bouncerLanding(LivingHurtEvent event) {
		
		if(event.source == DamageSource.fall && event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			int yc = (int) (player.lastTickPosY - 1);
			int yc2 = (int) (player.lastTickPosY - 2);
			
			if(mc.theWorld.getBlockId((int)player.lastTickPosX, yc, (int)player.lastTickPosZ) == Pogostick.tramp.blockID){
				event.setCanceled(true);
				event.ammount = 0.0F;
				
			}else if(mc.theWorld.getBlockId((int)player.lastTickPosX, yc2, (int)player.lastTickPosZ) == Pogostick.tramp.blockID){
				event.setCanceled(true);
				event.ammount = 0.0F;
				
			}
			
			
			
			
		}

	}
	
	@ForgeSubscribe
	public void bounce(LivingFallEvent event){
		
		if(event.entity instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			int yc = (int) (player.lastTickPosY - 1);
			int yc2 = (int) (player.lastTickPosY - 2);
			
			if(event.distance > 0.0F){
			
			if(mc.theWorld.getBlockId((int)player.lastTickPosX, yc, (int)player.lastTickPosZ) == Pogostick.tramp.blockID){
				player.motionY = 1;
				
			}else if(mc.theWorld.getBlockId((int)player.lastTickPosX, yc2, (int)player.lastTickPosZ) == Pogostick.tramp.blockID){
				player.motionY = 1;
				
			}
			
			
			
			}
			
		}
		
	}
	
	

}
