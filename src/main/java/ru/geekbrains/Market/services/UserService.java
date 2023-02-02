package ru.geekbrains.Market.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.Market.entities.RoleEntity;
import ru.geekbrains.Market.entities.UserEntity;
import ru.geekbrains.Market.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByUserName(username).orElseThrow(()->new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                mapRolesToAuthorities(userEntity.getRoleEntities())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName()))
                .toList();
    }

    private Optional<UserEntity> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
