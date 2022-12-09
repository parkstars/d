package com.project.pro.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes= {@Index(columnList="content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})

//AuditingFields로 createdBy등의 중복된 필드를 뺐을 때 아래 EntityListeners는 상속되는 클래스에 넣어준다.

//protected로 constructor를 만든것과 동일한 효과
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment extends AuditingFields{

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private Article article; //아티클 id
    @Setter @Column(nullable = false,length = 500) private String content;

//    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
//    @CreatedBy private String createdBy;
//    @LastModifiedDate private LocalDateTime modifiedAt;
//    @LastModifiedBy private String modifiedBy;

    protected  ArticleComment(){}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static  ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
