
package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping("/users/{urlIdentifier}")
    public String showProfile(Model model, @PathVariable String urlIdentifier) {
        Account user = accountService.getActiveUser();
        model.addAttribute("activeUrlIdentifier", user.getUrlIdentifier());
        
        Account profile = accountService.findByUrlIdentifier(urlIdentifier);
        model.addAttribute("profile", profile);
        return "profile";
    }
    
//    @GetMapping("/users/profile")
//    public String showProfile(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//        Account user = accountService.findByUsername(username);
//        
//        model.addAttribute("user", user);
//        return "profile";
//    }
    
}
