package benjibobs.Pogostick.common;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemPogoboots extends ItemArmor{

	public ItemPogoboots(ArmorMaterial MaterialEnum,
			int matid, int type) {
		super(MaterialEnum, matid, type);
		this.setCreativeTab(Pogostick.tabPogostick);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "pogoboots");

	}

	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
			if (stack.getItem() == Pogostick.pogoboots
			) {
			return "bpogostick:textures/models/armor/pogobootsrender_1.png";
			}
			else {
			return null;
			}
	
	
	

}
}
