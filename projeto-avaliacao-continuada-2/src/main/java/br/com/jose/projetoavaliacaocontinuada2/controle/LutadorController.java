package br.com.jose.projetoavaliacaocontinuada2.controle;

import br.com.jose.projetoavaliacaocontinuada2.dominio.Lutador;
import br.com.jose.projetoavaliacaocontinuada2.repositorio.LutadorRepositorio;
import br.com.jose.projetoavaliacaocontinuada2.requisicao.LutadorGolpeRequisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepositorio lutadorRepositorio;

    @PostMapping
    public ResponseEntity postLutadores(@RequestBody @Valid Lutador lutador) {
        lutadorRepositorio.save(lutador);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getLutadores() {
        return ResponseEntity.status(200).body(lutadorRepositorio.findAllByOrderByForcaGolpeDesc());
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getContagemVivos() {

        return ResponseEntity.status(200).body(lutadorRepositorio.countAllByVivoTrue());
    }

    @PostMapping("/{x}/concentrar")
    public ResponseEntity postConcentrar(@RequestParam int x) {
        int concentracoes = 0;
        concentracoes++;
        if(lutadorRepositorio.existsById(x)) {
            Optional<Lutador> lutador = lutadorRepositorio.findById(x);
            Lutador lutador1 = lutador.get();
            lutador1.setConcentracoesRealizadas(concentracoes);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @PostMapping("/golpe")
    public ResponseEntity postGolpe(@RequestBody LutadorGolpeRequisicao lutador) {
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/mortos")
    public ResponseEntity getMortos() {
        List<Lutador> lutadores = lutadorRepositorio.findAllByVivoFalse();
        if (lutadores.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(lutadorRepositorio.findAllByVivoFalse());
        }
    }
}
