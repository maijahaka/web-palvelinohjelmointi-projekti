
package projekti;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessController {
    
    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("title", "Käyttäjätunnus luotu");
        return "success";
    }
    
}
