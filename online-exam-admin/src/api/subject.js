/*
 * @Author: lz 2930507471@qq.com
 * @Date: 2023-05-21 13:57:59
 * @LastEditors: lz 2930507471@qq.com
 * @LastEditTime: 2023-05-25 08:46:11
 * @FilePath: \online-exam-admin\src\api\subject.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/admin/subject/page',
    method: 'post',
    data
  })
}

export function deleteSubject(id) {
  return request({
    url: '/admin/subject/delete',
    method: 'post',
    params: {
      id: id
    }
  })
}

export function addSubject(data) {
  return request({
    url: '/admin/subject/edit',
    method: 'post',
    data
  })
}

export function deleteSubjectUser(data) {
  return request({
    url: '/admin/subject/deleteSubjectUser',
    method: 'post',
    data
  })
}
