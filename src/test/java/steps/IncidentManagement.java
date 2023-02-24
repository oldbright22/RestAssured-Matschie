package steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Map;
import java.util.Map.Entry;

//import org.apache.commons.lang3.StringUtils;

//import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

public class IncidentManagement extends baseAPI{
	

	@Given("enable logs")
	public void setUp(){ 
		request = given().log().all();
	}
	
	@When("short description is added with (.*)$")
	public void add_short_description(String short_desc){
		request = request.when().body("{\"short_description\" : \""+short_desc+"\"}");
	}
	
	@When("description is added with (.*)$")
	public void add_description(String desc){
		request = request.when().body("{\"description\" : \""+desc+"\"}");
	}

	@When("new incident is created")
	public void a_new_incident_created(){
		response = request.when().contentType(ContentType.JSON).post("incident");
		response.prettyPrint();
	}
	
	@When("get all incidents")
	public void get_all_incidents(){
		response = request.when().contentType(ContentType.JSON).get("incident");
		response.prettyPrint();
	}

	@Then("the status code is (\\d+)$")// \d+ ->only Digit += 1 or more number
	public void verify_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}
	
	@And("response includes the following")
	public void response_equals(Map<String,String> responseFields){
		
		for (Entry<String, String> eachEntry : responseFields.entrySet()) {
			
				response
				.then()
				.body(eachEntry.getKey(), equalTo(eachEntry.getValue()));
			
		}
	}	

}