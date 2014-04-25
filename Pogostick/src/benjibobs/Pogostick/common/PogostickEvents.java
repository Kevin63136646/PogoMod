package benjibobs.Pogostick.common;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.server.FMLServerHandler;

public class PogostickEvents {

	MinecraftServer mc = FMLServerHandler.instance().getServer();
	
	public static float fallam;
	public static boolean pjumped = false;
	public static boolean activateTNTPogo;
	public static boolean explosiondmg;
	private static boolean cancelb;
	private static boolean jumped = false;
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event){
		
		if (event.entity instanceof EntityPlayer && ExtendedPlayerProperties.get((EntityPlayer)event.entity) == null){
			
			ExtendedPlayerProperties.register((EntityPlayer)event.entity);
		
		}
		
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayerProperties.bouncerProp) == null){
			
			event.entity.registerExtendedProperties(ExtendedPlayerProperties.bouncerProp, new ExtendedPlayerProperties((EntityPlayer) event.entity, false));
			
		}
			
	}

	@SubscribeEvent
	public void bouncerHandler(LivingFallEvent event){
		
		if((event.entity != null) && (event.entity instanceof EntityPlayer)){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			
			ExtendedPlayerProperties props = ExtendedPlayerProperties.get(player);
			
			int x = getFixedCoord(player.posX);
			int y = getFixedCoord(player.posY) - 2;
			int z = getFixedCoord(player.posZ);
			
			//System.out.println(player.worldObj.getBlock(x, y, z).getUnlocalizedName());
			//System.out.println("" + x + ", " + y + ", " + z + " - " + event.distance);
			
			
			
			if(player.worldObj.getBlock(x, y, z).equals(Pogostick.tramp)){
				
				props.jumpWasBouncer = true;
					
				while(player.isAirBorne){
					
				}
				if(!player.isSneaking()){	
				player.motionY = 1.0;
				}
			}
		  
		}
	  
	}

	//TODO: Add a pull request? This shouldn't be required.
	@SubscribeEvent
	public void cancelBounceDmg(LivingHurtEvent event){
		
		if(event.source.equals(DamageSource.fall) && event.entity != null && event.entity instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			ExtendedPlayerProperties props = ExtendedPlayerProperties.get(player);
			
			int x = getFixedCoord(player.posX);
			int y = getFixedCoord(player.posY) - 2;
			int z = getFixedCoord(player.posZ);
			
			if(player.worldObj.getBlock(x, y, z).equals(Pogostick.tramp)){
				event.setCanceled(true);
			}
			
		}
		
	}
	
	@SubscribeEvent
	public void jumpOnBouncer(LivingJumpEvent event){
		
		if(event.entity != null && event.entity instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			
			int x = getFixedCoord(player.posX);
			int y = getFixedCoord(player.posY) - 3;
			int z = getFixedCoord(player.posZ);
			
			if(player.worldObj.getBlock(x, y, z).equals(Pogostick.tramp)){
				
				player.motionY = 1;
				
			}
			
		}
		
	}
	
	@SubscribeEvent
	public void bootsHandler(LivingFallEvent event){
		
		if((event.entity != null) && (event.entity instanceof EntityPlayer)){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if((player.inventory.armorItemInSlot(0) != null) && (player.inventory.armorItemInSlot(0).getItem().equals(Pogostick.pogoboots)) && (event.distance > 2.6945614)){
				
				event.setCanceled(true);
				
				int dmg = (int)(event.distance + 1);
				
				damageItem(player.inventory.armorItemInSlot(0), dmg, player);
				
			}
			
		}
		
	}

	
	@SubscribeEvent
	public void pogoHandler(LivingFallEvent event) {
		
		if (event.entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) event.entity;

			if(pjumped && event.distance <= 2.6945615){
				
				pjumped = false;
				
			}
			
			/** Wooden pogo **/
			
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.wpogo){
				
				if(pjumped){
					
					pjumped = false;
					
					event.setCanceled(true);
				
				}
			}
			
			/** Stone pogo **/
			
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.spogo){
				
				if(pjumped){
					
					if(!(player.isSneaking())){
						
						ItemStack item = player.inventory.getCurrentItem();
						player.motionY = 0.8;
						damageItem(item, 1, player);
						pjumped = true;
					
					}
					
				}
			}

			/** Iron pogo **/
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.ipogo){
					
					if(pjumped){
						
						ItemStack item = player.inventory.getCurrentItem();
						
						if(!(player.isSneaking())){
							
							player.motionY = 0.9;
							damageItem(item, 1, player);
							pjumped = true;
							
						}
					}
					
				}
				
				/** Gold pogo **/
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.gpogo){
					if(pjumped){
						ItemStack item = player.inventory.getCurrentItem();
						if(!(player.isSneaking())){
							
							pjumped = true;
							player.motionY = 0.9;
							damageItem(item, 1, player);
							
							
						}
					}
					
				}

				/** Diamond pogo **/
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Pogostick.dpogo){
					
					if(pjumped){
						
						ItemStack item = player.inventory.getCurrentItem();
						if(!(player.isSneaking())){
							
							event.setCanceled(true);
							player.motionY = 1;
							pjumped = true;
							damageItem(item, 1, player);
							
						}
					}
					
				}	

		}
		
	}
	
	@SubscribeEvent
	public void pogoTNT(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer && event.source.equals(DamageSource.fall)){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			
		if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem().equals(Pogostick.tntpogo)){
			
			if(pjumped){
				
				if(player.isSneaking()){
					
					activateTNTPogo = true;
				
				}else{
					
					pjumped = false;
					
				}
			}
		}
		
		if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem().equals(Pogostick.tntpogo)){
			
			if(pjumped){
				
				if(activateTNTPogo){
					
					World world = FMLClientHandler.instance().getServer().worldServerForDimension(player.dimension);
					world.createExplosion(player, player.posX, player.posY, player.posZ, 2.0F, true);
					
					ItemStack item = player.inventory.getCurrentItem();
					
					damageItem(item, 1, player);
					
					explosiondmg = true;
					activateTNTPogo = false;
					player.posY = player.lastTickPosY + 1;	
					
				}
			
			}
				
			
		}
		
		}else if(event.entity instanceof EntityPlayer){
			
			if(explosiondmg){
				
				explosiondmg = false;
				event.setCanceled(true);
				
			}
			
		}
		
	}
	

	@SubscribeEvent
	public void pogoKB(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer && event.source.equals(DamageSource.fall) && !FMLClientHandler.instance().getServer().isDedicatedServer()){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			World world = FMLClientHandler.instance().getServer().worldServerForDimension(player.dimension);
			
			
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem().equals(Pogostick.kpogo)){
				
				if(pjumped){
					
					if(player.isSneaking()){
					
						event.setCanceled(true);
						knockMobs(4.0D, world, player);
						ItemStack item = player.inventory.getCurrentItem();
						pjumped = false;
						damageItem(item, 1, player);
						
					}else{
						
						event.setCanceled(true);
						pjumped = false;
						
					}
				}
			}
			
		}
	}
	
	@SubscribeEvent
	public void flamingPogo(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer && event.source == DamageSource.fall){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if(pjumped){

				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem().equals(Pogostick.fpogo)){
					
					if(player.isSneaking()){
						
						pjumped = false;
						event.setCanceled(true);
						event.ammount = 0.0F;
						World world = FMLClientHandler.instance().getServer().worldServerForDimension(player.dimension);
						burnMobs(4.0D, world, player);
						ItemStack item = player.inventory.getCurrentItem();
						damageItem(item, 1, player);
					
					}else{
						
						pjumped = false;
						event.setCanceled(true);
						event.ammount = 0.0F;
						
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void pogoDmg(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer){
		
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem().equals(Pogostick.dmgpogo)){
				
				if(pjumped){
					
					if(player.isSneaking()){
						
						World world = FMLClientHandler.instance().getServer().worldServerForDimension(player.dimension);
						world.createExplosion(player, player.posX, player.posY, player.posZ, 3.0F, false);
						event.setCanceled(true);
						explosiondmg = true;
						ItemStack item = player.inventory.getCurrentItem();
						damageItem(item, 1, player);
						pjumped = false;
						
					}else{
						
						event.setCanceled(true);
						pjumped = false;
						
					}
				}
			}
		}	
	}
	
	public void knockMobs(double area, World world, EntityPlayer player){
		
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(area, area, area));
		world.createExplosion(player, player.posX, player.posY, player.posZ, 0.1F, false);
		for(Entity mob : entities){
			
			if(mob != null && mob != player && mob instanceof EntityLiving){
				
				Random rand = new Random();
				
				int temp = rand.nextInt(2);
				
				if(temp == 0){
				
					mob.motionX = 0.8;
					mob.motionY = 0.6;
					mob.motionZ = 0.8;
			
				}else if(temp == 1){
					
					mob.motionX = -0.8;
					mob.motionY = 0.6;
					mob.motionZ = 0.8;
					
				}else{
					
					mob.motionX = 0.8;
					mob.motionY = 0.6;
					mob.motionZ = -0.8;
					
				}
				
			}
		}
		
	}
	
	public void burnMobs(double area, World world, EntityPlayer player){
		
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(area, area, area));
		
		for(Entity mob:entities){
			
			if(mob != null && mob instanceof EntityLiving && mob != player){
				
				mob.setFire(3);

				Random rand = new Random();
				
				int temp = rand.nextInt(2);
				
				if(temp == 0){
				
					mob.motionX = 0.8;
					mob.motionY = 0.6;
					mob.motionZ = 0.8;
			
				}else if(temp == 1){
					
					mob.motionX = -0.8;
					mob.motionY = 0.6;
					mob.motionZ = 0.8;
					
				}else{
					
					mob.motionX = 0.8;
					mob.motionY = 0.6;
					mob.motionZ = -0.8;
					
				}
				
			}
			
		}
		
	}
	
	public static int getFixedCoord(double pos){
		
		String spos = String.valueOf(pos);
		
		if(spos.contains(".")){
		
			String fixpos = (spos.substring(0, spos.indexOf("."))).replace(".", "");
			
			int fpos = Integer.parseInt(fixpos);
			if(("" + fpos).contains("-")){
				return fpos - 1;
			}else{
				return fpos + 1;
			}
		}
		
		return Integer.parseInt(spos);
		
	}	
	
	public static void damageItem(ItemStack item, int amount, EntityPlayer player) {
		
		if(item == null || amount == 0){
			return;
		}
		
		Random rand = new Random();
		
		if(item.attemptDamageItem(amount, rand)){
			item.setItemDamage(item.getMaxDamage());
			player.inventory.clearInventory(item.getItem(), item.getMaxDamage());
		}
		
	}
	
	@SubscribeEvent
	public void pogoDamageHandler(LivingHurtEvent event){
		
		if(event.entity instanceof EntityPlayer && pjumped){
			EntityPlayer player = (EntityPlayer)event.entity;
			if(!(player.inventory.getCurrentItem().equals(null) || player.inventory.getCurrentItem().getItem().equals(Pogostick.tntpogo) || player.inventory.getCurrentItem().getItem().equals(Pogostick.kpogo) || player.inventory.getCurrentItem().getItem().equals(Pogostick.dmgpogo) || player.inventory.getCurrentItem().getItem().equals(Pogostick.fpogo))){		
			event.setCanceled(true);
			}
		}
		
	}

}
