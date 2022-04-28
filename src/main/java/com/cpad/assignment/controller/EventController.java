package com.cpad.assignment.controller;

import com.cpad.assignment.repository.RawEventRepository;
import com.cpad.assignment.entity.RawEvent;
import com.cpad.assignment.domain.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class EventController {

    @Autowired
    private RawEventRepository rawEventRepository;

    @RequestMapping(value="/event", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody RawEvent event) {
        try {
            rawEventRepository.save(event);
        } catch (Exception e) {
            return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/lastEvent", method = RequestMethod.GET)
    public ResponseEntity<?> getLastEvent() {
        try {
            return new ResponseEntity<>(rawEventRepository.findTopByOrderByIdDesc(), HttpStatus.OK);
        } catch (Exception e) {
            Error error = new Error(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
