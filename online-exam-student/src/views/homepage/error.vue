<template>
    <div style="margin-top: 10px" class="app-contain">
        <el-row :gutter="50">
            <el-col :span="14">
                <el-table
                    v-loading="listLoading"
                    :data="tableData"
                    fit
                    highlight-current-row
                    style="width: 100%"
                    @row-click="itemSelect"
                >
                    <el-table-column
                        prop="shortTitle"
                        label="题干"
                        show-overflow-tooltip
                    />
                    <el-table-column
                        prop="questionType"
                        label="题型"
                        :formatter="questionTypeFormatter"
                        width="70"
                    />
                    <el-table-column
                        prop="subjectName"
                        label="学科"
                        width="50"
                    />
                    <el-table-column
                        prop="createTime"
                        label="做题时间"
                        width="170"
                    />
                </el-table>
                <pagination
                    v-show="total > 0"
                    :total="total"
                    :background="false"
                    :page.sync="queryParam.pageIndex"
                    :limit.sync="queryParam.pageSize"
                    @pagination="search"
                    style="margin-top: 20px"
                />
            </el-col>
            <el-col :span="10">
                <el-card class="record-answer-info">
                    <el-form>
                        <el-form-item>
                            <QuestionAnswerShow
                                :qType="selectItem.questionType"
                                :qLoading="qAnswerLoading"
                                :question="selectItem.questionItem"
                                :answer="selectItem.answerItem"
                            />
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>
        </el-row>
    </div>
    <!-- <el-container>
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
        <el-main>
            <el-card class="box-card">
                <div slot="header" class="card-header">
                    <span>题型: 多选题</span>
                    <span>学科: 语文</span>
                </div>
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
    </el-container> -->
</template>
<script>
import Pagination from '@/components/Pagination'
import { list } from '@/api/subject'
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
        }
    },
    created() {
        this.initSubject()
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
        search() {},
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
    .el-card ::v-deep .el-card__header {
        padding: 0;
        background-color: #8cbda4;
        height: 36px;
        text-align: left;
        line-height: 36px;
        color: #fff;
        font-size: 14px;
        span {
            margin-left: 20px;
        }
    }

    .box-card {
    }
}
</style>
