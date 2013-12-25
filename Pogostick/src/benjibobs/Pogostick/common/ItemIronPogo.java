package benjibobs.Pogostick.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemIronPogo extends Item {

	public ItemIronPogo(int id) {
		super(id);
		this.setCreativeTab(Pogostick.tabPogostick);
		this.setMaxDamage(250);
		this.canRepair = false;
		this.maxStackSize = 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "ironpogo");

	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer) {
			ItemStack item = EntityPlayer.getHeldItem();
			if(EntityPlayer.onGround){
				Pogostick.ground = true;
				EntityPlayer.motionY = 0.9;
				PogostickEvents.pjumped = true;
				item.setItemDamage(item.getItemDamage() + 1);
			}else{
				Pogostick.ground = false;
				
			}
			
			
			
		
		return super.onItemRightClick(ItemStack, World, EntityPlayer);
	}
	
}
