package com.ontheblock.www.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ontheblock.www.notice.entity.MemberNotice;

@Repository
public interface MemberNoticeRepository extends JpaRepository<MemberNotice, Integer> {


}
