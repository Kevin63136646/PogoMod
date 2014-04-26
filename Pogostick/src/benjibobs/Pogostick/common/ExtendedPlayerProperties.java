package benjibobs.Pogostick.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayerProperties implements IExtendedEntityProperties {
	
	public final static String bouncerProp = "lastJumpBouncer";
	
	private final EntityPlayer player;
	
	public static boolean jumpWasBouncer = false;
	
	public ExtendedPlayerProperties(EntityPlayer player, boolean jwb)
	{
		this.player = player;
		this.jumpWasBouncer = jwb;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(bouncerProp, new ExtendedPlayerProperties(player, false));
	}

	@Override
	public void init(Entity arg0, World arg1) {
		
		//No purpose?
		
	}

	@Override
	public void loadNBTData(NBTTagCompound arg0) {

		NBTTagCompound properties = (NBTTagCompound)arg0.getTag(this.bouncerProp);
		// Get our data from the custom tag compound
		this.jumpWasBouncer = properties.getBoolean(this.bouncerProp);
		// Just so you know it's working, add this line:
		
	}
	
	public static final ExtendedPlayerProperties get(EntityPlayer player)
	{
	return (ExtendedPlayerProperties)player.getExtendedProperties(bouncerProp);
	}

	@Override
	public void saveNBTData(NBTTagCompound arg0) {

		NBTTagCompound props = new NBTTagCompound();
		
		props.setBoolean(this.bouncerProp, this.jumpWasBouncer);
		
		arg0.setTag(this.bouncerProp, props);
		
	}

}
