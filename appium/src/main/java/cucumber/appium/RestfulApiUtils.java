package cucumber.appium;

import java.util.HashMap;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestfulApiUtils {
	
	/**
	 * @param baseURI : domain
	 * @param subUri : params 
	 * 
	 * @return JsonPath
	 * @author tjdudd2
	 * @date 2017. 9. 25. 오전 10:58:10
	 */
	public static JsonPath apiWithParams(Method method, String baseURI, HashMap<String, String> subUri) throws Exception {
		
		return apiWithHeadersAndParams(null, method, baseURI, subUri);
	}
	
	/**
	 * @param baseURI : domain
	 * @param subUri : params 
	 * 
	 * @return JsonPath
	 * @author tjdudd2
	 * @date 2017. 9. 25. 오전 10:58:10
	 */
	public static JsonPath apiWithHeadersAndParams(Headers headers, Method method, String baseURI, HashMap<String, String> subUri) throws Exception {
		
		RestAssured.baseURI = baseURI;
		RequestSpecification httpRequest = RestAssured.given();
		
		if (headers != null)
			httpRequest.headers(headers);

		httpRequest.contentType("application/x-www-form-urlencoded");
		
		String value;
		for(String key : subUri.keySet()){
            System.out.println( String.format("키 : %s, 값 : %s", key, subUri.get(key)));
//
//            value = subUri.get(key).replaceAll("\"", URLEncoder.encode("\"", "UTF-8"));
//            value = subUri.get(key).replaceAll("\\{", URLEncoder.encode("\\{", "UTF-8"));
//            value = subUri.get(key).replaceAll("\\}", URLEncoder.encode("\\}", "UTF-8"));
//            value = subUri.get(key).replaceAll("\\[", URLEncoder.encode("\\[", "UTF-8"));
//            value = subUri.get(key).replaceAll("\\]", URLEncoder.encode("\\]", "UTF-8"));
//            value = subUri.get(key).replaceAll("\\:", URLEncoder.encode("\\:", "UTF-8"));
//            value = subUri.get(key).replaceAll("\\s", URLEncoder.encode("\\s", "UTF-8"));
    		
//			httpRequest.param(key, value);
			httpRequest.param(key, subUri.get(key));
        }

		System.out.println("subUri is => " + subUri);
		
		Response response = httpRequest.request(method);
//		Response response = httpRequest.request(method, subUri);
		
		
		int statusCode = response.getStatusCode();
		if(statusCode != 200) {
			throw new Exception();
		}
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		JsonPath jsonPath = new JsonPath(responseBody).using(new JsonPathConfig("UTF-8"));
		
		return jsonPath;
		
		
		//return new JsonPath("");
	}
	
	/**
	 * contentType("application/json")
	 * @param baseURI : domain
	 * @param jsonInput : jsonInput
	 * 
	 * @return JsonPath
	 * @author tjdudd2
	 * @date 2017. 9. 25. 오전 10:59:59
	 */
	public static JsonPath apiWithBody(Method method, String baseURI, JSONObject jsonInput) throws Exception {
		
		return apiWithHeadersAndBody(null, method, baseURI, jsonInput);
	}
	
	/**
	 * contentType("application/json")
	 * @param baseURI : domain
	 * @param jsonInput : jsonInput
	 * 
	 * @return JsonPath
	 * @author tjdudd2
	 * @date 2017. 9. 25. 오전 10:59:59
	 */
	public static JsonPath apiWithHeadersAndBody(Headers headers, Method method, String baseURI, JSONObject jsonInput) throws Exception {
		
		RestAssured.baseURI = baseURI;
		RequestSpecification httpRequest = RestAssured.given();
		
		if (headers != null)
			httpRequest.headers(headers);
		
		httpRequest.contentType("application/json").body(jsonInput.toString());
	    httpRequest.log().body();
		
		Response response = httpRequest.request(method);
		
		int statusCode = response.getStatusCode();
		
		if(statusCode != 200) {
			throw new Exception();
		}
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		JsonPath jsonPath = new JsonPath(responseBody).using(new JsonPathConfig("UTF-8"));
		
		return jsonPath;
	}
}