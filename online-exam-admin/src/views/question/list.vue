<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
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
      <el-form-item label="题型">
        <el-select v-model="queryParam.params.questionType" clearable>
          <el-option
            v-for="item in questionType"
            :key="item.key"
            :value="item.key"
            :label="item.value"
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
    >
      <el-table-column prop="id" label="Id" width="90px" />
      <el-table-column
        prop="subjectId"
        label="学科"
        :formatter="subjectFormatter"
        width="120px"
      />
      <el-table-column
        prop="questionType"
        label="题型"
        :formatter="questionTypeFormatter"
        width="70px"
      />
      <el-table-column
        prop="questionObject.titleContent"
        label="题干"
        show-overflow-tooltip
      />
      <el-table-column prop="score" label="分数" width="60px" />
      <el-table-column prop="difficult" label="难度" width="60px" />
      <el-table-column
        prop="createTime"
        label="创建时间"
        width="160px"
        :formatter="timeFormatter"
      />
      <el-table-column label="操作" align="center" width="220px">
        <template slot-scope="{ row }">
          <el-button size="mini" @click="showQuestion(row)">预览</el-button>
          <el-button size="mini" @click="editQuestion(row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteQuestion(row)"
            class="link-left"
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
    <el-dialog
      :visible.sync="questionShow.dialog"
      style="width: 100%; height: 100%"
    >
      <QuestionShow
        :qType="questionShow.qType"
        :question="questionShow.question"
        :qLoading="questionShow.loading"
      />
    </el-dialog>
  </div>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";
import { pageList, selectById, del } from "@/api/question";
import { timeFormat } from "@/utils/util";
import QuestionShow from "./components/Show";
import Pagination from "@/components/Pagination";
export default {
  name: "list",
  components: {
    QuestionShow,
    Pagination,
  },
  data() {
    return {
      queryParam: {
        params: {
          teacherId: 2,
        },
        pageNum: 1,
        pageSize: 10,
      },
      listLoading: false,
      tableData: [],
      questionShow: {
        qType: 0,
        dialog: false,
        question: null,
        loading: false,
      },
      total: 0,
    };
  },
  created() {
    this.initSubject();
    this.search();
  },
  methods: {
    search() {
      let that = this;
      this.listLoading = true;
      if (this.queryParam.params.questionType == "") {
        this.queryParam.params.questionType = null;
      }
      if (this.queryParam.params.subjectId == "") {
        this.queryParam.params.subjectId = null;
      }
      pageList(this.queryParam).then((res) => {
        that.tableData = res.data.content;
        that.listLoading = false;
        that.total = res.data.totalSize;
        that.queryParam.pageNum = res.data.pageNum;
      });
    },
    submitForm() {
      this.queryParam.pageNum = 1;
      this.search();
    },
    subjectFormatter(row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue);
    },
    questionTypeFormatter(row, column, cellValue, index) {
      return this.enumFormat(this.questionType, cellValue);
    },
    ...mapActions("exam", { initSubject: "initSubject" }),
    timeFormatter(row, column, cellValue, index) {
      return timeFormat(cellValue);
    },
    showQuestion(row) {
      let that = this;
      this.questionShow.loading = true;
      this.questionShow.qType = row.questionType;
      selectById(row.id).then((res) => {
        this.questionShow.dialog = true;
        that.questionShow.question = res.data;
        that.questionShow.loading = false;
      });
    },
    editQuestion(row) {
      let url = this.enumFormat(this.editUrlEnum, row.questionType);
      this.$router.push({ path: url, query: { id: row.id } });
    },
    deleteQuestion(row) {
      let that = this;
      del(row.id).then((res) => {
        if (res.code == 0) {
          that.search();
          that.$message.success("操作成功");
        } else {
          that.$message.error("操作失败");
        }
      });
    },
  },
  computed: {
    ...mapState("exam", { subjectFilter: (state) => state.subjects }),
    ...mapGetters("exam", ["subjectEnumFormat"]),
    ...mapGetters("enumItem", ["enumFormat"]),
    ...mapState("enumItem", {
      questionType: (state) => state.exam.question.typeEnum,
      editUrlEnum: (state) => state.exam.question.editUrlEnum,
    }),
  },
};
</script>

<style lang="scss" scoped></style>
