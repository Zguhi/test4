package com.example.apfast.Config;

import com.example.apfast.Entity.Member;
import com.example.apfast.Entity.Role;
import com.example.apfast.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    // Check if users already exist
    if (memberRepository.findByUserId("admin") == null) {
      // Create admin user
      Member admin = new Member();
      admin.setUserId("admin");
      admin.setPassword(passwordEncoder.encode("admin"));
      admin.setActive(true);
      admin.setRoles(Arrays.asList(new Role("admin", "ADMIN")));
      memberRepository.save(admin);
    }

    if (memberRepository.findByUserId("sale") == null) {
      // Create sale user
      Member sale = new Member();
      sale.setUserId("sale");
      sale.setPassword(passwordEncoder.encode("sale"));
      sale.setActive(true);
      sale.setRoles(Arrays.asList(new Role("sale", "SALE")));
      memberRepository.save(sale);
    }
  }
}