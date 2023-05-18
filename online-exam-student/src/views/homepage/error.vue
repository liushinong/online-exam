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
                <div slot="header" class="card-header">
                    <span>题型: 多选题</span>
                    <span>学科: 语文</span>
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
