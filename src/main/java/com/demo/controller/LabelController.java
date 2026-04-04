package com.demo.controller;

import com.demo.entity.Label;
import com.demo.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/labels")
public class LabelController {

    @Autowired
    private LabelRepository labelRepository;

    @GetMapping
    public String listLabels(Model model) {
        model.addAttribute("labels", labelRepository.findAll());
        return "labels";
    }

    @PostMapping("/add")
    public String addLabel(@RequestParam String labelName) {
        Label label = new Label(labelName);
        labelRepository.save(label);
        return "redirect:/labels";
    }

    @PostMapping("/delete/{id}")
    public String deleteLabel(@PathVariable Integer id) {
        labelRepository.deleteById(id);
        return "redirect:/labels";
    }

    @PostMapping("/toggle/{id}")
    public String toggleStatus(@PathVariable Integer id) {
        Label label = labelRepository.findById(id).orElse(null);
        if (label != null) {
            label.setLabelStatus("1".equals(label.getLabelStatus()) ? "0" : "1");
            labelRepository.save(label);
        }
        return "redirect:/labels";
    }
}
