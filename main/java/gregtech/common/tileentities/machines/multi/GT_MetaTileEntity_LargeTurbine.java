package gregtech.common.tileentities.machines.multi;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.GT_MetaGenerated_Tool_01;

public abstract class GT_MetaTileEntity_LargeTurbine extends GT_MetaTileEntity_MultiBlockBase{

	public GT_MetaTileEntity_LargeTurbine(int aID, String aName, String aNameRegional){super(aID, aName, aNameRegional);}
	public GT_MetaTileEntity_LargeTurbine(String aName){super(aName);}


	@Override
	public boolean isCorrectMachinePart(ItemStack aStack) {
		return getMaxEfficiency(aStack) > 0;
	}

	protected int baseEff=0;
	protected int optFlow=0;
	protected int counter=0;

	@Override
	public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
	    byte tSide = getBaseMetaTileEntity().getBackFacing();
	    if ((getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 1)) && (getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2)))
	    {
	      int tAirCount = 0;
	      for (byte i = -1; i < 2; i = (byte)(i + 1)) {
	        for (byte j = -1; j < 2; j = (byte)(j + 1)) {
	          for (byte k = -1; k < 2; k = (byte)(k + 1)) {
	            if (getBaseMetaTileEntity().getAirOffset(i, j, k)) {
	              tAirCount++;
	            }
	          }
	        }
	      }
	      if (tAirCount != 10) {
	        return false;
	      }
	      for (byte i = 2; i < 6; i = (byte)(i + 1))
	      {
	        IGregTechTileEntity tTileEntity;
	        if ((null != (tTileEntity = getBaseMetaTileEntity().getIGregTechTileEntityAtSideAndDistance(i, 2))) && 
	          (tTileEntity.getFrontFacing() == getBaseMetaTileEntity().getFrontFacing()) && (tTileEntity.getMetaTileEntity() != null) && 
	          ((tTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_LargeTurbine))) {
	          return false;
	        }
	      }
	      int tX = getBaseMetaTileEntity().getXCoord();int tY = getBaseMetaTileEntity().getYCoord();int tZ = getBaseMetaTileEntity().getZCoord();
	      for (byte i = -1; i < 2; i = (byte)(i + 1)) {
	        for (byte j = -1; j < 2; j = (byte)(j + 1)) {
	          if ((i != 0) || (j != 0)) {
	            for (byte k = 0; k < 4; k = (byte)(k + 1)) {
	              if (((i == 0) || (j == 0)) && ((k == 1) || (k == 2)))
	              { 
	                if (getBaseMetaTileEntity().getBlock(tX + (tSide == 5 ? k : tSide == 4 ? -k : i), tY + j, tZ + (tSide == 2 ? -k : tSide == 3 ? k : i)) == getCasingBlock() && getBaseMetaTileEntity().getMetaID(tX + (tSide == 5 ? k : tSide == 4 ? -k : i), tY + j, tZ + (tSide == 2 ? -k : tSide == 3 ? k : i)) == getCasingMeta())
	                {}else if (!addToMachineList(getBaseMetaTileEntity().getIGregTechTileEntity(tX + (tSide == 5 ? k : tSide == 4 ? -k : i), tY + j, tZ + (tSide == 2 ? -k : tSide == 3 ? k : i)))) {
	                	return false;
	                }
	              }
	              else if (getBaseMetaTileEntity().getBlock(tX + (tSide == 5 ? k : tSide == 4 ? -k : i), tY + j, tZ + (tSide == 2 ? -k : tSide == 3 ? k : i)) == getCasingBlock()&& getBaseMetaTileEntity().getMetaID(tX + (tSide == 5 ? k : tSide == 4 ? -k : i), tY + j, tZ + (tSide == 2 ? -k : tSide == 3 ? k : i)) == getCasingMeta())
	              {}else {return false;
	              }
	            }
	          }
	        }
	      }
	      this.mDynamoHatches.clear();
	      IGregTechTileEntity tTileEntity = getBaseMetaTileEntity().getIGregTechTileEntityAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 3);
	      if ((tTileEntity != null) && (tTileEntity.getMetaTileEntity() != null)) {
	        if ((tTileEntity.getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Dynamo)) {
	          this.mDynamoHatches.add((GT_MetaTileEntity_Hatch_Dynamo)tTileEntity.getMetaTileEntity());
	          ((GT_MetaTileEntity_Hatch)tTileEntity.getMetaTileEntity()).mMachineBlock = getCasingTextureIndex();
	        } else {
	          return false;
	        }
	      }
	    }
	    else
	    {
	      return false;
	    }
	    return true;
	    }
	
	   public abstract Block getCasingBlock();
	   
	   public abstract byte getCasingMeta();
	   
	   public abstract byte getCasingTextureIndex();
	
	private boolean addToMachineList(IGregTechTileEntity tTileEntity){
		return ((addMaintenanceToMachineList(tTileEntity, getCasingTextureIndex())) || (addInputToMachineList(tTileEntity, getCasingTextureIndex())) || (addOutputToMachineList(tTileEntity, getCasingTextureIndex()))|| (addMufflerToMachineList(tTileEntity, getCasingTextureIndex())));
	}
	

	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
	}
	
	@Override
	public boolean checkRecipe(ItemStack aStack) {
		ArrayList<FluidStack> tFluids = getStoredFluids();
		if (tFluids.size() > 0) {
		    if (baseEff == 0 || optFlow == 0 || counter >= 1000 || this.getBaseMetaTileEntity().hasWorkJustBeenEnabled()
		            || this.getBaseMetaTileEntity().hasInventoryBeenModified()) {
		        counter = 0;
		        baseEff = (int) ((50.0F
		                + (10.0F * ((GT_MetaGenerated_Tool) aStack.getItem()).getToolCombatDamage(aStack))) * 100);
		        optFlow = (int) Math.max(Float.MIN_NORMAL,
		                ((GT_MetaGenerated_Tool) aStack.getItem()).getToolStats(aStack).getSpeedMultiplier()
		                        * ((GT_MetaGenerated_Tool) aStack.getItem()).getPrimaryMaterial(aStack).mToolSpeed
		                        * 50);
		    } else {
		        counter++;
		    }
		}
		
		int newPower = fluidIntoPower(tFluids, optFlow, baseEff);  // How much the turbine should be producing with this flow
		int difference = newPower - this.mEUt; // difference between current output and new output
		
		// Magic numbers: can always change by at least 10 eu/t, but otherwise by at most 1 percent of the difference in power level (per tick)
		// This is how much the turbine can actually change during this tick
		int maxChangeAllowed = Math.max(10, (int) Math.ceil(Math.abs(difference) * 0.01));
		
		if (Math.abs(difference) > maxChangeAllowed) { // If this difference is too big, use the maximum allowed change
		    int change = maxChangeAllowed * (difference > 0 ? 1 : -1); // Make the change positive or negative.
		    this.mEUt += change; // Apply the change
		}
		else
		    this.mEUt = newPower;
		
	        if (mEUt <= 0) {
	
	            this.mEfficiencyIncrease = (-10);
	            //stopMachine();
	            return false;
	        } else {
	            this.mMaxProgresstime = 1;
	            this.mEfficiencyIncrease = (10);
	            return true;
	        }
	}
	
	abstract int fluidIntoPower(ArrayList<FluidStack> aFluids, int aOptFlow, int aBaseEff);
	
	@Override
	public int getDamageToComponent(ItemStack aStack) {
	    return 1;
	  }

	 public int getMaxEfficiency(ItemStack aStack)
	  {
	    if (GT_Utility.isStackInvalid(aStack)) {
	      return 0;
	    }
	    if (aStack.getItem() instanceof GT_MetaGenerated_Tool_01) {
	    	return 10000;
	    }
	    return 0;
	  }
	 
	@Override
	public int getAmountOfOutputs() {return 0;}
	@Override
	public boolean explodesOnComponentBreak(ItemStack aStack) {return true;}
}
