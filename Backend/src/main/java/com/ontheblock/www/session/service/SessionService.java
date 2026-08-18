package com.ontheblock.www.session.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ontheblock.www.instrument.domain.Instrument;
import com.ontheblock.www.instrument.repository.InstrumentRepository;
import com.ontheblock.www.member.Member;
import com.ontheblock.www.member.repository.MemberRepository;
import com.ontheblock.www.session.domain.Session;
import com.ontheblock.www.session.dto.SessionRequest;
import com.ontheblock.www.session.repository.SessionRepository;
import com.ontheblock.www.video.domain.Video;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SessionService {

    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 s3Client;
    private final SessionRepository sessionRepository;
    private final MemberRepository memberRepository;
    private final InstrumentRepository instrumentRepository;

    // Session 생성
    public Session uploadSession(SessionRequest sessionRequest){
        Member member = memberRepository.findById(sessionRequest.getMemberId()).orElseThrow(()->new EntityNotFoundException("Member Not Found"));
        Instrument instrument = instrumentRepository.findById(sessionRequest.getInstrumentId()).orElseThrow(()->new EntityNotFoundException("Instrument Not Found"));
        //새로운 세션
        Session session = new Session(member, instrument, sessionRequest.getTotalFrame());
        sessionRepository.save(session);
        return session;
    }

    // Session 영상 S3에 저장후, 주소 반환
    public String saveSessionToS3(Session session, MultipartFile file, Long memberId){
        String folderName = "sessions" + "/" + memberId + "/" + session.getId(); // 원하는 폴더 이름
        String fileName = folderName + "/" + file.getOriginalFilename();
        try{
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            // 파일을 AWS S3에 업로드
            s3Client.putObject(bucket, fileName, file.getInputStream(), objectMetadata);
        } catch (Exception e) {
            logger.error("Failed to upload session video to S3. sessionId={}, memberId={}", session.getId(), memberId, e);
            throw new IllegalStateException("세션 영상 업로드에 실패했습니다.", e);
        }
        return s3Client.getUrl(bucket, fileName).toString();
    }

    // Session 영상 URL 변경
    @Transactional
    public void UpdateSessionUrl(Session session, String sessionUrl){
        session.changeSessionUrl(sessionUrl);
    }

    // Session 조회
    public Session getSession(Long sessionId){
        Session session = sessionRepository.findById(sessionId).orElseThrow(()->new EntityNotFoundException("Session Not Found"));
        return session;
    }

    // Session 삭제
    @Transactional
    public void removeSession(Long sessionId){
        Session session = sessionRepository.findById(sessionId).orElseThrow(()->new EntityNotFoundException("Session Not Found"));
        sessionRepository.delete(session);
    }

}
