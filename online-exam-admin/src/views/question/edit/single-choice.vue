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
      <el-form-item label="选项: " required>
        <el-form-item
          v-for="(item, index) in form.items"
          :label="item.prefix"
          :key="item.prefix"
          label-width="50px"
          class="question-item-label"
        >
          <el-input v-model="item.prefix" style="width: 50px"></el-input>
          <el-input
            v-model="item.content"
            @focus="inputClick(item, 'content')"
            class="question-item-content-input"
          ></el-input>
          <el-button
            type="danger"
            size="mini"
            class="question-item-remove"
            icon="el-icon-delete"
            @click="questionItemRemove(index)"
          ></el-button>
        </el-form-item>
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
      <el-form-item label="正确答案: " prop="correct" required>
        <el-radio-group v-model="form.correct">
          <el-radio
            v-for="item in form.items"
            :key="item.prefix"
            :label="item.prefix"
            >{{ item.prefix }}</el-radio
          >
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button type="success" @click="questionItemAdd">添加选项</el-button>
        <el-button type="success" @click="showQuestion">预览</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "single-choice",
  data() {
    return {
      form: {
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
      },
      formLoading: false,
      subjectFilter: [
        {
          id: 1,
          name: "语文",
        },
        {
          id: 2,
          name: "数学",
        },
      ],
      rules: {
        subjectId: [
          { required: true, message: "请选择学科", trigger: "change" },
        ],
        title: [{ required: true, message: "请输入题干", trigger: "blur" }],
        analyze: [{ required: true, message: "请输入解析", trigger: "blur" }],
        score: [{ required: true, message: "请输入分数", trigger: "blur" }],
        correct: [
          { required: true, message: "请选择正确答案", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    inputClick(object, parameterName) {

    },
    submitForm() {},
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
    showQuestion() {},
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
