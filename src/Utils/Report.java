package utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.relevantcodes.extentreports.ExtentReports;

public class Report {
	protected static ExtentReports _extent;

	public static ExtentReports GetExtent() {
		if (_extent != null)
			return _extent;

		File dir = new File("C://Reports");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		var fileName = "/report" + getTodayDate() + ".html";

		_extent = new ExtentReports(dir + fileName);

		return _extent;
	}

	public static String getTodayDate() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}
