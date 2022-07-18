package com.example.bid.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bid.data.PriceData;
import com.example.bid.model.PriceModel;
import com.example.bid.processor.PriceProcessor;

@Service
public class PriceService {

    private final PriceProcessor priceProcessor;

    @Autowired
    public PriceService(final PriceProcessor priceProcessor) {
        this.priceProcessor = priceProcessor;
    }

    public Map<String, List<PriceModel>> readPriceData() {
        return priceProcessor.read();
    }

    public List<PriceData> writePriceData() {
        List<PriceData> priceDataList = new ArrayList<>();
        Map<String, List<PriceModel>> concurrentMap = priceProcessor.write();

        concurrentMap.forEach((key, priceModelList) -> priceModelList.forEach(priceModel -> {
            PriceData priceData = new PriceData();
            priceData.setUniqueId(priceModel.getUniqueId());
            priceData.setInstrumentName(priceModel.getInstrumentName());
            priceData.setBid(priceModel.getBid());
            priceData.setAsk(priceModel.getAsk());
            priceData.setTimestamp(priceModel.getTimestamp());
            priceDataList.add(priceData);
        }));
        return priceDataList;
    }

}
