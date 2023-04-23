package com.codelabs.Requester.repostitory;

import com.codelabs.Requester.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequesterRepository  extends JpaRepository<Sender, Long> {
    Optional<Sender> findSenderByLastName(String email);
}
