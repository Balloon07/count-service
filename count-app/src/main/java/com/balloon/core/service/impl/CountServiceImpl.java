package com.balloon.core.service.impl;

import com.balloon.common.util.IDGenerator;
import com.balloon.core.repository.CountConfigRepository;
import com.balloon.core.service.CountService;
import com.balloon.count.api.admin.param.CountConfigParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王思远
 * @date 2023-12-16 18:40
 */
@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private IDGenerator idGenerator;
    @Autowired
    private CountConfigRepository countConfigRepository;


    @Override
    public String generateId(String countType) {
        return countType + idGenerator.generateId();
    }

    @Override
    public String createConfig(CountConfigParam param) {

        return null;
    }
}
