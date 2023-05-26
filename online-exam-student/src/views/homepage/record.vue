<template>
    <el-container>
        <el-header height="70px">
            <div class="item-contain">
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
            <el-card class="box-card" v-for="item in paperList" :key="item.id">
                <div class="card-content">
                    <div class="card-item">
                        <p>{{ item.paperName }}</p>
                        <p>提交时间: {{ timeFormatter(item.createTime) }}</p>
                        <p>
                            试卷状态:
                            <el-tag
                                v-if="item.status == 1"
                                type="warning"
                                size="small"
                                >待批改</el-tag
                            >
                            <el-tag v-else type="success" size="small"
                                >已批改</el-tag
                            >
                        </p>
                    </div>
                    <div class="card-item">
                        <p>
                            试卷类型:
                            {{
                                item.paperType == 1
                                    ? '固定试卷'
                                    : item.paperType == 2
                                    ? '时段试卷'
                                    : '任务试卷'
                            }}
                        </p>
                        <p>学科: {{ subjectFormat(item.subjectId) }}</p>
                        <p>题目数量: {{ item.questionCount }}</p>
                        <p>试卷总分: {{ item.paperScore }}</p>
                        <!-- <p>发布时间: 2023-05-18 01:01:01</p> -->
                    </div>
                    <div class="card-item">
                        <p>正确题数: {{ item.questionCorrect }}</p>
                        <p>自动批改: {{ item.systemScore }}</p>
                        <p>最终得分: {{ item.userScore }}</p>
                        <p>耗时: {{ item.doTime }}分钟</p>
                    </div>
                </div>
                <router-link
                    v-if="item.status == 2"
                    target="_blank"
                    :to="{ path: '/read', query: { id: item.id } }"
                >
                    <el-button
                        type="primary"
                        size="small"
                        style="float: right; margin-top: -30px"
                        >查看答卷</el-button
                    >
                </router-link>
                <el-button
                    v-else
                    type="primary"
                    size="small"
                    style="float: right; margin-top: -30px"
                    disabled
                    >查看答卷</el-button
                >
                <div class="bottom"></div>
            </el-card>
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
import { pageList } from '@/api/examPaperAnswer'
import Pagination from '@/components/Pagination'
import { timeFormat } from '@/utils/index'
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
                    userId: 3,
                },
            },
            total: 0,
            listLoading: false,
            paperList: [],
        }
    },
    created() {
        this.initSubject()
        this.queryParam.params.userId = parseInt(localStorage.getItem('userId'))
    },
    methods: {
        search() {
            this.listLoading = true
            pageList(this.queryParam).then((res) => {
                this.paperList = res.data.data.content
                this.total = res.data.data.totalSize
                this.queryParam.pageNum = res.data.data.pageNum
                this.listLoading = false
            })
        },

        initSubject() {
            let that = this
            list(localStorage.getItem('userId')).then((res) => {
                that.subjectList = res.data.data
                // let subjectId = that.subjectList[0].id
                // that.queryParam.params.subjectId = subjectId
                that.search()
            })
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
        timeFormatter(time) {
            return timeFormat(time)
        },
        subjectChange(val) {
            this.queryParam.params.subjectId = parseInt(
                this.queryParam.params.subjectId,
            )
            this.search()
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
    .box-card {
        text-align: start;
        margin-bottom: 20px;
        .card-content {
            width: 85%;
            height: 160px;
            display: flex;
            justify-content: space-between;
            .card-item {
                width: 200px;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }
        }
        p {
            // color: #a7a09b;
            font-size: 14px;
            margin: 0;
        }
    }
}
</style>
