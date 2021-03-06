package gregtech.common.covers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fluids.Fluid;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.util.GT_CoverBehavior;
import gregtech.api.util.GT_Utility;

public class GT_Cover_NeedMaintainance extends GT_CoverBehavior{

	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity, long aTimer)
	  {
		boolean needsRepair = false;
		if(aTileEntity instanceof IGregTechTileEntity){
		IGregTechTileEntity tTileEntity = (IGregTechTileEntity) aTileEntity;
		IMetaTileEntity mTileEntity = tTileEntity.getMetaTileEntity();
		if(mTileEntity instanceof GT_MetaTileEntity_MultiBlockBase){
			GT_MetaTileEntity_MultiBlockBase multi = (GT_MetaTileEntity_MultiBlockBase) mTileEntity;
			int ideal = multi.getIdealStatus();
			int real = multi.getRepairStatus();
			if((aCoverVariable ==0||aCoverVariable==1)&&(ideal-real>0)){
				needsRepair=true;
			}
			if((aCoverVariable ==2||aCoverVariable==3)&&(ideal-real>1)){
				needsRepair=true;
			}if((aCoverVariable ==4||aCoverVariable==5)&&(ideal-real>2)){
				needsRepair=true;
			}if((aCoverVariable ==6||aCoverVariable==7)&&(ideal-real>3)){
				needsRepair=true;
			}
		}}
		if(aCoverVariable % 2 == 0){
			needsRepair = !needsRepair;
		}
		
		aTileEntity.setOutputRedstoneSignal(aSide, (byte)(needsRepair ? 0 : 15));
		return aCoverVariable;
	}
	
	public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ)
	  {
	    aCoverVariable = (aCoverVariable + 1) % 10;
	    if (aCoverVariable == 0) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 1 Maintainance Needed");
	    }
	    if (aCoverVariable == 1) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 1 Maintainance Needed(inverted)");
	    }
	    if (aCoverVariable == 2) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 2 Maintainance Needed");
	    }
	    if (aCoverVariable == 3) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 2 Maintainance Needed(inverted)");
	    }
	    if (aCoverVariable == 4) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 3 Maintainance Needed");
	    }
	    if (aCoverVariable == 5) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 3 Maintainance Needed(inverted)");
	    }
	    if (aCoverVariable == 6) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 4 Maintainance Needed");
	    }
	    if (aCoverVariable == 7) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 4 Maintainance Needed(inverted)");
	    }
	    if (aCoverVariable == 8) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 5 Maintainance Needed");
	    }
	    if (aCoverVariable == 9) {
	      GT_Utility.sendChatToPlayer(aPlayer, "Emit if 5 Maintainance Needed(inverted)");
	    }
	    return aCoverVariable;
	  }
	  
	  public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
	  {
	    return true;
	  }
	  
	  public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
	  {
	    return true;
	  }
	  
	  public boolean letsFluidIn(byte aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity)
	  {
	    return true;
	  }
	  
	  public boolean letsFluidOut(byte aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity)
	  {
	    return true;
	  }
	  
	  public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity)
	  {
	    return true;
	  }
	  
	  public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity)
	  {
	    return true;
	  }
	  
	  public boolean manipulatesSidedRedstoneOutput(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
	  {
	    return true;
	  }
	  
	  public int getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity)
	  {
	    return 60;
	  }
	
}
