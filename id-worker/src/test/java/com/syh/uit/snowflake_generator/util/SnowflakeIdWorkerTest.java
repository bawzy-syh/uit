package com.syh.uit.snowflake_generator.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SnowflakeIdWorkerTest {

    @Test
    void nextId() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        ArrayList<Long> before = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            boolean temp = before.remove(id);
            before.add(id);
            Assertions.assertFalse(temp);
            Assertions.assertNotNull(Long.toBinaryString(id));
        }
    }
}