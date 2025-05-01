package com.i2i.lyrics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class LyricsController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/lyrics")
    public String getLyrics(@RequestParam String artist,
                            @RequestParam String title,
                            Model model) {
        String apiUrl = "https://api.lyrics.ovh/v1/" + artist + "/" + title;
        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<String, String> response = restTemplate.getForObject(apiUrl, Map.class);
            model.addAttribute("lyrics", response.get("lyrics"));
        } catch (Exception e) {
            model.addAttribute("lyrics", "Lyrics not found or error occurred.");
        }

        model.addAttribute("artist", artist);
        model.addAttribute("title", title);
        return "index";
    }
}
