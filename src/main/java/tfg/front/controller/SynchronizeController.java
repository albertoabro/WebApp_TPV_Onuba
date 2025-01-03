package tfg.front.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tfg.front.Synchronized;
import tfg.front.domain.Terminal;
import tfg.front.error.RestTemplateError;
import tfg.front.service.terminal.TerminalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/synchronize")
public class SynchronizeController {

    private final TerminalService terminalService;

    List<Terminal> terminals = new ArrayList<>();

    public SynchronizeController(TerminalService terminalService){
        this.terminalService = terminalService;
    }

  @GetMapping("/terminals")
    public ModelAndView getTerminals() throws JsonProcessingException {
        if(terminals.isEmpty())
            terminals = terminalService.getTerminals();

        ModelAndView modelAndView = new ModelAndView("/synchronize/terminals");
        modelAndView.addObject("terminals", terminals);

        return modelAndView;
    }

    @PostMapping("/synchronize")
    public void sync(HttpServletResponse response, @RequestParam List<Integer> terminalSelected) throws IOException {
        Synchronized sync = terminalService.getObject();
        int numSyncValid = 0;

        for(int t : terminalSelected)
            if(terminals.get(t).getIdterminal()==t+1){
                sync.syncWithDropBox();
                numSyncValid++;
            }
        if(numSyncValid == terminalSelected.size())
            response.sendRedirect("/index");
        else
            getTerminals();
    }

    @GetMapping("/tickets")
    public ModelAndView getTickets(){
        return new ModelAndView("/synchronize/tickets");
    }

    @PostMapping("/download")
    public void download(HttpServletResponse response){
        Synchronized sync = terminalService.getObject();
        try {
            if (sync.syncDropboxWithServer())
                response.sendRedirect("/index");

            else
                response.sendRedirect("/synchronize/tickets");
        } catch (Exception e) {
            throw new RestTemplateError(e.toString());
        }
    }
}
