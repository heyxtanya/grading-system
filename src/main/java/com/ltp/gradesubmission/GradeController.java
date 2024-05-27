package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {
    List<Grade> studentGrades = new ArrayList<>();
    @GetMapping("/")
    public String gradeForm(Model model, @RequestParam(required = false) String id) {
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : studentGrades.get(index));
        return "form";
    }
    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "grades";
    }
    @PostMapping("/handlesubmit")
    public String submitGrades(Grade grade) {
        if(getGradeIndex(grade.getId()) == Constants.NOT_FOUND) {
            studentGrades.add(grade);
        } else {
            studentGrades.set(getGradeIndex(grade.getId()), grade);
        }
        return "redirect:/grades";

    }
    public Integer getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getName().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }


}
