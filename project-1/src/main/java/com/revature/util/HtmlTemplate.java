package com.revature.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/*
 * This helper class is used to faciliate creating HTML documents
 * from Java with more ease!
 *  
 */
public class HtmlTemplate {
	private static Logger log = Logger.getLogger(HtmlTemplate.class);

	static {
		log.warn("request failed. showing error page.");
	}

	// this method is used to return after a failed request (like incorrect
	// credentials)
	public static void goBack(PrintWriter pw) {
		pw.println("<hr><input type='button' value='BACK'" + " onclick='goBack();'>" + " <script>"
				+ " function goBack(){" + "window.history.back(); }" + "</script>");
	}

	// this method establishes our custom html writer for responses
	public static PrintWriter getHtmlWriter(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		return response.getWriter();
	}

	// this method prints our table headers for us!
	// PLEASE NOTE: the ...headers parameters is VAR ARGS (which accepts an array of
	// params -- of an unspecified capacity)
	// ex. PrintWriter + ["header1", "header2", etc.]
	public static void printTableHeaders(PrintWriter pw, String... headers) {
		pw.println("<table border=2px><tr>");

		// iterate array thru to print each table header
		for (String head : headers) {
			pw.println("<th>" + head + "</th>");
		}

		// end of table header
		pw.println("</tr></table>");
	}
}