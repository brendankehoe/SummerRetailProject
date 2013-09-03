package sprint2_Day7;
/** 
* User Class for Retail Management System
* 
* @date 26/08/2013
* @version 1.0
* @param int userId 
* @param String userName
* @param String password
* @param boolean isAdmin
* @param boolean isActive
* @return 
*/ 

public class User { 
    
    //Declare private variables used to model the user class 
      
    private int userId = 0; 
    private String userName; 
    private String password; 
    private boolean isAdmin; 
    private boolean isActive; 
      
    //Write the constructor class
    
    public User(){
    	
    }
      
    public User(int userId,String userName,String password,boolean isAdmin,boolean isActive){ 
        this.userId = userId;  
        this.userName = userName; 
        this.password = password; 
        this.isAdmin = isAdmin; 
        this.isActive = isActive; 
          
          
    } 
  
    public int getUserId() { 
        return userId; 
    } 
  
    public void setUserId(int userId) { 
        this.userId = userId; 
    } 
  
    public String getUserName() { 
        return userName; 
    } 
  
    public void setUserName(String userName) { 
        this.userName = userName; 
    } 
  
    public String getPassword() { 
        return password; 
    } 
  
    public void setPassword(String password) { 
        this.password = password; 
    } 
  
      
  
    public boolean isAdmin() { 
        return isAdmin; 
    } 
  
    public void setAdmin(boolean isAdmin) { 
        this.isAdmin = isAdmin; 
    } 
  
    public boolean isActive() { 
        return isActive; 
    } 
  
    public void setActive(boolean isActive) { 
        this.isActive = isActive; 
    } 
  
} 