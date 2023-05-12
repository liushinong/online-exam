/**
 * 比较两个值是否相同
 */
export const equals = (x, y) => {
    var f1 = x instanceof Object
    var f2 = y instanceof Object

    if (JSON.stringify(x) === '{}' || JSON.stringify(y) === '{}') {
        return true
    }

    if (!f1 || !f2) {
        return x === y
    }

    if (Object.keys(x).length !== Object.keys(y).length) {
        return false
    }
    for (var p in x) {
        var a = x[p] instanceof Object
        var b = y[p] instanceof Object
        if (a && b) {
            equals(x[p], y[p])
        } else if (x[p] !== y[p]) {
            return false
        }
    }
    return true
}

/**
* 校验搜索条件是否发生变化
* @param var1 参数1
* @param var2 参数2
* @param excludeKeys 不参与判等的 key，默认值为 pageSize 和 page（分页参数）
*/
export const checkSearch = (var1, var2, excludeKeys = ['pageSize', 'page']) => {
    let arg1 = JSON.parse(JSON.stringify(var1))
    let arg2 = JSON.parse(JSON.stringify(var2))

    excludeKeys.forEach(function (item) {
        delete arg1[item]
        delete arg2[item]
    })

    return equals(arg1, arg2)
}