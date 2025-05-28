package src.model;
import java.util.*;
public class User {
  private String username;
  Set<User> followers;
  Set<User> following;
  List<Post> posts;

  public User(String username){
    this.username = username;
    this.followers = new HashSet<>();
    this.following = new HashSet<>();
    this.posts = new ArrayList<>();
  }

  public String getUsername(){
    return username;
  }

  public void follow(User other){
    if(other == this){
      return;
    }
    following.add(other);
    other.followers.add(this);
  }
  public void unfollow(User other){
    if(other==this){
      return;
    }
    following.remove(other);
    followers.remove(this);
  }
  public void addPost(String content){
    posts.add(new Post(content));
  }

  public List<Post> getOwnPosts(){
    return posts;
  }

  public List<Post> getFeed(){
    List<Post> feedList = new ArrayList<>();
    for(User users : following){
      List<Post> tempPost = users.getOwnPosts();
      for(Post eachPost: tempPost){
        feedList.add(eachPost);
      }
    }
    feedList.sort((a,b) -> b.getTimestamp().compareTo(a.getTimestamp()));
    return feedList;
  }

  public Set<User> getFollowers(){
    return followers;
  }

  public Set<User> getFollowings(){
    return following;
  }
}
