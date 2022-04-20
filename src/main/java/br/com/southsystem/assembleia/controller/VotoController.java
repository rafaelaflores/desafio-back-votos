package br.com.southsystem.assembleia.controller;

import br.com.southsystem.assembleia.AssembleiaApplication;
import br.com.southsystem.assembleia.exception.AssociadoException;
import br.com.southsystem.assembleia.exception.SessaoIniciadaException;
import br.com.southsystem.assembleia.model.ErroResponse;
import br.com.southsystem.assembleia.model.voto.dto.VotoDTO;
import br.com.southsystem.assembleia.model.voto.request.VotoRequest;
import br.com.southsystem.assembleia.service.VotoService;
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
@RequestMapping("v1/votos")
public class VotoController {
    @Autowired
    VotoService votoService;

    private static final Logger LOG = Logger.getLogger(AssembleiaApplication.class);

    @PostMapping
    public ResponseEntity registrarVoto(@RequestBody VotoRequest votoRequest) {
        try {
            VotoDTO votoDTO = votoService.registrarVoto(votoRequest);
            LOG.info("Voto foi cadastrado(a)!\n");
            return ResponseEntity.status(HttpStatus.CREATED).body(votoDTO);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            String mensagem = "Registro com título já existente";
            ErroResponse erroResponse = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST);
            LOG.error(mensagem);
            return ResponseEntity.badRequest().body(erroResponse);
        } catch (IllegalArgumentException illegalArgumentException) {
            String mensagem = illegalArgumentException.getMessage();
            ErroResponse erroResponse = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST);
            LOG.error(mensagem);
            return ResponseEntity.badRequest().body(erroResponse);
        } catch (SessaoIniciadaException sessaoIniciadaException) {
            String mensagem = sessaoIniciadaException.getMessage();
            ErroResponse erroResponse = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST);
            LOG.error(mensagem);
            return ResponseEntity.badRequest().body(erroResponse);
        } catch (AssociadoException associadoException) {
            String mensagem = associadoException.getMessage();
            ErroResponse erroResponse = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST);
            LOG.error(mensagem);
            return ResponseEntity.badRequest().body(erroResponse);
        } catch (Exception exception) {
            LOG.error("Houve um erro ao fazer o cadastro: " + exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}