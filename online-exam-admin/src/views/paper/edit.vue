<template>
  <div class="app-container">
    <el-form
      ref="form"
      v-loading="formLoading"
      :model="form"
      label-width="100px"
      :rules="rules"
    >
      <el-form-item label="学科: " prop="subjectId" required>
        <el-select v-model="form.subjectId" placeholder="学科">
          <el-option
            v-for="item in subjectFilter"
            :key="item.id"
            :value="item.id"
            :label="item.name"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="试卷类型: " prop="paperType" required>
        <el-select v-model="form.paperType" placeholder="试卷类型">
          <el-option
            v-for="item in paperTypeEnum"
            :key="item.key"
            :value="item.key"
            :label="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item v-show="form.paperType === 2" label="时间限制: " required>
        <el-date-picker
          v-model="form.limitDateTime"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="datetimerange"
          range-separator="至"
          start-placeholde="开始日期"
          end-placeholde="结束日期"
        />
      </el-form-item>
      <el-form-item label="试卷名称: " required prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item
        v-for="(titleItem, index) in form.titleItems"
        :key="index"
        required
        :label="'标题' + (index + 1) + ': '"
      >
        <el-input v-model="titleItem.name" style="width: 80%" />
        <el-button
          type="success"
          class="link-left"
          style="margin-left: 20px"
          @click="addQuestion(titleItem)"
          >添加题目</el-button
        >
        <el-button
          type="danger"
          class="link-left"
          @click="form.titleItems.splice(index, 1)"
          >删除</el-button
        >
        <el-card
          v-if="titleItem.questionItems.length != 0"
          class="exampaper-item-box"
        >
          <el-form-item
            v-for="(questionItem, questionIndex) in titleItem.questionItems"
            :key="questionIndex"
            :label="'题目' + (questionIndex + 1) + ': '"
            style="margin-bottom: 15px"
          >
            <el-row>
              <el-col :span="23">
                <QuestionShow
                  :q-type="questionItem.questionType"
                  :question="questionItem"
                />
              </el-col>
              <el-col :span="1">
                <el-button
                  type="danger"
                  size="small"
                  @click="titleItem.questionItems.splice(questionIndex, 1)"
                  >删除</el-button
                >
              </el-col>
            </el-row>
          </el-form-item>
        </el-card>
      </el-form-item>
      <el-form-item label="建议时长: " prop="suggestTime" required>
        <el-input v-model="form.suggestTime" placeholder="分钟" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button type="success" @click="addTitle">添加标题</el-button>
      </el-form-item>
    </el-form>
    <el-dialog :visible.sync="questionPage.showDialog" width="70%" top="5vh">
      <el-form ref="queryForm" :model="questionPage.queryParam" :inline="true">
        <el-form-item label="题型: ">
          <el-select
            v-model="questionPage.queryParam.params.questionType"
            clearable
          >
            <el-option
              v-for="item in questionTypeEnum"
              :key="item.key"
              :value="item.key"
              :label="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryForm">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table
        v-loading="questionPage.listLoading"
        :data="questionPage.tableData"
        border
        fit
        highlight-current-row
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="35" />
        <el-table-column prop="id" label="Id" width="60px" />
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
      </el-table>
      <pagination
        v-show="questionPage.total > 0"
        :total="questionPage.total"
        :page.sync="questionPage.queryParam.pageNum"
        :limit.sync="questionPage.queryParam.pageSize"
        @pagination="search"
      />
      <span slot="footer" class="dialog-footer">
        <el-button @click="questionPage.showDialog = false">取消</el-button>
        <el-button type="primary" @click="confirmQuestionSelect"
          >确定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from "vuex";
