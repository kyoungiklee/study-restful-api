package org.opennuri.study.restful.events.adapter.out.persistance.repository;

import org.opennuri.study.restful.events.adapter.out.persistance.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringJpaEventRepository extends JpaRepository<EventEntity, Long> {
}
