package com.example.apfast.Service.Impl;

import com.example.apfast.Entity.Member;
import com.example.apfast.Entity.Role;
import com.example.apfast.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementation of Spring Security's UserDetailsService interface.
 * This service is responsible for loading user-specific data during authentication.
 * It translates the application's user and role data into Spring Security's UserDetails objects.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  // Logger for debugging and monitoring
  private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

  // Injecting the MemberRepository to access user data
  @Autowired
  private MemberRepository memberRepository;

  /**
   * Core method that loads a user by username.
   * This is called by Spring Security during authentication process.
   *
   * @param username The username (user_id in our database) attempting to log in
   * @return UserDetails A Spring Security user object containing authentication details
   * @throws UsernameNotFoundException if the user is not found in the database
   */
  @Override
  @Transactional(readOnly = true) // Making this transactional for better database access
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("Attempting to load user: " + username);

    // Find the user in our database
    Member member = memberRepository.findByUserId(username);

    // If user doesn't exist, throw an exception
    if (member == null) {
      logger.warning("User not found: " + username);
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    logger.info("User found: " + username + " with " + member.getRoles().size() + " roles");

    // Convert our custom user entity to Spring's UserDetails
    return buildUserDetails(member);
  }

  /**
   * Builds a Spring Security UserDetails object from our custom Member entity.
   *
   * @param member The Member entity from our database
   * @return UserDetails object for Spring Security
   */
  private UserDetails buildUserDetails(Member member) {
    // Extract authorities from the member's roles
    Collection<? extends GrantedAuthority> authorities = getAuthorities(member);

    // Create and return a User object (Spring Security's implementation of UserDetails)
    return new User(
            member.getUserId(),           // username
            member.getPassword(),         // password (should be already encoded)
            member.isActive(),            // enabled
            true,                         // accountNonExpired
            true,                         // credentialsNonExpired
            true,                         // accountNonLocked
            authorities                   // authorities/roles
    );
  }

  /**
   * Converts the application's Role entities to Spring Security's GrantedAuthority objects.
   * Spring Security uses these authorities to determine access rights.
   *
   * @param member The Member entity containing roles
   * @return Collection of GrantedAuthority objects
   */
  private Collection<? extends GrantedAuthority> getAuthorities(Member member) {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    // Create a GrantedAuthority for each role
    // Note: Spring Security conventionally prefixes role names with "ROLE_"
    for (Role role : member.getRoles()) {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRole());
      authorities.add(authority);
      logger.info("Added authority: " + authority.getAuthority() + " for user: " + member.getUserId());
    }

    return authorities;
  }

  /**
   * Alternative implementation using Java Streams (functional approach)
   * This could replace the getAuthorities method above for a more concise implementation
   */
  private Collection<? extends GrantedAuthority> getAuthoritiesUsingStreams(Member member) {
    return member.getRoles().stream()
            .map(role -> {
              String authorityName = "ROLE_" + role.getRole();
              logger.info("Added authority: " + authorityName + " for user: " + member.getUserId());
              return new SimpleGrantedAuthority(authorityName);
            })
            .collect(java.util.stream.Collectors.toList());
  }
}