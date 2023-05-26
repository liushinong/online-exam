package com.exam.onlineexamapi.domain.DO.user;

import javafx.scene.chart.PieChart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDate {
    private String yearMonth;
    private Integer num;
}
