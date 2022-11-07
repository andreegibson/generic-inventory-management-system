package com.allianceair.gims.controller;

import com.allianceair.gims.model.Type;
import com.allianceair.gims.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//TODO - Make this better :)
@CrossOrigin("http://localhost:3000")
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    @GetMapping
    public List<Type> getAllTypes() {
        return typeService.findAllTypes();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Type addtype(@RequestBody Type type) {
        return typeService.addType(type);
    }
}