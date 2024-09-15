package org.mess.pridict.config;

import java.io.*;
import java.util.*;

public class PathHelper {
	
	public static FileInputStream fin=null;
	public static File f=null;
	static {
		f=new File(".");
		String path=(f.getAbsolutePath().substring(0,f.getAbsolutePath().length()-1)+"src\\db.properties");
//		System.out.println(path);
		try {
			fin=new FileInputStream(path);
		} catch (Exception e) {
			System.out.println("Error in the PathHelper Class and error is "+e);
		}
		
	}
}
