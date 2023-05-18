<template>
    <div id="container">
        <div id="title">
            <h1>在线考试平台</h1>
        </div>
        <el-form :model="loginForm" ref="ruleForm">
            <div class="input">
                <el-input
                    v-model="loginForm.username"
                    prefix-icon="el-icon-user"
                    placeholder="请输入用户名"
                ></el-input>
            </div>
            <div class="input">
                <el-input
                    v-model="loginForm.password"
                    type="password"
                    prefix-icon="el-icon-lock"
                    placeholder="请输入密码"
                    auto-complete="new-password"
                ></el-input>
            </div>
            <div class="input">
                <el-button
                    style="width: 500px"
                    type="primary"
                    @click="submitForm"
                    >登录</el-button
                >
            </div>
        </el-form>
    </div>
</template>

<script>
import { Message } from 'element-ui'
import { mapMutations } from 'vuex'
import { login } from '@/api/user'
import Cookies from 'js-cookie'
export default {
    data() {
        return {
            loginForm: {
                username: 'admin',
                password: 'admin',
            },
        }
    },
    methods: {
        ...mapMutations(['changeLogin']),
        submitForm() {
            let that = this
            if (
                this.loginForm.username === '' ||
                this.loginForm.password === ''
            ) {
                Message({
                    message: '账号密码不能为空',
                    type: 'error',
                })
            } else {
                login(this.loginForm).then((res) => {
                    let data = res.data
                    if (data.code === 0) {
                        console.log(data)
                        that.changeLogin({ token: data.data.token })
                        localStorage.setItem('userId', data.data.usedId)
                        localStorage.setItem('username', data.data.username)
                        Message({
                            message: '登录成功',
                            type: 'success',
                        })
                        that.$router.push({ path: '/' })
                    } else {
                        Message({
                            message: '账号或密码不正确',
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
