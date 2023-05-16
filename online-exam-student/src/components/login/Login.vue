<template>
   <div id="container">
    <div id ="title">
      <h1>在线考试平台</h1>
    </div>
    <div class="input">
      <el-input  prefix-icon="el-icon-user" placeholder="请输入用户名"></el-input>
    </div>
    <div class="input">
      <el-input  prefix-icon="el-icon-lock" placeholder="请输入密码" auto-complete="new-password"></el-input>
    </div>
    <div class="input">
      <el-button style="width: 500px" type="primary" >登录</el-button>
    </div>
  </div>
</template>
<style scoped>
#container{
  background: #595959;
  background-image: url("@/assets/2.png");
  height: 100%;
  width:100%;
  position: absolute;
}

#title{
  text-align: center;
  color:azure;
  margin-top: 200px;
}

.input{
  margin:20px auto;
  width: 500px;
}
</style>