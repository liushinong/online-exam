<template>
  <div class="app-container">
    <el-form
      ref="form"
      v-loading="formLoading"
      :model="form"
      label-width="100px"
      :rules="rules"
    >
      <el-form-item label="学科: " prop="subjectId" required>
        <el-select v-model="form.subjectId" placeholder="学科">
          <el-option
            v-for="item in subjectFilter"
            :key="item.id"
            :value="item.id"
            :label="item.name"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="题干: " prop="title" required>
        <el-input
          v-model="form.title"
          @focus="inputClick(form, 'title')"
        />
      </el-form-item>
      <el-form-item label="选项: " required>
        <el-form-item
          v-for="(item, index) in form.items"
          :key="item.prefix"
          :label="item.prefix"
          label-width="50px"
          class="question-item-label"
        >
          <el-input v-model="item.prefix" style="width: 50px" />
          <el-input
            v-model="item.content"
            class="question-item-content-input"
            @focus="inputClick(item, 'content')"
          />
          <el-button
            type="danger"
            size="mini"
            class="question-item-remove"
            icon="el-icon-delete"
            @click="questionItemRemove(index)"
          />
        </el-form-item>
      </el-form-item>
      <el-form-item label="解析: " prop="analyze" required>
        <el-input
          v-model="form.analyze"
          @focus="inputClick(form, 'analyze')"
        />
      </el-form-item>
      <el-form-item label="分数: " prop="score" required>
        <el-input-number
          v-model="form.score"
          :precision="1"
          :step="1"
          :max="100"
        />
      </el-form-item>
      <el-form-item label="难度: " required>
        <el-rate v-model="form.difficult" class="question-item-rate" />
      </el-form-item>
      <el-form-item label="正确答案: " prop="correctArray" required>
        <el-checkbox-group v-model="form.correctArray">
          <el-checkbox
            v-for="item in form.items"
            :key="item.prefix"
            :label="item.prefix"
          >{{ item.prefix }}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button type="success" @click="questionItemAdd">添加选项</el-button>
        <el-button type="success" @click="showQuestion">预览</el-button>
      </el-form-item>
    </el-form>
    <el-dialog :visible.sync="richEditor.dialogVisible">
      <Ueditor @ready="editorReady" />
      <span slot="footer">
        <el-button type="primary" @click="editorConfirm">确定</el-button>
        <el-button @click="richEditor.dialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="questionShow.dialog"
      style="width: 100%; height: 100%"
    >
      <QuestionShow
        :q-type="questionShow.qType"
        :question="questionShow.question"
        :q-loading="questionShow.loading"
      />
    </el-dialog>
  </div>
</template>

<script>
import Ueditor from '@/components/Ueditor'
import QuestionShow from '../components/Show'
import { selectById, edit } from '@/api/question'
import { mapActions, mapState } from 'vuex'
export default {
  name: 'MultipleChoice',
  components: {
    Ueditor,
    QuestionShow
  },
  data() {
    return {
      form: {
        id: null,
        userId: 2,
        questionType: 2,
        subjectId: null,
        title: '',
        items: [
          { prefix: 'A', content: '' },
          { prefix: 'B', content: '' },
          { prefix: 'C', content: '' },
          { prefix: 'D', content: '' }
        ],
        analyze: '',
        correct: '',
        correctArray: [],
        score: '',
        difficult: 0
      },
      formLoading: false,
      rules: {
        subjectId: [
          { required: true, message: '请选择学科', trigger: 'change' }
        ],
        title: [{ required: true, message: '请输入题干', trigger: 'blur' }],
        analyze: [{ required: true, message: '请输入解析', trigger: 'blur' }],
        score: [{ required: true, message: '请输入分数', trigger: 'blur' }],
        correctArray: [
          { required: true, message: '请选择正确答案', trigger: 'change' }
        ]
      },
      richEditor: {
        dialogVisible: false,
        object: null,
        parameterName: '',
        instance: null
      },
      questionShow: {
        qType: 0,
        dialog: false,
        question: null,
        loading: false
      }
    }
  },
  created() {
    this.form.userId = parseInt(localStorage.getItem("teacherId"))
    const that = this
    this.initSubject()
    const id = this.$route.query.id
    if (id && parseInt(id) !== 0) {
      that.formLoading = true
      selectById(id).then((res) => {
        that.form = res.data
        that.form.userId = 2
        that.formLoading = false
      })
    }
  },
  computed: {
    ...mapState('exam', { subjectFilter: (state) => state.subjects })
  },
  methods: {
    editorReady(instance) {
      this.richEditor.instance = instance
      const currentContent =
        this.richEditor.object[this.richEditor.parameterName]
      this.richEditor.instance.setContent(currentContent)
      // 光标定位到Ueditor
      this.richEditor.instance.focus(true)
    },
    inputClick(object, parameterName) {
      this.richEditor.object = object
      this.richEditor.parameterName = parameterName
      this.richEditor.dialogVisible = true
    },
    submitForm() {
      const that = this
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true
          edit(this.form)
            .then((res) => {
              if (res.code == 0) {
                that.$message.success('操作成功')
                that.delCurrentView(that).then(() => {
                  that.$router.push('/question/list')
                })
              } else {
                that.$message.error('操作失败')
                this.formLoading = false
              }
            })
            .catch((e) => {
              this.formLoading = false
            })
        }
      })
    },
    resetForm() {
      this.form = {
        id: null,
        questionType: 1,
        subjectId: null,
        title: '',
        items: [
          { prefix: 'A', content: '' },
          { prefix: 'B', content: '' },
          { prefix: 'C', content: '' },
          { prefix: 'D', content: '' }
        ],
        analyze: '',
        correct: '',
        score: '',
        difficult: 0
      }
    },
    questionItemAdd() {
      const items = this.form.items
      let newLastPrefix
      if (items.length > 0) {
        const last = items[items.length - 1]
        newLastPrefix = String.fromCharCode(last.prefix.charCodeAt() + 1)
      } else {
        newLastPrefix = 'A'
      }
      items.push({ id: null, prefix: newLastPrefix, content: '' })
    },
    questionItemRemove(index) {
      this.form.items.splice(index, 1)
    },
    showQuestion() {
      this.questionShow.dialog = true
      this.questionShow.qType = this.form.questionType
      this.questionShow.question = this.form
    },
    editorConfirm() {
      const content = this.richEditor.instance.getContent()
      this.richEditor.object[this.richEditor.parameterName] = content
      this.richEditor.dialogVisible = false
      this.richEditor.instance.setContent('')
    },
    ...mapActions('exam', { initSubject: 'initSubject' }),
    ...mapActions('tagsView', { delCurrentView: 'delCurrentView' })
  }
}
</script>

<style lang="scss" scoped>
.question-item-label {
  margin-top: 10px;
  margin-bottom: 10px !important;
}

.question-item-remove {
  margin-left: 20px;
}

.question-item-content-input {
  margin-left: 8px;
  width: 60%;
}

.question-item-rate {
  line-height: 2.5;
}
</style>
