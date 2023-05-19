<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="学科: ">
        <el-select v-model="queryParam.subjectId" clearable>
          <el-option
            v-for="item in subjectFilter"
            :key="item.id"
            :value="item.id"
            :label="item.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="题型">
        <el-select v-model="queryParam.questionType" clearable>
          <el-option
            v-for="item in questionType"
            :key="item.id"
            :value="item.id"
            :label="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="tableData"
      border
      fit
      highlight-current-row
    >
      <el-table-column prop="id" label="Id" width="90px" />
      <el-table-column
        prop="subjectId"
        label="学科"
        :formatter="subjectFormatter"
        width="120px"
      />
      <el-table-column
        prop="questionType"
        label="题型"
        :formatter="questionTypeFormatter"
        width="70px"
      />
      <el-table-column prop="shortTitle" label="题干" show-overflow-tooltip />
      <el-table-column prop="score" label="分数" width="60px" />
      <el-table-column prop="difficult" label="难度" width="60px" />
      <el-table-column prop="createTime" label="创建时间" width="160px" />
      <el-table-column label="操作" align="center" width="220px">
        <template slot-scope="{ row }">
          <el-button size="mini" @click="showQuestion(row)">预览</el-button>
          <el-button size="mini" @click="editQuestion(row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteQuestion(row)"
            class="link-left"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "list",
  data() {
    return {
      queryParam: {
        questionType: null,
        subjectId: null,
        pageIndex: 1,
        pageSize: 10,
      },
      subjectFilter: [
        {
          id: 1,
          name: "语文",
        },
        {
          id: 2,
          name: "数学",
        },
      ],
      questionType: [
        {
          id: 1,
          value: "单选题",
        },
        {
          id: 2,
          value: "多选题",
        },
        {
          id: 3,
          value: "判断题",
        },
        {
          id: 4,
          value: "填空题",
        },
        {
          id: 5,
          value: "简答题",
        },
      ],
      listLoading: false,
      tableData: [],
    };
  },
  methods: {
    submitForm() {},
    subjectFormatter() {},
    questionTypeFormatter() {},
  },
};
</script>

<style lang="scss" scoped></style>
