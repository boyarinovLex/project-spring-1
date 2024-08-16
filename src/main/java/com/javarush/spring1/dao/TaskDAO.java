package com.javarush.spring1.dao;

import com.javarush.spring1.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDAO extends JpaRepository<Task, Integer> {


}
