<template>
    <el-container>
        <el-header height="70px">
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
                <div class="card-content">
                    <div class="card-item">
                        <p>test</p>
                        <p>提交时间: 2023-05-18 07:20:47</p>
                        <p>
                            试卷状态:
                            <el-tag type="warning" size="small">待批改</el-tag>
                        </p>
                    </div>
                    <div class="card-item">
                        <p>试卷类型: 固定试卷</p>
                        <p>学科: 数学</p>
                        <p>题目数量: 2</p>
                        <p>试卷总分: 15</p>
                        <p>发布时间: 2023-05-18 01:01:01</p>
                    </div>
                    <div class="card-item">
                        <p>正确题数: 10</p>
                        <p>自动批改: 10</p>
                        <p>最终得分: 10</p>
                        <p>耗时: 3分钟</p>
                    </div>
                </div>
                <el-button
                    type="primary"
                    size="small"
                    style="float: right; margin-top: -30px"
                    >去批改</el-button
                >
                <div class="bottom"></div>
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
        text-align: start;
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
