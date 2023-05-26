/*
 * @Author: lz 2930507471@qq.com
 * @Date: 2023-05-19 21:14:25
 * @LastEditors: lz 2930507471@qq.com
 * @LastEditTime: 2023-05-25 11:43:59
 * @FilePath: \online-exam-admin\src\api\user.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request'

export function login (data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo (token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout () {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function selectAll (id) {
  return request({
    url: '/admin/user/selectAll',
    method: 'post',
    params: {
      teacherId: id
    }
  })
}

export function register (data) {
  return request({
    url: '/logon',
    method: 'post',
    data
  })
}
