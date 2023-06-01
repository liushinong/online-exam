<!--
 * @Author: lz 2930507471@qq.com
 * @Date: 2023-05-26 11:19:09
 * @LastEditors: lz 2930507471@qq.com
 * @LastEditTime: 2023-06-01 08:20:38
 * @FilePath: \online-exam-admin\src\views\dashboard\admin\index.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="dashboard-editor-container">
    <github-corner class="github-corner" />

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-form
      style="background: #fff; padding: 16px 16px 0; margin-bottom: 32px"
      inline="true"
    >
      <el-form-item> <pie-chart></pie-chart></el-form-item>

      <el-form-item><line-chart /></el-form-item>
    </el-form>
  </div>
</template>

<script>
import GithubCorner from "@/components/GithubCorner";
import PanelGroup from "./components/PanelGroup";
import LineChart from "./components/LineChart";
import PieChart from "./components/PieChart";

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145],
  },
};

export default {
  name: "DashboardAdmin",
  components: {
    GithubCorner,
    PanelGroup,
    LineChart,
    PieChart,
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis,
    };
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type];
    },
  },
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
