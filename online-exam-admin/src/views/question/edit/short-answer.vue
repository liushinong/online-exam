<template>
  <div class="app-container">
    <el-form
      :model="form"
      ref="form"
      label-width="100px"
      v-loading="formLoading"
      :rules="rules"
    >
      <el-form-item label="学科: " prop="subjectId" required>
        <el-select v-model="form.subjectId" placeholder="学科">
          <el-option
            v-for="item in subjectFilter"
            :key="item.id"
            :value="item.id"
            :label="item.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="题干: " prop="title" required>
        <el-input
          v-model="form.title"
          @focus="inputClick(form, 'title')"
        ></el-input>
      </el-form-item>
      <el-form-item label="答案: " prop="correct" required>
        <el-input v-model="form.correct" @focus="inputClick(form, 'correct')" />
      </el-form-item>
      <el-form-item label="解析: " prop="analyze" required>
        <el-input
          v-model="form.analyze"
          @focus="inputClick(form, 'analyze')"
        ></el-input>
      </el-form-item>
      <el-form-item label="分数: " prop="score" required>
        <el-input-number
          v-model="form.score"
          :precision="1"
          :step="1"
          :max="100"
        ></el-input-number>
      </el-form-item>
      <el-form-item label="难度: " required>
        <el-rate v-model="form.difficult" class="question-item-rate"></el-rate>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button type="success" @click="questionItemAdd">添加选项</el-button>
        <el-button type="success" @click="showQuestion">预览</el-button>
      </el-form-item>
    </el-form>
    <el-dialog :visible.sync="richEditor.dialogVisible">
      <Ueditor @ready="editorReady"></Ueditor>
      <span slot="footer">
        <el-button type="primary" @click="editorConfirm">确定</el-button>
        <el-button @click="richEditor.dialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
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
import Ueditor from "@/components/Ueditor";
import QuestionShow from "../components/Show";
import { selectById, edit } from "@/api/question";
import { mapActions, mapState } from "vuex";
export default {
  name: "gap-filling",
  components: {
    Ueditor,
    QuestionShow,
  },
  data() {
    return {
      form: {
        id: null,
        userId: 2,
        questionType: 5,
        subjectId: null,
        title: "",
        items: [],
        analyze: "",
        correct: "",
        score: "",
        difficult: 0,
      },
      formLoading: false,
      rules: {
        subjectId: [
          { required: true, message: "请选择学科", trigger: "change" },
        ],
        title: [{ required: true, message: "请输入题干", trigger: "blur" }],
        correct: [{ required: true, message: "请输入答案", trigger: "blur" }],
        analyze: [{ required: true, message: "请输入解析", trigger: "blur" }],
        score: [{ required: true, message: "请输入分数", trigger: "blur" }],
      },
      richEditor: {
        dialogVisible: false,
        object: null,
        parameterName: "",
        instance: null,
      },
      questionShow: {
        qType: 0,
        dialog: false,
        question: null,
        loading: false,
      },
    };
  },
  created() {
    let that = this;
    this.initSubject();
    let id = this.$route.query.id;
    if (id && parseInt(id) !== 0) {
      that.formLoading = true;
      selectById(id).then((res) => {
        that.form = res.data;
        that.form.userId = 2;
        that.formLoading = false;
      });
    }
  },
  computed: {
    ...mapState("exam", { subjectFilter: (state) => state.subjects }),
  },
  methods: {
    editorReady(instance) {
      this.richEditor.instance = instance;
      let currentContent =
        this.richEditor.object[this.richEditor.parameterName];
      this.richEditor.instance.setContent(currentContent);
      // 光标定位到Ueditor
      this.richEditor.instance.focus(true);
    },
    inputClick(object, parameterName) {
      this.richEditor.object = object;
      this.richEditor.parameterName = parameterName;
      this.richEditor.dialogVisible = true;
    },
    submitForm() {
      let that = this;
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true;
          edit(this.form)
            .then((res) => {
              if (res.code == 0) {
                that.$message.success("操作成功");
                that.delCurrentView(that).then(() => {
                  that.$router.push("/list");
                });
              } else {
                that.$message.error("操作失败");
                this.formLoading = false;
              }
            })
            .catch((e) => {
              this.formLoading = false;
            });
        }
      });
    },
    resetForm() {
      this.form = {
        id: null,
        questionType: 1,
        subjectId: null,
        title: "",
        items: [
          { prefix: "A", content: "" },
          { prefix: "B", content: "" },
          { prefix: "C", content: "" },
          { prefix: "D", content: "" },
        ],
        analyze: "",
        correct: "",
        score: "",
        difficult: 0,
      };
    },
    questionItemAdd() {
      let items = this.form.items;
      let newLastPrefix;
      if (items.length > 0) {
        let last = items[items.length - 1];
        newLastPrefix = String.fromCharCode(last.prefix.charCodeAt() + 1);
      } else {
        newLastPrefix = "A";
      }
      items.push({ id: null, prefix: newLastPrefix, content: "" });
    },
    questionItemRemove(index) {
      this.form.items.splice(index, 1);
    },
    showQuestion() {
      this.questionShow.dialog = true;
      this.questionShow.qType = this.form.questionType;
      this.questionShow.question = this.form;
    },
    editorConfirm() {
      let content = this.richEditor.instance.getContent();
      this.richEditor.object[this.richEditor.parameterName] = content;
      this.richEditor.dialogVisible = false;
      this.richEditor.instance.setContent("");
    },
    ...mapActions("exam", { initSubject: "initSubject" }),
    ...mapActions("tagsView", { delCurrentView: "delCurrentView" }),
  },
};
</script>

<style lang="scss" scoped>
.question-item-label {
  margin-top: 10px;
  margin-bottom: 10px !important;
}

.question-item-remove {
  margin-left: 20px;
}

.question-item-content-input {
  margin-left: 8px;
  width: 60%;
}

.question-item-rate {
  line-height: 2.5;
}
</style>
