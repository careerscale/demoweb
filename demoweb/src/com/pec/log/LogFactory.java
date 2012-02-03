package com.pec.log;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogFactory {
	/*static{
		PropertyConfigurator.configure("log4j.properties");
	}*/
	public static Logger getLogger(){
		return Logger.getLogger("demoWebLogger");
	}

}
