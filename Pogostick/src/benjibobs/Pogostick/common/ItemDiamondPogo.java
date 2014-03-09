package benjibobs.Pogostick.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDiamondPogo extends Item {

	public ItemDiamondPogo() {
		this.setCreativeTab(Pogostick.tabPogostick);
		this.setMaxDamage(1561);
		this.canRepair = false;
		this.maxStackSize = 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "diamondpogo");

	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer) {
			ItemStack item = EntityPlayer.getHeldItem();
			if(EntityPlayer.onGround){
				Pogostick.ground = true;
				EntityPlayer.motionY = 1;
				PogostickEvents.pjumped = true;
				int dur = item.getItemDamage() + 1;
				int durc = item.getMaxDamage() - item.getItemDamage();
				if(dur < item.getMaxDamage() && durc > 0){
					item.setItemDamage(dur);
					
				}else{
					item.setItemDamage(item.getMaxDamage());
					EntityPlayer.inventory.clearInventory(Pogostick.dpogo, item.getMaxDamage());
					
				}
			}else{
				Pogostick.ground = false;
				
			}
			
			
			
		
		return super.onItemRightClick(ItemStack, World, EntityPlayer);
	}

	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return Items.diamond == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

}
