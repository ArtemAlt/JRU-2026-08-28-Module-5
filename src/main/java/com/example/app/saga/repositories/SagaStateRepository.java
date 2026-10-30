package com.example.app.saga.repositories;

import com.example.app.saga.domain.SagaState;
import com.example.app.saga.domain.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SagaStateRepository extends JpaRepository<SagaState, String> {
    List<SagaState> findByStatusIn(List<SagaStatus> statuses);
}