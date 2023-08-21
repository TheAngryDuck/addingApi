package com.Add.Api.data;

import com.Add.Api.entity.RecordDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecordsKeeper {


    private static final Collection<RecordDto> records = Collections.synchronizedCollection(new ArrayList<>());

    public void addRecord(RecordDto dto){records.add(dto);}

    public List<RecordDto> getRecords(Optional<Integer> number, boolean ascend){
        List<RecordDto> response = new ArrayList<>();
        if (number.isEmpty()){
            response.addAll(records.stream().toList());
        }else{
            for (RecordDto dto: records) {
                if (dto.getFirstNumber() == number.get() || dto.getSecondNumber() == number.get() || dto.getResult() == number.get()){
                    response.add(dto);
                }
            }
        }

        if (ascend){
            response.sort((Comparator.comparingInt(RecordDto::getResult)));
        }else{
            response.sort((Comparator.comparingInt(RecordDto::getResult)).reversed());
        }
        return response;
    }
    public void deleteRecords(){
        records.clear();
    }

}
