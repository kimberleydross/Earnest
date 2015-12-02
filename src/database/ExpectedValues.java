package database;

import java.util.Arrays;
import java.util.List;

public class ExpectedValues {
	
	public List<String> stateDropDownList(){
	
		//simulate iterating through a db table and populating a list of expected values
		String states = " Alabama Alaska Arizona Arkansas California Colorado Connecticut Delaware Florida Georgia Hawaii Idaho Illinois Indiana Iowa Kansas Kentucky Louisiana Maine Maryland Massachusetts Michigan Minnesota Mississippi Missouri Montana Nebraska Nevada New Hampshire New Jersey New Mexico New York North Carolina North Dakota Ohio Oklahoma Oregon Pennsylvania Rhode Island South Carolina South Dakota Tennessee Texas Utah Vermont Virginia Washington West Virginia Wisconsin Wyoming";
		
		//split the string on any white space
		List<String> expectedStatesDropDown = Arrays.asList(states.split("\\s*,\\s*"));
		
		//return List
		return expectedStatesDropDown;
	}
	
}
