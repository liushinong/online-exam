<template>
    <el-container>
        <el-aside width="270px">
            <el-card class="box-card" shadow="never">
                <div slot="header" class="clearfix">
                    <h3>{{ form.name }}</h3>
                    <p>
                        <span>总分: {{ form.score }}</span
                        ><span>{{ form.suggestTime }}分钟</span>
                    </p>
                </div>
                <span :key="item.itemOrder" v-for="item in answer.answerItems">
                    <el-tag
                        :type="questionCompleted(item.completed)"
                        class="do-exam-title-tag"
                        @click="goAnchor('#question-' + item.itemOrder)"
                        >{{ item.itemOrder }}</el-tag
                    >
                </span>
                <div class="bottom">
                    <label>剩余时间: </label>
                    <label>{{ formatSeconds(remainTime) }}</label>
                </div>
            </el-card>
            <el-button
                style="margin-bottom: 20px"
                type="primary"
                @click="submitForm"
                >提交试卷</el-button
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
                            <QuestionEdit
                                :qType="questionItem.questionType"
                                :question="questionItem"
                                :answer="
                                    answer.answerItems[
                                        questionItem.itemOrder - 1
                                    ]
                                "
                            />
                        </el-form-item>
                    </el-card>
                </div>
            </el-form>
        </el-main>
    </el-container>
</template>

<script>
import QuestionEdit from '../components/QuestionEdit.vue'
import { select } from '@/api/examPaper'
import { formatSeconds } from '@/utils'
import { mapGetters, mapState } from 'vuex'
import { answerSubmit } from '@/api/examPaperAnswer'
export default {
    name: 'do',
    components: {
        QuestionEdit,
    },
    data() {
        return {
            form: {},
            answer: {
                userId: 3,
                questionId: null,
                doTime: 0,
                answerItems: [],
            },
            formLoading: false,
            remainTime: '',
            timer: null,
        }
    },
    created() {
        let id = this.$route.query.id
        let that = this
        if (id && parseInt(id) !== 0) {
            that.formLoading = true
            select(id).then((res) => {
                let data = res.data.data
                that.form = data
                that.remainTime = data.suggestTime * 60
                that.initAnswer()
                that.timeReduce()
                that.formLoading = false
            })
        }
    },
    beforeDestroy() {
        window.clearInterval(this.timer)
    },
    methods: {
        submitForm() {
            let that = this
            window.clearInterval(that.timer)
            that.formLoading = true
            answerSubmit(this.answer)
                .then((res) => {
                    if (res.data.code == 0) {
                        that.$alert(
                            '试卷得分：' + res.data.msg + '分',
                            '考试结果',
                            {
                                confirmButtonText: '返回考试记录',
                                callback: (action) => {
                                    that.$router.push('/record/index')
                                },
                            },
                        )
                    } else {
                        that.$message.error(res.data.msg)
                    }
                    that.formLoading = false
                })
                .catch((e) => {
                    that.formLoading = false
                })
        },
        formatSeconds(theTime) {
            return formatSeconds(theTime)
        },
        initAnswer() {
            this.answer.id = this.form.id
            let titleItemArray = this.form.titleItems
            for (let tIndex in titleItemArray) {
                let questionArray = titleItemArray[tIndex].questionItems
                for (let qIndex in questionArray) {
                    let question = questionArray[qIndex]
                    this.answer.answerItems.push({
                        questionId: question.id,
                        content: null,
                        contentArray: [],
                        completed: false,
                        itemOrder: question.itemOrder,
                    })
                }
            }
        },
        timeReduce() {
            let that = this
            this.timer = setInterval(function () {
                if (that.remainTime <= 0) {
                    that.submitForm()
                } else {
                    ++that.answer.doTime
                    --that.remainTime
                }
            }, 1000)
        },
        questionCompleted(completed) {
            return this.enumFormat(this.doCompletedTag, completed)
        },
        goAnchor(selector) {
            this.$el.querySelector(selector).scrollIntoView({
                behavior: 'instant',
                block: 'center',
                inline: 'nearest',
            })
        },
    },
    computed: {
        ...mapGetters('enumItem', ['enumFormat']),
        ...mapState('enumItem', {
            doCompletedTag: (state) =>
                state.exam.question.answer.doCompletedTag,
        }),
    },
}
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

    .box-card {
        margin-bottom: 20px;
    }
    .clearfix {
        span {
            margin-right: 20px;
        }
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
