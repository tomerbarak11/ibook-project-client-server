package common;
/**
 * this class saves the user info after the log in
 * @author Nadav
 *
 */
public class user {
	public static String userName;
	public static int accessLevel,userID,ibookStatus;
	
	
	public user(int userID,String userName, int accessLevel,int ibookStatus){
		user.userID=userID;
		user.accessLevel=accessLevel;
		user.userName=userName;
		user.ibookStatus=ibookStatus;
	}
	

}
