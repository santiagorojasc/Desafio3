package api.desafio3.foro.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByActivoTrue(Pageable paginacion);

    boolean existsByEmail(String email);
}
