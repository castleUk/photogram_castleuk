package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//JPA - Java Persistence API(자바로 데이터를 영구적으로 저장할수 있는 API제공)

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 디비에 테이블을 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private int id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String website; // 웹사이트

    private String bio; // 자기소개

    @Column(nullable = false)
    private String email;

    private String phone;

    private String gender;

    private String profileImageUrl;
    private String role; // 권한

    // 나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지마
    // User를 Select할때 해당 User id로 등록된 image 들을 다 가져와
    // Lazy = User를 Select 할 때 해당 User id로 등록된 image들을 가져오지마
    // 대신 getImages() 함수의 image들이 호출될 때 가져와!
    // Eager = User를 Select 할 때 해당 User id로 등록된 image들을 전부 Join해서 가져와
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images; // 양방향 매핑

    private LocalDateTime createDate;

    @PrePersist // 디비에 인서트 되기전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
