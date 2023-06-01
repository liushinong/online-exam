<template>
    <el-container>
        <el-aside width="240px">
            <div class="avatar">
                <img
                    src="https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/FWnEBMfbDPx.jpg"
                    alt=""
                />
            </div>
            <p>用户id: {{ userId }}</p>
            <p>用户名: {{ username }}</p>
            <!-- <p>学 &nbsp;号: admin</p> -->
            <!-- <p>南京工业职业技术大学</p> -->
            <el-button @click="logout" type="text" size="small"
                >退出登录</el-button
            >
        </el-aside>
        <el-main v-loading="listLoading">
            <el-header class="main-header" height="130px">
                <div class="item">
                    <p class="count">{{ info.subjectNum }}</p>
                    <p class="title">课程总数</p>
                </div>
                <div class="item">
                    <p class="count">{{ info.paperNum }}</p>
                    <p class="title">试卷总数</p>
                </div>
                <div class="item">
                    <p class="count">{{ info.finishPaperNum }}</p>
                    <p class="title">试卷完成</p>
                </div>
                <div class="item">
                    <p class="count">{{ info.wrongQuestionNum }}</p>
                    <p class="title">错题总数</p>
                </div>
            </el-header>
            <el-main class="main-content">
                <el-menu
                    :default-active="defaultActive"
                    class="el-menu-demo"
                    mode="horizontal"
                    active-text-color="#004b90"
                >
                    <el-menu-item index="1">我的课程</el-menu-item>
                    <el-button
                        style="float: right"
                        icon="el-icon-circle-plus-outline"
                        @click="dialogVisible = true"
                        >加入班级</el-button
                    >
                </el-menu>
                <div class="div-search">
                    <el-input
                        prefix-icon="el-icon-search"
                        placeholder="请输入课程名称"
                        style="width: 240px; margin-right: 14px"
                        v-model="queryParam.params.search"
                        clearable
                    ></el-input>
                    <el-button type="warning" @click="search">搜索</el-button>
                </div>
                <div
                    class="subject-item"
                    v-for="(item, index) in subjectList"
                    :key="index"
                >
                    <div class="item-info">
                        <div class="img-box">
                            <img
                                src="https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/1.png"
                                alt=""
                            />
                        </div>
                        <div class="item-content">
                            <h3>{{ item.name }}</h3>
                            <div class="item-detail">
                                <p>
                                    创建时间:
                                    {{ timeFormatter(item.createTime) }}
                                </p>
                                <!-- <p>主持人: {{ item.teacherId }}</p> -->
                                <p>课程码: {{ item.subjectCode }}</p>
                            </div>
                        </div>
                    </div>
                    <el-button
                        round
                        size="medium"
                        @click="
                            $router.push({
                                path: '/paper',
                                query: { id: item.id },
                            })
                        "
                        >进入</el-button
                    >
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
        </el-main>
        <el-dialog :visible.sync="dialogVisible" title="加入班级" width="600px">
            <span>课程码: </span>
            <el-input
                maxlength="6"
                placeholder="请输入课程码"
                v-model="subjectCode"
                style="width: 260px; margin-left: 20px"
            ></el-input>
            <div slot="footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="join">确定</el-button>
            </div>
        </el-dialog>
    </el-container>
</template>
<script>
import { Message } from 'element-ui'
import Cookies from 'js-cookie'
import { getInfo, pageList, joinSubject } from '@/api/home'
import Pagination from '@/components/Pagination'
import { timeFormat } from '@/utils'
export default {
    components: {
        Pagination,
    },
    data() {
        return {
            dataList: [
                {
                    count: 3,
                    title: '课程总数',
                    img: '',
                },
                {
                    count: 22,
                    title: '课堂总数',
                    img: '',
                },
                {
                    count: 11,
                    title: '考试总数',
                    img: '',
                },
                {
                    count: 111,
                    title: '任务总数',
                    img: '',
                },
            ],
            defaultActive: '1',
            subjectList: [],
            dialogVisible: false,
            subjectCode: '',
            username: '',
            userId: '',
            info: {},
            queryParam: {
                pageNum: 1,
                pageSize: 3,
                params: {
                    search: '',
                    studentId: -1,
                },
            },
            total: 0,
            listLoading: false,
        }
    },
    created() {
        this.username = localStorage.getItem('username')
        this.userId = localStorage.getItem('userId')
        this.queryParam.params.studentId = parseInt(
            localStorage.getItem('userId'),
        )
        getInfo(this.userId).then((res) => {
            this.info = res.data.data.data
        })
        this.search()
    },
    methods: {
        search() {
            this.listLoading = true
            pageList(this.queryParam).then((res) => {
                console.log(res.data.data)
                this.subjectList = res.data.data.content
                this.total = res.data.data.totalSize
                this.queryParam.pageNum = res.data.data.pageNum
                this.listLoading = false
            })
        },
        join() {
            this.dialogVisible = false
            let data = {
                userId: this.userId,
                subjectCode: this.subjectCode,
            }
            joinSubject(data).then((res) => {
                if (res.data.code != 0) {
                    this.$message.error(res.data.msg)
                } else {
                    this.$message.success('加入课程成功')
                    this.search()
                }
            })
        },
        logout() {
            localStorage.removeItem('userId'),
                localStorage.removeItem('username')
            Cookies.remove('token')
            this.$router.push({ path: '/login' })
        },
        timeFormatter(time) {
            return timeFormat(time)
        },
    },
}
</script>
<style scoped lang="scss">
.el-aside {
    height: 360px;
    font-size: 14px;
    background-color: white;
    text-align: start;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 0 auto;
    .avatar {
        width: 120px;
        height: 120px;
        margin-bottom: 30px;
        img {
            width: 100%;
            height: 100%;
            border-radius: 50%;
        }
    }
    p {
        color: #afafaf;
    }
}

.el-main {
    padding-top: 0;
    overflow: hidden;
    .main-header {
        background: white;
        display: flex;
        justify-content: space-between;
        align-items: center;
        // width: 1000px;
        padding: 60px 60px;
        .item {
            height: 130px;
            width: 250px;
            .count {
                font-size: 30px;
                color: black;
            }
            .title {
                margin-top: -14px;
                font-size: 18px;
                color: #939597;
            }
        }
    }
    .main-content {
        margin-top: 20px;
        background-color: #fff;
        // width: 0px;
        padding: 20px 30px;

        .el-menu-item {
            font-weight: 600;
            font-size: 16px;
        }
        .div-search {
            margin-top: 14px;
            display: flex;
            align-items: center;
            justify-content: end;
            .el-input__inner {
                height: 30px;
            }
        }
        .subject-item {
            padding: 10px 0;
            border-bottom: 1px solid #e6e6e6;
            width: 100%;
            height: 140px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            .item-info {
                display: flex;
                width: 80%;
                .img-box {
                    height: 120px;
                    width: 200px;
                    img {
                        width: 100%;
                        height: 100%;
                        border-radius: 7px;
                    }
                }
                .item-content {
                    text-align: start;
                    margin-left: 20px;
                    .item-detail {
                        width: 400px;
                        display: flex;
                        align-items: center;
                        justify-content: space-between;
                        color: #939597;
                    }
                }
            }
            .el-button {
                color: #004b90;
                border: 1px solid #004b90;
            }
            .el-button:hover {
                color: #fff;
                background: #004b90;
            }
        }
    }
}
</style>
