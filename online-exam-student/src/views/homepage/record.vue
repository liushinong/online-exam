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
        text-align: start;
        p {
            color: #a7a09b;
            font-size: 14px;
        }
    }
}
</style>
