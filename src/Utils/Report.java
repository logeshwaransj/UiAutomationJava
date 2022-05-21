package utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class Report {
	protected static ExtentReports _extent;
	
	public static ExtentReports GetExtent()
	{
		File dir = new File("C://Reports");
		if (!dir.exists()){
			dir.mkdirs();
		}
		
		var fileName = "/report"+ System.currentTimeMillis()+".html";
		_extent = new ExtentReports(dir + fileName);
		
		return _extent;
	}
}
