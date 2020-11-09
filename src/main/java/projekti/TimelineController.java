
package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping("/timeline")
    public String showTimeline(Model model) {
        Account user = accountService.getActiveUser();
        model.addAttribute("activeUrlIdentifier", user.getUrlIdentifier());
        return "timeline";
    }
    
}
