package br.com.jose.projetoavaliacaocontinuada2.repositorio;

import br.com.jose.projetoavaliacaocontinuada2.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LutadorRepositorio extends JpaRepository<Lutador, Integer> {
    List<Lutador> findAllByOrderByForcaGolpeDesc();
    int countAllByVivoTrue();
    List<Lutador> findAllByVivoFalse();
}
