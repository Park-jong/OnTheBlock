package com.ontheblock.www.session.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionResponse {
    Long memberId;

    Long instrumentId;

    LocalDateTime createdTime;

    Integer volume;

    Integer startPoint;

}
