package com.demo.services;

import com.demo.dtos.TestHistoryDTO;

public interface TestHistoryService {
    public boolean save(TestHistoryDTO testHistoryDTO);
    public boolean checkDone(int userId);
    public TestHistoryDTO findByUserIdAndTestId(int userId);
    public boolean update(TestHistoryDTO testHistoryDTO);
}
