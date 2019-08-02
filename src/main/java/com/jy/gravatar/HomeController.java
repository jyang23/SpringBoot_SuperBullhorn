package com.jy.gravatar;

import com.jy.gravatar.Beans.User;
import com.jy.gravatar.Configurations.UserService;
import com.jy.gravatar.Repository.PostRepository;
import com.jy.gravatar.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("posts",postRepository.findAll());


        if(userService.getUser() != null)
        {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "index";
    }
    //-Issue------------------------------------------------------------------------------------------------------------
    @GetMapping("/add")
    public String issueForm(Model model)
    {
        model.addAttribute("user",new User());
        return "issueform";
    }

    @PostMapping("/process")
    public String processForm(@Valid User user, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "issueform";
        }
        String hash = md5Hex(user.getEmail());
        hash = "https://www.gravatar.com/avatar/"+hash;
        user.setGravatar(hash);
        userRepository.save(user);
        return "redirect:/";
    }
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/post/{value}")
    public String processPost(@PathVariable("value") long id, Model model){
        model.addAttribute("post", postRepository.findById(id).get());
        return "post";
    }
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/user/{value}")
    public String processUser(@PathVariable("value") long id, Model model){
        model.addAttribute("user", userRepository.findById(id));
        return "user";
    }
    //------------------------------------------------------------------------------------------------------------------
    /*
     * The following class will provide you with a static method
     * that returns the hex format md5 of an input string
     * See: http://en.gravatar.com/site/implement/images/java/
     * Call this as shown below:
     * String email = "someone@somewhere.com";
     * String hash = MD5Util.md5Hex(email);
     */

    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
    public static String md5Hex (String message) {
        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    //------------------------------------------------------------------------------------------------------------------
}
