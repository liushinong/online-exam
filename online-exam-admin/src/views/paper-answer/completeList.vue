<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryParam" :inline="true">
      <el-form-item label="学科: ">
        <el-select v-model="queryParam.params.subjectId" clearable>
          <el-option
            v-for="item in subjectFilter"
            :key="item.id"
            :value="item.id"
            :label="item.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="tableData"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="Id" width="90px"></el-table-column>
      <el-table-column
        prop="subjectId"
        label="学科"
        :formatter="subjectFormatter"
        width="120px"
      ></el-table-column>
      <el-table-column
        prop="createUser"
        label="用户id"
        width="90px"
      ></el-table-column>
      <el-table-column prop="paperName" label="试卷名称"></el-table-column>
      <el-table-column prop="userScore" label="用户得分"></el-table-column>
      <!-- <el-table-column prop="systemScore" label="系统得分"></el-table-column> -->
      <el-table-column
        prop="createTime"
        label="提交时间"
        :formatter="timeFormatter"
        width="160px"
      ></el-table-column>
      <el-table-column label="操作" align="center" width="100px">
        <template slot-scope="{ row }">
          <el-button
            size="mini"
            type="primary"
            @click="$router.push({ path: '/editPaper', query: { id: row.id } })"
            >查看</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParam.pageNum"
      :limit.sync="queryParam.pageSize"
      @pagination="search"
    />
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from "vuex";
import Pagination from "@/components/Pagination";
import { timeFormat } from "@/utils/util";
import { pageListComplete } from "@/api/paper-answer";
export default {
  components: {
    Pagination,
  },
  data() {
    return {
      queryParam: {
        pageNum: 1,
        pageSize: 10,
        params: {
          teacherId: 2,
        },
      },
      listLoading: false,
      tableData: [],
      total: 0,
    };
  },
  created() {
    this.queryParam.params.teacherId = parseInt(
      localStorage.getItem("teacherId")
    );
    this.initSubject();
    this.search();
  },
  methods: {
    search() {
      if (this.queryParam.params.subjectId == "") {
        this.queryParam.params.subjectId = null;
      }
      this.listLoading = true;
      pageListComplete(this.queryParam).then((res) => {
        this.tableData = res.data.content;
        this.listLoading = false;
        this.total = res.data.totalSize;
        this.queryParam.pageNum = res.data.pageNum;
      });
    },
    submitForm() {
      this.queryParam.pageNum = 1;
      this.search();
    },
    subjectFormatter(row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue);
    },
    timeFormatter(row, column, cellValue, index) {
      return timeFormat(cellValue);
    },
    ...mapActions("exam", { initSubject: "initSubject" }),
  },
  computed: {
    ...mapGetters("exam", ["subjectEnumFormat"]),
    ...mapState("exam", { subjectFilter: (state) => state.subjects }),
  },
};
</script>

<style lang="scss" scoped>
.link-left {
  margin-left: 20px;
}
</style>
