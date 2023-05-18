<template>
    <el-container>
        <el-header height="110px">
            <div class="item-contain">
                <span>试卷类型:</span>
                <el-radio-group v-model="paperType" size="small">
                    <el-radio-button label="上海"></el-radio-button>
                    <el-radio-button label="北京"></el-radio-button>
                    <el-radio-button label="广州"></el-radio-button>
                    <el-radio-button label="深圳"></el-radio-button>
                </el-radio-group>
            </div>
            <div class="item-contain">
                <span>试卷科目:</span>
                <el-radio-group v-model="subjectType" size="small">
                    <el-radio-button label="语文"></el-radio-button>
                    <el-radio-button label="数学"></el-radio-button>
                    <el-radio-button label="外语"></el-radio-button>
                    <el-radio-button label="计算机"></el-radio-button>
                </el-radio-group>
            </div>
        </el-header>
        <el-main>
            <el-card class="box-card">
                <div slot="header">
                    <span>语文考试</span>
                </div>
                <p>学科: 语文</p>
                <p>题目数: 6</p>
                <p>试卷总分: 100</p>
                <p>考试时长: 100分钟</p>
                <p>开始时间: 2023-05-17</p>
                <p>结束时间: 2023-05-22</p>
                <div class="bottom">
                    <el-button type="text" style="float: right"
                        >开始答题</el-button
                    >
                </div>
            </el-card>
        </el-main>
    </el-container>
</template>
<script>
export default {
    data() {
        return {
            paperType: '上海',
            subjectType: '语文',
        }
    },
    methods: {},
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
        width: 280px;
        text-align: start;
        p {
            color: #a7a09b;
            font-size: 14px;
        }
    }
}
</style>
