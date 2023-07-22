package com.goan.football.controllers;

import com.goan.football.models.Payment;
import com.goan.football.models.Search;
import com.goan.football.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PaymentController {

    private final PaymentService paymentService;

    @MutationMapping
    public Payment addPayment(@Argument Payment payment){
        return paymentService.save(payment);
    }

    @QueryMapping
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public List<Payment> allPayments(@Argument Search search){
        return paymentService.all(search);
    }

}
