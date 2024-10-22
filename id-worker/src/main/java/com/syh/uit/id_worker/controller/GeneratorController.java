package com.syh.uit.id_worker.controller;

import com.syh.uit.id_worker.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorController {
    private GeneratorService generatorService;
    @Autowired
    public void setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @RequestMapping(value = "/" ,method = {RequestMethod.GET})
    public long getNewID(){
        return generatorService.newID();
    }
}
