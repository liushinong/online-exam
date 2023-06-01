<template>
  <el-card>
    <el-button
      type="primary"
      style="margin-bottom: 20px"
      @click="btnView()"
    >添加班级</el-button>
    <el-table
      v-loading="loading"
      :data="tableData"
      border
      stripe
      @filter-change="filterChange"
    >
      <el-table-column prop="id" label="id" align="center" width="80px" />
      <el-table-column prop="name" label="班级名称" align="center" />
      <el-table-column prop="subjectCode" label="班级口令" align="center" />
      <el-table-column prop="createTime" label="创建时间" align="center" />
      <el-table-column fixed="right" label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="btnView(scope.row)"
          >编辑</el-button>
          <el-button
            type="danger"
            size="small"
            @click="deleteS(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <Pagination
      v-show="total > 0"
      :total="total"
      :page.sync="pageNum"
      :limit.sync="pageSize"
      @pagination="search"
    />
    <el-dialog :visible.sync="IsShow">
      <el-form label-width="80px">
        <el-form-item label="班级名称">
          <el-input
            v-model="dataN.name"
            placeholder="请输入内容"
            width="300px"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit()">确定</el-button>
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
import { pageList } from '@/api/question'
import Pagination from '@/components/Pagination'
export default {
  components: {
    Pagination
  },
  data() {
    return {
      teacherId: 0,
      list: [],
      searchParams: {},
      loading: false,
      tableData: [],

      pageNum: 1,
      pageSize: 10,
      total: 0,
      searchText: '',
      l: '',
      options: [],
      value: '',
      IsShow: false,
      input: '',
      dataN: {
        name: ''
      }
    }
  },
  created() {
    this.teacherId = parseInt(localStorage.getItem('teacherId'))
    this.search()
  },
  methods: {
    deleteS(row) {
      deleteSubject(row.id).then((res) => {
        console.log(res)
      })
    },
    btnView(row) {
      if (row == undefined) {
        this.dataN = {}
        this.dataN.img =
          'https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/1.png'
        this.dataN.teacherId = this.teacherId
      } else {
        this.dataN.name = row.name
        this.dataN.id = row.id
        this.dataN.img =
          'https://xuekaikai.oss-cn-shanghai.aliyuncs.com/campus_navigatic/1.png'
      }
      this.IsShow = true
    },
    onSubmit() {
      var data = this.dataN
      addSubject(data).then((res) => {
        console.log(res)
        this.search()
      })
      this.IsShow = false
    },

    search() {
      this.loading = true
      var data = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        params: {
          teacherId: this.teacherId,
          name: ''
        }
      }
      findSubjectBT(data).then((res) => {
        this.total = res.data.totalSize
        this.pageNum = res.data.pageNum
        this.options = res.data.content
        this.tableData = res.data.content
        for (let i = 0; i < this.tableData.length; i++) {
          this.tableData[i].createTime = timeFormat(
            this.tableData[i].createTime
          )
        }
        this.loading = false
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
