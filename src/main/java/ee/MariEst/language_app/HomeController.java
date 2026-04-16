package ee.MariEst.language_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/learn")
    public String learn() {
        return "index";
    }

    @GetMapping("/learn/{lessonId}")
    public String lessonFlow(@PathVariable Long lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        return "lesson-flow";
    }
}