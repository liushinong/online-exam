/*
 * @Author: lz 2930507471@qq.com
 * @Date: 2023-05-19 21:14:25
 * @LastEditors: lz 2930507471@qq.com
 * @LastEditTime: 2023-05-23 13:11:46
 * @FilePath: \online-exam-admin\src\api\student.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request'

export function findStudent(data) {
  return request({
    url: '/admin/user/findByPage',
    method: 'post',
    data
  })
}
export function findSubjectBT(data) {
  return request({
    url: '/admin/subject/page',
    method: 'post',
    data
  })
}

export function findByDate(id) {
  return request({
    url: '/admin/user/findByDate',
    method: 'post',
    params: {
      createId: id
    }
  })
}
