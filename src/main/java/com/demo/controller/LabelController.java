package com.demo.controller;

import com.demo.entity.Label;
import com.demo.repository.LabelRepository;
import com.demo.constants.pubConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/labels")
public class LabelController {

    private final LabelRepository labelRepository;

    public LabelController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

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
            String newStatus = pubConstants.STATUS_ACTIVE.equals(label.getLabelStatus()) 
                    ? pubConstants.STATUS_INACTIVE 
                    : pubConstants.STATUS_ACTIVE;
            label.setLabelStatus(newStatus);
            labelRepository.save(label);
        }
        return "redirect:/labels";
    }
}
