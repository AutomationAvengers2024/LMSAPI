package Utilities;

	
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.util.Properties;
	
	public class ConfigReader {

	    private static Properties properties = new Properties();

	    static {
	        try {
	            
	            InputStream inputStream = new FileInputStream("src/test/resources/configurationfile/config.properties");
	            properties.load(inputStream);
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to load config.properties file");
	        }
	    }

	    
	    public static String getProperty(String key) {
	        return properties.getProperty(key);
	    }

	    
	    public static String getApiUrl() {
	        return getProperty("userapi.url");
	    }

	    
	    public static String getApiUsername() {
	        return getProperty("userapi.username");
	    }

	   
	    public static String getApiPassword() {
	    	return getProperty("userapi.password");

	    }

	    	    public static String getCreateUserEndpoint() 
	    	    {
	        return getProperty("userapi.endpoint.createuser");
	    }

	    
	    public static String getGetAllUsersEndpoint() {
	        return getProperty("userapi.endpoint.getallusers");
	    }

	    public static String getExcelPath() {
	        return getProperty("userapi.excelpath");
	    }
	    
	    public static String getinvalidUrl() {
	        return getProperty("userapi.invalidurl");
	    }
	    
	    public static String getinvalidpostendpoint() {
	        return getProperty("userapi.invalidendpoint.createuser");
	    }
	    
	    public static String getuserbyfirstname() {
	        return getProperty("userapi.endpoint.getuserbyFirstName");
	        		
	    }
	    
	    public static String getuserbyid() {
	        return getProperty("userapi.endpoint.getuserbyid");
	        		
	    }
	    
	    public static String getinvalidgetendpoint() {
	        return getProperty(" userapi.invalidendpoint.getuser");
	        		
	    }
	    public static String getputendpoint() {
	        return getProperty("userapi.endpoint.updateuser");
	        		
	    }
	    
	    public static String getputinvalidendpoint() {
	        return getProperty("userapi.invalidendpoint.updateuser");
	        		
	    }
	    
	    public static String getdeleteuseridendpoint() {
	        return getProperty("userapi.endpoint.deleteuser");
	        		
	    }   
	}
