
package projekti;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {
    
    @GetMapping("/timeline")
    public String showTimeline() {
        return "timeline";
    }
    
}
