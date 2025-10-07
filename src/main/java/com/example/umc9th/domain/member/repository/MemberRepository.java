package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query(value = "SELECT * " +
            "FROM member " +
            "WHERE name = :name AND deleted_at IS NULL", nativeQuery = true)
    List<Member> findActiveMember(@Param("name") String name);

    @Query(value = "SELECT * " +
            "FROM member m " +
            "WHERE m.deleted_at IS NULL AND m.address LIKE CONCAT('%', :address, '%')", nativeQuery = true)
    List<Member> searchMemberByAddress(@Param("address") String address);
    
    @Query(value = "SELECT * " +
            "FROM member m " +
            "WHERE m.deleted_at IS NULL AND m.point >= :point", nativeQuery = true)
    List<Member> searchMemberByPoint(
            @Param("point") Long point
    );

    @Query(value = "SELECT * " +
            "FROM member m " +
            "WHERE m.deleted_at IS NULL AND m.address LIKE CONCAT('%', :address, '%') " +
            "AND m.point >= :point", nativeQuery = true)
    List<Member> searchMemberByAddressAndPoint(
            @Param("address") String address,
            @Param("point") Long point
    );
}
