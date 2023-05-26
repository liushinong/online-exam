<template>
  <el-container>
    <el-aside width="270px">
      <el-card class="box-card" shadow="never">
        <div slot="header">
          {{ form.name }}
        </div>
        <span :key="item.itemOrder" v-for="item in answer.answerItems">
          <el-tag
            :type="questionDoRightTag(item.doRight)"
            class="do-exam-title-tag"
            @click="goAnchor('#question-' + item.itemOrder)"
            >{{ item.itemOrder }}</el-tag
          >
        </span>
      </el-card>
      <el-button style="margin: 0 auto" type="primary" @click="submitForm"
        >提交批改</el-button
      >
    </el-aside>
    <el-main>
      <el-form :model="form" ref="form" v-loading="formLoading">
        <div v-for="(titleItem, index) in form.titleItems" :key="index">
          <el-card
            shadow="never"
            class="box-card"
            v-if="titleItem.questionItems.length != 0"
          >
            <div slot="header" class="card-header">
              {{ titleItem.name }}
            </div>
            <el-form-item
              v-for="questionItem in titleItem.questionItems"
              :key="questionItem.itemOrder"
              :label="questionItem.itemOrder + '.'"
              label-width="50px"
              :id="'question-' + questionItem.itemOrder"
            >
              <QuestionAnswerShow
                :qType="questionItem.questionType"
                :question="questionItem"
                :answer="answer.answerItems[questionItem.itemOrder - 1]"
              />
              <el-row
                style="margin-top: 10px"
                v-if="
                  answer.answerItems[questionItem.itemOrder - 1].doRight ===
                  null
                "
              >
                <label style="color: #e6a23c">批改：</label>
                <el-input-number
                  v-model="answer.answerItems[questionItem.itemOrder - 1].score"
                  :precision="1"
                  :step="1"
                  :max="parseInt(questionItem.score)"
                ></el-input-number>
              </el-row>
            </el-form-item>
          </el-card>
        </div>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import QuestionAnswerShow from "./components/QuestionAnswerShow";
import { formatSeconds } from "@/utils";
import { edit, read } from "@/api/paper-answer";
import { mapGetters, mapState } from "vuex";
export default {
  components: {
    QuestionAnswerShow,
  },
  data() {
    return {
      form: {},
      formLoading: false,
      answer: {
        id: null,
        score: 0,
        doTime: 0,
        answerItems: [],
        doRight: false,
      },
    };
  },
  created() {
    let id = this.$route.query.id;
    let _this = this;
    if (id && parseInt(id) !== 0) {
      _this.formLoading = true;
      read(id).then((res) => {
        _this.form = res.data.paper;
        _this.answer = res.data.answer;
        _this.formLoading = false;
      });
    }
  },
  methods: {
    submitForm() {
      let _this = this;
      _this.formLoading = true;
      edit(this.answer)
        .then((res) => {
          console.log(res);
          if (res.code === 0) {
            _this.$message.success("批改成功");
            _this.$router.push("/checkList");
          } else {
            _this.$message.error(res.msg);
          }
          _this.formLoading = false;
        })
        .catch((e) => {
          _this.formLoading = false;
        });
    },
    scoreSelect(score) {
      let array = [];
      for (let i = 0; i <= parseInt(score); i++) {
        array.push(i.toString());
      }
      if (score.indexOf(".") !== -1) {
        array.push(score);
      }
      return array;
    },
    formatSeconds(theTime) {
      return formatSeconds(theTime);
    },
    questionDoRightTag(status) {
      return this.enumFormat(this.doRightTag, status);
    },
    goAnchor(selector) {
      this.$el.querySelector(selector).scrollIntoView({
        behavior: "instant",
        block: "center",
        inline: "nearest",
      });
    },
  },
  computed: {
    ...mapGetters("enumItem", ["enumFormat"]),
    ...mapState("enumItem", {
      doRightTag: (state) => state.exam.question.answer.doRightTag,
    }),
  },
};
</script>

<style lang="scss" scoped>
.el-container {
  padding: 20px 90px;
  background: #eff3f7;
}

.el-aside {
  height: 100%;
  // position: fixed;
  // z-index: 999;
  background: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;

  .box-card {
    margin-bottom: 20px;
  }
  .el-card ::v-deep .el-card__header {
    // padding: 18px 20px;
    height: 60px;
    background-color: #fafafa;
    text-align: center;
  }

  .do-exam-title-tag {
    margin-left: 5px;
    cursor: pointer;
  }

  .bottom {
    margin-top: 10px;
  }
}

.el-main {
  padding: 0 20px;

  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .el-card ::v-deep .el-card__header {
    padding: 18px 20px;
    height: 60px;
    background-color: #fafafa;
    text-align: left;
  }
}
</style>
