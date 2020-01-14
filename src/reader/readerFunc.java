package reader;

import common.user;
/**
 * enum for types of iBook memberships
 * @author Tomer
 *
 */
public class readerFunc {
	
	public static String returnIbookType(){
		if(user.ibookStatus==1)
			return "Regular Ibook";
		if(user.ibookStatus==2)
			return "Monthly Ibook";
		if(user.ibookStatus==3)
			return "Yearly Ibook";
		else return "Error";
	}

}
