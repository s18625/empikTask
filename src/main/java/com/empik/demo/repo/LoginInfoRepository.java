package com.empik.demo.repo;

import com.empik.demo.model.LoginInfo;
import org.springframework.data.repository.CrudRepository;

public interface LoginInfoRepository extends CrudRepository<LoginInfo, String> {
}
