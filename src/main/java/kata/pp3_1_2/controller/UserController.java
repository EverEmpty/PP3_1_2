package kata.pp3_1_2.controller;

import kata.pp3_1_2.model.User;
import kata.pp3_1_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.userList());
        return "users";
    }

    @GetMapping(value = "/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("newUser", new User());
        return "user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user) {
        if (user.getID() == 0) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/updateUser")
    public String updateEmployee(@RequestParam("ID") long ID, Model model) {
        model.addAttribute("newUser", userService.getUserById(ID));
        return "user-info";
    }

    @PatchMapping(value = "/updateUser")
    public String updateUser(@RequestParam("ID") long ID, @RequestBody User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("ID") long ID) {
        userService.deleteUser(ID);
        return "redirect:/";
    }
}
