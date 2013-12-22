package benjibobs.Pogostick.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWoodPogo extends Item {

	public ItemWoodPogo(int id) {
		super(id);
		this.maxStackSize = 1;
        this.setMaxDamage(59);
        this.setCreativeTab(Pogostick.tabPogostick);
        this.canRepair = false;
	}
	
	public static boolean ground;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "woodpogo");

	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer) {
			ItemStack item = EntityPlayer.getHeldItem();
			if(EntityPlayer.onGround){
				ground = true;
				EntityPlayer.motionY = 0.6;
				PogostickEvents.pjumped = true;
				item.setItemDamage(item.getItemDamage() + 1);
			}else{
				ground = false;
				PogostickEvents.pjumped = false;
			}
			
			
			
		
		return super.onItemRightClick(ItemStack, World, EntityPlayer);
	}
	
}
