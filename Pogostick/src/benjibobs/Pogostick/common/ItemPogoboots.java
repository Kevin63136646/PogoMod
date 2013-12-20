package benjibobs.Pogostick.common;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemPogoboots extends ItemArmor{

	public ItemPogoboots(int id, EnumArmorMaterial MaterialEnum,
			int matid, int type) {
		super(id, MaterialEnum, matid, type);
		this.setCreativeTab(Pogostick.tabPogostick);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Pogostick.modid + ":" + "pogoboots");

	}

	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			int layer) {
			if (stack.itemID == Pogostick.pogoboots.itemID
			) {
			return "bpogostick:textures/models/armor/pogobootsrender_1.png";
			}
			else {
			return null;
			}
	
	
	

}
}
