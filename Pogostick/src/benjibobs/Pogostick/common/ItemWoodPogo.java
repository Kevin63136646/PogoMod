package benjibobs.Pogostick.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ItemWoodPogo extends ItemPickaxe {

	

	

	public ItemWoodPogo(int id, EnumToolMaterial EnumToolMaterial) {
		super(id, EnumToolMaterial);
		this.setCreativeTab(Pogostick.tabPogostick);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "woodpogo");

	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack ItemStack, World World,
			EntityPlayer EntityPlayer) {
			
			
			
			
			
		
		return super.onItemRightClick(ItemStack, World, EntityPlayer);
	}
	
}
