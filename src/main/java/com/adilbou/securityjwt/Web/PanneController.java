package com.adilbou.securityjwt.Web;

import com.adilbou.securityjwt.DTOs.*;
import com.adilbou.securityjwt.Service.PanneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Panne-Controller")
@CrossOrigin("*")
public class PanneController {
    private final PanneService panneService;


    public PanneController(PanneService panneService
                           ) {
        this.panneService = panneService;

    }
    @GetMapping("Technicien")
    public PanneDTO_Tech getAllPannesTechnicien() {
        return panneService.panneTechnicien();
    }
    @GetMapping("Responsable")
    public PanneDTO_Tech getAllPannesResponsable() {
        return panneService.panneResponsable();
    }
    @GetMapping("getRessourceWithState/{idEnse}")
    public RessourceDTO_ENS getRessourceWithState(@PathVariable Integer idEnse) {
        return panneService.getRessourcesWithState(idEnse);
    }
    @PostMapping("setConstat")
    public void setPanneConstat(@RequestBody PanneDTO_Constat panneConstatDTO)
    {
        panneService.setConstatPanne(panneConstatDTO);
    }
    @PostMapping("setPanne")
    public void setPanne(@RequestBody PanneDTO_Ens panneDTOEns){
        panneService.setPanne(panneDTOEns);
    }
    @GetMapping("setEtat/{idPanne}")
    public void setEtat(@PathVariable Long idPanne){
        panneService.setEtat(idPanne);
    }


}