/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0710986
 */
public class Posts {
    private List<Post> posts;
    private Post currentPost;

    public Post getCurrentPost() {
        return currentPost;
    }
    
    public Posts () {
        getPostsFromDatabase();
    }

    public List<Post> getPosts() {
        return posts;
    }
    
    public String viewPost(Post p) {
        currentPost = p;
        return "viewerPost";
    }
    
    private void getPostsFromDatabase() {
    
        Connection conn = DBUtils.getConnection();
        Statement stmt;
        try {
            stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
        while (rs.next()) {
            Post p = new Post(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("title"),
                rs.getTimestamp("created_at"),
                rs.getString("contents")
            );
            posts.add(p);
        }
        } catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
}