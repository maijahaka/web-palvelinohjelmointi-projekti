
package projekti;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping("/registration")
    public String registrationForm(Model model, @ModelAttribute Account account) {
        String url = env.getProperty("app.url");
        model.addAttribute("url", url);
        return "registration";
    }
    
    @PostMapping("/registration")
    public String createUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String urlIdentifier,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult,
            Model model) {
        
        account.setPassword(passwordEncoder.encode(password));
        
        if (accountService.isUsernameAlreadyInUse(username)) {
            bindingResult.rejectValue("username", "error.username.reserved", 
                    "Käyttäjänimi on varattu. Valitse toinen käyttäjänimi.");
        }
        
        if (accountService.isUrlIdentifierAlreadyInUse(urlIdentifier)) {
            bindingResult.rejectValue("urlIdentifier", "error.urlIdentifier.reserved", 
                    "Tunnus on varattu. Valitse toinen tunnus.");
        }
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Luo käyttäjätunnus");            
            String url = env.getProperty("app.url");
            model.addAttribute("url", url);
            return "registration";
        }

        accountService.save(account);
        
        return "redirect:/success";
    }
    
}
