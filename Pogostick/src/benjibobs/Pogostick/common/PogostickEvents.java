package benjibobs.Pogostick.common;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PogostickEvents {

	MinecraftServer mc = FMLServerHandler.instance().getServer();
	Minecraft mc1 = FMLClientHandler.instance().getClient();

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

			if (mc1.theWorld.getBlockId((int) player.lastTickPosX, yc,
					(int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				event.setCanceled(true);
				tntblew = false;
				event.ammount = 0.0F;

			} else if (mc1.theWorld.getBlockId((int) player.lastTickPosX, yc2,
					(int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				event.setCanceled(true);
				event.ammount = 0.0F;
				tntblew = false;

			}

		}

	}

	@ForgeSubscribe
	public void bounce(LivingFallEvent event) {

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			 if((mc1.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 1, (int)player.lastTickPosZ) == Pogostick.tramp.blockID || mc1.theWorld.getBlockId((int)player.lastTickPosX, (int)player.lastTickPosY - 2, (int)player.lastTickPosZ) == Pogostick.tramp.blockID)){
			 fallam = 0;
			 }else{
			 fallam = event.distance;
			 }

			int yc = (int) (player.lastTickPosY - 1);
			int yc2 = (int) (player.lastTickPosY - 2);

			if (event.distance > 0.0F) {

				if (mc1.theWorld.getBlockId((int) player.lastTickPosX, yc,
						(int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
					player.motionY = 1.1;
					tntblew = false;

				} else if (mc1.theWorld.getBlockId((int) player.lastTickPosX,
						yc2, (int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
					player.motionY = 1.1;
					tntblew = false;

				}

			}

		}

	}

	@ForgeSubscribe
	public void jumpB(LivingJumpEvent event) {

		if (event.entity instanceof EntityPlayer) {
			pjumped = false;
			EntityPlayer player = (EntityPlayer) event.entity;
			if (mc1.theWorld.getBlockId((int) player.lastTickPosX,
					(int) player.lastTickPosY - 1, (int) player.lastTickPosZ) == Pogostick.tramp.blockID
					|| mc1.theWorld.getBlockId((int) player.lastTickPosX, (int) player.lastTickPosY - 2, (int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				player.motionY = 1.1;
				tntblew = false;
			}

			if (player.inventory.armorItemInSlot(0) != null) {
				ItemStack pogoboots = new ItemStack(Pogostick.pogoboots);
				if (player.inventory.armorItemInSlot(0).itemID == 7243) {
					player.motionY = 0.7;
					jumped = true;
					tntblew = false;
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
	public void pogoHandling(LivingHurtEvent event) {

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
						event.setCanceled(true);
					}
					}
				}
			
			//stone pogo end
			//iron pogo start
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7247){
					if(pjumped){
						ItemStack item = player.inventory.getCurrentItem();
						if(!(player.isSneaking())){
							event.setCanceled(true);
							player.motionY = 0.9;
							item.setItemDamage(item.getItemDamage() + 1);
						}else{
							pjumped = false;
							event.setCanceled(true);
						}
					}
					
				}
				
			//iron pogo end
			//gold pogo start
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7248){
					if(pjumped){
						ItemStack item = player.inventory.getCurrentItem();
						if(!(player.isSneaking())){
							event.setCanceled(true);
							player.motionY = 0.9;
							item.setItemDamage(item.getItemDamage() + 1);
						}else{
							pjumped = false;
							event.setCanceled(true);
						}
					}
					
				}
				
			//gold pogo end
			//diamond pogo start
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7249){
					if(pjumped){
						ItemStack item = player.inventory.getCurrentItem();
						if(!(player.isSneaking())){
							event.setCanceled(true);
							player.motionY = 1;
							item.setItemDamage(item.getItemDamage() + 1);
						}else{
							pjumped = false;
							event.setCanceled(true);
						}
					}
					
				}
				
			//diamond pogo end	
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7250){
					if(pjumped){
						if(player.isSneaking()){
						tntblew = true;
						event.setCanceled(true);
						
						
						}
					}
					}
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7250){
					if(pjumped){
						if(tntblew == false){
							ItemStack item = player.inventory.getCurrentItem();
							event.setCanceled(true);
							
						}else{
							World expo = FMLClientHandler.instance().getServer().getEntityWorld();
							EntityPlayerMP playerm = (EntityPlayerMP)event.entity;
							expo.createExplosion(playerm, playerm.posX, playerm.posY, playerm.posZ, 2.0F, true);
							ItemStack item = player.inventory.getCurrentItem();
							item.setItemDamage(item.getItemDamage() + 10);
							explosiondmg = true;
							tntblew = false;
							player.posY = player.lastTickPosY + 1;	
							}
					}
						
					}
				
				
				

		}
		
		
		
		if(event.entity instanceof EntityPlayer && explosiondmg == true){
			event.ammount = 0.0F;
			explosiondmg = false;
		}

	}
	
	

}
