package common;
/**
 * 
 * @author Hadi
 *class that save the connection details to the data base
 *include db name, user name, password and ip
 *every call to the data base we have to insert this details
 */
public final class dbInfo {
	public static String dbname, usrname, pass, ip;
	public dbInfo(String Dbname,String Usrname,String Pass ,String Ip){
		dbname=Dbname;
		pass=Pass;
		usrname=Usrname;
		ip=Ip;
	}
}
