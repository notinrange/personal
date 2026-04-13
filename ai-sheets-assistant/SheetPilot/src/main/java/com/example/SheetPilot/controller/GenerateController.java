package com.example.SheetPilot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class GenerateController {
    @PostMapping("/generate")
    public Map<String, String> generate(@RequestBody Map<String, Object> req) {

        System.out.println(req);

        return Map.of(
                "formula", "=SUM(B2:B100)"
        );
    }
}
