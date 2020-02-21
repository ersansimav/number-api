package com.ersan.assignment.number.controller;

import com.ersan.assignment.number.model.ResponseMessage;
import com.ersan.assignment.number.model.Number;
import com.ersan.assignment.number.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/numbers")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @PostMapping(value = "/{numberVal}")
    public ResponseMessage createNumber(@PathVariable Integer numberVal) {
        return numberService.createNumber(numberVal);
    }

    @GetMapping(value = "/{numberVal}")
    public Number findByNumber(@PathVariable Integer numberVal) {
        return numberService.findByNumber(numberVal);
    }

    @GetMapping(value = "/maxnumber")
    public Number findMaxNumber() {
        return numberService.findMaxNumber();
    }

    @GetMapping(value = "/minnumber")
    public Number findMinNumber() {
        return numberService.findMinNumber();
    }

    @DeleteMapping(value = "/{numberVal}")
    public ResponseMessage deleteNumber(@PathVariable Integer numberVal) {
        return numberService.deleteNumber(numberVal);
    }

    @GetMapping
    public List<Number> findAllNumbers(@RequestParam(value = "orderingType", required = false) String orderingType) {
        return numberService.findAllNumbers(orderingType);
    }
}
