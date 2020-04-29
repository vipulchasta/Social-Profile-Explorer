package com.abyeti.util;

public class Util {

	public static String getTechStack(String fileNameWithPath) {

		String fileExtension = fileNameWithPath.substring(fileNameWithPath.length() - 5);
		if (fileExtension.indexOf('.') > 0)
			fileExtension = fileExtension.substring(fileExtension.indexOf('.') + 1);
		fileExtension.toLowerCase();

		if (fileNameWithPath.contains("hibernate.cfg.xml")) {
			return "Hibernate";
		} else if (fileNameWithPath.contains("bootstrap.js")) {
			return "Twitter's Bootstrap";
		} else if (fileNameWithPath.contains("pom.xml")) {
			return "Maven";
		} else if (fileExtension.equals("java")) {
			return "Java";
		} else if (fileExtension.equals("cpp")) {
			return "C++";
		} else if (fileExtension.equals("c")) {
			return "C";
		} else if (fileExtension.equals("rb")) {
			return "Ruby";
		} else if (fileExtension.equals("py")) {
			return "Python";
		} else if (fileExtension.equals("html")) {
			return "HTML";
		} else if (fileExtension.equals("css")) {
			return "CSS";
		} else if (fileExtension.equals("jsp")) {
			return "JSP";
		} else if (fileExtension.equals("js")) {
			return "JavaScript";
		}

		return null;
	}

}
