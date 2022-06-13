package company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Page {

    Logger logger = LoggerFactory.getLogger(Page.class);

    @CrossOrigin(origins = "*")
    @GetMapping("a")
    public Object a() {
        logger.error("Errooooooooooor");
        return "uwu";
    }
}