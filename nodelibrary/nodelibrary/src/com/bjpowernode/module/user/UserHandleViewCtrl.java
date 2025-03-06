package com.bjpowernode.module.user;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.User;
import com.bjpowernode.global.util.Alerts;
import com.bjpowernode.service.UserService;
import com.bjpowernode.service.impl.UserServiceImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.UUID;


public class UserHandleViewCtrl {

    @FXML
    private TextField userIdField;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField moneyField;

    private Stage stage;

    private TableView<User> userTableView;

    private ObservableList<User> users;

    //修改的user对象
    private User user;

    // UserService对象
    private UserService userService = new UserServiceImpl();

    /*
        添加或修改数据
     */
    @FXML
    private void addOrEditUser() {
        try {
            String id = userIdField.getText();
            if ("".equals(id) || null == id) {
                // 生成UUID
                String uuid = UUID.randomUUID().toString();
                //添加操作
                User user = new User();
                // 设置id
                user.setId(uuid);
                populate(user);
                //设置状态为正常
                user.setStatus(Constant.USER_OK);
                // 设置是否借过书
                user.setLend(false);
                // 添加用户至文件
                userService.addUser(user);
                // 添加用户至内存
                users.add(user);
            }else {
                //修改操作
                populate(this.user);
                // 将修改保存至文件
                userService.updateUser(user);
                //刷新
                userTableView.refresh();
            }

            stage.close();
            Alerts.success("成功", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.error("失败","操作失败");
        }

    }

    private void populate(User user) {

        user.setMoney(new BigDecimal(moneyField.getText()));
        user.setName(userNameField.getText());
        // user.setId(id);
    }

    @FXML
    private void closeView() {
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            //填充修改界面的值
            userNameField.setText(user.getName());
            userIdField.setText(String.valueOf(user.getId()));
            moneyField.setText(user.getMoney().toString());
        }

    }

    public TableView<User> getUserTableView() {
        return userTableView;
    }

    public void setUserTableView(TableView<User> userTableView) {
        this.userTableView = userTableView;
    }
}
