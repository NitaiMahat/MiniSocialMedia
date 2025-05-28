package src.model;

import java.time.LocalDateTime;

public class Post {
  private String content;
  private LocalDateTime timestamp;

  public Post(String content){
    this.content = content;
  this.timestamp = LocalDateTime.now();
  }

  public String getContent(){
    return content;
  }

  public LocalDateTime getTimestamp(){
    return timestamp;
  }
  public String toString(){
      return  "[" + timestamp + "] " + content;
    }
  
}
