package benjibobs.Pogostick.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHarmPogo extends Item {

	public ItemHarmPogo(int id) {
		super(id);
		this.setCreativeTab(Pogostick.tabPogostick);
		this.setMaxDamage(1561);
		this.maxStackSize = 1;
		this.canRepair = false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "dmgpogo");

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
					EntityPlayer.inventory.clearInventory(this.itemID, item.getMaxDamage());
					
				}
			}else{
				Pogostick.ground = false;
				
			}
			
			
			
		
		return super.onItemRightClick(ItemStack, World, EntityPlayer);
	}

}
