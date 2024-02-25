package com.webapp7.trelloclone.Services;

import com.webapp7.trelloclone.Repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;


}
