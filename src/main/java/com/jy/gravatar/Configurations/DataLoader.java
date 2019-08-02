package com.jy.gravatar.Configurations;

import com.jy.gravatar.Beans.Post;
import com.jy.gravatar.Beans.Role;
import com.jy.gravatar.Beans.User;
import com.jy.gravatar.Repository.PostRepository;
import com.jy.gravatar.Repository.RoleRepository;
import com.jy.gravatar.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    //This is moved to the user class to keep it consistent
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Override
    public void run(String...strings) throws Exception{
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        //Constructors
        //==============================================================================================================
        User user = new User("user@name.com", "password", "User", "Name", true, "user","https://s.gravatar.com/avatar/");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        Post post = new Post();
        post.setTitle("Title");
        post.setPostpicture("Picture");
        post.setPosteddate("Date");
        post.setPostmessage("Message");
        post.setUser(user);
        postRepository.save(post);

        user.setPostid(post.getId());
        userRepository.save(user);
        //==============================================================================================================
        user = new User("system@admin.com", "password", "System", "Admin", true, "admin","https://s.gravatar.com/avatar/");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        post = new Post();
        post.setTitle("Title2");
        post.setPostpicture("Picture2");
        post.setPosteddate("Date2");
        post.setPostmessage("Message2");
        post.setUser(user);
        postRepository.save(post);

        user.setPostid(post.getId());
        userRepository.save(user);
        //==============================================================================================================
        user = new User("ymu@z.zgrco.com", "password", "Justin", "Jang", true, "justin","https://s.gravatar.com/avatar/5623241a8751fef7598d621664854feb?s=80");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        post = new Post();
        post.setTitle("Title3");
        post.setPostpicture("Picture3");
        post.setPosteddate("Date3");
        post.setPostmessage("Message3");
        post.setUser(user);
        postRepository.save(post);

        user.setPostid(post.getId());
        userRepository.save(user);

        //==============================================================================================================
        user = new User("lpr@s.rv55.com", "password", "Aaron", "Yan", true, "aaron","https://s.gravatar.com/avatar/b1b191f1dad35861c0a90f4e188db81d?s=80");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        post = new Post();
        post.setTitle("Title4");
        post.setPostpicture("Picture4");
        post.setPosteddate("Date4");
        post.setPostmessage("Message4");
        post.setUser(user);
        postRepository.save(post);

        user.setPostid(post.getId());
        userRepository.save(user);
        //==============================================================================================================
    }
}
