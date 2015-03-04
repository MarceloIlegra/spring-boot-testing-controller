
package br.com.marcelo.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandaloneController {

    @RequestMapping(value="standalone", method = RequestMethod.GET)
    public String showStandaloneOk(){
        return "Ok";
    }

}