package com.Add.Api.controller;

import com.Add.Api.data.RecordsKeeper;
import com.Add.Api.entity.RecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    private RecordsKeeper recordsKeeper;

    @GetMapping("/add")
    public ResponseEntity<RecordDto> add(@RequestParam int number1, @RequestParam int number2){
        if (number1 >= 0 && number1 <= 100 && number2 >= 0 && number2 <= 100){
            RecordDto recordDto = new RecordDto(number1,number2,number1+number2);
            recordsKeeper.addRecord(recordDto);
            return ResponseEntity.ok().body(recordDto);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<RecordDto>> get(@RequestParam Optional<Integer> number, @RequestParam boolean ascend){
            return ResponseEntity.ok().body(recordsKeeper.getRecords(number,ascend));
    }
}
