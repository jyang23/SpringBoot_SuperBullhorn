package com.jy.gravatar.Repository;

import com.jy.gravatar.Beans.Post;
import com.jy.gravatar.Beans.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PostRepository extends CrudRepository<Post, Long> {
        ArrayList<User> findByUser(User u);

}
