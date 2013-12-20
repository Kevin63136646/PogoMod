package benjibobs.Pogostick.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockTramp extends Block {
	
	public BlockTramp(int id, String texture) {
		super(id, Material.ground);
		this.setCreativeTab(Pogostick.tabPogostick);
		
		
	}
	
	@SideOnly(Side.CLIENT)
	public static Icon topIcon;
	@SideOnly(Side.CLIENT)
	public static Icon bottomIcon;
	@SideOnly(Side.CLIENT)
	public static Icon sideIcon;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
	topIcon = icon.registerIcon(Pogostick.modid + ":" + "bouncer_top");
	bottomIcon = icon.registerIcon(Pogostick.modid + ":" + "bouncer_bottom");
	sideIcon = icon.registerIcon(Pogostick.modid + ":" + "bouncer_side");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
	if(side == 0) {
	return bottomIcon;
	} else if(side == 1) {
	return topIcon;
	} else {
	return sideIcon;
	}
	}
}
