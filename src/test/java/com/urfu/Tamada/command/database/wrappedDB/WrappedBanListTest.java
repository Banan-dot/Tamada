package com.urfu.Tamada.command.database.wrappedDB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WrappedBanListTest {
    private WrappedBanList banList;

    @BeforeEach
    void setUp(){
        var strings = new HashMap<Long, List<Long>>();
        strings.put(1L, List.of(1L, 2L, 3L));
        strings.put(2L, List.of(10L, 20L, 30L));
        strings.put(3L, List.of(100L, 200L, 300L));
        banList = new WrappedBanList(strings);
    }

    @Test
    void addToBanListNewKey(){
        banList.addToBanList(22L, 33L);
        var containsKey = banList.getBanTable().containsKey(22L);
        var containsValue = banList.getBanTable().get(22L).get(0) == 33L;
        Assertions.assertTrue(containsKey);
        Assertions.assertTrue(containsValue);
    }

    @Test
    void addToBanListExistingKey(){
        banList.addToBanList(2L, 33L);
        var containsKey = banList.getBanTable().containsKey(2L);
        var containsValue = banList.getBanTable().get(2L).get(3) == 33L;
        Assertions.assertTrue(containsKey);
        Assertions.assertTrue(containsValue);
    }


    @Test
    void getBanTable(){
        var banTable = banList.getBanTable();
        var strings = new HashMap<Long, List<Long>>();
        strings.put(1L, List.of(1L, 2L, 3L));
        strings.put(2L, List.of(10L, 20L, 30L));
        strings.put(3L, List.of(100L, 200L, 300L));
        Assertions.assertNotNull(banTable);
        Assertions.assertArrayEquals(new HashMap[]{banTable}, new HashMap[]{strings});
    }
}