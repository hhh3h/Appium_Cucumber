package cucumber.appium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * ObjectMap, 환경변수 파일을 가져와 메모리에 로드하고, 그 값을 쓸수있게 하는 유틸 클래스 
 * @author hhh3h
 */
public class Property {

	/**
	 * ObjectMapping
	 */
	private static Map<String, String> objectMap = new HashMap<String, String>();
	
	/**
	 * 환경 변수
	 */
	private static Map<String, String> valueMap = new HashMap<String, String>();
	
	private static List<String> multipleEnvPropertyKeyList = new ArrayList<String>();
	
	/**
	 * 맨 처음 공통 환경변수를 등록한다.
	 * 
	 * @return void
	 * @author hhh3h
	 * @throws Exception
	 */
	private static void setGlobalEnvProperties() throws Exception {

		//////////////////////////////////////////////
		/// 사용자 지정 환경변수 파일 설정
		//////////////////////////////////////////////
		ClassLoader classLoader = Property.class.getClassLoader();
		File valueMapFile = new File(URLDecoder.decode(classLoader.getResource("환경변수/글로벌변수/글로벌.txt").getFile(), "UTF-8"));
		setMap(valueMap, valueMapFile.getAbsolutePath(), "=");

	}

	/**
	 * 맨 처음 글로벌 오브젝트 정보를 등록한다.
	 * 
	 * @return void
	 * @author hhh3h
	 */
	private static void setGlobalObjectProperties() throws Exception {

		////////////////////////////////////////
		/// 글로벌 오브젝트 파일 설정
		////////////////////////////////////////
		ClassLoader classLoader = Property.class.getClassLoader();
		File objectMapFile = new File(URLDecoder.decode(classLoader.getResource("objectMaps/GlobalMap/글로벌.txt").getFile(), "UTF-8"));

		setMap(objectMap, objectMapFile.getAbsolutePath(), "|");
	}

	/**
	 * objectMap에 objectMap.txt의 내용 맵핑
	 * @return void
	 * @author hhh3h
	 * @throws Exception 
	 */
	private static void setMap(Map map, String objectTextFilePath, String seperator) throws Exception {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(objectTextFilePath));

			int line = 0;
			String str = "";
			while ((str = br.readLine()) != null) {
				line++;
				if (str.equals("") || str.startsWith("*")) {
					continue;
				}
				String[] cutStr = new String[2];
				cutStr[0] = str.substring(0, str.indexOf(seperator));
				cutStr[1] = str.substring(str.indexOf(seperator)+1);
				cutStr[1] = cutStr[1].replaceAll("\\\\n", "\n");
				
				if (containsEnvPropertySign(cutStr[1]))
					multipleEnvPropertyKeyList.add(cutStr[0]);
				
				map.put(cutStr[0].replaceAll(" ", ""), cutStr[1]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("** 파일 불러오기를 실패하였습니다.");
		} catch (IOException e) {
			System.out.println("** 문자열 읽기를 실패하였습니다.");
		}

	}
	
	private static boolean containsEnvPropertySign(String value) throws Exception {
		
		return value.indexOf("${") != -1;
	}
	
	private static void setEnvProperties(String... useLocalEnvFileNames) throws Exception {
		
		setGlobalEnvProperties();
		
		for (String key : multipleEnvPropertyKeyList) {
			
			String value = convertEnvPropertyValuesInValue(getEnvironmentPropertyValue(key));
			setEnvironmentPropertyValue(key, value);
		}
	}
	
	private static void setObjectProperties(String... useLocalObjectFileNames) throws Exception {
		
		setGlobalObjectProperties();
	}
	
