package com.project.pro.repository;

import com.project.pro.config.JpaConfig;
import com.project.pro.domain.Article;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//application.yaml의 테스트용 DB설정 사용
// DB설정에 아래 내용이 없으면 실제 TEST자바클래스 위에 @ActiveProfiles(“heroku”)등을 넣어야
// 테스트용 인메모리 디비를 사용할 수 잇다.
// test.database.replace : none
//
//@ActiveProfiles(“testDB”);
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("JPA연결테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private static Logger logger = LoggerFactory.getLogger(JpaRepositoryTest.class);
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    //생성자 주입
    public JpaRepositoryTest(
        @Autowired ArticleRepository articleRepository,
        @Autowired ArticleCommentRepository articleCommentRepository){
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isNotNull()
                .hasSize(0);
    }
    @DisplayName("Insert test")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        long previousCount = articleRepository.count();
        Article sevedArticle = articleRepository.save(Article.of("new article","new content","#tag"));
        sevedArticle = articleRepository.save(Article.of("new article1","new content2","#tag"));
        sevedArticle = articleRepository.save(Article.of("new article3","new content2","#tag"));

        assertThat(articleRepository.count()).isEqualTo(previousCount+3);
        List<Article> list = articleRepository.findAll();
        list.stream().forEach(artcles->{
            logger.info(String.valueOf(artcles));
        });
        logger.info(String.valueOf(sevedArticle.getArticleComments().size()));
    }
}