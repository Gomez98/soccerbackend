package com.goan.football.controllers;

import com.goan.football.models.Payment;
import com.goan.football.models.Search;
import com.goan.football.models.Student;
import com.goan.football.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @MutationMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public Payment addPayment(@Argument Payment payment){
        return paymentService.save(payment);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Payment> allPayments(@Argument Search search){
        return paymentService.all(search);
    }

}
