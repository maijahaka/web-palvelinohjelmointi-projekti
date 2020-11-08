
package projekti;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {
    
    @GetMapping("/timeline")
    public String showTimeline(Model model) {
        model.addAttribute("title", "Aikajana");
        return "timeline";
    }
    
}
