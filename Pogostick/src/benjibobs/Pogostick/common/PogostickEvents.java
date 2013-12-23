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
	public static boolean tntblew;
	public static boolean explosiondmg;

	// @ForgeSubscribe
	// public void cancelFallDmg(LivingHurtEvent event) {
	//
	// if(event.source == DamageSource.fall && event.entity instanceof
	// EntityPlayer){
	// EntityPlayer player = (EntityPlayer)event.entity;
	//
	// if(player.inventory.getCurrentItem() != null){
	// if(player.inventory.getCurrentItem().itemID == 7243){
	// if(pjumped == true){
	// event.setCanceled(true);
	// event.ammount = 0.0F;
	// pjumped = false;
	// tc = false;
	// }
	// if(!(player.isSneaking()) && tc == false){
	// player.motionY = 1;
	// tc = false;
	// event.setCanceled(true);
	// }else{
	// pjumped = false;
	// tc = true;
	// }
	// }
	// }
	//
	// }
	//
	// }

	@ForgeSubscribe
	public void bouncerLanding(LivingHurtEvent event) {

		if (event.source == DamageSource.fall
				&& event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			int yc = (int) (player.lastTickPosY - 1);
			int yc2 = (int) (player.lastTickPosY - 2);

			if (mc.theWorld.getBlockId((int) player.lastTickPosX, yc,
					(int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				event.setCanceled(true);
				event.ammount = 0.0F;

			} else if (mc.theWorld.getBlockId((int) player.lastTickPosX, yc2,
					(int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				event.setCanceled(true);
				event.ammount = 0.0F;

			}

		}

	}

	@ForgeSubscribe
	public void bounce(LivingFallEvent event) {

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			 if(
			 (mc.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 1, (int)player.lastTickPosZ) == Pogostick.tramp.blockID || mc.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 2, (int)player.lastTickPosZ) == Pogostick.tramp.blockID)){
			 fallam = 0;
			 }else{
			 fallam = event.distance;
			 }

			int yc = (int) (player.lastTickPosY - 1);
			int yc2 = (int) (player.lastTickPosY - 2);

			if (event.distance > 0.0F) {

				if (mc.theWorld.getBlockId((int) player.lastTickPosX, yc,
						(int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
					player.motionY = 1.1;

				} else if (mc.theWorld.getBlockId((int) player.lastTickPosX,
						yc2, (int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
					player.motionY = 1.1;

				}

			}

		}

	}

	@ForgeSubscribe
	public void jumpB(LivingJumpEvent event) {

		if (event.entity instanceof EntityPlayer) {
			pjumped = false;
			EntityPlayer player = (EntityPlayer) event.entity;
			if (mc.theWorld.getBlockId((int) player.lastTickPosX,
					(int) player.lastTickPosY - 1, (int) player.lastTickPosZ) == Pogostick.tramp.blockID
					|| mc.theWorld.getBlockId((int) player.lastTickPosX, (int) player.lastTickPosY - 2, (int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				player.motionY = 1.1;
			}

			if (player.inventory.armorItemInSlot(0) != null) {
				ItemStack pogoboots = new ItemStack(Pogostick.pogoboots);
				if (player.inventory.armorItemInSlot(0).itemID == 7243) {
					player.motionY = 0.7;
					jumped = true;
				}
			}

		}

	}

	@ForgeSubscribe
	public void boots(LivingHurtEvent event) {

		if (event.source == DamageSource.fall && event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.inventory.armorItemInSlot(0) != null) {

				ItemStack pogoboots = new ItemStack(Pogostick.pogoboots);
				if (player.inventory.armorItemInSlot(0).itemID == 7243) {

					ItemStack pb = player.inventory.armorItemInSlot(0);
					int current = pb.getItemDamage();
					pb.setItemDamage(current + (int)fallam + 1);
					event.setCanceled(true);

				}
			}

		}

	}

	@ForgeSubscribe
	public void pogoBouncers(LivingHurtEvent event) {

		if (event.entity instanceof EntityPlayer
				&& event.source == DamageSource.fall) {
			EntityPlayer player = (EntityPlayer) event.entity;
			//stone pogo start
			
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7246){
					if(pjumped){
						if(!(player.isSneaking())){
						ItemStack item = player.inventory.getCurrentItem();
						event.setCanceled(true);
						player.motionY = 0.8;
						item.setItemDamage(item.getItemDamage() + 1);
					}else{
						pjumped = false;
					}
					}
				}
			
			//stone pogo end
			//iron pogo start
				
				if(player.inventory.getCurrentItem() != null & player.inventory.getCurrentItem().itemID == 7247){
					if(pjumped){
						ItemStack item = player.inventory.getCurrentItem();
						if(!(player.isSneaking())){
							event.setCanceled(true);
							player.motionY = 0.9;
							item.setItemDamage(item.getItemDamage() + 1);
						}else{
							pjumped = false;
						}
					}
					
				}
				
			//iron pogo end
			//gold pogo start
				
				if(player.inventory.getCurrentItem() != null & player.inventory.getCurrentItem().itemID == 7248){
					if(pjumped){
						ItemStack item = player.inventory.getCurrentItem();
						if(!(player.isSneaking())){
							event.setCanceled(true);
							player.motionY = 0.9;
							item.setItemDamage(item.getItemDamage() + 1);
						}else{
							pjumped = false;
						}
					}
					
				}
				
			//gold pogo end
			//diamond pogo start
				
				if(player.inventory.getCurrentItem() != null & player.inventory.getCurrentItem().itemID == 7249){
					if(pjumped){
						ItemStack item = player.inventory.getCurrentItem();
						if(!(player.isSneaking())){
							event.setCanceled(true);
							player.motionY = 1;
							item.setItemDamage(item.getItemDamage() + 1);
						}else{
							pjumped = false;
						}
					}
					
				}
				
			//diamond pogo end	
				
				if(player.inventory.getCurrentItem() != null & player.inventory.getCurrentItem().itemID == 7250){
					if(pjumped){
						if(tntblew == false){
							ItemStack item = player.inventory.getCurrentItem();
							event.setCanceled(true);
							player.motionY = 1;
							item.setItemDamage(item.getItemDamage() + 1);
						}else{
							mc.theWorld.createExplosion(player, player.lastTickPosX, player.lastTickPosY - 1, player.lastTickPosZ, (float)3, true);
							event.setCanceled(true);
							explosiondmg = true;
						}
					}
						
					}

		}
		
		if(event.entity instanceof EntityPlayer && explosiondmg == true){
			event.setCanceled(true);
			explosiondmg = false;
		}

	}
	
	@ForgeSubscribe
	public void tntPogo(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			if(player.inventory.getCurrentItem() != null & player.inventory.getCurrentItem().itemID == 7250){
				if(pjumped){
					if(player.isSneaking()){
					tntblew = true;
					event.setCanceled(true);
					
					}
				}
				}
				}
				
		
	}

}
