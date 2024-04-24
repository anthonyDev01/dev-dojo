package academy.devdojo.spring.repository;

import academy.devdojo.spring.domain.Anime;

import java.util.List;

public interface AnimeRepository {
    List<Anime> listAll();
}
