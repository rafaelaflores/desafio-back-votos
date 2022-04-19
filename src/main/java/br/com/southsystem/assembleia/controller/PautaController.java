package br.com.southsystem.assembleia.controller;

import br.com.southsystem.assembleia.AssembleiaApplication;
import br.com.southsystem.assembleia.exception.SessaoIniciadaException;
import br.com.southsystem.assembleia.model.ErroResponse;
import br.com.southsystem.assembleia.model.pauta.dto.PautaDTO;
import br.com.southsystem.assembleia.model.pauta.request.PautaRequest;
import br.com.southsystem.assembleia.model.sessao.dto.SessaoDTO;
import br.com.southsystem.assembleia.model.sessao.request.SessaoRequest;
import br.com.southsystem.assembleia.service.PautaService;
import br.com.southsystem.assembleia.service.SessaoService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pautas")
public class PautaController {
    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoService sessaoService;

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

    @PostMapping("/{idPauta}/sessoes")
    public ResponseEntity<?> iniciarSessao(@PathVariable Long idPauta, @RequestBody(required = false) SessaoRequest sessaoRequest) {
        try {
            SessaoDTO sessaoDTO = sessaoService.criarSessao(idPauta, sessaoRequest);
            LOG.info("A sessão foi aberta para a pauta " + idPauta);
            return ResponseEntity.status(HttpStatus.CREATED).body(sessaoDTO);
        } catch(IllegalArgumentException illegalArgumentException) {
            String mensagem = illegalArgumentException.getMessage();
            ErroResponse erroResponse = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST);
            LOG.error(mensagem);
            return ResponseEntity.badRequest().body(erroResponse);
        } catch (SessaoIniciadaException sessaoIniciadaException) {
            String mensagem = sessaoIniciadaException.getMessage();
            LOG.error(mensagem);
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPautas() {
        List<PautaDTO> pautas = pautaService.listarPautas();
        return ResponseEntity.status(HttpStatus.OK).body(pautas);
    }
}
