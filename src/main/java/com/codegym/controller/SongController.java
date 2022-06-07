package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller

public class SongController {

    @Autowired
    public ISongService songService;

    @GetMapping("/song")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("/song/index");
        modelAndView.addObject("song", new Song());
        return modelAndView;
    }

    @PostMapping("/validate")
    public String checkValidation(@Valid @ModelAttribute("song") Song song, BindingResult bindingResult, Model model) {
        new Song().validate(song, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "/song/index";
        } else {
            songService.save(song);
            Iterable<Song> songs = songService.findAll();
            model.addAttribute("songs", songs);
            model.addAttribute("song", song);
            return "/song/result";
        }

    }
}
