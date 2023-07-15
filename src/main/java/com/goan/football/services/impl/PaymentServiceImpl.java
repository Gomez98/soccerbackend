package com.goan.football.services.impl;

import com.goan.football.models.Payment;
import com.goan.football.models.Search;
import com.goan.football.repositories.PaymentRepository;
import com.goan.football.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.goan.football.utils.ModelUtils.*;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;


    @Override
    public Payment save(Payment entity) {
        prepare(entity);

        return paymentRepository.save(entity);
    }

    @Override
    public Payment update(Payment entity) {
        return null;
    }

    @Override
    public Payment get(String id) {
        return null;
    }

    @Override
    public List<Payment> all(Search search) {
        List<Payment> payments = new ArrayList<>();
        String term = null;
        Integer page = null;
        Integer size = null;

        if (search.getTerm() != null) {
            term = search.getTerm();
        }
        if (search.getPage() != null) {
            page = search.getPage();
        }
        if (search.getSize() != null) {
            size = search.getSize();
        }

        if (page == null && size == null && term == null){
            payments = paymentRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(Payment::getModifiedAt).reversed())
                    .collect(Collectors.toList());
        }

        return payments;
    }

    @Override
    public List<Payment> all(String id) {
        return null;
    }

}
