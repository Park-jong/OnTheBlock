package com.ontheblock.www.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ontheblock.www.genre.domian.MusicGenre;

@Repository
public interface MusicGenreRepository extends JpaRepository<MusicGenre, Integer> {


}
