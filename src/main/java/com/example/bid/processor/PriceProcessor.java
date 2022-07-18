package com.example.bid.processor;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import com.example.bid.model.PriceModel;
import com.example.bid.utils.PriceUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

@Component
public class PriceProcessor {

    private Map<String, List<PriceModel>> priceMap;
    private List<PriceModel> priceModelList;


    private void readCSV() {
        try {
            priceMap = new ConcurrentHashMap<>();
            final File file = ResourceUtils.getFile("classpath:data/pricedatafile.csv");
            priceModelList = new CsvToBeanBuilder(new FileReader(file))
                    .withType(PriceModel.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<PriceModel>> read() {
        if (CollectionUtils.isEmpty(priceModelList)) {
            readCSV();
        }

        priceModelList.forEach(priceModel -> {
            getAdjustments(priceModel);
            priceMap.get(PriceUtils.BUY);
        });
        return priceMap;
    }

    public Map<String, List<PriceModel>> write() {
        if (CollectionUtils.isEmpty(priceModelList)) {
            readCSV();
        }

        List<PriceModel> updatePriceModelList = new ArrayList<>();
        priceModelList.forEach(priceModel -> updatePriceModelList.add(getAdjustments(priceModel)));

        priceMap.put(PriceUtils.SALE, updatePriceModelList);
        return priceMap;
    }

    private PriceModel getAdjustments(PriceModel priceModel) {
        priceModel.setBid(priceModel.getBid() + (priceModel.getBid() * (-0.1)));
        priceModel.setAsk(priceModel.getAsk() + (priceModel.getAsk() * (0.1)));

        return priceModel;
    }

}
