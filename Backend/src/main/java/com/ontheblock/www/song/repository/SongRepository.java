package com.ontheblock.www.song.repository;

import java.util.List;

import com.ontheblock.www.song.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
	List<Song> findByName(String name);

}
