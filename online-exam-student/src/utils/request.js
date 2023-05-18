import axios from 'axios'
// 使用element-ui Message 做消息提醒
import { Message } from 'element-ui'

const service = axios.create({
    baseURL: 'http://127.0.0.1:8888',
    timeout: 20 * 1000
})

// 请求拦截器
service.interceptors.request.use(config => {
    // 发送请求前处理，数据转化，配置请求头，设置token等
    config.data = JSON.stringify(config.data) // 数据转化
    config.headers = {
        'Content-Type': 'application/json;charset=UTF-8' // 配置请求头
    }
    // config.url = baseURL + config.url
    // 设置token，使用token需要引入cookie方法或者本地localStorage等，推荐js-cookie
    // const token = localStorage.getItem('token') // 获取token
    // if (token) {
    //     config.params = { 'token': token } // 携带在参数中
    //     config.headers.token = token // 携带在请求头中
    // }
    return config
}, error => {
    Promise.reject(error)
})

// 响应拦截器
service.interceptors.response.use(response => {
    return response
}, error => {
    if (error && error.response) {
        switch (error.response.status) {
            case 400:
                error.message = '错误请求'
                break
            case 401:
                error.message = '未授权，请重新登录'
                break
            case 403:
                error.message = '拒绝访问'
                break
            case 404:
                error.message = '请求错误,未找到该资源'
                window.location.href = "/NotFound"
                break
            case 405:
                error.message = '请求方法未允许'
                break
            case 408:
                error.message = '请求超时'
                break
            case 500:
                error.message = '服务器端出错'
                break
            case 501:
                error.message = '网络未实现'
                break
            case 502:
                error.message = '网络错误'
                break
            case 503:
                error.message = '服务不可用'
                break
            case 504:
                error.message = '网络超时'
                break
            case 505:
                error.message = 'http版本不支持该请求'
                break
            default:
                error.message = `连接错误${error.response.status}`
        }
    } else {
        // 超时处理
        if (JSON.stringify(error).includes('timeout')) {
            Message({
                message: '服务器响应超时，请刷新当前页',
                type: 'error'
            })
        }
        error.message = '连接服务器失败'
    }

    Message({
        message: error.message,
        type: 'error'
    })

    return Promise.resolve(error.response)
})

export default service