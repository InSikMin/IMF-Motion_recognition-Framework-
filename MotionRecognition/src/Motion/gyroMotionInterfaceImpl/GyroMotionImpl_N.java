
package Motion.gyroMotionInterfaceImpl;

import Motion.Interfaces.GyroMotionInterface;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author CheolMin Kim
 */

public class GyroMotionImpl_N implements GyroMotionInterface{

	/**
	 * It is ovverided method that writes an algorithm to recognize the N motion using a list containing arrays(difference[]).
	 * @param differenceResultList
	 *  List containing arrays(difference[]) having angular difference values at  index numbers that are equal to the index number of yawRollPitchRangeList.
	 * difference[0] is Information about the order in which values were sent. 
	 * difference[1] is Information on the yaw axis difference value.
	 * difference[2] is Information on the roll axis difference value.
	 * difference[3] is Information on the pitch axis difference value.
	 * @param motionMap
	 * Map with motion names of String type as Keys , counts of Integer type as Values.
	 */
	@Override
	public void gyroMotion(List<List> differenceResultList, Map<String, Integer> motionMap) {
		
		int nCount=0;
		boolean step1=true;
		boolean step2=false;
		boolean step3=false;
		boolean check = false;
		List<double[]> factorsInRange = differenceResultList.get(7);
		for (int j = 0; j < factorsInRange.size(); j++) {

			double[] count = factorsInRange.get(j);
			if(step1==true){
		
				if (count[2] < 0&&count[1]==0) {
					
					check = false;

					nCount++;
				} else if (count[1] < 0&&count[2]>0) {
					if (check) {
						step1 = false;
						step2 = true;
						System.out.println("N - Step1");
						nCount++;
						nCount++;
						
					}else{
					check = true;
					}
				} else {
					check = false;
				}
			
			}else if(step2==true){
				if (count[1] < 0&&count[2]>0) {
					step2=false;	
					step3=true;    
					System.out.println("N-Step2");
					nCount++;
					}
				
			}else if(step3==true){
				if (count[2] < 0&&count[1]==0) {
					System.out.println("N-Step3");
					nCount++;
					
				}
			}
			
		}
		if(step3==true){
		motionMap.put("n", nCount);
		}else{
			motionMap.put("n", 0);
		}
	}

}
