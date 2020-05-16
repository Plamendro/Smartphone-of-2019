/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "chartView")
@RequestScoped
public class ChartView implements Serializable {

    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private BarChartModel initBarModel() {

        //reading from csv file
        String csvFile = "D:\\CustomerData.csv";

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<String> records = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                records.addAll(Arrays.asList(values));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String onePlus7 = "OnePlus 7T";
        String iphone11 = "Apple iPhone 11 Pro";
        String samsung10 = "Samsung S10+";
        String huawei30 = "Huawei Mate 30 Pro";
        String xiaomi10 = "Xiaomi Mi Note 10 Pro";

        //data for diagram
        int numberIphone = 0;
        int numberOnePlus = 0;
        int numberSamsung = 0;
        int numberHuawei = 0;
        int numberXiaomi = 0;

        for (String record : records) {
            System.out.println(record);
            if (record.contains(iphone11)) {
                numberIphone++;
                System.out.println(numberIphone);
            }
            if (record.contains(onePlus7)) {
                numberOnePlus++;
                System.out.println(numberOnePlus);
            }
            if (record.contains(samsung10)) {
                numberSamsung++;
                System.out.println(numberSamsung);
            }
            if (record.contains(huawei30)) {
                numberHuawei++;
                System.out.println(numberHuawei);
            }
            if (record.contains(xiaomi10)) {
                numberXiaomi++;
                System.out.println(numberXiaomi);
            }
        }

        BarChartModel model = new BarChartModel();

        ChartSeries apple = new ChartSeries();
        apple.setLabel("Apple");
        apple.set("2019", numberIphone);

        ChartSeries samsung = new ChartSeries();
        samsung.setLabel("Samsung");
        samsung.set("2019", numberSamsung);

        ChartSeries huawei = new ChartSeries();
        huawei.setLabel("Huawei");
        huawei.set("2019", numberHuawei);

        ChartSeries onePlus = new ChartSeries();
        onePlus.setLabel("OnePlus");
        onePlus.set("2019", numberOnePlus);

        ChartSeries xiaomi = new ChartSeries();
        xiaomi.setLabel("Xiaomi");
        xiaomi.set("2019", numberXiaomi);

        model.addSeries(apple);
        model.addSeries(xiaomi);
        model.addSeries(samsung);
        model.addSeries(onePlus);
        model.addSeries(huawei);

        return model;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Voting Chart");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Top 5 smartphones");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number of votes");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
}
