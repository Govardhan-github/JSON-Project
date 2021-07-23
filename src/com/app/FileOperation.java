package com.app;



	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;


	import org.json.simple.JSONObject;
	import org.json.simple.parser.JSONParser;
	import org.json.simple.parser.ParseException;


	public class FileOperation {


	public static JSONObject getJSONObjectFromJSONFile(String filePath) throws IOException, ParseException {

	JSONParser jsonparser = new JSONParser();
	FileReader reader = new FileReader(filePath);
	        Object obj = jsonparser.parse(reader);
	        JSONObject jsonObject = (JSONObject)obj;
	        reader.close();
	        return jsonObject;
	}

	public static void writeJSONStringIntoFile(String filePath, String jsonString) throws IOException {
	FileWriter fw=new FileWriter(filePath);    
	        fw.write(jsonString);    
	        fw.close(); 
	}
	
}
