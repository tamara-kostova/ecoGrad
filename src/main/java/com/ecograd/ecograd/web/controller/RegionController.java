package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.LitterType;
import com.ecograd.ecograd.model.Region;
import com.ecograd.ecograd.service.LitterService;
import com.ecograd.ecograd.service.RegionService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/statistic")
public class RegionController {
    private final RegionService regionService;
    private final LitterService litterService;

    public RegionController(RegionService regionService, LitterService litterService) {
        this.regionService = regionService;
        this.litterService = litterService;
    }

    @GetMapping
    public String getStatisticPage(Model model){
        List<Region> regionList = regionService.findAll();
        CategoryDataset datasetForAllRegions = createDatasetAllRegions(regionList);
        JFreeChart chart = ChartFactory.createBarChart(
                "By Region",         // chart title
                "Region",             // domain axis label
                "Value",                // range axis label
                datasetForAllRegions,                // data
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);


        // Customizing the domain axis (X-axis)
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90); // Rotate labels for better readability
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Customizing the range axis (Y-axis)
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new NumberTickUnit(10)); // Set tick unit
        rangeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Customizing the bar renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189)); // Set bar color
        renderer.setItemMargin(0.1); // Adjust space between bars

        // Set additional plot insets for better appearance
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

        CategoryDataset datasetForDays = createDatasetByDays(litterService.findAll());
        JFreeChart chartDays = ChartFactory.createBarChart(
                "By Days",         // chart title
                "Region",             // domain axis label
                "Value",                // range axis label
                datasetForDays,                // data
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        CategoryPlot plot1 = chartDays.getCategoryPlot();
        plot1.setBackgroundPaint(Color.WHITE);
        plot1.setDomainGridlinePaint(Color.GRAY);
        plot1.setRangeGridlinePaint(Color.GRAY);


        // Customizing the domain axis (X-axis)
        CategoryAxis domainAxis1 = plot1.getDomainAxis();
        domainAxis1.setCategoryLabelPositions(CategoryLabelPositions.UP_90); // Rotate labels for better readability
        domainAxis1.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Customizing the range axis (Y-axis)
        NumberAxis rangeAxis1 = (NumberAxis) plot1.getRangeAxis();
        rangeAxis1.setTickUnit(new NumberTickUnit(10)); // Set tick unit
        rangeAxis1.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Customizing the bar renderer
        BarRenderer renderer1 = (BarRenderer) plot.getRenderer();
        renderer1.setSeriesPaint(0, new Color(79, 129, 189)); // Set bar color
        renderer1.setItemMargin(0.1); // Adjust space between bars

        // Set additional plot insets for better appearance
        plot1.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        byte[] chartImageRegion = convertChartToImage(chart);
        byte[] chartImageDays = convertChartToImage(chartDays);
        String chartImageDaysString = encodeChartImage(chartImageDays);
        String chartImageReg = encodeChartImage(chartImageRegion);
        CategoryDataset datasetForLitterType = createDatasetByLitterType(litterService.findAll());
        JFreeChart chartLitterType = ChartFactory.createBarChart(
                "By Litter Type",         // chart title
                "Type",             // domain axis label
                "Value",                // range axis label
                datasetForLitterType,                // data
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        CategoryPlot plot2 = chartLitterType.getCategoryPlot();
        plot2.setBackgroundPaint(Color.WHITE);
        plot2.setDomainGridlinePaint(Color.GRAY);
        plot2.setRangeGridlinePaint(Color.GRAY);
        CategoryAxis domainAxis2 = plot2.getDomainAxis();
        domainAxis2.setCategoryLabelPositions(CategoryLabelPositions.UP_90); // Rotate labels for better readability
        domainAxis2.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        NumberAxis rangeAxis2 = (NumberAxis) plot2.getRangeAxis();
        rangeAxis2.setTickUnit(new NumberTickUnit(10)); // Set tick unit
        rangeAxis2.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();
        renderer2.setSeriesPaint(0, new Color(79, 129, 189)); // Set bar color
        renderer2.setItemMargin(0.1);
        plot2.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        byte[] chartImageLitterType = convertChartToImage(chartLitterType);
        String chartLitterTypeImageString = encodeChartImage(chartImageLitterType);
        model.addAttribute("chartImageForLitterType", chartLitterTypeImageString);
        model.addAttribute("chartImageForAllRegions", chartImageReg);
        model.addAttribute("chartImageForDays", chartImageDaysString);
        return "regions";
    }

    private CategoryDataset createDatasetAllRegions(List<Region> regionList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Region r : regionList) {
            List<Litter> litterList = litterService.findAll();
            List<Litter> tmp = new ArrayList<>();
            for (Litter l : litterList){
                if (l.getRegion().equals(r)) tmp.add(l);
            }
            dataset.addValue(tmp.size(), r.getName(), r.getName());
        }
        return dataset;
    }
    private CategoryDataset createDatasetByLitterType(List<Litter> litterList){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Litter> plastic = new ArrayList<>();
        List<Litter> glass = new ArrayList<>();
        List<Litter> paper = new ArrayList<>();
        List<Litter> other = new ArrayList<>();
        for (Litter l : litterList){
            if (l.getLitterType()== LitterType.GLASS){
                glass.add(l);
            }
            if (l.getLitterType()== LitterType.PLASTIC){
                plastic.add(l);
            }
            if (l.getLitterType()== LitterType.PAPER){
                paper.add(l);
            }
            if (l.getLitterType()== LitterType.OTHER){
                other.add(l);
            }
        }
        dataset.addValue(plastic.size(), "Plastic", "Plastic");
        dataset.addValue(glass.size(), "Glass", "Glass");
        dataset.addValue(other.size(), "Other", "Other");
        dataset.addValue(paper.size(), "Paper", "Paper");
        return dataset;
    }
    private CategoryDataset createDatasetByDays(List<Litter> litterList){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Litter> monday = new ArrayList<>();
        List<Litter> tuesday = new ArrayList<>();
        List<Litter> wednesday = new ArrayList<>();
        List<Litter> thursday = new ArrayList<>();
        List<Litter> friday = new ArrayList<>();
        List<Litter> saturday = new ArrayList<>();
        List<Litter> sunday = new ArrayList<>();
        for (Litter l: litterList){
            if (l.getDateReported().getDayOfWeek().getValue()==1){
                monday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==2){
                tuesday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==3){
                wednesday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==4){
                thursday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==5){
                friday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==6){
                saturday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==7){
                sunday.add(l);
            }
        }
        dataset.addValue(monday.size(), "Monday", "Monday");
        dataset.addValue(tuesday.size(), "Tuesday", "Tuesday");
        dataset.addValue(wednesday.size(), "Wednesday", "Wednesday");
        dataset.addValue(thursday.size(), "Thursday", "Thursday");
        dataset.addValue(friday.size(), "Friday", "Friday");
        dataset.addValue(saturday.size(), "Saturday", "Saturday");
        dataset.addValue(sunday.size(), "Sunday", "Sunday");
        return dataset;
    }
    private byte[] convertChartToImage(JFreeChart chart) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ChartUtils.writeChartAsPNG(outputStream, chart, 400, 300);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String encodeChartImage(byte[] chartImage) {
        return new String(Base64.getEncoder().encode(chartImage));
    }
}
