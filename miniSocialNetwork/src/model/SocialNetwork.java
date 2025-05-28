
package src.model;

import java.util.*;

public class SocialNetwork {
  private Map<String, User> users;

  public SocialNetwork(){
    this.users = new HashMap<>();
  }
  public User getUser(String username){
    return users.get(username);
  }

  public boolean registerUser(String username){
    if(users.containsKey(username)){
      return false;
    }else{
      users.put(username, new User(username));
      return true;
    }
  }

  public boolean userExists(String username){
    return (users.containsKey(username));
  }

  public Set<String> getAllUsernames(){
    return users.keySet();
  }

}
