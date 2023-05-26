<template>
    <div id="container">
        <div id="title">
            <h1>在线考试平台</h1>
        </div>
        <el-form :model="logonForm" ref="ruleForm">
            <div class="input">
                <el-input
                    v-model="logonForm.username"
                    prefix-icon="el-icon-user"
                    placeholder="请输入用户名"
                ></el-input>
            </div>
            <div class="input">
                <el-input
                    v-model="logonForm.password"
                    type="password"
                    prefix-icon="el-icon-lock"
                    placeholder="请输入密码"
                    auto-complete="new-password"
                ></el-input>
            </div>
            <div class="input">
                <el-input
                    v-model="confirmPassword"
                    type="password"
                    prefix-icon="el-icon-lock"
                    placeholder="请再次输入密码"
                    auto-complete="new-password"
                ></el-input>
            </div>
            <div class="input">
                <el-button
                    style="width: 500px"
                    type="primary"
                    @click="submitForm"
                    >注册</el-button
                >
            </div>
            <el-button
                type="text"
                style="color: #fff"
                @click="$router.push('/login')"
            >
                已有账号，去登录
            </el-button>
        </el-form>
    </div>
</template>

<script>
import { Message } from 'element-ui'
import { mapMutations } from 'vuex'
import { logon } from '@/api/user'
import Cookies from 'js-cookie'
export default {
    data() {
        return {
            logonForm: {
                username: '',
                password: '',
                role: 1,
            },
            confirmPassword: '',
        }
    },
    methods: {
        ...mapMutations(['user/changeLogin']),
        submitForm() {
            let that = this
            if (
                this.logonForm.username === '' ||
                this.logonForm.password === ''
            ) {
                Message({
                    message: '账号密码不能为空',
                    type: 'error',
                })
            } else if (this.logonForm.password != this.confirmPassword) {
                this.$message.error('两次密码不一致')
            } else {
                logon(this.logonForm).then((res) => {
                    let data = res.data
                    if (data.code === 0) {
                        this.$message.success('注册成功')
                        that.$router.push({ path: '/login' })
                    } else {
                        Message({
                            message: '注册失败',
                            type: 'error',
                        })
                    }
                })
            }
        },
    },
}
</script>

<style scoped>
#container {
    background: #595959;
    background-image: url('@/assets/2.png');
    height: 100%;
    width: 100%;
    position: absolute;
}

#title {
    text-align: center;
    color: azure;
    margin-top: 200px;
}

.input {
    margin: 20px auto;
    width: 500px;
}
</style>
