package com.formation.foadmanagementback.Services.Implements;

import com.formation.foadmanagementback.Entities.User;
import com.formation.foadmanagementback.Repositories.IUsersRepository;
import com.formation.foadmanagementback.Services.Abstracts.IUsersDetailsServ;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UsersDetailServImplements implements IUsersDetailsServ {

    private final IUsersRepository iUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = iUsersRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            Collections.singleton(
                new SimpleGrantedAuthority(
                    "ROLE_" + user.getRole().name()))
        );
    }

}
