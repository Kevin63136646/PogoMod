package benjibobs.Pogostick.common;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PogostickEvents {

	Minecraft mc = FMLClientHandler.instance().getClient();
	
	public static float fallam;
	private boolean jumped = false;
	public static boolean pjumped = false;
	private boolean tc = true;

//	@ForgeSubscribe
//	public void cancelFallDmg(LivingHurtEvent event) {
//		
//		if(event.source == DamageSource.fall && event.entity instanceof EntityPlayer){
//			EntityPlayer player = (EntityPlayer)event.entity;
//			
//			if(player.inventory.getCurrentItem() != null){
//			if(player.inventory.getCurrentItem().itemID == 7243){
//				if(pjumped == true){
//				event.setCanceled(true);
//				event.ammount = 0.0F;
//				pjumped = false;
//				tc = false;
//				}
//				if(!(player.isSneaking()) && tc == false){
//					player.motionY = 1;
//					tc = false;
//					event.setCanceled(true);
//				}else{
//					pjumped = false;
//					tc = true;
//				}
//			}
//			}
//			
//		}
//
//	}
	
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
//			if((player.inventory.getCurrentItem() != null && tc == false) || (mc.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 1, (int)player.lastTickPosZ) == Pogostick.tramp.blockID || mc.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 2, (int)player.lastTickPosZ) == Pogostick.tramp.blockID)){
//				fallam = 0;
//			}else{
//				fallam = event.distance;
//			}
			
			int yc = (int) (player.lastTickPosY - 1);
			int yc2 = (int) (player.lastTickPosY - 2);
			
			if(event.distance > 0.0F){
			
			if(mc.theWorld.getBlockId((int)player.lastTickPosX, yc, (int)player.lastTickPosZ) == Pogostick.tramp.blockID){
				player.motionY = 1.1;
				
			}else if(mc.theWorld.getBlockId((int)player.lastTickPosX, yc2, (int)player.lastTickPosZ) == Pogostick.tramp.blockID){
				player.motionY = 1.1;
				
			}
			
			
			
			}
			
		}
		
	}
	
	
	@ForgeSubscribe
	public void jumpB(LivingJumpEvent event){
		
		if(event.entity instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)event.entity;
				if(mc.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 1, (int)player.lastTickPosZ) == Pogostick.tramp.blockID || mc.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 2, (int)player.lastTickPosZ) == Pogostick.tramp.blockID){
					player.motionY = 1.1;
				}
				
				if(player.inventory.armorItemInSlot(0) != null){
					if(player.inventory.armorItemInSlot(0).itemID == 7244){
					player.motionY = 0.7;
					jumped = true;
					}
				}
			
		}
		
	}
	
	@ForgeSubscribe
	public void boots(LivingHurtEvent event){
		
		if(event.source == DamageSource.fall && event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			if(player.inventory.armorItemInSlot(0) != null){
			if(player.inventory.armorItemInSlot(0).itemID == 7244){
				
				ItemStack pb = player.inventory.armorItemInSlot(0);
				int current = pb.getItemDamage();
				pb.setItemDamage(current + (int)fallam);
				event.setCanceled(true);
			
			}
			}
			
			
		}
		
	}

}
