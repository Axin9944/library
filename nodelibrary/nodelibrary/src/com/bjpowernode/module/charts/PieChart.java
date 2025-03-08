package com.bjpowernode.module.charts;

import com.bjpowernode.service.BookService;
import com.bjpowernode.service.impl.BookServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author admin
 */
public class PieChart implements Initializable {

    @FXML
    private javafx.scene.chart.PieChart pieChart;

    private BookService bookService = new BookServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new javafx.scene.chart.PieChart.Data("计算机", 50),
                new javafx.scene.chart.PieChart.Data("文学", 12),
                new javafx.scene.chart.PieChart.Data("经济", 25),
                new javafx.scene.chart.PieChart.Data("管理", 10)
        );*/
        ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<String, Integer> statisticsBook = bookService.statisticsBook();
        for (Map.Entry<String, Integer> entry : statisticsBook.entrySet()) {
            pieChartData.add(new javafx.scene.chart.PieChart.Data(entry.getKey(), entry.getValue()));
        }

        pieChart.setData(pieChartData);
        pieChart.setClockwise(false);
    }
}
