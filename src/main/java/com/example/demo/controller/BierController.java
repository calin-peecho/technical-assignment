package com.example.demo.controller;

import com.example.demo.entity.Bier;
import com.example.demo.entity.User;
import com.example.demo.repository.BierRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.BierServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;


@Controller
@Transactional
public class BierController {

    private final BierServiceImpl bierService;
    private final BierRepository bierRepository;
    private final UserRepository userRepository;

    public BierController(BierServiceImpl bierService, BierRepository bierRepository, UserRepository userRepository) {
        this.bierService = bierService;
        this.bierRepository = bierRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/random")
    public String getRandom(Model model) {
        Bier randomBier = bierService.getRandom();
        model.addAttribute("randomBier", randomBier);
        return "/index";
    }

    @GetMapping(value = "/search")
    public String findBier(Model model) {
        return "/search";
    }

    @PostMapping(value = "/remove_item")
    public String remove(@RequestParam Long id, Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        if (bierRepository.findByIdAndUser_Id(id, user.getId()).isPresent()) {
            bierRepository.deleteByIdAndUser_Id(id, user.getId());
        }
        return "redirect:/saved";
    }

    @GetMapping(value = "/saved")
    public String showSaved(Model model, Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        Optional<Bier[]> allByUserId = bierRepository.findAllByUser_Id(user.getId());
        if (allByUserId.isPresent()) {
            model.addAttribute("bierList", allByUserId.get());
        }
        return "saved";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/search")
    public String findBier(@RequestParam(name = "beerName") String beerName, Model model) {
        model.addAttribute("biers", bierService.findBier(beerName));
        return "search";
    }

    @RequestMapping(value = "/api/getSearchResult")
    @ResponseBody
    public long getSearchResultViaAjax(Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        return bierRepository.countByUser_Id(user.getId());
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public void save(@ModelAttribute Bier bier, Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        if (!bierRepository.findByIdAndUser_Id(bier.getId(), user.getId()).isPresent()) {
            bier.setUser(userRepository.getByUsername(principal.getName()));
            bierRepository.save(bier);
        }
    }
}
