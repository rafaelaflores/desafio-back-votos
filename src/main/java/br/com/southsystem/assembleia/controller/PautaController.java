package br.com.southsystem.assembleia.controller;

import br.com.southsystem.assembleia.AssembleiaApplication;
import br.com.southsystem.assembleia.model.ErroResponse;
import br.com.southsystem.assembleia.model.pauta.dto.PautaDTO;
import br.com.southsystem.assembleia.model.pauta.request.PautaRequest;
import br.com.southsystem.assembleia.service.PautaService;
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
@RequestMapping("v1/pautas")
public class PautaController {
    @Autowired
    private PautaService pautaService;
    private static final Logger LOG = Logger.getLogger(AssembleiaApplication.class);

    @PostMapping
    public ResponseEntity<?> cadastrarPauta(@RequestBody PautaRequest pautaRequest) {
        try {
            PautaDTO pautaDTO = pautaService.cadastrarPauta(pautaRequest);
            LOG.info(pautaRequest.getTitulo() + " foi cadastrado(a)!\n");
            return ResponseEntity.status(HttpStatus.CREATED).body(pautaDTO);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            String mensagem = "Registro com título já existente";
            ErroResponse erroResponse = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST);
            LOG.error(mensagem);
            return ResponseEntity.badRequest().body(erroResponse);
        } catch (Exception exception) {
            LOG.error("Houve um erro ao fazer o cadastro: " + exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
