package org.msd.ebankingbackend.domain.service.auth;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.domain.model.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.msd.ebankingbackend.infrastructure.persistence.service.ICustomerPersistenceService;
import org.msd.ebankingbackend.infrastructure.security.SecurityUser;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Nous récupérons l'utilisateur par son nom d'utilisateur
        Customer customer = customerPersistenceService.findCustomerByEmail(username);
        return new SecurityUser(customer);
    }
}
