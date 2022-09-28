package com.example.springbootsecurity2022;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceRestController {
    @GetMapping("/navigation/destination")
    public String currentDestination(){
        return "Mars";
    }


    @GetMapping("/cantina/menu/today")
    public String menu(){
        return "Microwaved Pizza";
    }
}
