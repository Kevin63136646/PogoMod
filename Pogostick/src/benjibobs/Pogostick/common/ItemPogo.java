package benjibobs.Pogostick.common;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPogo extends Item {

	public ItemPogo() {
		this.setCreativeTab(Pogostick.tabPogostick);
		this.maxStackSize = 1;
	}
	
	public boolean onground = false;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "pogostick");

	}

//	@Override
//	public ItemStack onItemRightClick(ItemStack ItemStack, World World,
//			EntityPlayer EntityPlayer) {
//			
//			if(EntityPlayer.onGround){
//				onground = true;
//			}else{
//				onground = false;
//			}
//			
//			if(onground){
//			EntityPlayer.motionY = 0.8;
//			PogostickEvents.pjumped = true;
//			}
//			
//			
//			
//		
//		return super.onItemRightClick(ItemStack, World, EntityPlayer);
//	}

}
