package academy.devdojo.spring.service;

import academy.devdojo.spring.domain.Anime;
import academy.devdojo.spring.dtos.AnimeRequestDTO;
import academy.devdojo.spring.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return  animeRepository.findAll();
    }

    public Anime findById(Long id){
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(AnimeRequestDTO animeRequestDTO){
        // Um novo objeto anime é criado
        Anime anime = new Anime();
        // As propriedades de animeRequestDTO sao copiadas para o objeto anime
        BeanUtils.copyProperties(animeRequestDTO, anime);
        // O objeto anime é salvao no banco de dados
        return this.animeRepository.save(anime);
    }

    public void delete(long id) {
        Anime anime = this.animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
        this.animeRepository.delete(anime);
    }

    public Anime replace(Long animeId, AnimeRequestDTO animeRequestDTO) {
        Anime anime = this.animeRepository.findById(animeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
        BeanUtils.copyProperties(animeRequestDTO, anime);
        return this.animeRepository.save(anime);
    }
}
