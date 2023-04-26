package com.sparta.board_spring.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter // 정보를 가져옴으로 Getter사용
@MappedSuperclass // 여러 테이블에서 공통으로 사용되는 필드를 테이블에 컬럼으로 등록할 때 사용 - Column 자동 생성하게 도와줌
@EntityListeners(AuditingEntityListener.class) //Entity의 변화를 감지하고 테이블의 데이터 조작하는 일을 함
//AuditingEntityLinster.class를 사용하기 위해서는 ~Application.java파일에 @EnableJpaAuditing annotation 필수
//Auditing에서 데이터가 생성될떄, 업데이트 될때 업데이트 해주길 바라는 변수에 @CreatedDate와 @LastModifiedDate을 붙인다
public class Timestamped {

    @CreatedDate //생성 날짜 및 시간
    private LocalDateTime createdAt;

    //LocalDateTime - local 날짜 및 시간 기준으로 책정 (yyyy-mm-dd-T-hour-min-sec)

    @LastModifiedDate //수정 날짜 및 시간
    private LocalDateTime modifiedAt;


    // 필드에 private으로 선언함으로써, timestamp가 지정되어 있는 entity에서 발생하는 creation and modification을 저장하기 위함

}