	public static void setProperties(String... useLocalFileNames) throws Exception {
		
		setObjectProperties(useLocalFileNames);
		setEnvProperties(useLocalFileNames);
		
		for( Map.Entry<String, String> elem : objectMap.entrySet() )
			System.out.println( String.format("ObjectMap ==> 키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
		
		for( Map.Entry<String, String> elem : valueMap.entrySet() )
			System.out.println( String.format("ValueMap ==> 키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
	}
	
	public static String convertEnvPropertyValuesInValue(String value) throws Exception {
			
		// ex) envPropertyKey1 = ${envPropertyKey2}
		//      envPropertyKey2 = envPropertyValue2
		if (value.indexOf("${") == -1)
			return value;
		
		String temp = new String(value);
	    
		int startIndex;
		while ((startIndex = temp.indexOf("${")) != -1) {
			
			int endIndex = temp.indexOf("}");
			String envValue = getEnvironmentPropertyValue(temp.substring(startIndex+2, endIndex));
			value = value.replace(temp.substring(startIndex, endIndex+1), envValue);
			
			temp = temp.substring(endIndex + 1);
		}
		return convertEnvPropertyValuesInValue(value);
	}
	/**
	 * Object Property를 얻는다.
	 * 
	 * @return String
	 * @author hhh3h
	 */
	public static String getObjectPropertyValue(String key) throws NullPointerException {
		key = key.replaceAll(" ", "");
		if (!objectMap.containsKey(key))
			throw new NullPointerException("설정한 프로퍼티가 없습니다.");

		return objectMap.get(key);
	}

	/**
	 * 환경변수 Property를 설정
	 * 
	 * @return String
	 * @author hhh3h
	 */
	public static String setEnvironmentPropertyValue(String key, String value) throws NullPointerException {
		key = key.replaceAll(" ", "");
		
		return valueMap.put(key, value);
	}

	/**
	 * 환경변수 Property를 얻음
	 * 
	 * @return String
	 * @author hhh3h
	 */
	public static String getEnvironmentPropertyValue(String key) throws NullPointerException {

		key = key.replaceAll(" ", "");
		if (!valueMap.containsKey(key))
			throw new NullPointerException("프로퍼티 : " + key + "에 대해 설정한 프로퍼티가 없습니다.");

		return valueMap.get(key);
	}

	
	/**
	 * Property 매핑
	 * @return void
	 * @author hhh3h
	 */
	public static void initProperty(String serviceName) throws Exception {
		setGlobalEnvProperties();
		setGlobalObjectProperties();
	}

	/**
	 * 환경변수이면 환경변수에 매핑된 값을 찾아주고
	 * 환경변수가 아니면 그대로 리턴한다.
	 * 
	 * @return String
	 * @author hhh3h
	 * @throws Exception 
	 */
	public static String convertVariableToValue(String param) throws Exception {
		System.out.println("변환할 값 : " + param);
		
		param = convertEnvPropertyValuesInValue(param);
		param = param.replaceAll("\\\\n", "\n");
		
		if (param.length() >= 1 && param.contains("$")) {
			int count = StringUtils.countOccurrencesOf(param, "$");
			
			if (count == 1 && param.charAt(0) == '$')
				return getEnvironmentPropertyValue(param.replaceFirst("[$]", ""));
			else {
				String result = "";
				String[] splited = param.split("\\$");
				
				for(int i = 0; i < splited.length; i++) {
					System.out.println("splited : " + splited[i]);
					
					if(i % 2 == 1) {
						System.out.println("bf splited : " + splited[i]);
						splited[i] = getEnvironmentPropertyValue(splited[i]);
						System.out.println("af splited : " + splited[i]);
					}
					
					result += splited[i];
				}
				
				System.out.println("success result : " + result);
				
				return result;
			}
		}
		
		if (param.length() >= 1 && param.charAt(0) == '$')
			return getEnvironmentPropertyValue(param.replaceFirst("[$]", ""));
		else if (objectMap.containsKey(param.replaceAll(" ", "")))
			return getObjectPropertyValue(param);
		else {
			System.out.println("변환되지 않은 param : " + param);
			return param;
		}
	}

	/**
	 * 메모리에 로드된 Property들을 초기화한다.
	 * 
	 * @return void
	 * @author hhh3h
	 */
	public static void clearProperties() {
		
		objectMap.clear();
		valueMap.clear();
		multipleEnvPropertyKeyList.clear();
	}

	
	public static void printValueMap() {
		for (String key : valueMap.keySet()) {
			System.out.println("ValueMap | key: " + key + ", value: " + valueMap.get(key));
		}
	}
}
