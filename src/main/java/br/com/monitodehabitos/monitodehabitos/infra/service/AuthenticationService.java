package br.com.monitodehabitos.monitodehabitos.infra.service;

import br.com.monitodehabitos.monitodehabitos.infra.persistence.ClientEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final ClientEntityRepository clientEntityRepository;

    public AuthenticationService(ClientEntityRepository clientEntityRepository) {
        this.clientEntityRepository = clientEntityRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientEntityRepository.findByEmail(email);
    }
}
