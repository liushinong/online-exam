<template>
    <el-container>
        <el-aside width="240px">
            <div class="avatar">
                <img
                    src="https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/FWnEBMfbDPx.jpg"
                    alt=""
                />
            </div>
            <p>用户名: admin</p>
            <p>学 &nbsp;号: admin</p>
            <!-- <p>南京工业职业技术大学</p> -->
        </el-aside>
        <el-main>
            <el-header class="main-header" height="130px">
                <div
                    v-for="(item, index) in dataList"
                    :key="index"
                    class="item"
                >
                    <p class="count">{{ item.count }}</p>
                    <p class="title">{{ item.title }}</p>
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
                        >加入班级</el-button
                    >
                </el-menu>
                <div class="div-search">
                    <el-input
                        prefix-icon="el-icon-search"
                        placeholder="请输入课程名称"
                        style="width: 240px; margin-right: 14px"
                    ></el-input>
                    <el-button type="warning">搜索</el-button>
                </div>
                <div
                    class="subject-item"
                    v-for="(item, index) in subjectList"
                    :key="index"
                >
                    <div class="item-info">
                        <div class="img-box">
                            <img :src="item.img" alt="" />
                        </div>
                        <div class="item-content">
                            <h3>{{ item.title }}</h3>
                            <div class="item-detail">
                                <p>创建时间: {{ item.createTime }}</p>
                                <p>主持人: {{ item.teacher }}</p>
                                <p>学生数: {{ item.stuCount }}</p>
                            </div>
                        </div>
                    </div>
                    <el-button round="true" size="medium">进入</el-button>
                </div>
            </el-main>
        </el-main>
    </el-container>
</template>
<script>
export default {
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
            subjectList: [
                {
                    subjectId: 1,
                    img: 'https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/1.png',
                    title: 'Web前端框架',
                    createTime: '2023-02-12',
                    teacher: 'xxx',
                    stuCount: 33,
                },
                {
                    subjectId: 2,
                    img: 'https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/1.png',
                    title: 'Web前端框架',
                    createTime: '2023-02-12',
                    teacher: 'xxx',
                    stuCount: 33,
                },
                {
                    subjectId: 3,
                    img: 'https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/1.png',
                    title: 'Web前端框架',
                    createTime: '2023-02-12',
                    teacher: 'xxx',
                    stuCount: 33,
                },
            ],
        }
    },
    methods: {},
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
