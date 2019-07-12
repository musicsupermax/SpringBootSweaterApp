package com.spring.sweater.repository;

import com.spring.sweater.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
        List<Message> findAllByTag(String tag);
}
