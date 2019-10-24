package Helper;


import io.restassured.path.json.JsonPath;

import java.util.Dictionary;
import java.util.Hashtable;


public  class Helper {

	/*This method is used to generates a Dictionary object from a JSON String object.*/
	
	public  Dictionary<String, String> GenerateDataDictionary(String jsonString,
			String rootPath, String keyNode, String valueNode) {
		
		/*Creating JSON XPath from  JSON response*/ 
		JsonPath jsonPath = new JsonPath(jsonString).setRootPath(rootPath);

		/*creating a string array based on the user inputs (keyNode)
		using regex. to remove all the special characters and spaces if there are any*/
		
		String[] applicableDate = jsonPath.getString(keyNode).split(
				" *(,|=>| ) *");
		
		/*creating a string array based on the user inputs (valueNode) */  
		String[] weatherStatus = jsonPath.getString(valueNode).split(",");

		/*creating a Dictionary Object*/
		Dictionary<String, String> dataDictionary = new Hashtable<String, String>();

		/*Adding values (key and value pairs) in the dictionary.*/
		
		for (int i = 0; i < applicableDate.length - 1; i++) {
			dataDictionary.put(applicableDate[i].replaceAll("\\[|\\]", ""), weatherStatus[i].replaceAll("\\[|\\]", ""));
		}
		return dataDictionary;
	}
}