import QuestionShow from "../question/components/Show";
import Pagination from "@/components/Pagination";
import { pageList, selectById } from "@/api/question";
import { edit, select } from "@/api/paper";
export default {
  components: {
    QuestionShow,
    Pagination,
  },
  data() {
    return {
      form: {
        userId: 2,
        id: null,
        subjectId: null,
        paperType: null,
        limitDateTime: [],
        name: "",
        titleItems: [],
      },
      formLoading: false,
      rules: {
        subjectId: [
          { required: true, message: "请选择学科", trigger: "change" },
        ],
        paperType: [
          { required: true, message: "请选择试卷类型", trigger: "change" },
        ],
        name: [{ required: true, message: "请输入试卷名称", trigger: "blur" }],
        suggestTime: [
          { required: true, message: "请输入建议时长", trigger: "blur" },
        ],
      },
      subjectFilter: null,
      currentTitleItem: null,
      questionPage: {
        multipleSelection: [],
        showDialog: false,
        queryParam: {
          params: {
            teacherId: 2,
            questionType: "",
          },
          pageNum: 1,
          pageSize: 5,
        },
        listLoading: false,
        tableData: [],
        total: 0,
      },
    };
  },
  computed: {
    ...mapState("enumItem", {
      paperTypeEnum: (state) => state.exam.examPaper.paperTypeEnum,
      questionTypeEnum: (state) => state.exam.question.typeEnum,
    }),
    ...mapState("exam", { subjects: (state) => state.subjects }),
    ...mapGetters("enumItem", ["enumFormat"]),
  },
  created() {
    const id = this.$route.query.id;
    const that = this;
    this.initSubject(function () {
      that.subjectFilter = that.subjects;
    });
    if (id && parseInt(id) !== 0) {
      that.formLoading = true;
      select(id).then((res) => {
        that.form = res.data;
        that.formLoading = false;
      });
    }
  },
  methods: {
    addQuestion(titleItem) {
      this.currentTitleItem = titleItem;
      this.questionPage.showDialog = true;
      this.search();
    },
    submitForm() {
      const that = this;
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true;
          edit(this.form)
            .then((res) => {
              if (res.code == 0) {
                that.$message.success("操作成功");
                that.delCurrentView(that).then(() => {
                  that.$router.push("/paper/list");
                });
              } else {
                that.$message.error("操作失败");
                this.formLoading = false;
              }
            })
            .catch((e) => {
              this.formLoading = false;
            });
        } else {
          return false;
        }
      });
    },
    resetForm() {
      this.form = {
        userId: 2,
        id: null,
        subjectId: null,
        paperType: null,
        limitDateTime: [],
        name: "",
        titleItems: [],
      };
    },
    addTitle() {
      this.form.titleItems.push({
        name: "",
        questionItems: [],
      });
    },
    queryForm() {
      this.search();
    },
    handleSelectionChange(val) {
      this.questionPage.multipleSelection = val;
    },
    confirmQuestionSelect() {
      const that = this;
      this.questionPage.multipleSelection.forEach((q) => {
        selectById(q.id).then((res) => {
          that.currentTitleItem.questionItems.push(res.data);
        });
      });
      this.questionPage.showDialog = false;
    },
    search() {
      this.questionPage.queryParam.params.subjectId = this.form.subjectId;
      this.questionPage.listLoading = true;
      if (this.questionPage.queryParam.params.questionType == "") {
        this.questionPage.queryParam.params.questionType = null;
      }
      pageList(this.questionPage.queryParam).then((res) => {
        this.questionPage.tableData = res.data.content;
        this.questionPage.total = res.data.totalSize;
        this.questionPage.queryParam.pageNum = res.data.pageNum;
        this.questionPage.listLoading = false;
      });
    },
    questionTypeFormatter(row, column, cellValue, index) {
      return this.enumFormat(this.questionTypeEnum, cellValue);
    },
    ...mapActions("exam", { initSubject: "initSubject" }),
    ...mapActions("tagsView", { delCurrentView: "delCurrentView" }),
  },
};
</script>

<style lang="scss" scoped>
.link-left {
  margin-left: 5px;
}

.exampaper-item-box {
  margin-top: 20px;
}
</style>
