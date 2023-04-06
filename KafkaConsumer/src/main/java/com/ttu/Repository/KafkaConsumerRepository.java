package com.ttu.Repository;

import com.ttu.model.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaConsumerRepository extends JpaRepository<WikimediaData,Long> {
}
