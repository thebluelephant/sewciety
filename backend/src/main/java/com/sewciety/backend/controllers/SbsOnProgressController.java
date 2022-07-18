package com.sewciety.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RelationNotFoundException;

import com.sewciety.backend.entity.SbsOnProgress;
import com.sewciety.backend.services.SbsOnProgressService;

@RestController
@RequestMapping("/api/progress")
public class SbsOnProgressController {

    @Autowired
    private SbsOnProgressService sbsOnProgressService;

    @PostMapping("/new")
    SbsOnProgress creatNewProgress(@RequestBody SbsOnProgress progress) {
        return sbsOnProgressService.creatNewProgress(progress);
    }

    @PostMapping("update/{sbsId}")
    SbsOnProgress updateProgress(@PathVariable(value = "sbsId") Integer sbsId,
            @RequestParam("progress") Boolean progress) throws RelationNotFoundException {
        return sbsOnProgressService.updateProgress(sbsId, progress);
    }
}
