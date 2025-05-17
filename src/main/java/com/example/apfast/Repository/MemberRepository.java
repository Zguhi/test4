// src/main/java/com/example/apfast/Repository/MemberRepository.java
package com.example.apfast.Repository;

import com.example.apfast.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
  Member findByUserId(String userId);
}