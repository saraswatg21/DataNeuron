package com.example.demo.service;

import com.example.demo.dto.DataDTO;
import com.example.demo.entity.Data;
import com.example.demo.repository.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddDataService {
    @Autowired
    private DataRepo dataRepository;

    public Data addData(DataDTO requestData) {
        String data = requestData.getData();
        Data final_data = new Data();
        final_data.setUpdated(Boolean.FALSE);
        final_data.setData(data);
        return dataRepository.save(final_data);
    }

    public Data update_data(Data requestData) throws IllegalAccessException {
        Optional<Data> get_data = dataRepository.findById(requestData.getId());
        if (get_data.isPresent()){
            requestData.setUpdated(Boolean.TRUE);
            dataRepository.save(requestData);
            return requestData;
        }
        else {
            throw new IllegalAccessException("Id not found in database");
        }
    }

    public int[] getCount() {
        int addCount = dataRepository.findAll().size();
        int updateCount = dataRepository.findUpdatedDataCount();
        return new int[]{addCount, updateCount};
    }

}
