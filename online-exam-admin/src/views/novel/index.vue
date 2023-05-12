<template>
  <el-card>
    <div slot="header">
      <el-input
        v-model="searchText"
        placeholder="搜索"
        style="width: 350px"
        @keyup.enter.native="init()"
      ></el-input>
      <el-button style="float: right" type="primary" @click="crawlNovel()"
        >一键爬取</el-button
      >
    </div>
    <el-table
      :data="tableData"
      border
      height="550"
      stripe
      v-loading="loading"
      @filter-change="filterChange"
    >
      <el-table-column
        prop="id"
        label="小说id"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="name"
        label="名称"
        width="120"
        align="center"
      ></el-table-column>
      <el-table-column prop="img" label="封面" width="100" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.img" alt="" width="80" height="100" />
        </template>
      </el-table-column>
      <el-table-column
        prop="type"
        label="分类"
        width="80"
        align="center"
        :filters="getFilterNameItem()"
        column-key="filterTag"
      ></el-table-column>
      <el-table-column
        prop="author"
        label="作者"
        width="100"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="lastChapter"
        label="最新章节"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="lastPage"
        label="最后页码"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="totalChapter"
        label="总章节"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="lastChapterTitle"
        label="最新章节标题"
      ></el-table-column>
      <el-table-column prop="updateAt" label="更新时间"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="btnView(scope.row)" type="primary" size="small"
            >查看</el-button
          >
          <el-button type="danger" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next, sizes"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 15]"
      :page-size="pageSize"
    >
    </el-pagination>
  </el-card>
</template>

<script>
import { getNovelList, crawlNovel } from "@/api/novel";
import { getTypeList } from "@/api/type";
import { checkSearch } from "@/utils/util";
export default {
  data() {
    return {
      searchParams: {},
      loading: true,
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 50,
      searchText: "",
      typeList: [],
      filterList: [],
    };
  },
  methods: {
    // 初始化数据
    init() {
      this.loading = false;
      if (this.searchText != "") {
        this.currentPage = 1;
      }
      this.searchParams = {
        key: this.searchText === "" ? "" : this.searchText,
        index: this.currentPage - 1,
        length: this.pageSize,
        typeIdList: this.filterList,
      };
      // getNovelList(this.searchParams).then((res) => {
      //   this.tableData = res.data.list;
      //   this.total = res.data.total;
      //   // this.currentPage = 1;
      //   // if (this.searchText != "") {
      //   //   this.currentPage = 1;
      //   // }
      //   this.loading = false;
      // });
      // getTypeList().then((res) => {
      //   let list = res.data;
      //   this.typeList = [];
      //   for (let item of list) {
      //     this.typeList.push({ text: item.type, value: item.id });
      //   }
      // });
    },
    // 每页显示数量
    handleSizeChange(size) {
      this.pageSize = size;
      this.init();
    },
    // 控制页面切换
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.init();
    },
    // 查看按钮
    btnView(row) {
      console.log(row);
    },
    // 爬取
    crawlNovel() {
      crawlNovel().then((res) => {
        if (res.code == 0) {
          this.$message({
            message: "添加成功",
            type: "success",
          });
        } else {
          this.$message({
            message: "添加失败",
            type: "error",
          });
        }
      });
    },
    // 获取筛选条件
    getFilterNameItem() {
      return this.typeList;
    },
    // 开始筛选
    filterChange(filterObj) {
      this.filterList = filterObj.filterTag;
      this.init();
    },
  },
  created() {
    this.init();
  },
};
</script>
