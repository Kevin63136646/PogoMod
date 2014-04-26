package benjibobs.Pogostick.common;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGoldPogo extends Item {

	public ItemGoldPogo() {
		this.setCreativeTab(Pogostick.tabPogostick);
		this.setMaxDamage(32);
		//TODO: Fix this for all pogos, allow to be repaired.
		this.canRepair = false;
		this.maxStackSize = 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "goldpogo");

	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer) {
			ItemStack item = EntityPlayer.getHeldItem();
			if(EntityPlayer.onGround){
				Pogostick.ground = true;
				EntityPlayer.motionY = 0.9;
				PogostickEvents.pjumped = true;
				int dur = item.getItemDamage() + 1;
				int durc = item.getMaxDamage() - item.getItemDamage();
				if(dur < item.getMaxDamage() && durc > 0){
					item.setItemDamage(dur);
					
				}else{
					item.setItemDamage(item.getMaxDamage());
					EntityPlayer.inventory.clearInventory(Pogostick.gpogo, item.getMaxDamage());
					
				}
			}else{
				Pogostick.ground = false;
				
			}
			
			
			
		
		return super.onItemRightClick(ItemStack, World, EntityPlayer);
	}

}
