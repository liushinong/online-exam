export function formatSeconds (theTime) {
    let theTime1 = 0
    let theTime2 = 0
    if (theTime > 60) {
        theTime1 = parseInt(theTime / 60)
        theTime = parseInt(theTime % 60)
        if (theTime1 > 60) {
            theTime2 = parseInt(theTime1 / 60)
            theTime1 = parseInt(theTime1 % 60)
        }
    }
    let result = '' + parseInt(theTime) + '秒'
    if (theTime1 > 0) {
        result = '' + parseInt(theTime1) + '分' + result
    }
    if (theTime2 > 0) {
        result = '' + parseInt(theTime2) + '小时' + result
    }
    return result
}

/**
 * 时间格式化
 * @param {} time 
 * @returns 
 */
export function timeFormat (time) {
    return time.split("T")[0] + ' ' + time.split("T")[1].split('.')[0]
}