package com.example.nemo.controller;

import com.example.nemo.model.User;
import com.example.nemo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    // 1. Hubungkan ke UserRepository (Hapus ArrayList yang lama)
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String prosesLogin(@RequestParam String username, @RequestParam String password) {
        // Login menggunakan NIM kamu sebagai password
        if ("admin".equals(username) && "20230140057".equals(password)) {
            return "redirect:/home";
        }
        return "redirect:/";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        // 2. Ambil semua data dari DATABASE, bukan dari list
        List<User> mahasiswaDariDb = userRepository.findAll();
        model.addAttribute("mahasiswa", mahasiswaDariDb);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/save")
    public String simpanData(@ModelAttribute User user) {
        // 3. Simpan objek user langsung ke DATABASE
        userRepository.save(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}