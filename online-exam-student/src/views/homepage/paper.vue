<template>
    <el-container>
        <el-header height="110px">
            <div class="item-contain">
                <span>试卷类型:</span>
                <el-radio-group
                    v-model="queryParam.params.paperType"
                    size="small"
                    @change="paperTypeChange"
                >
                    <el-radio-button label="1">固定试卷</el-radio-button>
                    <el-radio-button label="2">时段试卷</el-radio-button>
                    <el-radio-button label="3">任务试卷</el-radio-button>
                </el-radio-group>
            </div>
            <div class="item-contain" v-show="id == null">
                <span>试卷科目:</span>
                <el-radio-group
                    v-model="queryParam.params.subjectId"
                    size="small"
                    @change="subjectChange"
                >
                    <el-radio-button
                        v-for="item in subjectList"
                        :label="item.id"
                        :key="item.id"
                        >{{ item.name }}</el-radio-button
                    >
                </el-radio-group>
            </div>
        </el-header>
        <el-main v-loading="listLoading">
            <div class="main-content">
                <el-card
                    class="box-card"
                    v-for="item in paperList"
                    :key="item.id"
                >
                    <div slot="header">
                        <span>{{ item.name }}</span>
                    </div>
                    <p>学科: {{ subjectFormat(item.subjectId) }}</p>
                    <p>题目数: {{ item.questionCount }}</p>
                    <p>试卷总分: {{ item.score }}</p>
                    <p>考试时长: {{ item.suggestTime }}分钟</p>
                    <p>
                        开始时间:
                        {{
                            item.paperType == 2
                                ? item.limitStartTime.split('T')[0] +
                                  ' ' +
                                  item.limitStartTime
                                      .split('T')[1]
                                      .split('.')[0]
                                : '-------'
                        }}
                    </p>
                    <p>
                        结束时间:
                        {{
                            item.paperType == 2
                                ? item.limitStartTime.split('T')[0] +
                                  ' ' +
                                  item.limitStartTime
                                      .split('T')[1]
                                      .split('.')[0]
                                : '-------'
                        }}
                    </p>
                    <div class="bottom">
                        <router-link
                            target="_blank"
                            :to="{ path: '/do', query: { id: item.id } }"
                        >
                            <el-button type="text" style="float: right"
                                >开始答题</el-button
                            >
                        </router-link>
                    </div>
                </el-card>
            </div>
            <pagination
                v-show="total > 0"
                :total="total"
                :background="false"
                :page.sync="queryParam.pageNum"
                :limit.sync="queryParam.pageSize"
                @pagination="search"
                style="margin-top: 20px"
            />
        </el-main>
    </el-container>
</template>
<script>
import { list } from '@/api/subject'
import { pageList } from '@/api/examPaper'
import Pagination from '@/components/Pagination'
export default {
    components: {
        Pagination,
    },
    data() {
        return {
            subjectList: [],
            queryParam: {
                pageNum: 1,
                pageSize: 10,
                params: {
                    subjectId: null,
                    paperType: 1,
                    userId: 3,
                },
            },
            total: 30,
            listLoading: false,
            paperList: [],
            id: null,
        }
    },
    created() {
        this.queryParam.params.userId = parseInt(localStorage.getItem('userId'))
        let id = this.$route.query.id
        this.id = id
        if (id && parseInt(id) !== 0) {
            this.queryParam.params.subjectId = parseInt(id)
            this.search()
        } else {
            this.id = null
            this.initSubject()
        }
    },
    methods: {
        initSubject() {
            let that = this
            list(localStorage.getItem('userId')).then((res) => {
                that.subjectList = res.data.data
                // let subjectId = that.subjectList[0].id
                // that.queryParam.params.subjectId = subjectId
                that.search()
            })
        },
        paperTypeChange(val) {
            this.queryParam.params.paperType = parseInt(
                this.queryParam.params.paperType,
            )
            this.search()
        },
        subjectChange(val) {
            this.queryParam.params.subjectId = parseInt(
                this.queryParam.params.subjectId,
            )
            this.search()
        },
        subjectFormat(id) {
            let name = ''
            for (let item of this.subjectList) {
                if (item.id == id) {
                    name = item.name
                }
            }
            return name
        },
        search() {
            this.listLoading = true
            pageList(this.queryParam).then((res) => {
                this.paperList = res.data.data.content
                this.total = res.data.data.totalSize
                this.queryParam.pageNum = res.data.data.pageNum
                this.listLoading = false
            })
        },
    },
}
</script>
<style scoped lang="scss">
.el-header {
    border-bottom: 1px solid #e6e6e6;
    text-align: start;
    .item-contain {
        margin-top: 10px;
        span {
            font-size: 14px;
            margin-right: 20px;
        }
    }
}
.el-main {
    .main-content {
        display: flex;
    }
    .box-card {
        margin-right: 50px;
        width: 280px;
        text-align: start;
        p {
            color: #a7a09b;
            font-size: 14px;
        }
    }
}
</style>
