<template>
  <div class="app-container">
    <el-form ref="queryParam" :model="queryParam" :inline="true">
      <el-form-item label="学科: ">
        <el-select v-model="queryParam.params.subjectId" clearable>
          <el-option
            v-for="item in subjectFilter"
            :key="item.id"
            :value="item.id"
            :label="item.name"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <router-link :to="{ path: '/paper/edit' }" class="link-left">
          <el-button type="primary">添加</el-button>
        </router-link>
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
      <el-table-column prop="id" label="Id" width="90px" />
      <el-table-column
        prop="subjectId"
        label="学科"
        :formatter="subjectFormatter"
        width="120px"
      />
      <el-table-column prop="name" label="名称" />
      <el-table-column
        prop="createTime"
        label="创建时间"
        :formatter="timeFormatter"
        width="160px"
      />
      <el-table-column label="操作" align="center" width="160px">
        <template slot-scope="{ row }">
          <el-button
            size="mini"
            @click="
              $router.push({ path: '/paper/edit', query: { id: row.id } })
            "
            >编辑</el-button
          >
          <el-button size="mini" type="danger" @click="deletePaper(row)"
            >删除</el-button
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
import { pageList, del } from "@/api/paper";
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
          teacherId: 0,
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
      pageList(this.queryParam).then((res) => {
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
    deletePaper(row) {
      const that = this;
      del(row.id).then((res) => {
        if (res.code == 0) {
          that.search();
          that.$message.success("操作成功");
        } else {
          that.$message.error("操作失败");
        }
      });
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
