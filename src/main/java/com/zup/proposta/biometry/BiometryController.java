package com.zup.proposta.biometry;

import com.zup.proposta.card.Card;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class BiometryController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Tracer tracer;

    @PostMapping("/card/{idCard}/biometry")
    @Transactional
    public ResponseEntity<?> create(@PathVariable("idCard") Long idCard,
                                    @RequestBody @Valid NewBiometryRequest request,
                                    UriComponentsBuilder uriComponentsBuilder)  {
        Card card = entityManager.find(Card.class, idCard);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        Biometry biometry = request.toEntity(card);
        entityManager.persist(biometry);

        URI uri = uriComponentsBuilder.path("/biometry/{id}").buildAndExpand(biometry.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
