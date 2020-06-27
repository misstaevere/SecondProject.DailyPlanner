package com.qa.account.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.account.persistence.domain.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
	
//	List<Task> findByName(String taskName);

}
