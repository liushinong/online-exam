
<template>
  <el-card>
    <el-button type="primary" @click="IsShow = true">添加班级</el-button>
    <el-table
      v-loading="loading"
      :data="tableData"
      border
      height="550"
      stripe
      @filter-change="filterChange"
    >
      <el-table-column prop="id" label="id" align="center" />
      <el-table-column prop="name" label="班级名称" align="center" />
      <el-table-column prop="subjectCode" label="班级口令" align="center" />
      <el-table-column prop="createTime" label="创建时间" align="center" />
      <el-table-column fixed="right" label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="btnView(scope.row)"
          >查看</el-button>
          <el-button
            type="danger"
            size="small"
            @click="deleteS(scope.row)"
          >删除</el-button>
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
    <el-dialog :visible.sync="IsShow">
      <el-form label-width="80px">
        <el-form-item label="班级名称">
          <el-input
            v-model="input"
            placeholder="请输入内容"
            width="300px"
          />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="onSubmit(input)"
          >立即创建</el-button>
          <el-button @click="IsShow = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </el-card>
</template>

<script>
import { findSubjectBT } from '@/api/student'
import { timeFormat } from '@/utils/util'
import { deleteSubject, addSubject } from '@/api/subject'
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
      value: '',
      IsShow: false,
      input: ''
    }
  },
  created() {
    this.init()
    var data = {
      pageNum: 1,
      pageSize: 10,
      params: {
        teacherId: 2,
        name: ''
      }
    }
    findSubjectBT(data).then((res) => {
      this.options = res.data.content
      this.tableData = res.data.content
      for (let i = 0; i < this.tableData.length; i++) {
        this.tableData[i].createTime = timeFormat(this.tableData[i].createTime)
      }
    })
  },
  methods: {
    deleteS(id) {
      var subjectId = id.id
      deleteSubject(this.subjectId).then((res) => {
        console.log(res)
      })
    },
    onSubmit(sname) {
      console.log(sname)
      var data = {
        name: sname,
        img: 'https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/1.png',
        teacherId: 2
      }
      addSubject(data).then((res) => {
        console.log(res)
      })

      this.input = ''

      var data = {
        pageNum: 1,
        pageSize: 10,
        params: {
          teacherId: 2,
          name: ''
        }
      }
      findSubjectBT(data).then((res) => {
        this.options = res.data.content
        this.tableData = res.data.content
        for (let i = 0; i < this.tableData.length; i++) {
          this.tableData[i].createTime = timeFormat(
            this.tableData[i].createTime
          )
        }
      })
      this.IsShow = false
    },
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
