package benjibobs.Pogostick.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockTramp extends Block {
	
	public BlockTramp(String texture) {
		super(Material.cloth);
		this.setCreativeTab(Pogostick.tabPogostick);
		
		
	}
	
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon bottomIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon sideIcon;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
	topIcon = icon.registerIcon(Pogostick.modid + ":" + "semibouncer_top");
	bottomIcon = icon.registerIcon(Pogostick.modid + ":" + "semibouncer_bottom");
	sideIcon = icon.registerIcon(Pogostick.modid + ":" + "semibouncer_side");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
	if(side == 0) {
	return bottomIcon;
	} else if(side == 1) {
	return topIcon;
	} else {
	return sideIcon;
	}
	}
}
