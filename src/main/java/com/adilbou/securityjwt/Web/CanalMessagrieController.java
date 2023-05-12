package com.adilbou.securityjwt.Web;


import com.adilbou.securityjwt.Entities.Besoin;
import com.adilbou.securityjwt.Entities.Message;
import com.adilbou.securityjwt.Service.CanalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Canal-Controller")
@CrossOrigin("*")
public class CanalMessagrieController {

    private CanalService canalService;

    public CanalMessagrieController(CanalService canalService) {
        this.canalService = canalService;
    }


    @PostMapping("/save")
    Message  save(@RequestBody Message message) {
         return canalService.sendMessage(message);
    }

    @GetMapping("/getMsgDepart/{idSender}")
    List<Message> getMsgDepart(@PathVariable Integer idSender){
        return canalService.getMssgByDepart(idSender);
    }





}
