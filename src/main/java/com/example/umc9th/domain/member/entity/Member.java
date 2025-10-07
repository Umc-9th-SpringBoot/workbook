package com.example.umc9th.domain.member.entity;


import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.store.enums.Address;
import com.example.umc9th.global.auth.enums.SocialType;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address")
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "social_uid")
    private String socialUid;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "point")
    private Integer point;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    // 함수
    public void update(String newName){
        this.name = newName;
    }
}
