package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    @RequestMapping("/list")
    public List<Anime> list(){
        return  List.of(new Anime("One Piece"), new Anime("Steins Gate"));
    }
}
