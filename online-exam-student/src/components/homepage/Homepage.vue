<template>

  <el-container>
  <el-header>
    <div class="header-container">
       <div class="header-box">
           置顶头部
       </div>
    </div>
  </el-header>
  <el-container>
    <el-aside width="200px">
      <div class="aside-container">
        <div class="aside-box">
              aside部分
        </div>
      </div>
    </el-aside>
    <el-main>Main</el-main>
  </el-container>
</el-container>

</template>
<script>
export default({
  
})
</script>
<style scoped>
.header-container {
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 120px;
        background-color:#d3d3d3;
        color: #ffffff;
        /*吸顶效果*/
        position: sticky;
        position: -webkit-sticky;
        top: 0px;
}
.aside-container{
      display: flex;
      background-color: #d3d3d3;
      margin-top: 300px;
      height:300px;
      width: 200px;
      
}
</style>
