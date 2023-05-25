
<template>
  <el-card>
    <div slot="header">
      <el-input
        v-model="searchText"
        placeholder="搜索"
        style="width: 350px"
        @keyup.enter.native="init()"
      />
      班级：
      <el-select v-model="value" placeholder="请选择" @change="changesubject">
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        />
      </el-select>
    </div>

    <el-table
      v-loading="loading"
      :data="tableData"
      border
      height="550"
      stripe
      @filter-change="filterChange"
    >
      <el-table-column prop="id" label="学生id" align="center" />
      <el-table-column prop="user_uuid" label="用户名" align="center" />
      <el-table-column prop="real_name" label="真实姓名" align="center" />
      <el-table-column prop="level" label="年级" align="center" />
      <el-table-column prop="sex" label="性别" width="100" align="center" />
      <el-table-column prop="phone" label="手机号" align="center" />
      <el-table-column prop="age" label="年龄" align="center" />
      <el-table-column prop="birth_day" label="生日" align="center" />

      <el-table-column fixed="right" label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="btnView(scope.row)"
          >查看</el-button>
          <el-button type="danger" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next, sizes"
      :total="total"
      :current-page="currentPage"
      :page-size="pageSize"
      :page-sizes="[5, 10, 15]"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </el-card>
</template>

<script>
import { findStudent } from '@/api/student'
import { findSubjectBT } from '@/api/student'

export default {
  data() {
    return {
      list: [],
      searchParams: {},
      loading: true,
      tableData: [],

      currentPage: 1,
      pageSize: 10,
      total: 50,
      searchText: '',
      l: '',
      options: [],
      value: ''
    }
  },
  created() {
    this.init()
    var data = {
      pageNum: 1,
      pageSize: 10,
      params: {
        teacherId: 1,
        name: ''
      }
    }
    findSubjectBT(data).then((res) => {
      console.log(res.data.content)
      this.options = res.data.content
    })
  },
  methods: {
    // 初始化数据
    init() {
      this.loading = false
      if (this.searchText != ' ') {
        this.currentPage = 1
      }
      this.searchParams = {
        key: this.searchText === '' ? '' : this.searchText,
        index: this.currentPage - 1,
        length: this.pageSize,
        typeIdList: this.filterList
      }
    },
    changesubject(val) {
      console.log(val)

      var data = {
        pageNum: 1,
        pageSize: 10,
        params: {
          subjectId: val
        }
      }
      findStudent(data).then((res) => {
        this.list = res.data.content
        this.tableData2 = new Array()
        for (let i = 0; i < res.data.content.length; i++) {
          if ((this.list[i].sex = 1)) {
            this.list[i].sex = '男'
          } else {
            this.list[i].sex = '女'
          }

          this.tableData2[i] = {
            id: this.list[i].id,
            user_uuid: this.list[i].userName,
            real_name: this.list[i].realName,
            level: this.list[i].userLevel,
            sex: this.list[i].sex,
            phone: this.list[i].phone,
            age: this.list[i].age,
            birth_day: this.list[i].birthDay
          }
        }
        this.tableData = this.tableData2
      })
    },

    // 每页显示数量
    handleSizeChange(size) {
      this.pageSize = size
      this.init()
    },
    // 控制页面切换
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage
      this.init()
    },
    // 查看按钮
    btnView(row) {
      console.log(row)
    },
    // 爬取
    crawlNovel() {
      crawlNovel().then((res) => {
        if (res.code == 0) {
          this.$message({
            message: '添加成功',
            type: 'success'
          })
        } else {
          this.$message({
            message: '添加失败',
            type: 'error'
          })
        }
      })
    },
    // 获取筛选条件
    getFilterNameItem() {
      return this.typeList
    },
    // 开始筛选
    filterChange(filterObj) {
      this.filterList = filterObj.filterTag
      this.init()
    }
  }
}
</script>
