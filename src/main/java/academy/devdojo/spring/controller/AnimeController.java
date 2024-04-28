package academy.devdojo.spring.controller;

import academy.devdojo.spring.domain.Anime;
import academy.devdojo.spring.dtos.AnimeRequestDTO;
import academy.devdojo.spring.service.AnimeService;
import academy.devdojo.spring.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;
    @GetMapping
    public ResponseEntity<List<Anime>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK);
        //return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> animeById(@PathVariable("id") Long id){
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimeRequestDTO animeRequestDTO){
        Anime anime = this.animeService.save(animeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(anime);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        this.animeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Anime deleted successfully");
    }

    @PutMapping("/{idAnime}")
    public ResponseEntity<Anime> replace(@PathVariable long idAnime, @RequestBody AnimeRequestDTO animeRequestDTO){
        Anime anime = this.animeService.replace(idAnime, animeRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(anime);
    }
}
