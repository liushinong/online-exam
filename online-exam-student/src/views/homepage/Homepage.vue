<template>
    <el-container style="background: #fafafa">
        <el-header>
            <div class="logo">
                <img src="@/assets/logo.png" alt="" />
            </div>
            <el-menu
                :default-active="defaultActive"
                mode="horizontal"
                close="el-menu-demo"
                :router="router"
                style="width: 360px"
            >
                <el-menu-item index="/home">首页</el-menu-item>
                <el-menu-item index="/paper">试卷中心</el-menu-item>
                <el-menu-item index="/record">考试记录</el-menu-item>
                <el-menu-item index="/error">错题本</el-menu-item>
            </el-menu>
        </el-header>
        <el-container class="container-content">
            <router-view />
        </el-container>
    </el-container>
</template>
<script>
export default {
    data() {
        return {
            defaultActive: '/home',
            router: true,
        }
    },
    methods: {},
}
</script>
<style scoped lang="scss">
.el-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    // margin: 0 auto;
    padding: 0 200px;
    background-color: #fff;
    position: fixed;
    z-index: 9999;
    .logo {
        width: 40px;
        padding: 10px;
        img {
            width: 100%;
            line-height: 60px;
        }
    }
}

.container-content {
    margin: 60px 0;
    padding: 0 200px;
    padding-top: 20px;
    // background-color: #f5f3f7;
    background: #fafafa;
}
</style>
