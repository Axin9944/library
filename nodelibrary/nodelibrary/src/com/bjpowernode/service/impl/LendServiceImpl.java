package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Lend;
import com.bjpowernode.dao.lendDao;
import com.bjpowernode.dao.impl.lendDaoImpl;
import com.bjpowernode.service.LendService;

import java.util.Collections;
import java.util.List;

public class LendServiceImpl implements LendService {
    private lendDao lendDao = new lendDaoImpl();
    /*
    *   查询所有的借书订单
    * */
    @Override
    public List<Lend> selectLend() {
        return lendDao.selectLend();
    }
}
