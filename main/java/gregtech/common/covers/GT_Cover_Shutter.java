package gregtech.common.covers;

import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.interfaces.tileentity.IMachineProgress;
import gregtech.api.util.GT_CoverBehavior;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fluids.Fluid;

public class GT_Cover_Shutter
  extends GT_CoverBehavior
{
  public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity, long aTimer)
  {
    return aCoverVariable;
  }
  
  public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ)
  {
    aCoverVariable = (aCoverVariable + 1) % 4;
    if (aCoverVariable == 0) {
      GT_Utility.sendChatToPlayer(aPlayer, "Open if work enabled");
    }
    if (aCoverVariable == 1) {
      GT_Utility.sendChatToPlayer(aPlayer, "Open if work disabled");
    }
    if (aCoverVariable == 2) {
      GT_Utility.sendChatToPlayer(aPlayer, "Only Output allowed");
    }
    if (aCoverVariable == 3) {
      GT_Utility.sendChatToPlayer(aPlayer, "Only Input allowed");
    }
    return aCoverVariable;
  }
  
 public boolean letsRedstoneGoIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
{
    return aCoverVariable >= 2 ? aCoverVariable == 3 : (aTileEntity instanceof IMachineProgress) ? ((IMachineProgress)aTileEntity).isAllowedToWork() ? aCoverVariable % 2 == 0 : aCoverVariable % 2 != 0 : true;
}

public boolean letsRedstoneGoOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
{
    return aCoverVariable >= 2 ? aCoverVariable == 2 : (aTileEntity instanceof IMachineProgress) ? ((IMachineProgress)aTileEntity).isAllowedToWork() ? aCoverVariable % 2 == 0 : aCoverVariable % 2 != 0 : true;
}

public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
{
    return aCoverVariable >= 2 ? aCoverVariable == 3 : (aTileEntity instanceof IMachineProgress) ? ((IMachineProgress)aTileEntity).isAllowedToWork() ? aCoverVariable % 2 == 0 : aCoverVariable % 2 != 0 : true;
}

public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
{
    return aCoverVariable >= 2 ? aCoverVariable == 2 : (aTileEntity instanceof IMachineProgress) ? ((IMachineProgress)aTileEntity).isAllowedToWork() ? aCoverVariable % 2 == 0 : aCoverVariable % 2 != 0 : true;
}

public boolean letsFluidIn(byte aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity)
{
    return aCoverVariable >= 2 ? aCoverVariable == 3 : (aTileEntity instanceof IMachineProgress) ? ((IMachineProgress)aTileEntity).isAllowedToWork() ? aCoverVariable % 2 == 0 : aCoverVariable % 2 != 0 : true;
}

public boolean letsFluidOut(byte aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity)
{
    return aCoverVariable >= 2 ? aCoverVariable == 2 : (aTileEntity instanceof IMachineProgress) ? ((IMachineProgress)aTileEntity).isAllowedToWork() ? aCoverVariable % 2 == 0 : aCoverVariable % 2 != 0 : true;
}

public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity)
{
    return aCoverVariable >= 2 ? aCoverVariable == 3 : (aTileEntity instanceof IMachineProgress) ? ((IMachineProgress)aTileEntity).isAllowedToWork() ? aCoverVariable % 2 == 0 : aCoverVariable % 2 != 0 : true;
}

public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity)
{
    return aCoverVariable >= 2 ? aCoverVariable == 2 : (aTileEntity instanceof IMachineProgress) ? ((IMachineProgress)aTileEntity).isAllowedToWork() ? aCoverVariable % 2 == 0 : aCoverVariable % 2 != 0 : true;
}
  
  public boolean alwaysLookConnected(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
  {
    return true;
  }
  
  public int getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
  {
    return 0;
  }
}


/* Location:           F:\Torrent\minecraft\jd-gui-0.3.6.windows\gregtech_1.7.10-5.07.07-dev.jar
 * Qualified Name:     gregtech.common.covers.GT_Cover_Shutter
 * JD-Core Version:    0.7.0.1
 */