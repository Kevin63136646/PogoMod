package benjibobs.Pogostick.common;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.server.FMLServerHandler;

public class PogostickEvents {

	MinecraftServer mc = FMLServerHandler.instance().getServer();
	Minecraft mc1 = FMLClientHandler.instance().getClient();

	public static float fallam;
	public static boolean pjumped = false;
	public static boolean tntblew;
	public static boolean explosiondmg;
	private static boolean cancelb;
	@SuppressWarnings("unused")
	private static boolean jumped = false;

	@SubscribeEvent
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
			
			if (mc1.theWorld.getBlock((int) player.lastTickPosX, yc,
					(int) player.lastTickPosZ) == Pogostick.tramp) {
				event.setCanceled(true);
				tntblew = false;
				event.ammount = 0.0F;
				cancelb = true;

			} else if (mc1.theWorld.getBlock((int) player.lastTickPosX, yc2,
					(int) player.lastTickPosZ) == Pogostick.tramp) {
				event.setCanceled(true);
				event.ammount = 0.0F;
				tntblew = false;
				cancelb = true;


			}
			
			
			
		}

	}

	@SubscribeEvent
	public void bouncerBounce(LivingFallEvent event) {

		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			 if((mc1.theWorld.getBlock((int)player.lastTickPosX, (int)player.lastTickPosY - 1, (int)player.lastTickPosZ) == Pogostick.tramp || mc1.theWorld.getBlock((int)player.lastTickPosX, (int)player.lastTickPosY - 2, (int)player.lastTickPosZ) == Pogostick.tramp)){
			 fallam = 0;
			 }else{
			 fallam = event.distance;
			 }

			int yc = (int) (player.lastTickPosY - 1);
			int yc2 = (int) (player.lastTickPosY - 2);

			if (event.distance > 0.0F) {

				if (mc1.theWorld.getBlock((int) player.lastTickPosX, yc,
						(int) player.lastTickPosZ) == Pogostick.tramp) {
					player.motionY = 1.1;
					tntblew = false;

				} else if (mc1.theWorld.getBlock((int) player.lastTickPosX,
						yc2, (int) player.lastTickPosZ) == Pogostick.tramp) {
					player.motionY = 1.1;
					tntblew = false;

				}

			}

		}

	}

	@SubscribeEvent
	public void jumpHandler(LivingJumpEvent event) {

		if (event.entity instanceof EntityPlayer) {
			pjumped = false;
			EntityPlayer player = (EntityPlayer) event.entity;
			if (mc1.theWorld.getBlock((int) player.lastTickPosX,
					(int) player.lastTickPosY - 1, (int) player.lastTickPosZ) == Pogostick.tramp
					|| mc1.theWorld.getBlock((int) player.lastTickPosX, (int) player.lastTickPosY - 2, (int) player.lastTickPosZ) == Pogostick.tramp) {
				player.motionY = 1.1;
				tntblew = false;
				cancelb = true;
			}

			if (player.inventory.armorItemInSlot(0) != null) {
				
				if (player.inventory.armorItemInSlot(0).getItem() == Pogostick.pogoboots) {
					player.motionY = 0.7;
					jumped = true;
					tntblew = false;
				}
			}

		}

	}

	@SubscribeEvent
	public void bootsHandler(LivingHurtEvent event) {

		if (event.source == DamageSource.fall && event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.inventory.armorItemInSlot(0) != null) {

				
				if (player.inventory.armorItemInSlot(0).getItem() == Pogostick.pogoboots) {

					ItemStack pb = player.inventory.armorItemInSlot(0);
					int dur = pb.getItemDamage() + (int)fallam + 1;
					int durc = pb.getMaxDamage() - pb.getItemDamage();
					if(dur < pb.getMaxDamage() && durc > 0){
						pb.setItemDamage(dur);
						
					}else{
						pb.setItemDamage(pb.getMaxDamage());
						player.inventory.clearInventory(pb.getItem(), pb.getMaxDamage());
						
					}
					event.setCanceled(true);

				}
			}

		}

	}

	@SubscribeEvent
	public void pogoHandling(LivingHurtEvent event) {

		if (event.entity instanceof EntityPlayer
				&& event.source == DamageSource.fall) {
			EntityPlayer player = (EntityPlayer) event.entity;
			//wood pogo start
			
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.wpogo){
				if(pjumped){
					
					pjumped = false;
					event.setCanceled(true);
				
				}
			}
		
		//wood pogo end
			
			//stone pogo start
			
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.spogo){
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
							player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
							
						}
					}else{
						pjumped = false;
						event.setCanceled(true);
					}
					}
				}
			
			//stone pogo end
			//iron pogo start
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.ipogo){
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
								player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
								
							}
						}else{
							pjumped = false;
							event.setCanceled(true);
						}
					}
					
				}
				
			//iron pogo end
			//gold pogo start
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.gpogo){
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
								player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
								
							}
						}else{
							pjumped = false;
							event.setCanceled(true);
						}
					}
					
				}
				
			//gold pogo end
			//diamond pogo start
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.dpogo){
					
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
								player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
								
							}
						}else{
							pjumped = false;
							event.setCanceled(true);
						}
					}
					
				}
				
			//diamond pogo end	
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.tntpogo){
					if(pjumped){
						if(player.isSneaking()){
						tntblew = true;
						event.setCanceled(true);
						
						
						}
					}
					}
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.tntpogo){
					if(pjumped){
						if(tntblew == false){
							
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
								player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
								
							}
							explosiondmg = true;
							tntblew = false;
							player.posY = player.lastTickPosY + 1;	
							}
					}
						
					}
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.dmgpogo){
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
							player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
							
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
	
	@SubscribeEvent
	public void pogoOfKB(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer && event.source == DamageSource.fall){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			World world = FMLClientHandler.instance().getServer().getEntityWorld();
			
			
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.kpogo){
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
						player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
						
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
	
	@SubscribeEvent
	public void flamingPogo(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer && event.source == DamageSource.fall){
			if(pjumped){
				
				EntityPlayer player = (EntityPlayer)event.entity;
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.fpogo){
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
						player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
						
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
		
		@SuppressWarnings("unchecked")
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
		
		@SuppressWarnings("unchecked")
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(area, area, area));
		
		for(Entity mob:entities){
			
			if(mob != null && mob instanceof EntityLiving){
				
				mob.setFire(3);
				mob.motionX = 0.5;
				
			}
			
		}
		
	}
	
	
	
}
