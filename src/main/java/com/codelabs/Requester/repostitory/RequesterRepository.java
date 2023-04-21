package com.codelabs.Requester.repostitory;

import com.codelabs.Requester.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequesterRepository  extends JpaRepository<Sender, Long> {
}
