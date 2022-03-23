package com.example.emlakburadaadvert.queue;

import com.example.emlakburadaadvert.dto.request.AdvertStatusRequest;

public interface QueueService {
	
	void sendMessage(AdvertStatusRequest advertStatusRequest);

}
