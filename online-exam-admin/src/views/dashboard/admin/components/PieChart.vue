<!--
 * @Author: lz 2930507471@qq.com
 * @Date: 2023-05-19 21:14:25
 * @LastEditors: lz 2930507471@qq.com
 * @LastEditTime: 2023-06-01 08:13:52
 * @FilePath: \online-exam-admin\src\views\dashboard\admin\components\PieChart.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div :class="className" :style="{ height: height, width: width }" />
</template>

<script>
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
import resize from "./mixins/resize";
import { findByDate } from "@/api/student";

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart",
    },
    width: {
      type: String,
      default: "100%",
    },
    height: {
      type: String,
      default: "300px",
    },
  },
  data() {
    return {
      chart: null,
      datam: [],
    };
  },
  mounted() {
    findByDate(localStorage.getItem("teacherId")).then((res) => {
      for (let i = 0; i < res.data.data.length; i++) {
        var data = {
          value: res.data.data[i].num,
          name: res.data.data[i].yearMonth,
        };
        this.datam.push(data);
      }
      this.initChart();
      // console.log(this.datam);
    });

    this.$nextTick(() => {
      this.initChart();
    });
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, "macarons");

      this.chart.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
        legend: {
          left: "center",
          bottom: "10",
        },
        series: [
          {
            name: "每月发布题目数",
            type: "pie",
            roseType: "radius",
            radius: [15, 95],
            center: ["50%", "38%"],
            data: this.datam,
            animationEasing: "cubicInOut",
            animationDuration: 2600,
          },
        ],
      });
    },
  },
};
</script>
