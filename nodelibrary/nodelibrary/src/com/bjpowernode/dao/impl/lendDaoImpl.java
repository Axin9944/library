package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.dao.lendDao;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;

public class lendDaoImpl implements lendDao {
    /*
    *  获取所有借书订单
    * */
    @Override
    public List<Lend> selectLend() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.Lend_PATH))){
            List<Lend> lendList = (List<Lend>) ois.readObject();
            return lendList;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
