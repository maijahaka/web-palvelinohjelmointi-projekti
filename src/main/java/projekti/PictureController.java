
package projekti;

import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {
    
    @Autowired
    private PictureRepository pictureRepository;
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping(path = "/pictures/{userId}/content", produces = "image/jpeg")
    @ResponseBody
    public byte[] get(@PathVariable Long userId) {
        Long pictureId = accountService.getOne(userId).getPicture().getId();
        return pictureRepository.getOne(pictureId).getContent();
    }

    @Transactional
    @PostMapping("/pictures")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        Account user = accountService.getActiveUser();       
        
        if (file.getContentType().equals("image/jpeg")) {
        
            Picture picture = new Picture();
            picture.setContent(file.getBytes());
            
            picture = pictureRepository.save(picture);
            
            user.setPicture(picture);
            accountService.save(user);
        }
        
        return "redirect:/users/" + user.getUrlIdentifier();
    }
    
    @Transactional
    @PostMapping("/pictures/change")
    public String change(@RequestParam("changefile") MultipartFile file) throws IOException  {
        Account user = accountService.getActiveUser();
        Long oldPictureId = user.getPicture().getId();
        
        if (file.getContentType().equals("image/jpeg")) {
        
            Picture picture = new Picture();
            picture.setContent(file.getBytes());
            
            picture = pictureRepository.save(picture);
            
            user.setPicture(picture);
            accountService.save(user);
            pictureRepository.deleteById(oldPictureId);
        }
        
        return "redirect:/users/" + user.getUrlIdentifier();
    }
    
    @PostMapping("/pictures/delete")
    public String delete() {
        Account user = accountService.getActiveUser();
        Long pictureId = user.getPicture().getId();
        
        user.setPicture(null);
        
        pictureRepository.deleteById(pictureId);
        return "redirect:/users/" + user.getUrlIdentifier();
    }
    
}
