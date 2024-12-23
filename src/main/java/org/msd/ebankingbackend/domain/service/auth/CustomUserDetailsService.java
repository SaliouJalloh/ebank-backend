package org.msd.ebankingbackend.domain.service.auth;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.domain.model.Customer;
import org.msd.ebankingbackend.infrastructure.persistence.ICustomerPersistenceService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ICustomerPersistenceService customerPersistenceService;

    // Méthode qui permet de charger un utilisateur par son nom d'utilisateur.
    // Cette méthode est appelée par Spring Security lorsqu'un utilisateur tente de se connecter.
    @Override
    public Customer loadUserByUsername(String username) throws UsernameNotFoundException {
        // Nous récupérons l'utilisateur par son nom d'utilisateur
        return customerPersistenceService.findCustomerByEmail(username);
    }
}
