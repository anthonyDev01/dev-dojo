package academy.devdojo.spring.service;

import academy.devdojo.spring.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {
    //private final AnimeRepository animeRepository;

    private List<Anime> animes = List.of(new Anime(1L,"Black Clover"), new Anime(2L, "Steins Gate"), new Anime(3L, "One Piece "));
    public List<Anime> listAll(){
        return  animes;
    }

    public Anime findById(Long id){
        return  animes.stream().filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }
}
