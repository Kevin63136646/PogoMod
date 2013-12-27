package benjibobs.Pogostick.common;

import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
	private static boolean cancelb;

	// TODO: Check if comment still needed, if not remove, if so add notes.
	//
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
	public void bouncerHealthCancel(LivingHurtEvent event) {

		if (event.source == DamageSource.fall
				&& event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			int yc = (int) (player.lastTickPosY - 1);
			int yc2 = (int) (player.lastTickPosY - 2);
			
			if(cancelb){
				cancelb = false;
				event.setCanceled(true);
				event.ammount = 0.0F;
			}
			
			if (mc1.theWorld.getBlockId((int) player.lastTickPosX, yc,
					(int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				event.setCanceled(true);
				tntblew = false;
				event.ammount = 0.0F;
				cancelb = true;

			} else if (mc1.theWorld.getBlockId((int) player.lastTickPosX, yc2,
					(int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				event.setCanceled(true);
				event.ammount = 0.0F;
				tntblew = false;
				cancelb = true;


			}
			
			
			
		}

	}

	@ForgeSubscribe
	public void bouncerBounce(LivingFallEvent event) {

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
	public void jumpHandler(LivingJumpEvent event) {

		if (event.entity instanceof EntityPlayer) {
			pjumped = false;
			EntityPlayer player = (EntityPlayer) event.entity;
			if (mc1.theWorld.getBlockId((int) player.lastTickPosX,
					(int) player.lastTickPosY - 1, (int) player.lastTickPosZ) == Pogostick.tramp.blockID
					|| mc1.theWorld.getBlockId((int) player.lastTickPosX, (int) player.lastTickPosY - 2, (int) player.lastTickPosZ) == Pogostick.tramp.blockID) {
				player.motionY = 1.1;
				tntblew = false;
				cancelb = true;
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
	public void bootsHandler(LivingHurtEvent event) {

		if (event.source == DamageSource.fall && event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.inventory.armorItemInSlot(0) != null) {

				ItemStack pogoboots = new ItemStack(Pogostick.pogoboots);
				if (player.inventory.armorItemInSlot(0).itemID == 7243) {

					ItemStack pb = player.inventory.armorItemInSlot(0);
					int dur = pb.getItemDamage() + (int)fallam + 1;
					int durc = pb.getMaxDamage() - pb.getItemDamage();
					if(dur < pb.getMaxDamage() && durc > 0){
						pb.setItemDamage(dur);
						
					}else{
						pb.setItemDamage(pb.getMaxDamage());
						player.inventory.clearInventory(pb.itemID, pb.getMaxDamage());
						
					}
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
			//wood pogo start
			
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7245){
				if(pjumped){
					
					pjumped = false;
					event.setCanceled(true);
				
				}
			}
		
		//wood pogo end
			
			//stone pogo start
			
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7246){
					if(pjumped){
						if(!(player.isSneaking())){
						ItemStack item = player.inventory.getCurrentItem();
						event.setCanceled(true);
						player.motionY = 0.8;
						int dur = item.getItemDamage() + 1;
						int durc = item.getMaxDamage() - item.getItemDamage();
						if(dur < item.getMaxDamage() && durc > 0){
							item.setItemDamage(dur);
							
						}else{
							item.setItemDamage(item.getMaxDamage());
							player.inventory.clearInventory(item.itemID, item.getMaxDamage());
							
						}
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
							int dur = item.getItemDamage() + 1;
							int durc = item.getMaxDamage() - item.getItemDamage();
							if(dur < item.getMaxDamage() && durc > 0){
								item.setItemDamage(dur);
								
							}else{
								item.setItemDamage(item.getMaxDamage());
								player.inventory.clearInventory(item.itemID, item.getMaxDamage());
								
							}
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
							
							int dur = item.getItemDamage() + 1;
							int durc = item.getMaxDamage() - item.getItemDamage();
							if(dur < item.getMaxDamage() && durc > 0){
								item.setItemDamage(dur);
								
							}else{
								item.setItemDamage(item.getMaxDamage());
								player.inventory.clearInventory(item.itemID, item.getMaxDamage());
								
							}
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
							event.ammount = 0.0F;
							player.motionY = 1;
							
							int dur = item.getItemDamage() + 1;
							int durc = item.getMaxDamage() - item.getItemDamage();
							
							if(dur < item.getMaxDamage() && durc > 0){
								item.setItemDamage(dur);
								
							}else{
								item.setItemDamage(item.getMaxDamage());
								player.inventory.clearInventory(item.itemID, item.getMaxDamage());
								
							}
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
							pjumped = false;
							
						}else{
							World expo = FMLClientHandler.instance().getServer().getEntityWorld();
							EntityPlayerMP playerm = (EntityPlayerMP)event.entity;
							expo.createExplosion(playerm, playerm.posX, playerm.posY, playerm.posZ, 2.0F, true);
							ItemStack item = player.inventory.getCurrentItem();
							
							int dur = item.getItemDamage() + 9;
							int durc = item.getMaxDamage() - item.getItemDamage();
							if(dur < item.getMaxDamage() && durc > 0){
								item.setItemDamage(dur);
								
							}else{
								item.setItemDamage(item.getMaxDamage());
								player.inventory.clearInventory(item.itemID, item.getMaxDamage());
								
							}
							explosiondmg = true;
							tntblew = false;
							player.posY = player.lastTickPosY + 1;	
							}
					}
						
					}
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7251){
					if(pjumped){
						if(player.isSneaking()){
						World expo = FMLClientHandler.instance().getServer().getEntityWorld();
						EntityPlayerMP playerm = (EntityPlayerMP)event.entity;
						expo.createExplosion(playerm, playerm.posX, playerm.posY, playerm.posZ, 3.0F, false);
						event.setCanceled(true);
						event.ammount = 0.0F;
						explosiondmg = true;
						ItemStack item = player.inventory.getCurrentItem();
						int dur = item.getItemDamage() + 4;
						int durc = item.getMaxDamage() - item.getItemDamage();
						if(dur < item.getMaxDamage() && durc > 0){
							item.setItemDamage(dur);
							
						}else{
							item.setItemDamage(item.getMaxDamage());
							player.inventory.clearInventory(7251, item.getMaxDamage());
							
						}
						}else{
							event.setCanceled(true);
							event.ammount = 0.0F;
							pjumped = false;
						}
					}
				}
				

		}
		
		
		
		if(event.entity instanceof EntityPlayer && explosiondmg == true){
			event.ammount = 0.0F;
			explosiondmg = false;
		}
		
		

	}
	
	@ForgeSubscribe
	public void pogoOfKB(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer && event.source == DamageSource.fall){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			World world = FMLClientHandler.instance().getServer().getEntityWorld();
			
			ItemStack kpogo = new ItemStack(Pogostick.kpogo);
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7252 ){
				if(pjumped){
					
				if(player.isSneaking()){
					
					event.setCanceled(true);
					event.ammount = 0.0F;
					knockMobs(4.0D, world, player);
					ItemStack item = player.inventory.getCurrentItem();
					int dur = item.getItemDamage() + 1;
					int durc = item.getMaxDamage() - item.getItemDamage();
					if(dur < item.getMaxDamage() && durc > 0){
						item.setItemDamage(dur);
						
					}else{
						item.setItemDamage(item.getMaxDamage());
						player.inventory.clearInventory(7252, item.getMaxDamage());
						
					}
					
					pjumped = false;
				}else{
					event.setCanceled(true);
					event.ammount = 0.0F;
					pjumped = false;
				}
			}
			}
		}
		
	}
	
	@ForgeSubscribe
	public void flamingPogo(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer && event.source == DamageSource.fall){
			if(pjumped){
				
				EntityPlayer player = (EntityPlayer)event.entity;
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == 7253 ){
				if(player.isSneaking()){
					pjumped = false;
					event.setCanceled(true);
					event.ammount = 0.0F;
					World world = FMLClientHandler.instance().getServer().getEntityWorld();
					burnMobs(4.0D, world, player);
					ItemStack item = player.inventory.getCurrentItem();
					int dur = item.getItemDamage() + 2;
					int durc = item.getMaxDamage() - item.getItemDamage();
					if(dur < item.getMaxDamage() && durc > 0){
						item.setItemDamage(dur);
						
					}else{
						item.setItemDamage(item.getMaxDamage());
						player.inventory.clearInventory(7253, item.getMaxDamage());
						
					}
				}else{
					pjumped = false;
					event.setCanceled(true);
					event.ammount = 0.0F;
				}
			}
			}
		}
		
	}

	public void knockMobs(double area, World world, EntityPlayer player){
		
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(area, area, area));
		
		for(Entity mob:entities){
			
			if(mob != null){
				
				mob.motionX = 0.8;
				mob.motionY = 0.6;
				mob.motionZ = 0.8;
			
				
			}
		}
		
	}
	
	public void burnMobs(double area, World world, EntityPlayer player){
		
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(area, area, area));
		
		for(Entity mob:entities){
			
			if(mob != null && mob instanceof EntityLiving){
				
				mob.setFire(3);
				mob.motionX = 0.5;
				
			}
			
		}
		
	}
	
	
	
}
