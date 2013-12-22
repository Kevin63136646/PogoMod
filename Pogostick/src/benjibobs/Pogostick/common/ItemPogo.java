package benjibobs.Pogostick.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ItemPogo extends Item {

	public ItemPogo(int id) {
		super(id);
		this.setCreativeTab(Pogostick.tabPogostick);
		this.maxStackSize = 1;
	}
	
	public boolean onground = false;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
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
