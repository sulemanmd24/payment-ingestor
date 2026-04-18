package com.payment.ingestor.kafka;


import com.payment.ingestor.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentIngestorProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("payment-processor.kafka.producer.topics.payment-submitted")
    private String PAYMENT_SUBMITTED_TOPIC;


    public void createPayment(PaymentRequest paymentRequest){
        kafkaTemplate.send(PAYMENT_SUBMITTED_TOPIC,paymentRequest);
        System.out.println("orderDetails -------"+paymentRequest);
    }
}
