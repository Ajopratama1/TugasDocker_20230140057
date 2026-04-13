package com.example.nemo.controller;

import com.example.nemo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private List<User> daftarMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String prosesLogin(@RequestParam String username, @RequestParam String password) {

        if ("admin".equals(username) && "20230140057".equals(password)) {
            return "redirect:/home";
        }
        return "redirect:/";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("mahasiswa", daftarMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/save")
    public String simpanData(@ModelAttribute User user) {
        daftarMahasiswa.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
