package br.com.southsystem.assembleia.controller;

import br.com.southsystem.assembleia.AssembleiaApplication;
import br.com.southsystem.assembleia.model.ErroResponse;
import br.com.southsystem.assembleia.model.associado.dto.AssociadoDTO;
import br.com.southsystem.assembleia.model.associado.request.AssociadoRequest;
import br.com.southsystem.assembleia.service.AssociadoService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/associados")
public class AssociadoController {
    @Autowired
    private AssociadoService associadoService;
    private static final Logger LOG = Logger.getLogger(AssembleiaApplication.class);

    @PostMapping
    public ResponseEntity<?> cadastrarAssociado(@RequestBody AssociadoRequest associadoRequest) {
        try {
            AssociadoDTO associadoDTO = associadoService.cadastrarAssociado(associadoRequest);
            LOG.info(associadoRequest.getNome() + " foi cadastrado(a)!\n");
            return ResponseEntity.status(HttpStatus.CREATED).body(associadoDTO);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            String mensagem = "Associado já cadastrado";
            ErroResponse erroResponse = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(erroResponse);
        } catch (Exception exception) {
            LOG.error("Houve um erro ao fazer o cadastro: " + exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
