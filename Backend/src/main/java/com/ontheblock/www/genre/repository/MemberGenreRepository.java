package com.ontheblock.www.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ontheblock.www.genre.entity.MusicGenre;

@Repository
public interface MemberGenreRepository extends JpaRepository<MusicGenre, Integer> {


}
