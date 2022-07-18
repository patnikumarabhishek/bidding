package com.example.bid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bid.data.PriceData;
import com.example.bid.service.PriceService;

@RestController
@RequestMapping("/api")
public class PriceController {

	@Autowired
	private PriceService priceService;

	@GetMapping(value = "/market")
	public ResponseEntity<List<PriceData>> getLatestBid() {
		return ResponseEntity.ok(priceService.writePriceData());
	}

}
