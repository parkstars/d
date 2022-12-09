package com.project.pro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
//중복된 필드를 따로 모아 하나의 클래스에서 정의
//EntityListeners를 여기에서 넣어준다.
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable=false) private LocalDateTime createdAt;
    @CreatedBy
    @Column(nullable=false, length=100) private String createdBy;
    @LastModifiedDate
    @Column(nullable=false) private LocalDateTime modifiedAt;
    @LastModifiedBy
    @Column(nullable=false, length=100) private String modifiedBy;

}
